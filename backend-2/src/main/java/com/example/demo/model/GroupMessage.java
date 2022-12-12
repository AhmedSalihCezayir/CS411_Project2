package com.example.demo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class GroupMessage {
    private String senderName;
    private String groupName;
    private String message;
    private String date;
    private Status status;
}

