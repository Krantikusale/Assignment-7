package assignment_7;



import org.testng.annotations.DataProvider;

public class DP {
	
	 @DataProvider (name = "data-provider")
     public Object[][] dpBrowsers(){
	 return new Object[][] {{"chrome"}, {"firefox"}};
     }

}