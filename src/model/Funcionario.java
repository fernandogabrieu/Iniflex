package model;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{
	public BigDecimal salario;
	public String funcao;
	
	public Funcionario(String nome, LocalDate dataNasc, BigDecimal salario, String funcao) {
		super(nome, dataNasc);
		this.salario = salario;
		this.funcao = funcao;
    }
    
	public BigDecimal setSalario(BigDecimal salario) {
		return this.salario = salario;
	}
	
	public BigDecimal getSalario() {
		return this.salario;
	}
	
	public String setFuncao(String funcao) {
		return this.funcao = funcao;
	}
	
	public String getFuncao() {
		return this.funcao;
	}
}
