package com.sy.s1.board.notice;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sy.s1.board.BoardRepository;
import com.sy.s1.board.BoardService;
import com.sy.s1.board.BoardVO;
import com.sy.s1.board.notice.noticeFile.NoticeFileRepository;
import com.sy.s1.board.notice.noticeFile.NoticeFileVO;
import com.sy.s1.util.FileManager;
import com.sy.s1.util.FilePathGenerator;
import com.sy.s1.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	@Autowired
	private FilePathGenerator pathGenerator;  		//저장할 파일 경로를 생성 
	@Autowired
	private FileManager fileManager;				//실제 파일을 HDD에 저장
	@Value("${board.notice.filePath}")
	private String filePath;

	
	@Override
	public int setInsert(BoardVO boardVO,MultipartFile[] files) throws Exception {
		File file = pathGenerator.getUseClassPathResource(filePath);
		int result = noticeRepository.setInsert(boardVO);
		
		for(MultipartFile multipartFile: files) {
			// 0KB 파일 저장되는 것 처리
			if(multipartFile.getSize()<=0) {
				continue;
			}
			String fileName = fileManager.saveFileCopy(multipartFile, file);
			NoticeFileVO noticeFileVO = new NoticeFileVO();
			noticeFileVO.setNum(boardVO.getNum());
			noticeFileVO.setFileName(fileName);
			noticeFileVO.setOriName(multipartFile.getOriginalFilename());
			
			result = noticeFileRepository.setInsert(noticeFileVO);
			
			System.out.println(fileName);
		}
		return result;

	}

	@Override
	public int setDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.setDelete(boardVO);
	}

	@Override
	public int setUpdate(BoardVO boardVO) throws Exception {
		
		return noticeRepository.setUpdate(boardVO);
	}

	@Override
	public BoardVO getSelectOne(BoardVO boardVO) throws Exception {
		this.hitUpdate(boardVO.getNum());
		return noticeRepository.getSelectOne(boardVO);
	}

	@Override
	public List<BoardVO> getSelectList(Pager pager) throws Exception {
		pager.makeRow();	
		long totalCount = noticeRepository.getTotalCount(pager);
		pager.makePage(totalCount);

		return noticeRepository.getSelectList(pager);
	}

	@Override
	public long getTotalCount() throws Exception {		
		return 0;
	}
	
	public NoticeFileVO fileDown(NoticeFileVO noticeFileVO) throws Exception{
		return noticeFileRepository.fileDown(noticeFileVO);
	}

	@Override
	public int hitUpdate(long num) throws Exception {
		// TODO Auto-generated method stub
		return noticeRepository.hitUpdate(num);
	}

	@Override
	public int replyInsert(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
