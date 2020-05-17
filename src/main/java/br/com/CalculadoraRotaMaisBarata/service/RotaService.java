package br.com.CalculadoraRotaMaisBarata.service;

import java.util.List;

import br.com.CalculadoraRotaMaisBarata.domain.Rota;

public interface RotaService {

	String getMelhorRota(String origem, String destino);

	void addRota(Rota rota);

	List<Rota> getRotas();
	
}
