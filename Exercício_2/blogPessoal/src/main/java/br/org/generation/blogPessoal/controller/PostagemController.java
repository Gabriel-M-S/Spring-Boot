package br.org.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogPessoal.model.Postagem;
import br.org.generation.blogPessoal.repository.PostagemRepository;

/**
 * 
 * A anotação @RestController: indica que a Classe é uma RestController, ou seja, 
 * é responsável por responder às requisições http enviadas para um endpoint 
 * (endereço) definido na anotação @RequestMapping
 * 
 * A anotação @RequestMapping("/postagens"): indica o endpoint (endereço) que a 
 * controladora responderá as requisições 
 * 
 * A anotação @CrossOrigin("*"): indica que a classe controladora permitirá o 
 * recebimento de requisições realizadas de fora do domínio (localhost, em nosso caso) ao qual 
 * ela pertence. Essa anotação é essencial para que o front-end (Angular ou React), tenha
 * acesso à nossa API (O termo técnico é consumir a API)
 * 
 * Para as versões mais recentes do Angular e do React, recomenda-se configurar esta anotação 
 * com os seguintes parâmetros: @CrossOrigin(origins = "*", allowedHeaders = "*") 
 * 
 * Esta anotação, além de liberar todas as origens (origins), libera também todos os parâmetros
 * do cabeçalho das requisições (allowedHeaders).
 * 
 */

@RestController
@RequestMapping("/postagens") 
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class PostagemController {
	
	/*
	 * 
	 * Injeção de Dependência (@Autowired): Consiste  na  maneira,  ou  seja,  na  implementação 
	 * utilizada pelo  Spring  Framework  de  aplicar  a  Inversão  de  Controle  quando  for 
	 * necessário.
	 * 
	 * A Injeção de Dependência define quais classes serão instanciadas e em quais lugares serão 
	 * injetadas quando houver necessidade. 
	 * 
	 * Em nosso exemplo a classe controladora cria um ponto de injeção da interface PostagemRepository, 
	 * e quando houver a necessidade o Spring Framework irá criar uma instância (objeto) desta interface
	 * permitindo o uso de todos os métodos (padrão ou personalizados em PostagemRepository)
	 *  
	 * */
	
	@Autowired 
	private PostagemRepository postagemRepository;
	
	/**
	 * Listar todas as Postagens
	 *  
	 * A anotação @GetMapping: indica que o método abaixo responderá todas as 
	 * requisições do tipo GET que forem enviadas no endpoint /postagens
	 * 
	 * O Método getAll() será do tipo ResponseEntity porque ele responderá a requisição (Request),
	 * com uma HTTP Response (Resposta http).
	 * 
	 * <List<Postagem>>: O Método além de retornar um objeto da Classe ResponseEntity (OK 🡪200), 
	 * no parâmetro body (Corpo da Resposta), será retornado um Objeto da Classe List (Collection), 
	 * contendo todos os Objetos da Classe Postagem persistidos no Banco de dados, na tabela 
	 * tb_postagens. Observe que nesta linha foi utilizado um recurso chamado Java Generics, 
	 * que além de simplificar o retorno do Objeto da Classe List, dispensa o uso do casting(mudança de tipos). 
	 * Observe que na definição do Método foram utilizados os símbolos <T>, onde T é o Tipo do Objeto 
	 * que será retornado no Corpo da Resposta.
	 * 
	 * return ResponseEntity.ok(postagemRepository.findAll());: Executa o método findAll() (Método padrão da 
	 * Interface JpaRepository), que retornará todos os Objetos da Classe Postagem persistidos no Banco de dados
	 * (<List<Postagem>>). Como a List sempre será gerada (vazia ou não), o Método sempre retornará o Status 200 🡪 OK.
	 * 
	 * O Método findAll() é equivalente a consulta SQL: SELECT * FROM tb_postagens;
	 * 
	 */
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll (){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
	return postagemRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		postagemRepository.deleteById(id);
	}	
	
	}
