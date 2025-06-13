package com.silvertown.resident.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    int id;
    UserName name;
    Birth birth;

    public User(UserName name, Birth birth) {
        this.name = name;
        this.birth = birth;
    }
}
