package com.sy.s1.board.notice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sy.s1.board.BoardVO;
import com.sy.s1.board.notice.noticeFile.NoticeFileVO;
import com.sy.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("noiceDelete")
	public ModelAndView setDelete(BoardVO boardVO,RedirectAttributes rd) throws Exception{
		ModelAndView mv = new ModelAndView();
		int res = noticeService.setDelete(boardVO);
		rd.addFlashAttribute("result", res);
		mv.setViewName("redirect:./noticeList");
		return mv;
	}
	
	@GetMapping("noticeUpdate")
	public ModelAndView setUpdate(ModelAndView mv, BoardVO boardVO) throws Exception{
		boardVO = noticeService.getSelectOne(boardVO);
		mv.addObject("vo", boardVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("noticeUpdate")
	public String setUpdate(BoardVO boardVO, RedirectAttributes rd) throws Exception{
		int res = noticeService.setUpdate(boardVO);
		String msg = "Update Success";
		if(res<0) {
			msg="Update Fail";
		}
		rd.addFlashAttribute("result", res);
		rd.addFlashAttribute("msg", msg);
		return "redirect:./noticeList";
	}

	@GetMapping("noticeWrite")
	public ModelAndView setInsert() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("path", "Write");
		mv.addObject("boardVO",new BoardVO());
		return mv;
	}
	
	// spring Form 적용 전
//	@PostMapping("noticeWrite")
//	public String setInsert(BoardVO boardVO,MultipartFile[] files,RedirectAttributes rd) throws Exception{
//		int res = noticeService.setInsert(boardVO,files);
//		rd.addFlashAttribute("result", res);
//		return "redirect:./noticeList"; 
//	}

	// spring Form 처리
	// bindingResult는 검증 대상 객체 바로 뒤에 선언해야함
	@PostMapping("noticeWrite")
	public ModelAndView setInsert(@Valid BoardVO boardVO,BindingResult bindingResult,MultipartFile[] files,RedirectAttributes rd) throws Exception{
		// insert 정보 전에 잘 받아오는지 검증
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			// 에러 발생 시 입력 페이지로 이동
			mv.setViewName("board/boardWrite");
			mv.addObject("path", "Write");
		} else {
			int res = noticeService.setInsert(boardVO,files);
			rd.addFlashAttribute("result", res);
			mv.setViewName("redirect:./noticeList");
		}
		return mv; 
	}
	
	
	@GetMapping("noticeList")
	public ModelAndView getSelectList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = noticeService.getSelectList(pager);
//		System.out.println(" block:"+pager.getCurBlock());
//		System.out.println("total :"+pager.getStartRow());
		mv.addObject("list", ar);
		mv.addObject("path", "List");
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView getSelectOne(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = noticeService.getSelectOne(boardVO);
		mv.addObject("vo",boardVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(NoticeFileVO noticeFileVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		noticeFileVO = noticeService.fileDown(noticeFileVO);
		mv.addObject("path", "upload/notice/");
		mv.addObject("fileVO", noticeFileVO);
		
		// 해당 이름을 가진 것 찾아감, 못찾으면 jsp를 찾으러
		mv.setViewName("fileDown");
		
		return mv;
	}
	
	@GetMapping("noticeRedirectAdmin")
	public ModelAndView qnaRedirect(ModelAndView mv) throws Exception{
		mv.addObject("result", "권한이 없습니다");
		mv.addObject("path", "notice/noticeList");
		mv.setViewName("template/result");
		return mv;
	}
	
	
	//예외 처리 메서드
	//nullPointer 예외 시 처리
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView errorNull() {
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	//숫자가 아닌 값을 숫자로 받아올 때 예외 처리
	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView errorMismatch() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/serverError");
		return mv;
	}
	
	//모든 예외 처리
	@ExceptionHandler(Exception.class)
	public ModelAndView errorNumber() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/serverError");
		return mv;
	}
	
}
