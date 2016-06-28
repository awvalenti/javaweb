package com.github.awvalenti.javaweb.ingressolento.web.controledeacesso;

import static java.util.Arrays.*;

import java.util.Collection;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

import com.github.awvalenti.javaweb.ingressolento.controllers.IndexController;
import com.github.awvalenti.javaweb.ingressolento.controllers.LoginController;
import com.github.awvalenti.javaweb.ingressolento.web.Sessao;

@Intercepts
public class AutorizacaoInterceptor implements Interceptor {

	private static final Collection<Class<?>> CONTROLLERS_LIBERADOS = asList(
			IndexController.class,
			LoginController.class
			);

	private final Result result;
	private final Sessao sessao;

	public AutorizacaoInterceptor(Result result, Sessao sessao) {
		this.result = result;
		this.sessao = sessao;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		if (sessao.temUsuarioLogado()) return false;

		Class<?> controllerQueTentouAcessar = method.getResource().getType();
		return !CONTROLLERS_LIBERADOS.contains(controllerQueTentouAcessar);
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance)
			throws InterceptionException {
		result.redirectTo(IndexController.class).index();
	}

}
