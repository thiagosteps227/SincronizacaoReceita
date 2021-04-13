package com.receita.SincronizacaoReceita.controller;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.receita.SincronizacaoReceita.service.CsvFileServices;
import com.receita.SincronizacaoReceita.utils.ApacheCommonsCsvUtil;
import com.receita.SincronizacaoReceita.message.*;

@RestController
@RequestMapping("/api/upload/csv")
public class UploadCsv {
	

	@Autowired
	CsvFileServices csvFileServices;

	@PostMapping("/single")
	public ResponseMessage uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) {

		ResponseMessage response = new ResponseMessage();

		// Checking the upload-file's name before processing
		if (csvfile.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(csvfile.getOriginalFilename(),
					"No selected file to upload! Please do the checking", "fail"));

			return response;
		}


		if(!ApacheCommonsCsvUtil.isCSVFile(csvfile)) { 
			response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: this is not a CSV file!", "fail")); 
			return response; 
		}


		try {

			csvFileServices.store(csvfile.getInputStream());
			response.addMessage(new Message(csvfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(csvfile.getOriginalFilename(), e.getMessage(), "fail"));
		}

		return response;
	}
}
