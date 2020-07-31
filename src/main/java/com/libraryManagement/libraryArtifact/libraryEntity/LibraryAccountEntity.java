package com.libraryManagement.libraryArtifact.libraryEntity;

import com.libraryManagement.libraryArtifact.bookEnumPacakage.AccountStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "LIB_ACCOUNT")
public class LibraryAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_SEQ")
    @SequenceGenerator(sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1, name = "ACCOUNT_ID_SEQ")
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_STATUS")
    private String accountStatus;

    @Column(name = "PERSON_EMAIL")
    private String personEmail;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "PHONENUMBER")
    private String phoneNumber;


   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_EMAIL", referencedColumnName = "EMAIL")
    private PersonEntity personEntity;*/
}
