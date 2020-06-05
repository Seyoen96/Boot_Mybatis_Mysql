package com.sy.s1.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.protocol.x.Notice;
import com.sy.s1.board.BoardVO;

@SpringBootTest
//@Transactional
//@Rollback(value=true)
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Test
	void setInsertTest() throws Exception{
		for(int i=0; i<100; i++) {
			NoticeVO noticeVO = new NoticeVO();			
			noticeVO.setTitle("title"+i);
			noticeVO.setWriter("writer"+i);
			noticeVO.setContents("contents"+i);
			int result = noticeRepository.setInsert(noticeVO);	
		}
			
		
//		assertEquals(1, result);		
	}
	
//	@Test
	void setUpdateTest() throws Exception{
		NoticeVO noticeVO = new NoticeVO();	
		noticeVO.setNum(1);
		noticeVO.setTitle("title554654");
		noticeVO.setContents("contents11232");
		int result = noticeRepository.setUpdate(noticeVO);		
		
		assertEquals(1, result);
		
	}
	
//	@Test
	void setDeleteTest() throws Exception{
		
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(6);
		int res = noticeRepository.setDelete(boardVO);
		System.out.println("resss: "+res);
		
		
	}
	
	
	
	

}
