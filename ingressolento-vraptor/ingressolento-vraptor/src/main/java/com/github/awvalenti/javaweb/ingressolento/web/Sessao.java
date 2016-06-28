package com.github.awvalenti.javaweb.ingressolento.web;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class Sessao {

	private Long idUsuarioLogado;

	public Long getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(Long idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}

	public boolean temUsuarioLogado() {
		return idUsuarioLogado != null;
	}

	public void setNenhumUsuarioLogado() {
		setIdUsuarioLogado(null);
	}

}
