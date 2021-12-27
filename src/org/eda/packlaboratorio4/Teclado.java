package org.eda.packlaboratorio4;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Teclado {
	//Atributos 
	private static Teclado miTeclado;
	private Scanner teclado;
	
	//Constructora
	private Teclado() {
		this.teclado= new Scanner(System.in);
	}
	
	public static Teclado getTeclado() {
		if(Teclado.miTeclado==null) {
			Teclado.miTeclado = new Teclado();
		}
		return Teclado.miTeclado;
	}
	
	public double leerDouble() {
		double texto = 0.0;
		boolean correcto = false;
		do {
			try {
			texto=teclado.nextDouble();
			teclado.nextLine();
			correcto = true;
			}
			catch(InputMismatchException i) {
				System.out.println("Caracter no valido");
				teclado.next();
			}
		}while(!correcto);
		return texto;
	}


	
	public String leerString() {
		String texto=teclado.nextLine();
		return texto;
	}

	public int leerInt() {
		int numero = 10;
		boolean correcto = false;
		do {
			try {
				numero=teclado.nextInt();
				teclado.nextLine();
				correcto = true;
			}
			catch (InputMismatchException i){
				System.out.println("Caracter no valido");
				teclado.next();
			}
		}while (!correcto);
		return numero;
	}
}
