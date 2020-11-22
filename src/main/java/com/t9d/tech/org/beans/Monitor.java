package com.t9d.tech.org.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "monitor")
@Getter
@Setter
public class Monitor {

    private Point leftUpPoint;
    private Point leftDownPoint;
    private Point rightUpPoint;
    private Point rightDownPoint;
    private Point mapButton;
    private Point searchButton;
    private Point xInput;
    private Point yInput;
    private Point searchConformButton;

    public int getWidth(){
        return rightUpPoint.getX() -leftUpPoint.getX()  ;
    }

    public int getHight(){
        return leftDownPoint.getY() -leftUpPoint.getY() ;
    }

    public double getHD(){
        return (leftDownPoint.getY() -leftUpPoint.getY())/8;
    }

    public double getWD(){
        return (rightUpPoint.getX() -leftUpPoint.getX())/12;
    }
}