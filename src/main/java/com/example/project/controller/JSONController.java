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
                ,"74번째날 : 74.8케이지"
                ,"75번째날 : 75.4~75.2케이지"
                ,"76번째날 : 75.1~75.0케이지"
                ,"77번째날 : 75.5~74.9케이지"
                ,"78번째날 : 75.7~75.1케이지"
                ,"79번째날 : 75.0~74.9케이지"
                ,"80번째날 : 74.8~74.4케이지"
                ,"81번째날 : 74.5~74.0케이지"
                ,"82번째날 : 73.7~73.3케이지"
                ,"83번째날 : 73.9~73.6케이지"
                ,"84번째날 : 74.2케이지"
                ,"85번째날 : 74.0~73.7케이지"
                ,"86번째날 : 74.1~73.9케이지"
                ,"87번째날 : 73.7~73.6케이지"
                ,"88번째날 : 73.9~73.8케이지"
                ,"89번째날 : 73.6~73.5케이지"
                ,"90번째날 : 73.0~72.9케이지"
                ,"91번째날 : 73.5~73.1케이지"
                ,"92번째날 : 72.9케이지"
                ,"93번째날 : 73.1~72.9케이지"
                ,"94번째날 : 73.0~72.5케이지"
                ,"95번째날 : 72.9~72.8케이지"
                ,"96번째날 : 72.6케이지"
                ,"97번째날 : 72.7~72.5케이지"
                ,"98번째날 : 72.7~72.4케이지"
                ,"99번째날 : 72.7~72.5케이지"
                ,"100번째날 : 72.5~72.4케이지"
                ,"101번째날 : 72.3~72.2케이지"
                ,"102번째날 : 71.5~71.4케이지"
                ,"103번째날 : 71.9~71.5케이지"
                ,"104번째날 : 71.8~71.5케이지"
                ,"105번째날 : 72.2~72.0케이지"
                ,"106번째날 : 71.8~71.7케이지"
                ,"107번째날 : 72.1~71.9케이지"
                ,"108번째날 : 72.0~71.9케이지"
                ,"109번째날 : 72.6~72.4케이지"
                ,"110번째날 : 72.5~72.0케이지"
                ,"111번째날 : 71.9케이지"
                ,"112번째날 : 71.6~71.4케이지"
                ,"113번째날 : 71.7~71.3케이지"
                ,"114번째날 : 71.6~71.2케이지"
                ,"115번째날 : 71.0~70.8케이지"
                ,"116번째날 : 70.9~70.8케이지"
                ,"117번째날 : 70.3~70.2케이지"
                ,"118번째날 : 70.7~70.6케이지"
                ,"119번째날 : 70.8~70.7케이지"
                ,"120번째날 : 70.7~70.4케이지"
                ,"121번째날 : 70.7케이지"
                ,"122번째날 : 70.6~70.3케이지"
                ,"123번째날 : 70.6~70.3케이지"
                ,"124번째날 : 70.6~70.2케이지"
                ,"125번째날 : 70.1 ~ 70.0케이지"
                ,"126번째날 : 69.6 ~ 69.8케이지"
                ,"127번째날 : 70.0 ~ 69.9케이지"
                ,"128번째날 : 70.1케이지"
                ,"129번째날 : 69.8케이지"
                ,"130번째날 : 70.4~70,3케이지"
                ,"131번째날 : 69.9케이지"
                ,"132번째날 : 69.9~69.7케이지"
                ,"133번째날 : 69.8~69.7케이지"
                ,"134번째날 : 69.5~69.4케이지"
                ,"135번째날 : 69.2케이지"
                ,"136번째날 : 69.1~69.0케이지"
                ,"137번째날 : 69.2~68.9케이지"
                ,"138번째날 : 69.0케이지"
                ,"139번째날 : 69.4케이지"
                ,"140번째날 : 69.8~69.3?케이지"
                ,"141번째날 : 68.9케이지"
                ,"142번째날 : 68.9~68.5케이지"
                ,"143번째날 : 68.8~68.5케이지"
                ,"144번째날 : 68.9~68.7케이지"
                ,"145번째날 : 68.3~68.6?케이지"
                ,"146번째날 : 69.1케이지"
                ,"147번째날 : 68.8~68.7케이지"
                ,"148번째날 : 68.7~68.2케이지"
                ,"149번째날 : 68.6~68.5케이지"
                ,"150번째날 : 68.6~68.3케이지"
                ,"151번째날 : 68.1~68.3?케이지"
                ,"152번째날 : 67.9~67.6케이지"
                ,"153번째날 : 67.6케이지"
                ,"154번째날 : 68.1~67.6케이지"
                ,"155번째날 : 67.9~67.6케이지"
                ,"156번째날 : 68.2~68.0케이지"
                ,"157번째날 : 68.3~68.2케이지"
                ,"158번째날 : 67.7~67.5케이지"
                ,"159번째날 : 68.1~67.7케이지"
                ,"160번째날 : 67.4~67.5?케이지"
                ,"161번째날 : 67.4케이지"
                ,"162번째날 : 68.4~68.1케이지"
                ,"163번째날 : 67.9~67.5케이지"
                ,"164번째날 : 67.8~67.5케이지"
                ,"165번째날 : 67.1~66.9케이지"
                ,"166번째날 : 67.2~67.1케이지"
                ,"167번째날 : 67.2~67.1케이지"
                ,"168번째날 : 66.8~66.4케이지"
                ,"169번째날 : 66.6~66.5케이지"
                ,"170번째날 : 66.9~66.4케이지"
                ,"171번째날 : 66.3~66.2케이지"
                ,"172번째날 : 66.3~66.2케이지"
                ,"173번째날 : 66.0~65.7케이지"
                ,"174번째날 : 66.1~65.6케이지"
                ,"175번째날 : 66.2~65.9케이지"
                ,"176번째날 : 66.1~66.2케이지"
                ,"177번째날 : 66.1케이지"
                ,"178번째날 : 65.5~65.3케이지"
                ,"179번째날 : 65.7~65.3케이지"
                ,"180번째날 : 65.3케이지"
                ,"181번째날 : 66.3~65.8케이지"
                ,"182번째날 : 66.1~65.6케이지"
                ,"183번째날 : 65.8~65.6케이지"
                ,"184번째날 : 65.4~65.8케이지"
                ,"185번째날 : 65.8~65.3케이지"
                ,"186번째날 : 65.5케이지"
                ,"187번째날 : 65.4~65.1케이지"
                ,"188번째날 : 65.6~65.5케이지"
                ,"189번째날 : 66.0케이지"
                ,"190번째날 : 65.8~65.3케이지"
                ,"191번째날 : 65.3케이지"
                ,"192번째날 : 64.4~64.3케이지"};
    }
}
