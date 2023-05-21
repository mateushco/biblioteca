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
			
			<c:if test="${sucesso == true}">
				<div class="alert alert-success alert-dismissible fade show" role="alert">
					<strong>Sucesso!</strong> Operação realizada com sucesso.
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</c:if>
			
			<section class="content">
				<div class="bancoiner-fluid">
					<div class="row">

						<div class="col-md-12">
							<div class="card">

								<div class="card-header">
									<h4 class="card-title">Dados da Reserva</h4>
								</div>

								<form:form action="/biblioteca/reserva/salvar" method="post" modelAttribute="reserva">

									<input type="hidden" id="id" name="id" value="${reserva.id}">

									<div class="card-body">

										<div class="form-row">
											
											<div class="row mb-4">
												
												<div class="col-md-3">
													<label class="form-label" for="identificador">Identificador</label>
													<input type="text" class="form-control" id="identificador" name="identificador" value="${reserva.identificador}" disabled="disabled"/>
												</div>
												
											</div>
											
											<div class="row mb-4">
												
												<div class="col-md-6">
													<label class="form-label" for="cliente">Cliente</label>
													<input type="text" class="form-control" id="cliente" name="cliente" value="${reserva.cliente.nome}" disabled="disabled"/>
												</div>
												
												<div class="col-md-2">
													<label class="form-label" for="dataReserva">Data Reserva</label>
													<input type="text" class="form-control" id="dataReserva" name="dataReserva" value="${reserva.dataReservaFormatada}" disabled="disabled"/>
												</div>
												
												<div class="col-md-2">
													<label class="form-label" for="status">Status</label>
													<input type="text" class="form-control" id="status" name="status" value="${reserva.status.nome}" disabled="disabled"/>
												</div>
												
												<div class="col-md-2">
													<label class="form-label" for="dataPrevisaoEntrega">Data Status</label>
													<input type="text" class="form-control" id="dataStatus" name="dataPrevidataStatussaoEntrega" value="${reserva.dataStatusFormatada}" disabled="disabled"/>
												</div>
												
											</div>
											
											<hr>
											
											<div class="row mb-4">
												
												<div class="card-body">
													<table id="table" class="table table-striped"
														style="width: 100%">
														<thead>
															<tr>
																<th width="30%">Título</th>
																<th width="20%">ISBN</th>
																<th width="20%">Editora</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="livro" items="${reserva.livros}">
																<tr>
																	<td>${livro.titulo}</td>
																	<td>${livro.isbn}</td>
																	<td>${livro.editora.nome}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
												
											</div>
							              
										</div>
									</div>

								</form:form>
								
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>

	</div>
	
	<script type="text/javascript">
	
		$( '#select-estudante-professor' ).select2( {
		    theme: "bootstrap-5",
			    width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
			    placeholder: $( this ).data( 'placeholder' ),
		} );
		
		$( '#select-livro' ).select2( {
		    theme: "bootstrap-5",
 		    width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
 		    placeholder: $( this ).data( 'placeholder' ),
		} );
		
	</script>
	
	<c:import url="footer.jsp" />
	
</body>
</html>

