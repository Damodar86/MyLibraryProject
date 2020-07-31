/*
package com.libraryManagement.libraryArtifact.libraryService;

import com.libraryManagement.libraryArtifact.libraryEntity.LibraryMemberEntity;
import com.libraryManagement.libraryArtifact.libraryModel.LibraryMemberDetailsModel;
import com.libraryManagement.libraryArtifact.libraryRepository.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    public void addNewMemberDetails_with_existing_id_number() {
        Optional<LibraryMemberEntity> mockEntity = Optional.of(new LibraryMemberEntity());
        when(libraryRepository.findById(anyLong())).thenReturn(mockEntity);

        LibraryMemberDetailsModel libraryMemberDetailsModel = new LibraryMemberDetailsModel();
        libraryMemberDetailsModel.setIdNumber(123654L);
        Long actualIdNumber = libraryService.addNewMemberDetails(libraryMemberDetailsModel);

        assertThat(actualIdNumber).isEqualTo(12L);
    }

    @Test
    public void addNewMemberDetails_with_new_id_number() {
        LibraryMemberDetailsModel libraryMemberDetailsModel = new LibraryMemberDetailsModel();
        libraryMemberDetailsModel.setIdNumber(123654L);
        Long actualIdNumber = libraryService.addNewMemberDetails(libraryMemberDetailsModel);
        assertThat(actualIdNumber).isEqualTo(12L);
    }

    @Test
    public void findByMobileNumber() {
    }
}
*/
