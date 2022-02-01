package br.org.generation.blogpessoalnovo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Atributo nome é obrigatório")
	private String nome;

	@NotNull(message = "Atributo nome é obrigatório")
	@Email(message = "O atributo usuário deve ser um email válido!")
	private String usuario;

	// Senha terá número mínimo de caraceteres como 8 e possui número máximo
	// indefinido, pois
	// dependerá da chave criada a partir da criptografia da senha de acordo com a
	// chave gerada pelo framework

	@NotBlank(message = "O atributo senha é obrigatório")
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;

	private String foto;

	// Cardinalidade um para muitos, pois um usuário (com somente um id na
	// tb_usuario) poderá ter
	// várias postagens

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	public Usuario(long id, String nome, String usuario, String senha, String foto) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.foto = foto;
	}
	
	
	public Usuario() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

}
