package com.sy.s1.board.qna;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.board.BoardService;
import com.sy.s1.board.BoardVO;
import com.sy.s1.board.qna.qnaFile.QnaFileRepository;
import com.sy.s1.board.qna.qnaFile.QnaFileVO;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;
import com.sy.s1.util.Pager;

@Service
public class QnaService implements BoardService {
	
	@Autowired
	private QnaRepository qnaRepository;
	@Autowired
	private QnaFileRepository qnaFileRepository;
	
	@Autowired
	private FilePathGenerator pathGenerator;
	@Autowired
	private FileManager fileManager;
	@Value("${board.qna.filePath}")
	private String filePath;
	

	
	@Override
	public int setInsert(BoardVO boardVO, MultipartFile[] files) throws Exception {
		File file = pathGenerator.getUseClassPathResource(filePath);
		int res = qnaRepository.setInsert(boardVO);
		for(MultipartFile multipartFile: files) {
			if(multipartFile.getSize()>0) {
				String fileName = fileManager.saveFileCopy(multipartFile, file);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setNum(boardVO.getNum());
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(multipartFile.getOriginalFilename());
				System.out.println("num:"+boardVO.getNum());
				res = qnaFileRepository.setInsert(qnaFileVO);
			}	
		}	
		res = qnaRepository.setUpdateRef(boardVO);
		return res;
	}
	
	@Override
	public int replyInsert(BoardVO boardVO) throws Exception {
		
		int res = qnaRepository.replyInsert(boardVO);
		System.out.println("insert:"+boardVO.getNum());
		int result = qnaRepository.setUpdateReply(boardVO);
		System.out.println("reUpdate:"+result);
		return res;
	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {

		return qnaRepository.setDelete(boardVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {

		return qnaRepository.setUpdate(boardVO);
	}

	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		this.hitUpdate(boardVO.getNum());
		return qnaRepository.getSelectOne(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();
		long totalCount = qnaRepository.getTotalCount(pager);
		pager.makePage(totalCount);
		return qnaRepository.getSelectList(pager);
	}

	@Override
	public long getTotalCount() throws Exception {
		return 0;
	}

	@Override
	public int hitUpdate(long num) throws Exception {
		return qnaRepository.hitUpdate(num);
	}
	
	

}
