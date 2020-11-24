package com.t9d.tech.org.beans;

import lombok.Data;

@Data
public class Req {
    private int x;
    private int y;
    private int t;

    public static int toInt(String titel) {

        if ("大科学家".equals(titel)) {
            return 1;
        }

        if ("公爵".equals(titel)) {
            return 2;
        }

        if ("大建筑师".equals(titel)) {
            return 3;
        }

        return 0;
    }
}
