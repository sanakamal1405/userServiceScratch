package com.microservice.userServiceScratch.dtos;

import lombok.*;

@Getter
@Setter
@Builder
public class NotificationDTO {

    private String to;
    private String from;
    private String body;
    private String subject;
}
