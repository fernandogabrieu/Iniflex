package model;
import java.time.LocalDate;

public class Pessoa {
	private String nome;
	private LocalDate dataNasc;
	
	public Pessoa(String nome, LocalDate dataNasc) {
		this.nome = nome;
		this.dataNasc = dataNasc;
	}
	
	public String setNome(String nome) {
		return this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public LocalDate setDataNasc(LocalDate dataNasc) {
		return this.dataNasc = dataNasc;
	}
	
	public LocalDate getDataNasc() {
		return this.dataNasc;
	}
}
