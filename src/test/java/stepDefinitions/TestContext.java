package stepDefinitions;

public class TestContext {

	public DataContext dataContext;
	
	public TestContext() {
		dataContext = new DataContext();
		
	}
	
	public DataContext getDataContext() {
		return dataContext;
	}
}
