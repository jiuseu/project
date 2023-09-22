package com.example.project.repository.search;

import com.example.project.domain.Board;
import com.example.project.domain.QBoard;
import com.example.project.domain.QReply;
import com.example.project.dto.BoardListReplyCountDTO;
import com.example.project.repository.Querydsl5RepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BoardSearchImpl extends Querydsl5RepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable){

        QBoard board = QBoard.board;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if((types != null && types.length > 0) && keyword != null){

            for(String type : types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.user.contains(keyword));
                        break;
                }
            }// end for
        } // end if
        return applyPagination(pageable, query -> query.selectFrom(board)
                        .where(booleanBuilder,board.bno.gt(0L))
        );
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,String keyword,Pageable pageable){

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if((types != null && types.length > 0) && keyword != null){

            for(String type : types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.user.contains(keyword));
                        break;
                }
            }
        }
        return applyPagination(pageable, query -> query.select(Projections.bean(
                                BoardListReplyCountDTO.class,
                                board.bno,
                                board.title,
                                board.user,
                                board.regDate,
                                reply.count().as("replyCount")
                        )).from(board).leftJoin(reply).on(reply.board.eq(board))
                        .groupBy(board)
                        .where(booleanBuilder,
                                board.bno.gt(0L))
        );
    }
}
