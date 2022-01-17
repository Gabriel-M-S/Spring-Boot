package com.helloworld.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
     
	@GetMapping
    public String hello() {
		
		return "Mentalidades: Orientação ao futuro, responsabilidade pessoal, mentalidade de crescimento e persistência."
				+ " Habilidades: Trabalho em Equipe, Atenção aos Detalhes, Proatividade e Comunicação";
	}

}
