<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
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
		<h1>${titulo}</h1>
		<h4>${subtitulo}</h4>
	</header>

	<section>
		<p>${mensagem}</p>
		<c:choose>
			<c:when test="${not empty idUsuarioLogado}">
				<p>Usuario: ${nomeUsuarioLogado}</p>
				<form action="logout" method="post">
					<button>Logout</button>
				</form>
				<p>Voce ja comprou ${quantidadeIngressosComprados} ingresso(s)</p>
			</c:when>
			<c:otherwise>
				<h4>${login}</h4>
				<form action="login">
					<fieldset>
						<ul>
							<li>
								<label>${nomeUsuario}</label>
								<input name="nomeUsuario">
							</li>
							<li>
								<label>${senha}</label>
								<input name="senha" type="password">
							</li>
							<li>
								<button>${entrar}</button>
							</li>
						</ul>
					</fieldset>
				</form>
			</c:otherwise>
		</c:choose>
	</section>
	
	<section>
		<h2>${eventostxt}</h2>
		<ul>
			<c:forEach var="evento" items="${eventos}">
				<li class="evento">
					<h3>${evento.nome}</h3>
					<p>
						<img src="<c:url value='/img/evento-${evento.id}.jpg' />"
							alt="Foto do evento ${evento.nome}">
						${evento.descricao}
					</p>
					<c:if test="${not empty idUsuarioLogado}">
						<p>
							<a href="comprar-ingresso?evento.id=${evento.id}" class="comprar">Comprar ingresso</a>
						</p>
					</c:if>
				</li>
			</c:forEach>
		</ul>
	</section>
	
</body>
</html>
