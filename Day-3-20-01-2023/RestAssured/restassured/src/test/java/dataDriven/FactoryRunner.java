package dataDriven;

import org.testng.annotations.Factory;

public class FactoryRunner {

	@Factory
	public Object[] factory()
	{
		return new Object[] {new FactoryExample(1),new FactoryExample(2),new FactoryExample(3)};
	}
}
