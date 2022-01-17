package com.helloworld2.hello2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Hello2Controller {
     
	@GetMapping
    public String hello() {
		
		return "Nesta semana, haverá o estudo sobre Spring-boot e suas funcionalidades. Para começar, vamos implementar o programa Hello World.";
	}

}
