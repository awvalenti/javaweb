package com.github.awvalenti.javaweb.ingressolento.controllers;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.github.awvalenti.javaweb.ingressolento.entidades.Ingresso;
import com.github.awvalenti.javaweb.ingressolento.entidades.Usuario;
import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioEventos;
import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioIngressos;
import com.github.awvalenti.javaweb.ingressolento.web.Sessao;

@Resource
public class IngressoController {

	private final Result result;
	private final Sessao sessao;

	private final RepositorioEventos repoEventos;
	private final RepositorioIngressos repoIngressos;

	public IngressoController(Result result, Sessao sessao, RepositorioEventos repoEventos,
			RepositorioIngressos repoIngressos) {
		this.result = result;
		this.sessao = sessao;
		this.repoEventos = repoEventos;
		this.repoIngressos = repoIngressos;
	}

	public void form(long eventoId) {
		result.include("evento", repoEventos.comId(eventoId));
	}

	@Post
	public void comprar(Ingresso ingresso) {
		ingresso.setComprador(new Usuario(sessao.getIdUsuarioLogado()));
		repoIngressos.incluir(ingresso);
		result.redirectTo(IndexController.class).index();
	}

}
