package com.example.project.service;

import com.example.project.domain.Board;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.BoardListReplyCountDTO;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResponseDTO;
import org.springframework.data.domain.PageRequest;

public interface BoardService {

    Long register(BoardDTO boardDTO);
    BoardDTO read(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
