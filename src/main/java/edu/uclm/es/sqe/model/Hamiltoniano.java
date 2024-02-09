package edu.uclm.es.sqe.model;

import java.util.ArrayList;
import java.util.List;

public class Hamiltoniano {

	private List<Ecuacion> ecuaciones = new ArrayList<>();

	public void add(Ecuacion equation) {
		this.ecuaciones.add(equation);
	}

	public String getNombre() {
		return "Soy un Hamiltoniano";
	}
}
