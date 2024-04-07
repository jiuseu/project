package com.example.project.service;

import com.example.project.domain.Reply;
import com.example.project.dto.PageRequestDTO;
import com.example.project.dto.PageResponseDTO;
import com.example.project.dto.ReplyDTO;
import com.example.project.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO){

        log.info("=================== Reply Service register"+replyDTO.getRno()+"... ===================");
        modelMapper.typeMap(ReplyDTO.class, Reply.class).addMapping(
                src -> src.getBno(), (dest, v) -> dest.getBoard().setBno((Long) v)
        );

        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long bno = replyRepository.save(reply).getBoard().getBno();

        return bno;
    }

    @Override
    public ReplyDTO read(Long rno){

        modelMapper.typeMap(Reply.class, ReplyDTO.class).addMapping(
                src -> src.getBoard().getBno(), (dest, v) -> dest.setBno((Long)v)
        );

        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();

        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);

        return replyDTO;
    }

    @Override
    public void modify(ReplyDTO replyDTO){

        Optional<Reply> result = replyRepository.findById(replyDTO.getRno());
        Reply reply = result.orElseThrow();

        reply.changeText(replyDTO.getReplyText());
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno){
        replyRepository.deleteById(rno);;
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListBoard(Long bno, PageRequestDTO pageRequestDTO){

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0:
                pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                Sort.by("rno").descending());

        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);
        List<ReplyDTO> dtoList = result.getContent().stream()
                .map(reply -> modelMapper.map(reply,ReplyDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
