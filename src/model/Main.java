package model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	private List<Funcionario> funcionarios;

    public Main(){
        this.funcionarios = new ArrayList<>();
    }
    
	public static void main(String[] args) {
        Main mainApp = new Main();
		
        mainApp.addFuncionarios();
        mainApp.removeFuncionario("João");
        mainApp.printFuncionarios();
    	System.out.println("/-----------------------------------/\n");
        
    	mainApp.aumentoFuncionarios();
        mainApp.printFuncionarios();
    	System.out.println("/-----------------------------------/\n");
        
        mainApp.printFuncionariosByFunction(mainApp.groupByFunction());
    	System.out.println("/-----------------------------------/\n");
    	
    	mainApp.printFuncionariosAniversariantes(10);
    	mainApp.printFuncionariosAniversariantes(12);
    	System.out.println("/-----------------------------------/\n");
    	
    	mainApp.printFuncionarioMaisVelho();
    	System.out.println("/-----------------------------------/\n");
    	    	
    	mainApp.printFuncionariosOrderByName();
    	System.out.println("/-----------------------------------/\n");
    	
    	mainApp.printFuncionarioSalarySum();
    	System.out.println("/-----------------------------------/\n");
    	
    	mainApp.howManyMinimumWages();
    	System.out.println("/-----------------------------------/\n");
	}
	
	//Função para adicionar os funcionários;
	public void addFuncionarios() {
		this.funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"));
		this.funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal(2284.38), "Operador"));
		this.funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal(9836.14), "Coordenador"));
		this.funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"));
		this.funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 05), new BigDecimal(2234.68), "Recepcionista"));
		this.funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"));
		this.funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal(4071.84), "Contador"));
		this.funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal(3017.45), "Gerente"));
		this.funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal(1606.85), "Eletricista"));
		this.funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal(2799.93), "Gerente"));		
	}
	
	//Remover o João (ou outro Funcionário) da lista
	public void removeFuncionario(String nome) {
		/*for(Funcionario funcionario: this.funcionarios) {
			if(funcionario.getNome().equalsIgnoreCase(nome)) {
				this.funcionarios.remove(funcionario);
			}
		}*/
		this.funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
	}
	
	//Imprimir lista
	public void printFuncionarios() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("R$#,###.00");
        for(Funcionario funcionario: this.funcionarios){
        	System.out.println(funcionario.getNome() 	 + " " + 
			        		   funcionario.getDataNasc().format(formatter) + " " + 
			        		   df.format(funcionario.getSalario())  + " " +
			        		   funcionario.getFuncao());
        }
	}
	
	//Reajuste de 10% no salário dos funcionários
	public void aumentoFuncionarios() {
		for(Funcionario funcionario: this.funcionarios) {
			BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal(1.1));
			funcionario.setSalario(novoSalario);
		}
	}
	
	//Agrupar funcionários por função em um MAP, key = funcao e value = list of funcionarios
	public Map<String, List<Funcionario>> groupByFunction(){
		Map<String, List<Funcionario>> funcionariosByFunction = new HashMap<>();
		
		for(Funcionario funcionario:this.funcionarios) {
			String funcao = funcionario.getFuncao();
			List<Funcionario> listaAux = funcionariosByFunction.get(funcao);
			if (listaAux == null) {
                listaAux = new ArrayList<>();
                funcionariosByFunction.put(funcao, listaAux);
            }
            listaAux.add(funcionario);
        }
        return funcionariosByFunction;
	}
	
	//imprimir funcionarios agrupados por funcao
	public void printFuncionariosByFunction(Map<String, List<Funcionario>> funcionariosByFunction) {
		for (Map.Entry<String, List<Funcionario>> entry : funcionariosByFunction.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println(funcionario.getNome());
            }
            System.out.println();
        }
    }
	
	//Imprimir os funcionários que fazem aniversário no mes 10 e 12
	public void printFuncionariosAniversariantes(int mes) {
		
        for(Funcionario funcionario: this.funcionarios){
        	int mesFuncionario = funcionario.getDataNasc().getMonthValue();
        	if(mesFuncionario == mes) {
        		System.out.println(funcionario.getNome());
        	}
        }
	}
	
	//Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
	public void printFuncionarioMaisVelho() {
		LocalDate dataReferencia = LocalDate.now();
		Funcionario funcionarioMaisVelho = null;
        for(Funcionario funcionario: this.funcionarios){
        	LocalDate dataNascimento = funcionario.getDataNasc();
        	if(dataNascimento.isBefore(dataReferencia)) {
        		dataReferencia = dataNascimento;
        		funcionarioMaisVelho = funcionario;
        	}
        }
        System.out.println(funcionarioMaisVelho.getNome() + " " + Period.between(funcionarioMaisVelho.getDataNasc(), LocalDate.now()).getYears() + " anos.");
	}
	
	//Imprimir a lista de funcionários em ordem alfabética.
	public void printFuncionariosOrderByName() {
		List<Funcionario> funcionariosOrdedByName = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("R$#,###.00");
		funcionariosOrdedByName.addAll(this.funcionarios);
		funcionariosOrdedByName.sort(Comparator.comparing(Funcionario::getNome));
		for(Funcionario funcionario: funcionariosOrdedByName){
        	System.out.println(funcionario.getNome() 	 + " " + 
			        		   funcionario.getDataNasc().format(formatter) + " " + 
			        		   df.format(funcionario.getSalario())  + " " +
			        		   funcionario.getFuncao());
        }
	}
	
	//Imprimir o total dos salários dos funcionários.
	public void printFuncionarioSalarySum() {
		BigDecimal sum = BigDecimal.ZERO;
		for(Funcionario funcionario: this.funcionarios){
        	sum = sum.add(funcionario.getSalario());
        }
        DecimalFormat df = new DecimalFormat("R$#,###.00");
    	System.out.println("Soma salarial de todos os funcionários: R$ " + df.format(sum));
	}
	
	//Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00
	public void howManyMinimumWages() {
		BigDecimal salarioMinimo = new BigDecimal("1212.00");
		BigDecimal result = BigDecimal.ZERO;
        DecimalFormat df = new DecimalFormat("##.00");
        MathContext mc = new MathContext(5); 
		for(Funcionario funcionario: this.funcionarios){
        	result = (funcionario.getSalario()).divide(salarioMinimo, mc);
        	System.out.println("O(a) funcionário(a) " + funcionario.getNome() + " ganha cerca de " + df.format(result) + " salários mínimos.");
		}
	}
}
