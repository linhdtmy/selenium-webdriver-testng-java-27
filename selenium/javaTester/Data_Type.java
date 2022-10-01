package javaTester;

import java.util.Scanner;

public class Data_Type {

	public static void main(String[] args) {
		int a = 6;
		int b = 2;
		System.out.println("a + b = " + (a + b));
		System.out.println("a - b = " + (a - b));
		System.out.println("a x b = " + (a * b));
		System.out.println("a / b = " + (a / b));
		float x= 7.5f;
		float y= 3.8f;
		System.out.println("a * b =" +(x*y));
		String name ="Automation Test";
		System.out.println("Hello " +name );

		System.out.println("Enter name : ");
		Scanner scanner = new Scanner(System.in);
		String nameUser = scanner.nextLine();
		System.out.println("Enter age : ");
		int age = scanner.nextInt();
		System.out.println("After 10 year ,"+nameUser+" will "+age);
		
	}

}
