package com.example.project.service;

import com.example.project.domain.Member;
import com.example.project.domain.MemberRole;
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

        log.info("===================== Member Join.... =====================");

        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);

        if(exist){
            log.info("===================== 입력한 아이디가 이미 존재합니다. =====================");
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("====================================");
        log.info(member);
        log.info(member.getRoleSet());
        log.info("====================================");

        memberRepository.save(member);
    }
}
