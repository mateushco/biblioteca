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

<body>

	<c:import url="menu.jsp" />
	
	<div class="container">

		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content">
				<div class="bancoiner-fluid">
					<div class="row">

						<div class="col-md-12">
							<div class="card">

								<div class="card-header">
									<c:choose>
										<c:when test="${funcionalidade.id == null}">
											<h4 class="card-title">Cadastrar Funcionalidade</h4>
										</c:when>
										<c:otherwise>
											<h4 class="card-title">Alterar Funcionalidade</h4>
										</c:otherwise>
									</c:choose>
								</div>

								<form:form action="salvar" method="post" modelAttribute="funcionalidade">

									<input type="hidden" id="id" name="id" value="${funcionalidade.id}">

									<div class="card-body">

										<div class="form-row">

											<div class="row mb-4">
												
												<div class="col-md-3">
													<label class="form-label" for="menu">Menu</label>
													<select class="form-select" id="single-select-field" name="idMenu" required="required">
														<option value="">Selecione um menu</option> 
					 									<c:forEach var="menuVar" items="${menu}"> 
					 										<c:choose>
															    <c:when test="${funcionalidade.menu.id == menuVar.id}">
					 												<option selected="selected" value="${menuVar.id}">${menuVar.nome}</option> 
															    </c:when>    
															    <c:otherwise>
					 												<option value="${menuVar.id}">${menuVar.nome}</option> 
															    </c:otherwise>
															</c:choose>
					 									</c:forEach> 
					 								</select> 
												</div>
												
												<div class="col-md-3">
													<div class="form-outline">
														<label class="form-label" for="nome">Nome</label>
														<input type="text" class="form-control" id="nome" name="nome" value="${funcionalidade.nome}" required="required"  />
													</div>
												</div>
												
												<div class="col-md-4">
													<div class="form-outline">
														<label class="form-label" for="url">URL</label>
														<input type="text" class="form-control" id="url" name="url" value="${funcionalidade.url}" required="required"  />
													</div>
												</div>
												
												<div class="col-md-2">
													<div class="form-outline">
														<label class="form-label" for="ordemMenu">Ordem</label>
														<input type="number" class="form-control" id="ordemMenu" name="ordemMenu" value="${funcionalidade.ordemMenu}" required="required"  />
													</div>
												</div>

											</div>
										</div>
									</div>

									<div class="card-footer">
										<button type="submit" class="btn btn-primary">Salvar</button>
										<a class="btn btn-secondary" href="/biblioteca/funcionalidade/lista" role="button">Cancelar</a>
									</div>

								</form:form>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>

	</div>
	
	<c:import url="footer.jsp" />

</body>
</html>

