package com.example.project.service;

import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResponseDTO;
import com.example.project.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListBoard(Long bno, PageRequestDTO pageRequestDTO);
}
