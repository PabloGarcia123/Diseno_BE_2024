package edu.uclm.es.sqe.model;

public class Sumando {

	private int factor;
	private int index;

	public void setFactor(int factor) {
		this.factor = factor;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int calcular(int x) {
		return this.factor * x;
	}

}