<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${titulo}</title>
	<link rel="stylesheet" type="text/css" href="estilos.css">
</head>
<body>
	<header>
		<h1>Ingresso Lento</h1>
		<h4>Os melhores ingressos do mundo para os melhores eventos do mundo!</h4>
	</header>

	<section>
		<p>${mensagem}</p>
		<c:choose>
			<c:when test="${not empty usuarioLogado}">
				<p>Usuario: ${usuarioLogado.nomeUsuario}</p>
				<form method="post" action="<c:url value='/logout/sair' />">
					<button>Logout</button>
				</form>
				<p>Voce ja comprou ${quantidadeIngressosComprados} ingresso(s)</p>
			</c:when>
			<c:otherwise>
				<h4>Login</h4>
				<form method="post" action="<c:url value='/login/entrar' />">
					<fieldset>
						<ul>
							<li>
								<label>Nome de usuario</label>
								<input name="nomeUsuario">
							</li>
							<li>
								<label>Senha</label>
								<input name="senha" type="password">
							</li>
							<li>
								<button>Entrar</button>
							</li>
						</ul>
					</fieldset>
				</form>
			</c:otherwise>
		</c:choose>
	</section>
	
	<section>
		<h2>Eventos</h2>
		<ul>
			<c:forEach var="evento" items="${eventos}">
				<li class="evento">
					<h3>${evento.nome}</h3>
					<p>
						<img src="<c:url value='/img/evento-${evento.id}.jpg' />"
							alt="Foto do evento ${evento.nome}">
						${evento.descricao}
					</p>
					<c:if test="${not empty usuarioLogado}">
						<p>
							<a href="<c:url value='/ingresso/form?eventoId=${evento.id}' />" class="comprar">Comprar ingresso</a>
						</p>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</section>
	
</body>
</html>
