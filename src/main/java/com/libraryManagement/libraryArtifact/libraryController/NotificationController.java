package com.libraryManagement.libraryArtifact.libraryController;

import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationModel;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationReturnModel;
//import com.libraryManagement.libraryArtifact.libraryService.NotificationService;
import com.libraryManagement.libraryArtifact.libraryService.NotificationService2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    private NotificationService2 notificationService2;

    public NotificationController(NotificationService2 notificationService2) {
        this.notificationService2 = notificationService2;
    }

    @PostMapping(value = "/notification")
    public List<NotificationReturnModel> notificationController() throws LibraryException {
        return notificationService2.notificationMethod();
    }
}
