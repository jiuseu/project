package com.example.project.controller;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResponseDTO;
import com.example.project.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;

@RestController
@Log4j2
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ModelAndView list(PageRequestDTO pageRequestDTO){

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

        ModelAndView mav = new ModelAndView("/board/list.html");
        mav.addObject("responseDTO",responseDTO);
        mav.addObject("pageRequestDTO",pageRequestDTO);

        return mav;
    }

    @GetMapping("/register")
    public ModelAndView registerGet(){
        ModelAndView mav = new ModelAndView("/board/register.html");

        return mav;

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerPost(@Valid BoardDTO boardDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        log.info("board POST register....");

        if(bindingResult.hasErrors()){
            log.info("has error.....");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/board/register"));
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }

        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result",bno);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/board/list"));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/read")
    public ModelAndView read(Long bno,PageRequestDTO pageRequestDTO){

        BoardDTO boardDTO = boardService.read(bno);

        log.info(boardDTO);

        ModelAndView mav = new ModelAndView("/board/read.html");
        mav.addObject("dto",boardDTO);
        mav.addObject("pageRequestDTO",pageRequestDTO);

        return mav;
    }

    @GetMapping("/modify")
    public ModelAndView modifyGet(Long bno,PageRequestDTO pageRequestDTO){

        BoardDTO boardDTO = boardService.read(bno);

        log.info(boardDTO);

        ModelAndView mav = new ModelAndView("/board/modify.html");
        mav.addObject("dto",boardDTO);
        mav.addObject("pageRequestDTO",pageRequestDTO);

        return mav;
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyPost(@Valid BoardDTO boardDTO,
                         PageRequestDTO pageRequestDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        log.info("modify POST....");
        String link = pageRequestDTO.getLink();

        if(bindingResult.hasErrors()){
            log.info("has Errors....");

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/board/modify?bno="+boardDTO.getBno()+link));
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);

        }
        boardService.modify(boardDTO);


        redirectAttributes.addFlashAttribute("result","modifed");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/board/read?bno="+boardDTO.getBno()+"&"+link));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(Long bno,RedirectAttributes redirectAttributes){

        log.info("remove post.. "+bno);

        boardService.remove(bno);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/board/list"));
        redirectAttributes.addFlashAttribute("result","removed");

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
