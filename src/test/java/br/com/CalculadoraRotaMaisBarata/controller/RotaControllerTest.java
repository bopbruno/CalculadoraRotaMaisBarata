package br.com.CalculadoraRotaMaisBarata.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.CalculadoraRotaMaisBarata.service.RotaService;

@RunWith(SpringRunner.class)
@WebMvcTest(RotaController.class)
@ActiveProfiles("test")
public class RotaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RotaService rotaService;	
	
	@Test
	public void getMelhoRota_DeveRetornarMelhorRota() throws Exception {
		when(rotaService.getMelhorRota(anyString(), anyString())).thenReturn("GRU - BRC - SCL - ORL - CDG > 40");		
		mockMvc.perform(MockMvcRequestBuilders.get("/rota/GRU-CGD"))
			.andExpect(status().isOk())
			.andExpect(content().string("GRU - BRC - SCL - ORL - CDG > 40"));
	}
	
	@Test
	public void getMelhoRota_NoEncontrado() throws Exception {
		
		when(rotaService.getMelhorRota(anyString(), anyString())).thenReturn("");
		
		mockMvc.perform(MockMvcRequestBuilders.get("/rota/GRU-BRZ"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("Rota não encontrada!"));
		
	}
	
}