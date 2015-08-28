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
		<h4>${login}</h4>
		<form action="login" method="post">
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
	</section>
	
	<section>
		<h2>${eventostxt}</h2>
		<ul>
			<c:forEach var="evento" items="${eventos}">
				<li>
					<h3>${evento.nome}</h3>
					<p><img src="<c:url value='/img/evento-${evento.id}.jpg' />"> ${evento.descricao}</p>
				</li>
			</c:forEach>
		</ul>
	</section>
	
</body>
</html>
