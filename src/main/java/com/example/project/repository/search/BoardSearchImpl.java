package com.example.project.repository.search;

import com.example.project.domain.Board;
import com.example.project.domain.QBoard;
import com.example.project.domain.QReply;
import com.example.project.dto.BoardImageDTO;
import com.example.project.dto.BoardListAllDTO;
import com.example.project.dto.BoardListReplyCountDTO;
import com.example.project.repository.Querydsl5RepositorySupport;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Log4j2
public class BoardSearchImpl extends Querydsl5RepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }
    private QBoard board = QBoard.board;
    private QReply reply = QReply.reply;

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable){

        log.info("=================== Board SearchAll... ===================");

        return applyPagination(pageable, query -> query.selectFrom(board)
                        .where(TitleSearch(types,keyword),
                                ContentSearch(types,keyword),
                                UserSearch(types,keyword),
                                board.bno.gt(0L))
        );
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,String keyword,Pageable pageable){


        return applyPagination(pageable, query -> query.select(Projections.bean(
                                BoardListReplyCountDTO.class,
                                board.bno,
                                board.title,
                                board.user,
                                board.regDate,
                                reply.count().as("replyCount")
                        )).from(board).leftJoin(reply).on(reply.board.eq(board))
                        .groupBy(board)
                        .where(TitleSearch(types,keyword),
                                ContentSearch(types,keyword),
                                UserSearch(types,keyword),
                                board.bno.gt(0L))
        );
    }

    @Override
    public Page<BoardListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable){

        JPQLQuery<Board> result = JPQLPagination(pageable, query -> query.select(Projections.bean(
                        BoardListReplyCountDTO.class,
                        board.bno,
                        board.title,
                        board.user,
                        board.regDate,
                        reply.count().as("replyCount")
                )).from(board).leftJoin(reply).on(reply.board.eq(board))
                .groupBy(board)
                .where(TitleSearch(types,keyword),
                        ContentSearch(types,keyword),
                        UserSearch(types,keyword),
                        board.bno.gt(0L))
        );

        JPQLQuery<Tuple> tuple = result.select(board, reply.countDistinct());
        List<Tuple> tupleList = tuple.fetch();

       List<BoardListAllDTO> dtoList = tupleList.stream().map(tuple1 ->{

           Board board1 = (Board)tuple1.get(board);
           long replyCount = tuple1.get(1,Long.class);

           BoardListAllDTO dto = BoardListAllDTO.builder()
                   .bno(board1.getBno())
                   .title(board1.getTitle())
                   .user(board1.getUser())
                   .regDate(board1.getRegDate())
                   .replyCount(replyCount)
                   .build();

           //BoardImage를 BoardImageDTO 처리할 부분
           List<BoardImageDTO> imageDTOS = board1.getImageSet().stream().sorted()
                   .map(boardImage -> BoardImageDTO.builder()
                           .uuid(boardImage.getUuid())
                           .fileName(boardImage.getFileName())
                           .ord(boardImage.getOrd())
                           .build()).collect(Collectors.toList());
           dto.setBoardImages(imageDTOS); // 처리된 BoardImageDTO들을 추가
           return dto;
       }).collect(Collectors.toList());

       long totalCount = result.fetchCount();
       return new PageImpl<>(dtoList, pageable, totalCount);
    }

    private BooleanExpression TitleSearch(String[] types, String keyword){
        if(types == null){
            return null;
        }
        else{
            return Arrays.asList(types).contains("t") == true? board.title.contains(keyword) : null;
        }
    }

    private BooleanExpression ContentSearch(String[] types, String keyword){
        if(types == null){
            return null;
        }
        else{
            return Arrays.asList(types).contains("c")  == true? board.content.contains(keyword) : null;
        }
    }

    private BooleanExpression UserSearch(String[] types, String keyword){
        if(types == null){
            return null;
        }
        else{
            return Arrays.asList(types).contains("w") == true ? board.user.contains(keyword) : null;
        }
    }
}
