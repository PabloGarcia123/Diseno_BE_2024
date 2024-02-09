package edu.uclm.es.sqe.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.uclm.es.sqe.model.Ecuacion;
import edu.uclm.es.sqe.model.Hamiltoniano;

@Service
public class EcuacionesService {

	public EcuacionesService() {
		System.out.println("Creado!!!!");
	}

	public String contarIncognitas(String eq) {
		int cont = 0;
		for (int i = 0; i < eq.length(); i++) {
			if (eq.charAt(i) == 'x') {
				cont++;
			}
		}
		return "Hay " + cont + " incognitas";
	}

	public String calcular(String eq, List<Integer> x) {
		Ecuacion ecuacion = new Ecuacion();
		return ecuacion.calcular(x);
	}

	public Hamiltoniano generarHamiltoniano(List<Map<String, Object>> ecuaciones) {
		Hamiltoniano h = new Hamiltoniano();
		for (Map<String, Object> ecuacion : ecuaciones) {
			String eq = (String) ecuacion.get("eq");
			int lambda = (int) ecuacion.get("lambda");
			Ecuacion equation = new Ecuacion(eq);
			equation.setLambda(lambda);
			h.add(equation);
		}
		return h;
	}
}
