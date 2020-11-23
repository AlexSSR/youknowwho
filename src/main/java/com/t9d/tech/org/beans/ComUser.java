package com.t9d.tech.org.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComUser {

    private String name;
    private String passWord;
    private int proority;
    private int queueSize;

    public ComUser(String name, String passWord, int proority, int queueSize) {
        this.name = name;
        this.passWord = passWord;
        this.proority = proority;
        this.queueSize = queueSize;
    }



    public ComUser() {

    }
}
