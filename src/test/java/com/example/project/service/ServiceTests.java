package com.example.project.service;

import com.example.project.dto.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
                    .user("Person"+i)
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

    @Test
    public void testRegisterWithImages(){

        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("File....Sample Title..")
                .content("Sample Content...")
                .user("user00")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID()+"_aaa.jpg",
                        UUID.randomUUID()+"_bbb.jpg",
                        UUID.randomUUID()+"_bbb.jpg"
                ));
        Long bno = boardService.register(boardDTO);
        log.info("bno: "+ bno);
    }

    @Test
    public void testReadAll(){
        Long bno = 101L;
        BoardDTO boardDTO = boardService.read(bno);
        log.info(boardDTO);

        for(String fileName : boardDTO.getFileNames()){
            log.info(fileName);
        }
    }

    @Test
    public void testModify2(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Updated..101")
                .content("Updated Content 101...")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(UUID.randomUUID()+"_zzz.jpg"));
        boardService.modify(boardDTO);
    }

    @Test
    public void testListWithAll(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);
        List<BoardListAllDTO> dtoList = responseDTO.getDtoList();

        dtoList.forEach(boardListAllDTO -> {
            log.info(boardListAllDTO.getBno()+":"+boardListAllDTO.getTitle());

            if(boardListAllDTO.getBoardImages() != null){
                for(BoardImageDTO boardImage : boardListAllDTO.getBoardImages()){
                    log.info(boardImage);
                }
            }
            log.info("---------------------------------------------");
        });
    }
}
