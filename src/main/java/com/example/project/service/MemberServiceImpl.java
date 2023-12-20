package com.example.project.service;

import com.example.project.dto.MemberJoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{

    @Override
    public void join(MemberJoinDTO memberJoinDTO)throws MidExistException{

    }
}
