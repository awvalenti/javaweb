<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Ingresso Lento - comprar</title>
	<link rel="stylesheet" type="text/css" href="estilos.css">
</head>
<body>
	<header>
		<h1>Ingresso Lento</h1>
		<h4>Os melhores ingressos do mundo para os melhores eventos do mundo!</h4>
	</header>

	<h2>Comprar ingresso</h2>

	<article class="evento">
		<h3>${evento.nome}</h3>
		<p>
			<img src="<c:url value='/img/evento-${evento.id}.jpg' />"
				alt="Foto do evento ${evento.nome}">
			${evento.descricao}
		</p>
	</article>
	
	<form method="post">
		<input type="hidden" name="evento.id" value="${evento.id}">
		<button>Confirmar compra</button>
	</form>
	
</body>
</html>
