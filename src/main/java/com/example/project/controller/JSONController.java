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
        return new String[]{"AAA","BBB","CCC","이지베이션 BS미니2","모토벨로 tx7","AU테크 스카닉 AMI"};
    }
}
