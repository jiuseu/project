package com.example.project.repository.search;

import com.example.project.domain.Board;
import com.example.project.repository.BoardRepository;
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
public class SearchTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testSearchAll(){

        String[] types = {"t","c","w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);

        log.info(result.getTotalElements());
        log.info(result.getTotalPages());
        log.info(result.getSize());
        log.info(result.getNumber());
        log.info(result.hasPrevious() +": "+ result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }
}
