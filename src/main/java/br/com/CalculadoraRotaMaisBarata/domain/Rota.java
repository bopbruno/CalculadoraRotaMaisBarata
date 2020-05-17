package br.com.CalculadoraRotaMaisBarata.domain;

public class Rota {

	private String origem;
	private String destino;
	private Integer valor;
	
	public Rota() {
		
	}
	
	public Rota(String origem, String destino, int valor) {
		this.origem = origem.toUpperCase();
		this.destino = destino.toUpperCase();
		this.valor = valor;
	}
	
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem.toUpperCase();
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino.toUpperCase();
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rota other = (Rota) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		return true;
	}	
}
