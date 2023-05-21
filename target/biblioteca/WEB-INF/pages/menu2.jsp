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

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css">
	
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/dataTables.bootstrap5.min.css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.3/js/bootstrap.bundle.min.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.7/jquery.inputmask.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.2.0/chart.min.js" 
	integrity="sha512-qKyIokLnyh6oSnWsc5h21uwMAQtljqMZZT17CIMXuCQNIfFSFF4tJdMOaJHL9fQdJUANid6OB6DRR0zdHrbWAw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<spring:url value="/resources/css/navbars.css" var="navbars" />
<link href="${navbars}" rel="stylesheet" />

<!-- Styles -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />

<!-- Scripts -->
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>

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
						
						<li class="nav-item">
							<a class="nav-link" href="/biblioteca/sair" aria-expanded="false">Sair</a>
						</li>
						
					</ul>
					
					<font style="color: #fff;">Seja bem-vindo, <a href="/biblioteca/meus-dados?id=${usuarioLogado.id}">${usuarioLogado.nome}</a></font>
					
				</div>
			</div>
		</nav>

	</main>

</body>
</html>