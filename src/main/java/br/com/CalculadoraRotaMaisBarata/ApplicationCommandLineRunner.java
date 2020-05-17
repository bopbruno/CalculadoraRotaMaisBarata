package br.com.CalculadoraRotaMaisBarata;

import java.io.FileReader;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.CalculadoraRotaMaisBarata.domain.Rota;
import br.com.CalculadoraRotaMaisBarata.service.RotaService;
import br.com.CalculadoraRotaMaisBarata.util.CsvUtil;

@Component
@Profile("!test")
public class ApplicationCommandLineRunner implements CommandLineRunner {

	@Autowired
	private RotaService rotaService;

	@Override
	public void run(String... args) throws Exception {

		if (args.length != 0) {
			addRota(args);
		}

		Scanner scan = new Scanner(System.in);
		System.out.print("please enter the route: ");
		while (scan.hasNext()) {
			String dado = scan.nextLine();
			if (dado != null && !dado.isEmpty()) {
				getMelhorRota(dado);
			}

			System.out.print("please enter the route: ");
		}
		scan.close();
	}

	private void getMelhorRota(String dado) {
		try {
			String[] deParaList = dado.split("-");
			String melhorRota = rotaService.getMelhorRota(deParaList[0], deParaList[1]);
			if (melhorRota.isEmpty())
				System.out.println("Rota nao encontrada! Certifique-se de estar digidando o formato correto: DE-PARA");
			else
				System.out.println("best route: " + melhorRota);
		} catch (Exception e) {
			System.out.println(
					"Erro ao obter a rota " + dado + "   Certifique-se de estar digidando o formato correto: DE-PARA");
		}
	}

	private void addRota(String... args) {
		try {
			for (Rota rota : new CsvUtil().csvToRota(new FileReader(args[0]))) {
				rotaService.addRota(rota);
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar arquivo.");
			System.exit(1);
		}
	}
}