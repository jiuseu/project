package com.example.project.repository;

import com.example.project.domain.Board;
import com.example.project.dto.BoardListReplyCountDTO;
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
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testSearchReplyCount() {

        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        //total pages
        log.info(result.getTotalPages());
        //page size
        log.info(result.getSize());
        //pageNumber
        log.info(result.getNumber());
        //prev next
        log.info(result.hasPrevious()+" "+result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void searchTest() {

        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> list = boardRepository.searchAll(types, keyword, pageable);

        list.getContent().forEach(i -> {
            log.info(i);
        });
    }
}
