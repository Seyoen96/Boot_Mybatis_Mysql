package com.sy.s1.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.sy.s1.board.BoardRepository;
import com.sy.s1.board.BoardVO;

@Mapper
public interface QnaRepository extends BoardRepository {
	
	public int replyInsert(BoardVO boardVO);
	
	public int setUpdateRef(BoardVO boardVO);
	
	public int setUpdateReply(BoardVO boardVO);
	

}
