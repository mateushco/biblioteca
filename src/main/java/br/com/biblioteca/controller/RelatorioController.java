package br.com.biblioteca.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Relatorio;
import br.com.biblioteca.model.Reserva;
import br.com.biblioteca.model.StatusReserva;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.ReservaService;
import br.com.biblioteca.utils.DataUtils;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private EmprestimoService emprestimoService;

	@Autowired
	private ReservaService reservaService;

	public RelatorioController() {
	}

	@RequestMapping(value = "/geral")
	public ModelAndView geral(ModelAndView model) throws IOException {
		model.setViewName("relatorio-filtro");
		model.addObject("dataInicio", LocalDate.now());
		model.addObject("dataFim", LocalDate.now());
		return model;
	}

	@RequestMapping(value = "/gerar")
	public ModelAndView gerarRelatorio(@ModelAttribute Relatorio relatorio) throws IOException {

		ModelAndView model = new ModelAndView();
		model.addObject("dataInicio", relatorio.getDataInicio());
		model.addObject("dataFim", relatorio.getDataFim());
		model.addObject("dataInicioFormat",
				DataUtils.converterLocalDateParaString(LocalDate.parse(relatorio.getDataInicio())));
		model.addObject("dataFimFormat",
				DataUtils.converterLocalDateParaString(LocalDate.parse(relatorio.getDataFim())));

		model.setViewName("relatorio");
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/geral/dados", method = RequestMethod.GET)
	public @ResponseBody String getDados(@RequestParam("dataInicio") String dataInicio,
			@RequestParam("dataFim") String dataFim) throws IOException {

		// total de emprestimos realizados no intervalo
		List<Emprestimo> emprestimos = emprestimoService.pesquisarEmprestimoPorIntervaloDatas(
				DataUtils.converterStringParaLocalDateTime(dataInicio + " 00:00"),
				DataUtils.converterStringParaLocalDateTime(dataFim + " 23:59"));

		long emprestimoAndamento = 0;
		long emprestimoFinalizados = 0;
		for (Emprestimo emprestimo : emprestimos) {
			if (emprestimo.isAtivo())
				emprestimoAndamento++;
			else
				emprestimoFinalizados++;
		}

		// total de reservas realizadas no intervalo
		List<Reserva> reservas = reservaService.pesquisarReservaPorIntervaloDatas(
				DataUtils.converterStringParaLocalDateTime(dataInicio + " 00:00"),
				DataUtils.converterStringParaLocalDateTime(dataFim + " 23:59"));

		long reservasEfetivadas = 0;
		long reservasCanceladas = 0;
		for (Reserva reserva : reservas) {
			if (reserva.getStatus() == StatusReserva.EFETIVADA)
				reservasEfetivadas++;
			else if (reserva.getStatus() == StatusReserva.CANCELADA)
				reservasCanceladas++;
		}

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("mes", " ");
		jsonObject.put("emprestimo", emprestimos.size());
		jsonObject.put("emprestimoAndamento", emprestimoAndamento);
		jsonObject.put("emprestimoFinalizados", emprestimoFinalizados);
		jsonObject.put("reservas", reservas.size());
		jsonObject.put("reservasEfetivadas", reservasEfetivadas);
		jsonObject.put("reservasCanceladas", reservasCanceladas);
		jsonArray.add(jsonObject);

		return jsonArray.toJSONString();
	}

}
