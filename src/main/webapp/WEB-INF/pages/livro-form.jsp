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

<!-- Styles -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css" />

<!-- Scripts -->
<!-- <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>

<body>

	<c:import url="menu.jsp" />
	
	<div class="container">

		<div class="content-wrapper">
			
			<c:if test="${sucesso == false}">
				<div class="alert alert-warning alert-dismissible fade show" role="alert">
					<strong>Erro!</strong> ${mensagem}
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</c:if>
			
			<section class="content">
				<div class="bancoiner-fluid">
					<div class="row">

						<div class="col-md-12">
							<div class="card">

								<div class="card-header">
									<c:choose>
										<c:when test="${exemplar.id == null}">
											<h4 class="card-title">Cadastrar Livro</h4>
										</c:when>
										<c:otherwise>
											<h4 class="card-title">Alterar Livro</h4>
										</c:otherwise>
									</c:choose>
								</div>

								<form:form action="salvar" method="post" modelAttribute="exemplar">

									<input type="hidden" id="id" name="id" value="${livro.id}">

									<div class="card-body">

										<div class="form-row">

											<div class="row mb-4">
												<div class="col-md-4">
													<div class="form-outline">
														<label class="form-label" for="titulo">Título</label>
														<input type="text" class="form-control" id="titulo" name="titulo" value="${livro.titulo}" required="required"  />
													</div>
												</div>
												
												<div class="col-md-4">
													<div class="form-outline">
														<label class="form-label" for="isbn">ISBN</label>
														<input type="text" class="form-control" id="isbn" name="isbn" value="${livro.isbn}" required="required"  />
													</div>
												</div>
												
												<div class="col-md-4">
													<label class="form-label" for="categoria">Categoria</label>
													<select class="form-select" id="single-select-categoria" name="idCategoria" required="required">
														<option value="">Selecione um categoria</option> 
					 									<c:forEach var="categoriaVar" items="${categorias}"> 
					 										<c:choose>
															    <c:when test="${livro.categoria.id == categoriaVar.id}">
					 												<option selected="selected" value="${categoriaVar.id}">${categoriaVar.nome}</option> 
															    </c:when>    
															    <c:otherwise>
					 												<option value="${categoriaVar.id}">${categoriaVar.nome}</option> 
															    </c:otherwise>
															</c:choose>
					 									</c:forEach> 
					 								</select> 
												</div>
												
											</div>
											
											<div class="row mb-4">
												<div class="col-md-12">
													<label class="form-label" for="autor">Autores</label>
													<select class="form-select" id="select-autor" multiple data-mdb-filter="true" name="idsAutores" required="required">
						 								<c:forEach var="autorVar" items="${autores}"> 
					 										<c:choose>
															    <c:when test="${autorVar.selecionado == true}">
						 											<option value="${autorVar.id}" selected="selected">${autorVar.nome}</option> 
															    </c:when>    
															    <c:otherwise>
						 											<option value="${autorVar.id}">${autorVar.nome}</option> 
															    </c:otherwise>
															</c:choose>
						 								</c:forEach> 
						 							</select> 
												</div>
											</div>
											
											
											<div class="row mb-4">
												
												<div class="col-md-3">
													<label class="form-label" for="editora">Editora</label>
													<select class="form-select" id="single-select-editora" name="idEditora" required="required">
														<option value="">Selecione um editora</option> 
					 									<c:forEach var="editoraVar" items="${editoras}"> 
					 										<c:choose>
															    <c:when test="${livro.editora.id == editoraVar.id}">
					 												<option selected="selected" value="${editoraVar.id}">${editoraVar.nome}</option> 
															    </c:when>    
															    <c:otherwise>
					 												<option value="${editoraVar.id}">${editoraVar.nome}</option> 
															    </c:otherwise>
															</c:choose>
					 									</c:forEach> 
					 								</select> 
												</div>
												
												<div class="col-md-3">
													<div class="form-outline">
														<label class="form-label" for="localEdicao">Local da Edição</label>
														<input type="text" class="form-control" id="localEdicao" name="localEdicao" value="${livro.localEdicao}" required="required"  />
													</div>
												</div>
												
												<div class="col-md-2">
													<div class="form-outline">
														<label class="form-label" for="anoEdicao">Ano da Edição</label>
														<input type="number" class="form-control" id="anoEdicao" name="anoEdicao" value="${livro.anoEdicao}" required="required"  />
													</div>
												</div>
												
												<div class="col-md-2">
													<div class="form-outline">
														<label class="form-label" for="numeroPaginas">N° de Páginas</label>
														<input type="number" class="form-control" id="numeroPaginas" name="numeroPaginas" value="${livro.numeroPaginas}" required="required"  />
													</div>
												</div>
												
												<div class="col-md-2">
													<div class="form-outline">
														<label class="form-label" for="qtdExemplares">Qtd. Exemplares</label>
														<input type="number" class="form-control" id="qtdExemplares" name="qtdExemplares" value="${livro.qtdExemplares}" min="1" required="required"  />
													</div>
												</div>
												
											</div>
											
										</div>
									</div>

									<div class="card-footer">
										<button type="submit" class="btn btn-primary">Salvar</button>
										<a class="btn btn-secondary" href="/biblioteca/livro/lista" role="button">Cancelar</a>
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
	
	<script type="text/javascript">
		
		$( '#single-select-categoria' ).select2( {
		    theme: "bootstrap-5",
 		    width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
 		    placeholder: $( this ).data( 'placeholder' ),
		} );
		
		$( '#single-select-editora' ).select2( {
		    theme: "bootstrap-5",
 		    width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
 		    placeholder: $( this ).data( 'placeholder' ),
		} );
		
		$( '#select-autor' ).select2( {
		    theme: "bootstrap-5",
 		    width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
 		    placeholder: $( this ).data( 'placeholder' ),
		} );
		
	</script>
</body>
</html>

