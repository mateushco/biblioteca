<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.108.0">

<title>Biblioteca - Login</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.3/examples/sign-in/">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>

<spring:url value="/resources/css/sign-in.css" var="signin" />
<link href="${signin}" rel="stylesheet" />

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
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>

<!-- Jquery / Datable Scripts -->
<!-- Javascript-->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.2/js/dataTables.bootstrap5.min.js"></script> <!-- ? -->
<script src="https://cdn.datatables.net/fixedheader/3.3.1/js/dataTables.fixedHeader.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.4.0/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.4.0/js/responsive.bootstrap.min.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.2/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/fixedheader/3.3.1/css/fixedHeader.bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.4.0/css/responsive.bootstrap.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.7/jquery.inputmask.min.js"></script>

<body class="text-center">
    <script src="https://accounts.google.com/gsi/client" async defer></script>
	<main class="form-signin w-100 m-auto">

		<form id="signUp" action="/biblioteca/cliente/salvar-externo" method="post">

			<h1 class="h5 mb-3 fw-normal">Cadastro de Cliente</h1>
			<hr>

			<div class="form-floating">
				<input type="text" class="form-control" id="nome" name="nome" placeholder="Nome" autofocus="autofocus"> 
				<label for="nome">Nome</label>
			</div>
			
			<div class="checkbox mb-3"></div>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="cpf" name="cpf" placeholder="CPF"required="required" 
					data-inputmask='"mask": "999.999.999-99"' data-mask > 
				<label for="cpf">CPF</label>
			</div>
													
			<div class="checkbox mb-3"></div>
			
			<div class="form-floating">
				<input type="email" class="form-control" id="email" name="email" placeholder="E-mail"> 
				<label for="email">E-mail</label>
			</div>
			
			<div class="checkbox mb-3"></div>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="telefone" name="telefone" placeholder="Telefone" > 
				<label for="telefone">Telefone</label>
			</div>
			
			<div class="checkbox mb-3"></div>
			
			<div class="form-floating">
				<input type="text" class="form-control" id="login" name="login" placeholder="Login" > 
				<label for="login">Login</label>
			</div>
			
			<div class="checkbox mb-3"></div>
			
			<div class="form-floating">
				<input type="password" class="form-control" id="senha" name="senha" placeholder="Senha"> 
				<label for="senha">Senha</label>
			</div>
			
			<div class="checkbox mb-3"></div>

			<c:if test="${sucesso == false}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					<strong>Erro!</strong> Login ou senha inválidos.
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>

			<button class="w-100 btn btn-lg btn-primary" type="submit">Salvar</button>
			<hr>
		</form>
		
		<a class="btn btn-link" href="/biblioteca/" role="button">Já tem conta? Faça seu login</a>

	</main>
    <script>
          //google callback. This function will redirect to our login servlet
          function onSignIn(googleUser) {

             console.log("GOOGLE CREDENTIAL OBJECT");
             console.log(googleUser.credential);

             $("#credential").val(googleUser.credential);
             $("#signUp").submit();
          }
          
  		$('[data-mask]').inputmask()
  	
   </script>
</body>
</html>

