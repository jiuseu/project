package com.example.project.repository;

import com.example.project.domain.Board;
import com.example.project.domain.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert(){

        Long bno = 90L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글.....")
                .replyer("replyer1")
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testBoardReplies(){
        Long bno = 90L;
        Pageable pageable = PageRequest.of(0,10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
        result.getContent().forEach(reply -> {
            log.info(reply);
        });
    }

}
