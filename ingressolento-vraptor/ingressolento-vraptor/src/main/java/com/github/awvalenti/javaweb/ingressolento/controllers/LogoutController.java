package com.github.awvalenti.javaweb.ingressolento.controllers;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.github.awvalenti.javaweb.ingressolento.web.controledeacesso.Autenticador;

@Resource
public class LogoutController {

	private final Autenticador autenticador;
	private final Result result;

	public LogoutController(Autenticador autenticador, Result result) {
		this.autenticador = autenticador;
		this.result = result;
	}

	@Post
	public void sair() {
		autenticador.efetuarLogout();
	}

}
