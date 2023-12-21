package com.example.project.service;

import com.example.project.dto.MemberJoinDTO;
import com.example.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void join(MemberJoinDTO memberJoinDTO)throws MidExistException{

        log.info("Member Join...");

    }
}
