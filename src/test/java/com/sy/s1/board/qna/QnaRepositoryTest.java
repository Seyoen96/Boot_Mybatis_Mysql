package com.sy.s1.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sy.s1.board.BoardVO;
import com.sy.s1.board.notice.NoticeVO;

@SpringBootTest
class QnaRepositoryTest {

	@Autowired
	private QnaRepository qnaRepository;
	
	@Test
	void setInsertTest() throws Exception{
		for(int i=0; i<30;i++) {
			BoardVO boardVO = new BoardVO();	
			boardVO.setWriter("seri"+i);
			boardVO.setTitle("title"+i);
			boardVO.setContents("contents"+i);
			int res = qnaRepository.setInsert(boardVO);
			
		}
		
		
		
//		assertEquals(1, res);	
	}

}
