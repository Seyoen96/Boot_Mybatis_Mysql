package com.sy.s1.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.board.qna.QnaVO;
import com.sy.s1.util.Pager;

public interface BoardService {
	
		//insert
		public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception;
		
		//delete
		public int setDelete(BoardVO boardVO) throws Exception;
		
		//update
		public int setUpdate(BoardVO boardVO) throws Exception;
		
		//selectOne
		public BoardVO getSelectOne(BoardVO boardVO) throws Exception;
		
		//selectList
		public List<BoardVO> getSelectList(Pager pager) throws Exception;
		
		//totalCount
		public long getTotalCount() throws Exception;
		
		//hitUpdate
		public int hitUpdate(long num) throws Exception;

		public int replyInsert(BoardVO boardVO) throws Exception;
}
