package javaTester;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_03_Data_Type {

	public static void main(String[] args) {
		//I. Kiểu dữ liệu nguyên thủy:8 loại(int/long/double/boolean)
		
		/*
		 * Số nguyên :byte/short/int/long(Số không có phần thập phân)
		 * 
		 * Số thực :float/double
		 * Kí tự:char
		 * logic:boolean
		 * 
		 */
		
		//II. Kiểu dữ liệu tham chiếu
		/*
		 * Chuỗi: String
		 * Class/Interface
		 * Đối tượng:object
		 * Array/List/Set/Queue(Collection)
		 */
		WebDriver driver=new FirefoxDriver();
		int numbers[]= {1,2,3};
		String strings[]= {"Da Nang","Ho Chi Minh","Ha Noi"};
		List<Integer>listInt = new ArrayList<Integer>();
        List<String>studentAddress = new ArrayList<String>();
        Set<String>studentCity = new LinkedHashSet<String>();
        
        
	}

}
