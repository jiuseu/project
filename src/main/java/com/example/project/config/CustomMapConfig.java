package com.example.project.config;

import com.example.project.domain.Board;
import com.example.project.domain.Reply;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.ReplyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "string", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomMapConfig {

    @Mapping(target = "imageSet", ignore = true)
    Board toBoardEntity(BoardDTO boardDTO);

    @Mapping(target = "fileNames", expression = "java(new BoardDTO().getMapperFileName(board.getImageSet()))")
    BoardDTO toBoardDTO(Board board);

    @Mapping(target = "board.bno", source = "bno")
    Reply toReplyEntity(ReplyDTO replyDTO);

    @Mapping(target="bno", source = "board.bno")
    ReplyDTO toReplyDTO(Reply reply);
}
