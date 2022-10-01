package javaTester;

import java.util.Scanner;

public class Topic_08_Loop {

	public static void main(String[] args) {
		Scanner scaner = new Scanner(System.in);
		/*
		 * System.out.println("Enter n : "); int n = scaner.nextInt(); for (int i = 1; i <= n; i++) { System.out.print(i); System.out.print(" "); } int a = scaner.nextInt(); int b = scaner.nextInt(); for (int i = a; i <=
		 * b; i++) { System.out.print(i); System.out.print(" "); }
		 */
		int sum = 0;
		for (int i = 0; i <= 10; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		System.out.println("Sum = "+sum);

	}

}
