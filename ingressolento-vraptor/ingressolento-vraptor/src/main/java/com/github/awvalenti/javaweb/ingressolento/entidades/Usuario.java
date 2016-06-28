package com.github.awvalenti.javaweb.ingressolento.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;

	private String nomeUsuario;

	private String senhaBcrypt;

	Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String nomeUsuario, String senhaBcrypt) {
		this.nomeUsuario = nomeUsuario;
		this.senhaBcrypt = senhaBcrypt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaBcrypt() {
		return senhaBcrypt;
	}

	public void setSenhaBcrypt(String senhaBcrypt) {
		this.senhaBcrypt = senhaBcrypt;
	}

}
