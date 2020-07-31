package com.libraryManagement.libraryArtifact.libraryController;

import com.libraryManagement.libraryArtifact.libraryException.LibraryException;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationModel;
import com.libraryManagement.libraryArtifact.libraryModel.NotificationReturnModel;
import com.libraryManagement.libraryArtifact.libraryService.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/notification")
    public NotificationReturnModel notificationController(@RequestBody NotificationModel notificationModel) throws LibraryException {
        return notificationService.notificationMethod(notificationModel);
    }
}
