<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.108.0">
<title>Biblioteca</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/navbars/">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" 
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" 
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

<spring:url value="/resources/css/navbars.css" var="navbars" />
<link href="${navbars}" rel="stylesheet" />

<!-- aqui -->

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}
</style>

</head>
<body>

	<main>

		<nav class="navbar navbar-expand-lg navbar-dark bg-dark" aria-label="Eighth navbar example">
			<div class="container">
				<a class="navbar-brand" href="#">Biblioteca</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarsExample07"
					aria-controls="navbarsExample07" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarsExample07">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						
						<c:if test="${funcionalidadesReserva.size() > 0}">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Reserva</a>
								<ul class="dropdown-menu">
									<c:forEach var="func" items="${funcionalidadesReserva}"> 
										<li><a class="dropdown-item" href="${func.url}">${func.nome}</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
						
						<c:if test="${funcionalidadesEmprestimo.size() > 0}">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Empréstimo</a>
								<ul class="dropdown-menu">
									<c:forEach var="func" items="${funcionalidadesEmprestimo}"> 
										<li><a class="dropdown-item" href="${func.url}">${func.nome}</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
						
						<c:if test="${funcionalidadesCadastro.size() > 0}">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Cadastro</a>
								<ul class="dropdown-menu">
									<c:forEach var="func" items="${funcionalidadesCadastro}"> 
										<li><a class="dropdown-item" href="${func.url}">${func.nome}</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
						
						<c:if test="${funcionalidadesRelatorio.size() > 0}">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Relatório</a>
								<ul class="dropdown-menu">
									<c:forEach var="func" items="${funcionalidadesRelatorio}"> 
										<li><a class="dropdown-item" href="${func.url}">${func.nome}</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
						
						<c:if test="${funcionalidadesAdministracao.size() > 0}">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">Administração</a>
								<ul class="dropdown-menu">
									<c:forEach var="func" items="${funcionalidadesAdministracao}"> 
										<li><a class="dropdown-item" href="${func.url}">${func.nome}</a></li>
									</c:forEach>
								</ul>
							</li>
						</c:if>
						
						<c:if test="${usuarioLogado != null}">
							<li class="nav-item">
								<a class="nav-link" href="/biblioteca/sair" aria-expanded="false">Sair</a>
							</li>
						</c:if>
						
						
					</ul>
					
					<c:if test="${usuarioLogado != null}">
						<font style="color: #fff;">Seja bem-vindo, <a href="/biblioteca/meus-dados?id=${usuarioLogado.id}">${usuarioLogado.nome}</a></font>
					</c:if>
					
				</div>
			</div>
		</nav>

	</main>

</body>
</html>