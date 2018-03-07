package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
	
	@RequestMapping("hello")
	public String showHello(){
		return "hello world";
	}

}
