package com.example.project.controller;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResponseDTO;
import com.example.project.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public void registerGet(){
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO, BindingResult bindingResult
    , RedirectAttributes redirectAttributes){

        log.info("board POST register....");

        if(bindingResult.hasErrors()){
            log.info("has error.....");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result",bno);

        return "redirect:/board/list";
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
    public ModelAndView modifyPost(@Valid BoardDTO boardDTO,
                         PageRequestDTO pageRequestDTO,
                         BindingResult bindingResult){

        log.info("modify POST....");
        ModelAndView mav;

        if(bindingResult.hasErrors()){
            log.info("has Errors....");

            String link = pageRequestDTO.getLink();
            mav = new ModelAndView("/board/modify.html");
            mav.addObject("errors",bindingResult.getAllErrors());
            mav.addObject("bno",boardDTO.getBno());

            return mav;
        }
        boardService.modify(boardDTO);

        mav = new ModelAndView("/board/read.html");
        mav.addObject("result","modified");
        mav.addObject("dto",boardDTO);
        log.info(mav);
        return mav;
    }

    @PostMapping("/remove")
    public String remove(Long bno,RedirectAttributes redirectAttributes){

        log.info("remove post.. "+bno);
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result","removed");

        return "redirect:/board/list";
    }

}
