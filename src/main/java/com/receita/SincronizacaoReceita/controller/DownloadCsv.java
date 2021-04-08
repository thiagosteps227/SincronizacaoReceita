package com.receita.SincronizacaoReceita.controller;


import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.receita.SincronizacaoReceita.service.CsvFileServices;

@RestController
public class DownloadCsv {

	@Autowired
	CsvFileServices csvFileService;

	@GetMapping("/api/download/csv/")
	public void downloadFile(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=customers.csv");
		csvFileService.loadFile(response.getWriter());
	}
}

