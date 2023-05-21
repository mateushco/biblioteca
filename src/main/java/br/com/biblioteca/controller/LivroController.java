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
import br.com.biblioteca.model.Categoria;
import br.com.biblioteca.model.Editora;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.AutorService;
import br.com.biblioteca.service.CategoriaService;
import br.com.biblioteca.service.EditoraService;
import br.com.biblioteca.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@Autowired
	private EditoraService editoraService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private AutorService autorService;

	public LivroController() {
	}

	@RequestMapping(value = "/lista")
	public ModelAndView listarLivro(ModelAndView model) throws IOException {
		List<Livro> listLivro = livroService.pesquisarLivros();
		model.addObject("listLivro", listLivro);
		model.setViewName("livro-lista");
		return model;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelAndView model) {

		List<Editora> editoras = editoraService.pesquisarEditoras();
		List<Categoria> categorias = categoriaService.pesquisarCategorias();
		List<Autor> autores = autorService.pesquisarAutores();

		Livro livro = new Livro();
		model.addObject("livro", livro);
		model.addObject("editoras", editoras);
		model.addObject("categorias", categorias);
		model.addObject("autores", autores);
		model.setViewName("livro-form");
		return model;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@ModelAttribute Livro livro) {

		ModelAndView model = new ModelAndView();

		livroService.salvar(livro);

		List<Livro> listLivro = livroService.pesquisarLivros();

		model.addObject("listLivro", listLivro);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("livro-lista");
		return model;
	}

	@RequestMapping(value = "/deletar", method = RequestMethod.GET)
	public ModelAndView deletar(HttpServletRequest request) {

		Long id = Long.parseLong(request.getParameter("id"));
		livroService.deletar(id);

		List<Livro> listLivro = livroService.pesquisarLivros();

		ModelAndView model = new ModelAndView();
		model.addObject("listLivro", listLivro);
		model.addObject("sucesso", Boolean.TRUE);
		model.setViewName("livro-lista");
		return model;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public ModelAndView editar(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("id"));
		Livro livro = livroService.pesquisarLivroPorId(id);

		List<Editora> editoras = editoraService.pesquisarEditoras();
		List<Categoria> categorias = categoriaService.pesquisarCategorias();
		List<Autor> autores = autorService.pesquisarAutores();

		for (Autor autor : autores) {
			for (Autor autorPerfil : livro.getAutores()) {
				if (autor.getId() == autorPerfil.getId()) {
					autor.setSelecionado(true);
				}
			}
		}

		ModelAndView model = new ModelAndView("livro-form");
		model.addObject("editoras", editoras);
		model.addObject("categorias", categorias);
		model.addObject("autores", autores);
		model.addObject("livro", livro);

		return model;
	}
	
	@RequestMapping(value = "/exportar")
	public void exportar(HttpServletResponse response) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetEditoras = workbook.createSheet("Livros");

		List<Livro> lista = livroService.pesquisarLivros();
		
		int rownum = 0;

		Row cabecalho = sheetEditoras.createRow(rownum++);

		Cell cellIdCab = cabecalho.createCell(0);
		cellIdCab.setCellValue("ID");

		Cell cellTituloCab = cabecalho.createCell(1);
		cellTituloCab.setCellValue("TITULO");

		Cell cellIsbnCab = cabecalho.createCell(2);
		cellIsbnCab.setCellValue("ISBN");
		
		Cell cellAnoCab = cabecalho.createCell(3);
		cellAnoCab.setCellValue("ANO EDIÇÃO");
		
		Cell cellLocalCab = cabecalho.createCell(4);
		cellLocalCab.setCellValue("LOCAL");
		
		Cell cellEditoraCab = cabecalho.createCell(5);
		cellEditoraCab.setCellValue("EDITORA");

		Cell cellQtdCab = cabecalho.createCell(6);
		cellQtdCab.setCellValue("QTD. EXEMPLARES");
		
		for (Livro livro : lista) {
			Row row = sheetEditoras.createRow(rownum++);
			int cellnum = 0;

			Cell cellId = row.createCell(cellnum++);
			cellId.setCellValue(livro.getId());

			Cell cellTitulo = row.createCell(cellnum++);
			cellTitulo.setCellValue(livro.getTitulo());
			
			Cell cellIsbn = row.createCell(cellnum++);
			cellIsbn.setCellValue(livro.getIsbn());
			
			Cell cellAno = row.createCell(cellnum++);
			cellAno.setCellValue(livro.getAnoEdicao());
			
			Cell cellLocal = row.createCell(cellnum++);
			cellLocal.setCellValue(livro.getLocalEdicao());
			
			Cell cellEditora = row.createCell(cellnum++);
			cellEditora.setCellValue(livro.getEditora().getNome());
			
			Cell cellQtd = row.createCell(cellnum++);
			cellQtd.setCellValue(livro.getQtdExemplares());
		}

		try {

			response.setHeader("Content-Disposition", "attachment;filename=\"livros.xlsx\"");

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
