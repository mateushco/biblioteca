package br.com.biblioteca.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import br.com.biblioteca.model.Cliente;

public class Teste {

	public static void main(String[] args) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheetAlunos = workbook.createSheet("Alunos");

		List<Cliente> lista = new ArrayList<Cliente>();Cliente cliente = new Cliente();
		cliente.setNome("Alex");
		cliente.setCpf("1111");
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Alex 2");
		cliente2.setCpf("1111222");
		
		lista.add(cliente);
		lista.add(cliente2);
		

		int rownum = 0;
		
		Row cabecalho = sheetAlunos.createRow(rownum++);
		
		Cell cellNomeCab = cabecalho.createCell(0);
		cellNomeCab.setCellValue("Nome");
		
		Cell cellCPFCab = cabecalho.createCell(1);
		cellCPFCab.setCellValue("CPF");
		
		for (Cliente cli : lista) {
			Row row = sheetAlunos.createRow(rownum++);
			int cellnum = 0;
			
			Cell cellNome = row.createCell(cellnum++);
			cellNome.setCellValue(cli.getNome());

			Cell cellCPF = row.createCell(cellnum++);
			cellCPF.setCellValue(cli.getCpf());
		}

		try {
			FileOutputStream out = new FileOutputStream(new File("teste.xls"));
			workbook.write(out);
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
