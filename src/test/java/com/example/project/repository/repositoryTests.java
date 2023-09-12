package com.example.project.repository;

import com.example.project.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class repositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void InsertTest(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder()
                    .title("title.."+i)
                    .content("content.."+i)
                    .user("user"+(i%10))
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO: "+result.getBno());
        });
    }

    @Test
    public void SelectTest(){
        long num = 10L;

        Optional<Board> result = boardRepository.findById(num);
        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void UpdateTest(){
        long num = 100L;

        Optional<Board> result = boardRepository.findById(num);
        Board board = result.orElseThrow();
        board.change("update..title 100", "update..content 100");

        boardRepository.save(board);
        log.info(board);
    }

    @Test
    public void DeleteTest(){
        long num = 1L;
        boardRepository.deleteById(num);
    }

    @Test
    public void PagingTest(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        log.info("Total count: "+result.getTotalElements());
        log.info("Total pages: "+result.getTotalPages());
        log.info("Page number: "+result.getNumber());
        log.info("Page size: "+result.getSize());

        List<Board> list = result.getContent();
        list.forEach(board -> log.info(board));
    }
}
