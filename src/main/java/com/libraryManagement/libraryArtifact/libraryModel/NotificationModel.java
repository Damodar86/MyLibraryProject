package com.libraryManagement.libraryArtifact.libraryModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationModel {
    private LocalDate bookReturnDate;
    private LocalDate renewalDate;
   @JsonFormat(pattern="yyyy-MM-dd")
   //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   @JsonSerialize(using = LocalDateSerializer.class)
   @JsonDeserialize(using= LocalDateDeserializer.class)
    private LocalDate borrowedDate;

}
