package com.receita.SincronizacaoReceita.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import com.receita.SincronizacaoReceita.model.Conta;

public class ApacheCommonsCsvUtil {
	private static String csvExtension = "csv";
	
	
	
	public static void customersToCsv(Writer writer, List<Conta> contas) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("id", "agencia","conta", "saldo", "status"));) {
			for (Conta conta : contas) {
				List<String> data = Arrays.asList(String.valueOf(conta.getId()), conta.getAgencia(),
						conta.getConta(), String.valueOf(conta.getSaldo()), conta.getStatus());

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

	public static List<Conta> parseCsvFile(InputStream is) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;

		List<Conta> contas = new ArrayList<Conta>();
		ReceitaService receitaService = new ReceitaService();
		
		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			
			
			for (CSVRecord csvRecord : csvRecords) {
				Conta conta = new Conta(csvRecord.get("agencia"),
						csvRecord.get("conta"), Double.parseDouble(csvRecord.get("saldo")), csvRecord.get("status"));
				
				if (receitaService.atualizarConta(conta.getAgencia(), conta.getConta(), conta.getSaldo(), conta.getStatus())) {
					contas.add(conta);
				} else {
					System.out.println("Valores inseridos fora do padrão determinado em uma das contas");
				}
				
			}

		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}

		return contas;
	}
	
	public static boolean isCSVFile(MultipartFile file) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		
		if(!extension.equals(csvExtension)) {
			return false;
		}
		
		return true;
	}

}