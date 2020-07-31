package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import com.libraryManagement.libraryArtifact.bookEnumPacakage.LibraryErrorMessages;
import com.libraryManagement.libraryArtifact.libraryEntity.AddressEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.LibraryAccountEntity;
import com.libraryManagement.libraryArtifact.libraryEntity.PersonEntity;
import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.Account;
import com.libraryManagement.libraryArtifact.libraryModel.Address;
import com.libraryManagement.libraryArtifact.libraryModel.NewAccountResponse;
import com.libraryManagement.libraryArtifact.libraryModel.Person;
import com.libraryManagement.libraryArtifact.libraryRepository.AccountRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.AddressRepository;
import com.libraryManagement.libraryArtifact.libraryRepository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {
    private AddressRepository addressRepository;
    private PersonRepository personRepository;
    private AccountRepository accountRepository;

    public AccountService(AddressRepository addressRepository, PersonRepository personRepository, AccountRepository accountRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public NewAccountResponse addUser(Person person, Address address, Account account) throws LibraryException {

        Optional<PersonEntity> personEntity1 = personRepository.findByEmail(person.getEmail());

        if (personEntity1.isPresent()) {
            log.info("email already existed");
            LibraryException libraryException = new LibraryException(LibraryErrorMessages.USER_ALREADY_EXITS);

            throw libraryException;

        }

        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(person.getName());
        personEntity.setPhoneNumber(person.getPhone());
        personEntity.setEmail(person.getEmail());

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreetAddress(address.getStreetAddress());
        addressEntity.setCity(address.getCity());
        addressEntity.setZipCode(address.getZipCode());
        addressEntity.setState(address.getState());
        addressEntity.setCountry(address.getCountry());


        LibraryAccountEntity libraryAccountEntity = new LibraryAccountEntity();
        libraryAccountEntity.setPersonEmail(person.getEmail());
        libraryAccountEntity.setPassword(account.getPassword());
        libraryAccountEntity.setAccountType(account.getAccountType());
        libraryAccountEntity.setPhoneNumber(person.getPhone());
        libraryAccountEntity.setAccountStatus(AccountStatus.Active.name());


        AddressEntity savedAddressEntity = addressRepository.save(addressEntity);

        personEntity.setAddressId(savedAddressEntity.getAddressId());
        personRepository.save(personEntity);

        LibraryAccountEntity savedLibraryAccountEntity = accountRepository.save(libraryAccountEntity);

        Long newAccountId = savedLibraryAccountEntity.getAccountId();
        NewAccountResponse newAccountResponse = new NewAccountResponse();
        newAccountResponse.setAccountId(newAccountId);
        log.info("successfully created new account with account Id" + newAccountId);

        return newAccountResponse;


    }
}
