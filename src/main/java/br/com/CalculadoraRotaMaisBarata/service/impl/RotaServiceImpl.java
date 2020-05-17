package br.com.CalculadoraRotaMaisBarata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.CalculadoraRotaMaisBarata.domain.Rota;
import br.com.CalculadoraRotaMaisBarata.service.RotaService;

@Service
public class RotaServiceImpl implements RotaService {
	
	private static class Ponto {
		public int custo;
		public List<String> caminho;

		public Ponto(int l) {
			this.custo = l;
			this.caminho = new ArrayList<>();
		}
	}

	private List<Rota> rotas = new ArrayList<>();
	private Set<String> nomePontos = new HashSet<>();
	private Map<String, Ponto> pontos = new HashMap<>();
	private Ponto pontoFinal;
	
	private List<String> inicializarPesquisa(String inicio, String fim) {
		nomePontos.add(inicio);
		nomePontos.add(fim);
		List<String> naoVizitado = new ArrayList<>(nomePontos);
		for (String ponto : naoVizitado)
			pontos.put(ponto, new Ponto(Integer.MAX_VALUE));

		pontos.get(inicio).custo = 0;
		return naoVizitado;
	}

	private void visitar(String ponto) {
		List<Rota> vizinhos = acharVizinhos(ponto);
		Ponto pontoAtual = pontos.get(ponto);
		for (Rota e : vizinhos) {
			Ponto nbr = pontos.get(e.getDestino());

			int novoTamanho = pontoAtual.custo + e.getValor();
			if (nbr.custo > novoTamanho) {
				nbr.custo = novoTamanho;
				nbr.caminho = new ArrayList<String>();
				nbr.caminho.addAll(pontoAtual.caminho);
				nbr.caminho.add(ponto);
			}
		}
	}

	private void setupPontoFinal(String fim) {
		pontoFinal = pontos.get(fim);
		if (pontoFinal.custo != Integer.MAX_VALUE)
			pontoFinal.caminho.add(fim);
		else
			pontoFinal.custo = 0;
	}

	private String getProximo(List<String> naoVizitados) {
		String pontoMinimo = null;
		int tamanhoMinimo = Integer.MAX_VALUE;

		for (String nome : naoVizitados) {
			Ponto candidato = pontos.get(nome);
			if (candidato.custo < tamanhoMinimo) {
				tamanhoMinimo = candidato.custo;
				pontoMinimo = nome;
			}
		}
		return pontoMinimo;
	}

	private List<Rota> acharVizinhos(String begin) {
		List<Rota> achados = new ArrayList<>();
		for (Rota e : rotas) {
			if (e.getOrigem().equals(begin))
				achados.add(e);
		}
		return achados;
	}

	@Override
	public void addRota(Rota rota) {
		if(rotas.contains(rota)) {
			for(Rota rotaLista : rotas) {
				if(rotaLista.equals(rota)) {
					rotaLista.setValor(rota.getValor());
				}
			}
			return;
		}			
		rotas.add(rota);
		nomePontos.add(rota.getOrigem());
		nomePontos.add(rota.getDestino());
	}
	
	@Override
	public List<Rota> getRotas(){
		return rotas;
	}
	
	@Override
	public String getMelhorRota(String origem, String destino) {

		origem = origem.toUpperCase();
		destino = destino.toUpperCase();
		
		List<String> naoVizitado = inicializarPesquisa(origem, destino);

		for (String ponto = origem; ponto != null && !ponto.equals(destino); ponto = getProximo(naoVizitado)) {
			naoVizitado.remove(ponto);
			visitar(ponto);
		}

		setupPontoFinal(destino);		

		String melhoRota = "";
		
		for(String ponto : this.pontoFinal.caminho) {
			if(melhoRota.isEmpty()) {
				melhoRota = ponto;
				continue;
			}
			
			melhoRota = melhoRota + " - " + ponto;
		}
		
		if(this.pontoFinal.custo > 0) {
			melhoRota = melhoRota + " > " + this.pontoFinal.custo;
		}
		
		return melhoRota;
		
	}
}
