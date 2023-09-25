package com.example.project.repository.search;

import com.example.project.domain.Board;
import com.example.project.domain.QBoard;
import com.example.project.domain.QReply;
import com.example.project.dto.BoardListReplyCountDTO;
import com.example.project.repository.Querydsl5RepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

public class BoardSearchImpl extends Querydsl5RepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }
    private QBoard board = QBoard.board;
    private QReply reply = QReply.reply;

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable){

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
