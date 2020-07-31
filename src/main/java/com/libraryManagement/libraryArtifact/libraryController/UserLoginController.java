package com.libraryManagement.libraryArtifact.libraryController;

import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.*;
import com.libraryManagement.libraryArtifact.libraryService.LibraryService;
import com.libraryManagement.libraryArtifact.libraryService.LoginService;
import com.libraryManagement.libraryArtifact.libraryService.UserCancellationService;
import com.libraryManagement.libraryArtifact.libraryService.UserDeletionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserLoginController {
    private LoginService loginService;
    private LibraryService libraryService;
    private UserDeletionService userDeletionService;
private UserCancellationService userCancellationService;
    public UserLoginController(LoginService loginService, LibraryService libraryService, UserDeletionService userDeletionService, UserCancellationService userCancellationService) {
        this.loginService = loginService;
        this.libraryService = libraryService;
        this.userDeletionService = userDeletionService;
        this.userCancellationService = userCancellationService;
    }

    @GetMapping(value = "/login/{mobileNumber}")
    public List<Person> login(@PathVariable(name = "mobileNumber") String mobileNumber) throws LibraryException {
        return loginService.userLogin(mobileNumber);
    }

    @PostMapping(value = "/login")
    public Account login(@RequestBody UserLoginModel userLoginModel) throws LibraryException {
        return loginService.login(userLoginModel);
    }

    @PostMapping(value = "/userLogin")
    public Long addNewMemberDetails(@RequestBody LibraryMemberDetailsModel libraryMemberDetailsModel) {
        return libraryService.addNewMemberDetails(libraryMemberDetailsModel);
    }

    @GetMapping(value = "/userLogin/{mobileNumber}")
    public List<LibraryMemberDetailsModel> getUserDetailsByMobileNumber(@PathVariable(name = "mobileNumber") String mobileNumber) {
        return libraryService.findByMobileNumber(mobileNumber);
    }
    @PostMapping(value = "/userDeletion_Id")
    public UserDeletionModel userDeletionByUserId(@RequestBody UserDeletionModel userDeletionModel) throws LibraryException {
        return userDeletionService.userDeletionByIdMethod(userDeletionModel);
    }
    @PostMapping(value = "/userCancellation")
    public UserCancellationModel userCancellation(@RequestBody UserCancellationModel userCancellationModel) throws LibraryException {
        return userCancellationService.userCancellationMethod(userCancellationModel);
    }

}
