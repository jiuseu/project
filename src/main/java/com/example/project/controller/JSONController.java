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
        return new String[]{"첫째날 : 87.2케이지","둘째날 : 87.6케이지","셋째날 : 87.4케이지"
                ,"넷째날 : 87.2~86.9케이지","다섯째날 : 87.0~86.6케이지"
                ,"여섯째날 : 87.5(운동전)~86.8(운동후)케이지"
                ,"7번째날 : 86.9~86.8케이지"
                ,"8번째날 : 86.3~85.8케이지"
                ,"9번째날 : 86.0~85.7케이지"
                ,"10번째날 : 85.8케이지"
                ,"11번째날 : 85.8~85.6케이지"
                ,"12번째날 : 85.5케이지"
                ,"13번째날 : 85.4케이지"
                ,"14번째날 : 85.1케이지"
                ,"15번째날 : 85.0케이지"
                ,"16번째날 : 85.2케이지"
                ,"17번째날 : 85.2~84.3케이지"
                ,"18번째날 : 84.8~84.6케이지"
                ,"19번째날 : 84.3~84.0케이지"
                ,"20번째날 : 84.4~84.0케이지"
                ,"21번째날 : 84.2~83.9케이지"
                ,"22번째날 : 84.1~83.7케이지"
                ,"23번째날 : 83.5케이지"
                ,"24번째날 : 83.8~83.3케이지"};
    }
}
