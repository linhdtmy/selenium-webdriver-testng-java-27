package javaTester;

import org.openqa.selenium.interactions.internal.DisplayAction;

public class Student {

	int age;
	String name;

	public Student(String name, int age) {
		this.age = age;
		this.name = name;
	}

	public void display() {
		System.out.println("Name : " + name + " Age : " + age);
	}

	public static void main(String[] args) {
		Student a= new Student("Abi",12);
		a.display();

	}

}
