package com.receita.SincronizacaoReceita.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.receita.SincronizacaoReceita.model.Conta;
import com.receita.SincronizacaoReceita.repository.ContaRepository;
import com.receita.SincronizacaoReceita.utils.ApacheCommonsCsvUtil;

@Service
public class CsvFileServices {
	
	@Autowired
	ContaRepository contaRepository;

	
	public void store(InputStream file) {
		try {
			
			List<Conta> lstContas = ApacheCommonsCsvUtil.parseCsvFile(file);
			
			contaRepository.saveAll(lstContas);
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	
    public void loadFile(Writer writer) throws IOException {
    	try {
        	List<Conta> contas = (List<Conta>) contaRepository.findAll();
        	
             ApacheCommonsCsvUtil.customersToCsv(writer, contas);
        	   		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
    }
}