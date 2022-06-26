package com.zsq.other;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class CommonComponent {



    private String data;

    public void test() {
        System.out.println("CommonComponent test ,data:" + data);

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
