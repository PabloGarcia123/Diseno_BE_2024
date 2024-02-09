package edu.uclm.es.sqe.http;

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

@RestController
@RequestMapping("ecuaciones")
@CrossOrigin("*")
public class EcuacionesController {

	@Autowired
	private EcuacionesService service;

	@GetMapping("/recibir")
	public String recibir(@RequestParam String eq) {
		if (eq.trim().length() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "La ecuacion no puede estar vacia");
		}
		return this.service.contarIncognitas(eq);
	}

	@PutMapping("/recibir")
	public String recibirPorPut(@RequestBody Map<String, Object> info) {
		String eq = info.get("eq").toString();
		int lambda = (int) info.get("lambda");

		return this.recibir(eq);
	}

	@PutMapping("/calcular")
	public String calcular(@RequestBody Map<String, Object> info) {
		String eq = info.get("eq").toString();
		int lambda = (int) info.get("lambda");
		List<Integer> x = (List<Integer>) info.get("x");

		return this.service.calcular(eq, x);
	}

	@PutMapping("/generarHamiltoniano")
	public Hamiltoniano generarHamiltoniano(@RequestBody List<Map<String, Object>> ecuaciones) {
		return this.service.generarHamiltoniano(ecuaciones);
	}

}
