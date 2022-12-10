package com.cs411project2.instantMessageApp.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CreateGroupRequest {
    String groupName;
    String userName;
}
