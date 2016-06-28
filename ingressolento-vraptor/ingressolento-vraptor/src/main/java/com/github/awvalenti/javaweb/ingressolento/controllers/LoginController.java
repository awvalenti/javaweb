package com.github.awvalenti.javaweb.ingressolento.controllers;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.github.awvalenti.javaweb.ingressolento.web.Sessao;
import com.github.awvalenti.javaweb.ingressolento.web.controledeacesso.Autenticador;

@Resource
public class LoginController {

	private final Autenticador autenticador;
	private final Sessao sessao;
	private final Result result;

	public LoginController(Autenticador autenticador, Sessao sessao, Result result) {
		this.autenticador = autenticador;
		this.sessao = sessao;
		this.result = result;
	}

	@Post
	public void entrar(String nomeUsuario, String senha) {
		autenticador.efetuarLogin(nomeUsuario, senha);

		if (!sessao.temUsuarioLogado()) {
			result.include("mensagem", "Erro no login: usuario/senha desconhecido(s)");
		}

		result.redirectTo(IndexController.class).index();
	}

}
