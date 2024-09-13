package com.example.project.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class JSONController {

    @GetMapping("/helloArr")
    public String[] helloArr(){

        log.info("Hello Arr..........................");
        return new String[]{"첫째날 : 87.2케이지","둘째날 : 87.6케이지","CCC","DDD"};
    }
}
