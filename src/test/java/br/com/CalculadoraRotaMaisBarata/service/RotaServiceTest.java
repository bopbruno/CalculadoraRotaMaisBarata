package br.com.CalculadoraRotaMaisBarata.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.CalculadoraRotaMaisBarata.domain.Rota;
import br.com.CalculadoraRotaMaisBarata.service.impl.RotaServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RotaServiceTest {

	private RotaService rotaService;

	@Before
	public void setUp() throws Exception{
		rotaService = new RotaServiceImpl();		
	}

	@Test
	public void getMelhorRota() {
		
		//arrange		
		rotaService = new RotaServiceImpl();
		rotaService.addRota(new Rota("GRU","BRC",10));
		rotaService.addRota(new Rota("BRC","SCL",5));
		rotaService.addRota(new Rota("GRU","CDG",75));
		rotaService.addRota(new Rota("GRU","SCL",20));
		rotaService.addRota(new Rota("GRU","ORL",56));
		rotaService.addRota(new Rota("ORL","CDG",5));
		rotaService.addRota(new Rota("SCL","ORL",20));
				
		//act
		String melhorRota = rotaService.getMelhorRota("GRU", "CDG");
		
		//assert
		assert(melhorRota.equals("GRU - BRC - SCL - ORL - CDG > 40"));
	}
	
	@Test
	public void getMelhorRota_rotaNaoExiste() {
		
		//arrange
		rotaService = new RotaServiceImpl();
		rotaService.addRota(new Rota("GRU", "BRC", 10));
		rotaService.addRota(new Rota("BRC","SCL",5));
		rotaService.addRota(new Rota("SCL","CDG",75));
				
		//act
		String melhorRota = rotaService.getMelhorRota("GRU", "HHH");
		
		//assert
		assert(melhorRota.isEmpty());
	}
}