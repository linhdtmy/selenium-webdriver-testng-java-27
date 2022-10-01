package javaTester;

public class Topic_09_ArrayList {

	public static void main(String[] args) {
		// Khai báo 1 mảng và in ra phần tử lớn nhất
		int[] number = { 2, 7, 8, 6, 9 };
		int maxNumber = 2;
		for (int i = 0; i < number.length; i++) {
			if (number[i] > maxNumber)
				maxNumber = number[i];
		}
		System.out.println("Max number : " + maxNumber);
		System.out.println("Sum of first and end is : " + (number[0] + number[number.length - 1]));
	}

}
 
