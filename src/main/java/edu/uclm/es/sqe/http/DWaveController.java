package edu.uclm.es.sqe.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.es.sqe.model.Hamiltoniano;
import edu.uclm.es.sqe.services.EcuacionesService;
import edu.uclm.es.sqe.so.EjecutorPython;
import edu.uclm.es.sqe.model.MatrizTriangular;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("dwave")
@CrossOrigin("*")
public class DWaveController extends CommonController {

	@Autowired
	private DWaveController service;
	private Object ecuacionesService;
	private Object dwaveService;

	@PutMapping("/generarCodigo")
	public Hamiltoniano generarCodigo(HttpServletRequest req, List<Map<String, Object>> ecuaciones) {
		String token = super.validarPeticion(req);
		
		try{
			Hamiltoniano h = this.ecuacionesService.generarHamiltoniano(ecuaciones);
			String hFileName = super.save(token, h);
			MatrizTriangular mt = this.ecuacionesService.generarMatrizTriangular(hFileName);
			String mtFileName = super.saveCodigo(token, codigo);
			String codigo = this.dwaveService.generarCodigo(mtFileName);
			String codigoFileName = super.saveCodigo(token, codigo);
			
			EjecutorPython ejecutor = new EjecutorPython();
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("respuesta", ejecutor.ejecuta(codigoFileName));
			return resultado;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}