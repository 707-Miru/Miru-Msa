package com.back.miru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String index() throws Exception {
		//airService.insertAir();
		//marketService.insertMarket();
		return "index";
	}

	@GetMapping("/qnaboard")
	public String qna() {
		return "qnaboard";
	}

}
