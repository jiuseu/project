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
                ,"24번째날 : 83.8~83.3케이지"
                ,"25번째날 : 84.3~83.7케이지"
                ,"26번째날 : 83.2~83.0케이지"
                ,"27번째날 : 83.3~82.8케이지"
                ,"28번째날 : 83.0~82.7케이지"
                ,"29번째날 : 83.0~82.7케이지"
                ,"30번째날 : 82.7케이지"
                ,"31번째날 : 82.6~82.5케이지"
                ,"32번째날 : 82.5~82.3케이지"
                ,"33번째날 : 82.7케이지"
                ,"34번째날 : 82.1~81.9케이지"
                ,"35번째날 : 82.1~81.7케이지"
                ,"36번째날 : 82.2~81.7케이지"
                ,"37번째날 : 82.0~81.8케이지"
                ,"38번째날 : 82.1~81.8케이지"
                ,"39번째날 : 82.0~81.2케이지"
                ,"40번째날 : 81.2~81.0케이지"
                ,"41번째날 : 81.0~80.7케이지"
                ,"42번째날 : 81.0~80.7케이지"
                ,"43번째날 : 80.8~80.6케이지"
                ,"44번째날 : 80.3~80.2케이지"
                ,"45번째날 : 79.8케이지"
                ,"46번째날 : 80.4~80.3케이지"
                ,"47번째날 : 80.3~79.9케이지"
                ,"48번째날 : 79.6~79.4케이지 ModelMapper에서 MapStruct로 교체 생각해보기"
                ,"49번째날 : 79.5~79.3케이지 프로젝트 이름명에 한글 붙이지 말기 오류남"
                ,"50번째날 : 79.5~79.3케이지 MapStruct 매핑할 때 안에 연관관계는 따로 분리하자"
                ,"51번째날 : 79.1~78.8케이지"
                ,"52번째날 : 79.3~79.2케이지"
                ,"53번째날 : 79.0~78.9케이지"
                ,"54번째날 : 79.2~78.9케이지"
                ,"55번째날 : 78.8~78.5케이지"
                ,"56번째날 : 78.2~77.6(80분 걸어 600g 기형적인 소모)케이지"
                ,"57번째날 : 77.8~77.5케이지"
                ,"58번째날 : 78.0~77.8케이지"
                ,"59번째날 : 77.8~77.6케이지"
                ,"60번째날 : 77.8~77.4케이지"
                ,"61번째날 : 77.4~76.9케이지"
                ,"62번째날 : 77.1~76.8케이지"
                ,"63번째날 : 77.4~77.0케이지"
                ,"64번째날 : 77.8~77.2케이지"
                ,"65번째날 : 77.0케이지"
                ,"66번째날 : 76.8~76.7케이지"
                ,"67번째날 : 76.0~75.7케이지 타임리프 th:with 활용할 때 안에 변수쓰고 싶을 때 같은 태그에 쓰기"
                ,"68번째날 : 76.0~75.7케이지"
                ,"69번째날 : 75.4~75.0케이지"
                ,"70번째날 : 75.2~75.0케이지"
                ,"71번째날 : 75.3~75.2케이지"
                ,"72번째날 : 74.7~74.6케이지"
                ,"73번째날 : 74.9~74.8케이지"
                ,"74번째날 : 74.8케이지"};
    }
}
