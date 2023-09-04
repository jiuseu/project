package com.example.project.service;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void RegisterTest(){

        IntStream.rangeClosed(1,100).forEach(i ->{
            BoardDTO boardDTO = BoardDTO.builder()
                    .title("Title.."+i)
                    .content("Content.."+i)
                    .writer("Person"+i)
                    .build();

            boardService.register(boardDTO);
            log.info(boardDTO);
        });

    }

    @Test
    public void ReadTest(){
        Long bno = 100L;
        BoardDTO boardDTO = boardService.read(bno);
        log.info(boardDTO);
    }

    @Test
    public void ModifyTest(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(100L)
                .title("Update Title 100")
                .content("Update Content 100")
                .build();
        boardService.modify(boardDTO);
        log.info(boardDTO);
    }

    @Test
    public void RemoveTest(){
        Long bno = 1L;
        boardService.remove(bno);
    }

    @Test
    public void ListTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        log.info(responseDTO);
    }
}
