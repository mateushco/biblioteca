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

import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	public CategoriaController() {
		System.out.println("CategoriaController()");
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listar(ModelAndView model) throws IOException {
		List<Categoria> listCategoria = categoriaService.pesquisarCategorias();
		model.addObject("listCategoria", listCategoria);
		model.setViewName("categoria-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {
		
		Categoria categoria = new Categoria();
		model.addObject("categoria", categoria);
		model.setViewName("categoria-form");
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Categoria categoria) {

		categoriaService.salvar(categoria);
		List<Categoria> listCategoria = categoriaService.pesquisarCategorias();

		ModelAndView model = new ModelAndView();
		model.addObject("listCategoria", listCategoria);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("categoria-lista");
		return model;
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {

		Long id = Long.parseLong(request.getParameter("id"));
		categoriaService.deletar(id);

		List<Categoria> listCategoria = categoriaService.pesquisarCategorias();

		ModelAndView model = new ModelAndView();
		model.addObject("listCategoria", listCategoria);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("categoria-lista");
		return model;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Categoria categoria = categoriaService.pesquisarCategoriaPorId(id);
		ModelAndView model = new ModelAndView("categoria-form");
		model.addObject("categoria", categoria);

		return model;
	}
	
	@RequestMapping(value = "/exportar")
	public void exportar(HttpServletResponse response) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetEditoras = workbook.createSheet("Categorias");

		List<Categoria> lista = categoriaService.pesquisarCategorias();
		
		int rownum = 0;

		Row cabecalho = sheetEditoras.createRow(rownum++);

		Cell cellIdCab = cabecalho.createCell(0);
		cellIdCab.setCellValue("ID");

		Cell cellNomeCab = cabecalho.createCell(1);
		cellNomeCab.setCellValue("NOME");

		for (Categoria categoria : lista) {
			Row row = sheetEditoras.createRow(rownum++);
			int cellnum = 0;

			Cell cellId = row.createCell(cellnum++);
			cellId.setCellValue(categoria.getId());

			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(categoria.getNome());
		}

		try {

			response.setHeader("Content-Disposition", "attachment;filename=\"categorias.xlsx\"");

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
