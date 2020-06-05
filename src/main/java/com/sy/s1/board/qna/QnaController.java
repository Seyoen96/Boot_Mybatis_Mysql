package com.sy.s1.board.qna;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sy.s1.board.BoardVO;
import com.sy.s1.util.Pager;

@Controller
@RequestMapping("/qna/**/")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;

	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	
	@GetMapping("qnaReply")
	public ModelAndView replyInsert(int num, ModelAndView mv) throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(num);
//		boardVO = qnaService.getSelectOne(boardVO);
		mv.addObject("vo", boardVO);
		mv.addObject("path", "Reply");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public String replyInsert(BoardVO boardVO, ModelAndView mv,RedirectAttributes rd) throws Exception{
		System.out.println("getNum:"+boardVO.getNum());
		int res = qnaService.replyInsert(boardVO);
		rd.addFlashAttribute("result", res);
		return "redirect:./qnaList";
	}
	
	
	@PostMapping("qnaUpdate")
	public String setUpdate(BoardVO boardVO,RedirectAttributes rd) throws Exception{
		int res = qnaService.setUpdate(boardVO);
		rd.addFlashAttribute("result", res);
		return "redirect:./qnaList";
	}
	
	@GetMapping("qnaUpdate")
	public ModelAndView setUpdate(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = qnaService.getSelectOne(boardVO);
		mv.addObject("vo", boardVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView setInsert() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("path", "Write");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public String setInsert(BoardVO boardVO, MultipartFile[] files,RedirectAttributes rd) throws Exception{
		int res = qnaService.setInsert(boardVO, files);
		rd.addFlashAttribute("result", res);
		return "redirect:./qnaList";
	}
	
	@GetMapping("qnaList")
	public ModelAndView getSelectList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = qnaService.getSelectList(pager);
		mv.addObject("list", ar);
		mv.addObject("path", "List");
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");

		return mv;
	}
	
	@GetMapping("qnaSelect")
	public ModelAndView getSelectOne(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = qnaService.getSelectOne(boardVO);
		mv.addObject("vo", boardVO);
		mv.setViewName("board/boardSelect");
//		request.setAttribute("memberID", boardVO.getWriter());
		
		return mv;
	}
	
	@GetMapping("qnaRedirect")
	public ModelAndView qnaRedirect(ModelAndView mv) throws Exception{
		mv.addObject("result", "권한이 없습니다");
		mv.addObject("path", "qna/qnaList");
		mv.setViewName("template/result");
		return mv;
	}
	
	
}
