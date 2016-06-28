package com.github.awvalenti.javaweb.ingressolento.web.controledeacesso;

import br.com.caelum.vraptor.ioc.Component;

import com.github.awvalenti.javaweb.ingressolento.repositorios.RepositorioUsuarios;
import com.github.awvalenti.javaweb.ingressolento.web.Sessao;

@Component
public class Autenticador {

	private final Sessao sessao;
	private final RepositorioUsuarios repoUsuarios;

	public Autenticador(Sessao sessao, RepositorioUsuarios repoUsuarios) {
		this.sessao = sessao;
		this.repoUsuarios = repoUsuarios;
	}

	public void efetuarLogin(String nomeUsuario, String senha) {
//		sessao.setIdUsuarioLogado(repoUsuarios.idDoUsuario(nomeUsuario, senha));
	}

	public void efetuarLogout() {
		sessao.setNenhumUsuarioLogado();
	}

}
