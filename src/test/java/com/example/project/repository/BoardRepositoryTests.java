package com.example.project.repository;

import com.example.project.domain.Board;
import com.example.project.domain.BoardImage;
import com.example.project.dto.BoardListAllDTO;
import com.example.project.dto.BoardListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertAll() {

        for (int i = 1; i <= 100; i++) {
            Board board = Board.builder()
                    .title("Title.." + i)
                    .content("Content.." + i)
                    .user("User.." + i)
                    .build();

            for (int j = 0; j < 3; j++) {

                if (i % 5 == 0) {
                    continue;
                }
                board.addImage(UUID.randomUUID().toString(), i + "file" + j + ".jpg");
            }
            boardRepository.save(board);
        }//end for
    }

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
        log.info(result.hasPrevious() + " " + result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }

    @Test
    public void searchTest() {

        String[] types = {"t", "c", "w"};
        String keyword = "100";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> list = boardRepository.searchAll(types, keyword, pageable);

        list.getContent().forEach(i -> {
            log.info(i);
        });
    }

    @Test
    public void testInsertWithImages() {

        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .user("Tester")
                .build();

        for (int i = 0; i < 3; i++) {
            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }
        boardRepository.save(board);
    }

    @Test
    public void testReadWithImages() {

        Optional<Board> result = boardRepository.findByIdWithImages(1L);
        Board board = result.orElseThrow();

        log.info(board);
        log.info("------------------------------");
        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }
    }

    @Transactional
    @Commit
    @Test
    public void testModifyImages() {

        Optional<Board> result = boardRepository.findByIdWithImages(1L);
        Board board = result.orElseThrow();

        //기존 첨부파일들은 삭제
        board.clearImages();

        for (int i = 0; i < 2; i++) {
            board.addImage(UUID.randomUUID().toString(), "updatefile" + i + ".jpg");
        }
        boardRepository.save(board);
    }

    @Test
    @Transactional
    @Commit
    public void testRemoveAll() {

        Long bno = 2L;
        replyRepository.deleteByBoard_Bno(bno);
    }

    @Transactional
    @Test
    public void testSearchImageReplyCount() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        //boardRepository.searchWithAll(null, null, pageable);

        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);
        log.info("-------------------------------------");
        log.info(result.getTotalElements());

        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
    }
}