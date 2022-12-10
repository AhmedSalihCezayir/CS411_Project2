package com.cs411project2.instantMessageApp.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class AddFriendRequest {
    String user1;
    String user2;
}
