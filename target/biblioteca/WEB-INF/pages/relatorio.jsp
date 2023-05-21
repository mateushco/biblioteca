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
<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.108.0">
<title>Biblioteca</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">

	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawChart);
      
    function drawChart() {
    	
    	var param = 'dataInicio='
            + encodeURIComponent(document.getElementById('dataInicio').value)
            + '&dataFim='
            + encodeURIComponent(document.getElementById('dataFim').value);
            
    	// carregar os dados do relatório via ajax
    	$.ajax({
            url : "/biblioteca/relatorio/geral/dados",
            data : param,
            dataType : "JSON",
            type : "GET",
	
            success : function(response) {
            	var dataArray = [
            		['Dados', 'Total de Empréstimos', 'Empréstimos em Andamento', 'Empréstimos Finalizados', 'Total de Reservas', 'Reservas Efetivadas', 'Reservas Canceladas'],
            	];
            	
            	for (var i = 0; i < response.length; i++) {
                    var row = [response[i].mes, response[i].emprestimo, response[i].emprestimoAndamento, response[i].emprestimoFinalizados, response[i].reservas, response[i].reservasEfetivadas, response[i].reservasCanceladas];
                    dataArray.push(row);
                }
            	
        		var data = google.visualization.arrayToDataTable(dataArray);
        		
         		var options = {
        			chart: {
                    	title: 'Relatório Geral do dia ' + document.getElementById('dataInicioFormat').value + " até o dia "+ document.getElementById('dataFimFormat').value,
                    	subtitle: 'Relatório com o quantitativo dos empréstimos e reservas de livros',
                  	},
        			bars: 'vertical' // Required for Material Bar Charts.
                };

                var chart = new google.charts.Bar(document.getElementById('barchart_material'));
        		chart.draw(data, google.charts.Bar.convertOptions(options));
        		
            },
            error : function(xhr, status, error) {
                alert("ERRO: " + xhr.responseText);
            }
        });
    	
	}
</script>
    
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
		
		<input type="hidden" id="dataInicio" name="dataInicio" value="${dataInicio}" />
		<input type="hidden" id="dataFim" name="dataFim" value="${dataFim}" />
		
		<input type="hidden" id="dataInicioFormat" name="dataInicioFormat" value="${dataInicioFormat}" />
		<input type="hidden" id="dataFimFormat" name="dataFimFormat" value="${dataFimFormat}" />
		
		<div id="barchart_material" style="width: 900px; height: 500px;"></div>
		
	</div>

	<c:import url="footer.jsp" />
	
</body>
</html>

