package dataDriven;

import org.testng.annotations.DataProvider;

public class dataForTests {

	@DataProvider(name = "dataProviderInput1")
	public Object[][] dataForPost1() {
		Object[][] data = new Object[3][3];

		data[0][0] = "Seventh";
		data[0][1] = "Seven";
		data[0][2] = 9;

		data[1][0] = "Eighth";
		data[1][1] = "Eight";
		data[1][2] = 5;

		data[2][0] = "Nine";
		data[2][1] = "Nine";
		data[2][2] = 10;

		return data;
	}

	@DataProvider(name = "dataProviderInput2")
	public Object[][] dataForPost2() {
		// Object [][] data = new Object [2][3];
		return new Object[][] { { "Eighth", "Eight", 1 }, { "Ninth", "Nine", 3 } };
	}

	@DataProvider(name = "dataProviderInputExcel")
	public Object[][] dataForPost3() {
//		Object[][] data = new Object[2][3];

		return new Object[][] { { "Eighth", "Eight", 1 }, { "Ninth", "Nine", 3 } };
	}

	@DataProvider(name = "dataProviderDelete")
	public Object[][] dataForDelete() {
//		Object[][] data = new Object[3][1];

		return new Object[][] { { 9 }, { 10 } };
	}

}
