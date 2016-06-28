package com.github.awvalenti.javaweb.ingressolento.controllers;

import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioEventos;
import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioIngressos;
import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioUsuarios;
import com.github.awvalenti.javaweb.ingressolento.web.Sessao;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private final Result result;
	private final Sessao sessao;

	private final RepositorioEventos repoEventos;
	private final RepositorioIngressos repoIngressos;
	private final RepositorioUsuarios repoUsuarios;

	public IndexController(Result result, Sessao sessao, RepositorioEventos repoEventos,
			RepositorioIngressos repoIngressos, RepositorioUsuarios repoUsuarios) {
		this.result = result;
		this.sessao = sessao;
		this.repoEventos = repoEventos;
		this.repoIngressos = repoIngressos;
		this.repoUsuarios = repoUsuarios;
	}

	@Get("/")
	public void index() {
		Long idUsuarioLogado = sessao.getIdUsuarioLogado();
		if (idUsuarioLogado != null) result.include("usuarioLogado", repoUsuarios.comId(idUsuarioLogado));

		result.include("eventos", repoEventos.todos());
		result.include("quantidadeIngressosComprados",
				repoIngressos.quantidadeCompradaPor(sessao.getIdUsuarioLogado()));
	}

}
