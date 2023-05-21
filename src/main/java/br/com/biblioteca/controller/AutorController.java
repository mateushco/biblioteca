package br.com.biblioteca.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.biblioteca.model.Autor;
import br.com.biblioteca.service.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	public AutorController() {
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listar(ModelAndView model) throws IOException {
		List<Autor> listAutor = autorService.pesquisarAutores();
		model.addObject("listAutor", listAutor);
		model.setViewName("autor-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {
		
		Autor autor = new Autor();
		model.addObject("autor", autor);
		model.setViewName("autor-form");
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Autor autor) {

		autorService.salvar(autor);
		List<Autor> listAutor = autorService.pesquisarAutores();

		ModelAndView model = new ModelAndView();
		model.addObject("listAutor", listAutor);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("autor-lista");
		return model;
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {

		Long id = Long.parseLong(request.getParameter("id"));
		autorService.deletar(id);

		List<Autor> listAutor = autorService.pesquisarAutores();

		ModelAndView model = new ModelAndView();
		model.addObject("listAutor", listAutor);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("autor-lista");
		return model;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Autor autor = autorService.pesquisarAutorPorId(id);
		ModelAndView model = new ModelAndView("autor-form");
		model.addObject("autor", autor);

		return model;
	}
	
	@RequestMapping(value = "/exportar")
	public void exportar(HttpServletResponse response) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetEditoras = workbook.createSheet("Autores");

		List<Autor> lista = autorService.pesquisarAutores();
		
		int rownum = 0;

		Row cabecalho = sheetEditoras.createRow(rownum++);

		Cell cellIdCab = cabecalho.createCell(0);
		cellIdCab.setCellValue("ID");

		Cell cellNomeCab = cabecalho.createCell(1);
		cellNomeCab.setCellValue("NOME");

		for (Autor autor : lista) {
			Row row = sheetEditoras.createRow(rownum++);
			int cellnum = 0;

			Cell cellId = row.createCell(cellnum++);
			cellId.setCellValue(autor.getId());

			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(autor.getNome());
		}

		try {

			response.setHeader("Content-Disposition", "attachment;filename=\"autores.xlsx\"");

			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "cache");
			response.setHeader("Cache-Control", "must-revalidate");
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(workbook.getBytes().length);

			OutputStream out = response.getOutputStream();
			out.write(workbook.getBytes(), 0, workbook.getBytes().length);
			out.flush();
			out.close();

			System.out.println("Arquivo Excel criado com sucesso!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na edição do arquivo!");
		}

	}
}
