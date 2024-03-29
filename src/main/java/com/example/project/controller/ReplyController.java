package com.example.project.controller;

import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResponseDTO;
import com.example.project.dto.ReplyDTO;
import com.example.project.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "Replies POST",description = "POST 방식으로 댓글 등록")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                                     BindingResult bindingResult)throws BindException{

        log.info(replyDTO);
        log.info("reply register....");

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long rno = replyService.register(replyDTO);
        resultMap.put("rno", rno);

        return resultMap;
    }

    @Operation(summary = "Replies Of Board",description = "GET 방식으로 특정 게시물의 댓글 목록")
    @GetMapping(value = "/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno,
                                             PageRequestDTO pageRequestDTO){

        log.info("reply list get....");
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListBoard(bno,pageRequestDTO);

        return responseDTO;
    }

    @Operation(summary = "Read Reply", description = "GET 방식으로 특정 댓글 조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno){

        log.info("reply rno get....");
        ReplyDTO replyDTO = replyService.read(rno);

        return replyDTO;
    }

    @Operation(summary =  "DELETE 방식으로 특정 댓글 삭제")
    @DeleteMapping("/{rno}")
    public Map<String,Long> remove( @PathVariable("rno") Long rno ){

        log.info("reply delete....");
        replyService.remove(rno);

        Map<String, Long> resultMap = new HashMap<>();

        resultMap.put("rno", rno);

        return resultMap;
    }

    @Operation(summary = "PUT 방식으로 특정 댓글 수정")
    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> modify(@PathVariable("rno") Long rno, @RequestBody ReplyDTO replyDTO){

        log.info("reply modify....");
        replyDTO.setRno(rno);
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("rno" , rno);

        return resultMap;
    }
}
