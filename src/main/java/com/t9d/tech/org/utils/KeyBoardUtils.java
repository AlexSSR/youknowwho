package com.t9d.tech.org.utils;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyBoardUtils {


    public static List<Integer> getKey(int x){

        ArrayList<Integer> list = new ArrayList<>();

        int hundrand = (x/100)%10;
        int ten = (x/10)%10;
        int one = x%10;

        int ele1 = whichKey2Use(hundrand);
        int ele2 = whichKey2Use(ten);
        int ele3 = whichKey2Use(one);

        list.add(ele1);
        list.add(ele2);
        list.add(ele3);
        return list;
    }

    private static int whichKey2Use(int hundrand) {

        int res = 0;
        switch (hundrand){
            case 0:
                res = KeyEvent.VK_0;
                break;
            case 1:
                res = KeyEvent.VK_1;
                break;
            case 2:
                res = KeyEvent.VK_2;
                break;
            case 3:
                res = KeyEvent.VK_3;
                break;
            case 4:
                res = KeyEvent.VK_4;
                break;
            case 5:
                res = KeyEvent.VK_5;
                break;
            case 6:
                res = KeyEvent.VK_6;
                break;
            case 7:
                res = KeyEvent.VK_7;
                break;
            case 8:
                res = KeyEvent.VK_8;
                break;
            case 9:
                res = KeyEvent.VK_9;
                break;

        }
        return res;
    }
}
