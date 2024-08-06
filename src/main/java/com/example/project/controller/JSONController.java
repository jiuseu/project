package com.example.project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class JSONController {

    @GetMapping("/helloArr")
    public String[] helloArr(){

        log.info("Hello Arr..............................");
        return new String[]{"AAA","BBB","CCC","이지베이션 BS미니2","삼천리 팬텀 s1,s2","삼천리 어라운드"};
    }
}
