package com.receita.SincronizacaoReceita.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.receita.SincronizacaoReceita.model.Conta;

public class OpenCsvUtil {

	public static List<Conta> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "id", "agencia", "conta","saldo", "status" };
		Reader fileReader = null;
		CsvToBean<Conta> csvToBean = null;
	
		List<Conta> contas = new ArrayList<Conta>();
		
		try {
			fileReader = new InputStreamReader(is);
	
			ColumnPositionMappingStrategy<Conta> mappingStrategy = new ColumnPositionMappingStrategy<Conta>();
	
			mappingStrategy.setType(Conta.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			csvToBean = new CsvToBeanBuilder<Conta>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();
	
			contas = csvToBean.parse();
			
			return contas;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}
		
		return contas;
	}

	public static void contasToCsv(Writer writer, List<Conta> contas) {
		String[] CSV_HEADER = { "id", "agencia","conta", "saldo", "status" };
	    
	    StatefulBeanToCsv<Conta> beanToCsv = null;
	 
	    try {
	      // write List of Objects
	      ColumnPositionMappingStrategy<Conta> mappingStrategy = 
	                new ColumnPositionMappingStrategy<Conta>();
	      
	      mappingStrategy.setType(Conta.class);
	      mappingStrategy.setColumnMapping(CSV_HEADER);
	      
	      beanToCsv = new StatefulBeanToCsvBuilder<Conta>(writer)
	          .withMappingStrategy(mappingStrategy)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();
	 
	      beanToCsv.write(contas);
	    } catch (Exception e) {
	      System.out.println("Writing CSV error!");
	      e.printStackTrace();
	    }
	}
}