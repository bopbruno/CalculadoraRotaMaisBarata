package br.com.CalculadoraRotaMaisBarata.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.CalculadoraRotaMaisBarata.domain.Rota;
import br.com.CalculadoraRotaMaisBarata.response.Response;
import br.com.CalculadoraRotaMaisBarata.service.RotaService;
import br.com.CalculadoraRotaMaisBarata.util.CsvUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rota")
@Api(value = "Calculadora de Rotas")
public class RotaController {

	@Autowired
	private RotaService rotaServie;
	
	@GetMapping(value = "/{dePara}")
	@ApiOperation(value = "Calcula rota mais barata entre dois pontos. Formato Aceito origem-destino")
	public String getMelhorRota(@PathVariable String dePara, HttpServletResponse response){
		String rota = "";
		try {
			String[] deParaList = dePara.split("-");
			rota = rotaServie.getMelhorRota(deParaList[0], deParaList[1]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(rota == "") {
			rota = "Rota não encontrada!";
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return rota; 
	}
	
	@PostMapping("/file")
	@ApiOperation(value = "Envio de arquivo para adicionar rotas às rotas existentes. Será atualizado o custo de rotas já existentes.")
	public ResponseEntity<Response<List<Rota>>> handleFileUpload(@RequestParam("file") MultipartFile file) {
		Response<List<Rota>> response = new Response<>();
		try {
			file.getOriginalFilename();
			for(Rota rota: new CsvUtil().csvToRota(new InputStreamReader(file.getInputStream()))) {
				rotaServie.addRota(rota);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new CsvUtil().addRotas(rotaServie.getRotas());
		
		response.setData(rotaServie.getRotas());
		
		return ResponseEntity.ok(response);
	}
}
