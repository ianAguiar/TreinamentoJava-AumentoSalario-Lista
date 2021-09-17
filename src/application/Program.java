package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> list = new ArrayList<>();
		
		System.out.print("Quantos funcionários serão registrados? ");
		int N = sc.nextInt();
		
		for (int i=0; i<N; i++) {
			System.out.println();
			System.out.println("Employee #" + (i +1));
			System.out.print("Id: ");
			Integer id = sc.nextInt();
			
			while(hasId(list, id)) {
				System.out.println("O ID informado já existe na lista! Entre com um novo ID: ");
			}
			
			System.out.print("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			
			System.out.print("Salary: ");
			Double salary = sc.nextDouble();
			
			Employee emp = new Employee(id, name, salary);
			
			list.add(emp);			
		}
		System.out.println();
		System.out.println("Entre com o ID do funcionário que terá aumento no salário: ");
		int idSalary = sc.nextInt();
		//Será validado se o ID do funcionário existe de duas formas
		// 1ª Usando a função hasId criada na classe Program
		/*Integer pos = position(list, idSalary);
		if (pos == null) {
			System.out.println("O ID informado não existe na lista de funcionários!");
		} else {
			System.out.print("Qual a porcentagem que deseja aumentar no salário? ");
			double percent = sc.nextDouble();
			
			list.get(pos).increaseSalary(percent);
		}
		*/
		//2ª Usando predicado para retornar se o funcionario existe na lista
		Employee emp = list.stream().filter(x -> x.getId() == idSalary).findFirst().orElse(null);
		
		if (emp == null) {
			System.out.println("O ID informado não existe na lista de funcionários!");
		} else {
			System.out.print("Qual a porcentagem que deseja aumentar no salário? ");
			double percent = sc.nextDouble();
			
			emp.increaseSalary(percent);
		}
		
		//Imprime lista de funcionário com o salario atualizado
		System.out.println();
		System.out.println("Lista de funcionários: ");
		
		for (Employee e : list) {
			System.out.println(e);
		}
		
		sc.close();
	}
	
	//Função para procurar um elemnento na lista na 1ª forma de ser realizado o exercicio
	public static Integer position(List<Employee> list, int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}
	//Função para validar que não seja inserido um id de funcionário repetido
	public static boolean hasId(List<Employee> list, int id) {
		Employee emp = list.stream().filter(x -> x.getId()== id).findFirst().orElse(null);
		return emp != null;
	}

}
