package com.example.spring.security.springsecurity.exception_handling;

import lombok.Getter;

@Getter
public class UserIncorrectData {
    private String info;

    public UserIncorrectData() {
    }

    public void setInfo(String info) {
        this.info = info;
    }
}