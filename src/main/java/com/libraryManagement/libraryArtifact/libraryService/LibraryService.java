package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.libraryEntity.LibraryMemberEntity;
import com.libraryManagement.libraryArtifact.libraryModel.LibraryMemberDetailsModel;
import com.libraryManagement.libraryArtifact.libraryRepository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Long addNewMemberDetails(LibraryMemberDetailsModel libraryMemberDetailsModel) {
        Optional<LibraryMemberEntity> memberEntity = libraryRepository.findById(libraryMemberDetailsModel.getIdNumber());
        if (memberEntity.isPresent()) {
            return 12L;
        } else {
            LibraryMemberEntity libraryMemberEntity = new LibraryMemberEntity();
            libraryMemberEntity.setMemberName(libraryMemberDetailsModel.getMemberName());
            libraryMemberEntity.setAge(libraryMemberDetailsModel.getAge());
            libraryMemberEntity.setFatherName(libraryMemberDetailsModel.getFather());
            libraryMemberEntity.setMotherName(libraryMemberDetailsModel.getMother());
            libraryMemberEntity.setAddress(libraryMemberDetailsModel.getAddress());
            libraryMemberEntity.setCourse(libraryMemberDetailsModel.getCourse());
            libraryMemberEntity.setRollNo(libraryMemberDetailsModel.getRollNo());
            libraryMemberEntity.setMobile(libraryMemberDetailsModel.getMobileNumber());
            libraryMemberEntity.setIdNumber(libraryMemberDetailsModel.getIdNumber());
            return libraryRepository.save(libraryMemberEntity).getIdNumber();
        }
    }

    public List<LibraryMemberDetailsModel> findByMobileNumber(String mobileNumber) {
        List<LibraryMemberEntity> libraryMemberEntityList = libraryRepository.findByMobile(mobileNumber);

        List<LibraryMemberDetailsModel> libraryMemberDetailsModels = new ArrayList<>();

        libraryMemberEntityList.forEach(libraryMemberEntity -> {
            LibraryMemberDetailsModel libraryMemberDetailsModel = new LibraryMemberDetailsModel();
            libraryMemberDetailsModel.setAddress(libraryMemberEntity.getAddress());
            libraryMemberDetailsModels.add(libraryMemberDetailsModel);
        });

        return libraryMemberDetailsModels;

    }
}
