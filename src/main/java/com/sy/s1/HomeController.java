package com.sy.s1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sy.s1.util.FilePathGenerator;

@Controller
public class HomeController {
	
	@Autowired
	private FilePathGenerator pathGenerator;
	
	//REST ful 사용
	@RequestMapping(value = "",method = RequestMethod.PUT)
	private void test() {}
	
	
	@GetMapping("message/messageResult")
	public ModelAndView message(String result, String path) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.addObject("path", path);
		mv.setViewName("template/result");
		return mv;
	}
	
	@GetMapping("/")
	public String home(Model model) throws Exception{
		pathGenerator.getUserResourceLoader("upload");
		model.addAttribute("name", "seyeon");
		model.addAttribute("phone", "010-xxxx-xxxx");
		return "index";
	}
	
	
}
