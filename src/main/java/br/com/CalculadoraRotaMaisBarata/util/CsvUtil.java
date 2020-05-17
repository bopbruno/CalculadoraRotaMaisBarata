package br.com.CalculadoraRotaMaisBarata.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.CalculadoraRotaMaisBarata.domain.Rota;

public class CsvUtil {
	
	public List<Rota> csvToRota(Reader csv) throws FileNotFoundException, IOException {
		List<Rota> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(csv)) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(new Rota(values[0], values[1], Integer.parseInt(values[2])));
		    }
		}
		return records;
	}
	
	public void addRotas(List<Rota> rotas) {

		try {
		    
		    // create a writer
		    BufferedWriter writer = Files.newBufferedWriter(Paths.get("input-routes.csv"));

		    // write all records
		    for (Rota record : rotas) {
		        writer.write(record.getOrigem() +","+ record.getDestino() +","+record.getValor());
		        writer.newLine();
		    }

		    //close the writer
		    writer.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	
	}
}
