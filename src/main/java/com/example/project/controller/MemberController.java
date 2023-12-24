package com.example.project.controller;

import com.example.project.dto.MemberJoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/login")
    public void loginGet(String logout, String error){
       log.info("login get....");
       log.info("logout:"+ logout);
    }

    @GetMapping("/join")
    public void joinGet(){

        log.info("join Get...");
    }

    @PostMapping("/join")
    public String joinPost(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){

        log.info("join POST");
        log.info(memberJoinDTO);

        return "redirect:/member/login";
    }

}
