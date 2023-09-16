package com.example.project.service;

import com.example.project.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    public void registerTest(){

        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("댓글 테스트333~")
                .replyer("댓글이")
                .bno(100L)
                .build();

        Long bno = replyService.register(replyDTO);
        log.info(bno);
    }
}
