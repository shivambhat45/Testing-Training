package dataDriven;

import org.testng.annotations.Test;

public class FactoryExample {
	
	public int a;
	
	public FactoryExample(int a)
	{
		this.a=a;
	}

	@Test(priority = 0)
	public void op_1()
	{
		System.out.println("Test 1 >>"+ a);
		
	}
	
	@Test(priority = 1)
	public void op_2()
	{
		System.out.println("Test 2 >>"+ a);
		
	}
	
	@Test(priority = 2)
	public void op_3()
	{
		System.out.println("Test 3 >>"+ a);
		
	}
}
