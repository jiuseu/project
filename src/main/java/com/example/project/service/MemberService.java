package com.example.project.service;

import com.example.project.dto.MemberJoinDTO;

public interface MemberService {

    static class MidExistException extends Exception{
    }

    void join(MemberJoinDTO memberJoinDTO)throws MidExistException;
}
