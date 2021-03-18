package BDD.APITestFramework.DataDrivenExcel;

import java.util.List;

public class DataTest {
	
	public static void main(String[] args) {
		
		DataDriver dataDriver = new DataDriver();
		List<String> dataList = dataDriver.getData("search");
		
		for(String data : dataList){
			System.out.println(data);
		}
		
	}
	
}
