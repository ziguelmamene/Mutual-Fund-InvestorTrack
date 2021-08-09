import java.util.ArrayList;

public class CustomerTest {
	public static void main(String[] args) {
		
		MutualFund m1 = new MutualFund("Alphabet", "GOOG", 1621, .25);	//"Alphabet", "GOOG", 1621
		MutualFund m2 = new MutualFund("Amazon", "AMZN", 3036, .2);		//"Apple", "AAPL", 108
		MutualFund m3 = new MutualFund("Apple", "AAPL", 108, .15);		//"Amazon", "AMZN", 3036
		MutualFund m4 = new MutualFund("Boeing", "BA", 158, .1);
		MutualFund m5 = new MutualFund("Caterpillar", "CAT", 161, .05);
		MutualFund m6 = new MutualFund("Cisco", "CSCO", 38, .3);
		MutualFund m7 = new MutualFund("Facebook", "FB", 261, .9);
		MutualFund m8 = new MutualFund("AAPL", "Apple", 108, .5);
		MutualFund m9 = new MutualFund("CSCO", "Cisco", 38, .32);
		MutualFund m10 = new MutualFund("BA", "Boeing", 158, .23);
		
		ArrayList<MutualFundAccount> a1 = new ArrayList<>();
		
		MutualFundAccount mfa1 = new MutualFundAccount(m1, 10);
		
		a1.add(mfa1);
		
		
		Customer x = new Customer("John", "Smith", "john.smith@gmail.com", "abc123", 100000.00, a1);
		
		/*
		System.out.println(x.getFirstName());
		System.out.println(x.getLastName());
		System.out.println(x.getCash());
		System.out.println(x.getEmail());
		System.out.println(x.getAccountNum());*/
		

		x.addFund(5, m1);
		x.addFund(10, m4);
		x.addFund(5, m10);
		x.addFund(15, m3);
		
		System.out.println("Test:" + x.getAccountByName("Alphabet"));
		//System.out.println(x);
		
		//x.sellFund("Apple");
		
		//x.printAccountsByName();
		//x.printAccountsByValue();
	}
}
