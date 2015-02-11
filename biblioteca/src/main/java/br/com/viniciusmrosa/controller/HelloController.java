package br.com.viniciusmrosa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String ola(Model model){
		model.addAttribute("name","Vinicius Martins");
		return "index";
	}
}
