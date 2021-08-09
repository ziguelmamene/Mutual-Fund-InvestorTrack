/**
* Customer.java
* @author PARTNER_NAME_1
* @author PARTNER_NAME_1
* CIS 22C, Lab 6
*/

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Customer {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String accountNum;
	private double cash;
	
	private BST<MutualFundAccount> funds_value = new BST<>();
	private BST<MutualFundAccount> funds_name = new BST<>();
	
	DecimalFormat df = new DecimalFormat("###,###,###.00");


/**CONSTRUCTORS*/

	/**
	* Creates a new Customer when only email
	* and password are known
	* @param email the Customer email
	* @param password the Customer password
	* Assigns firstName to "first name unknown"
	* Assigns lastName to "last name unknown"
	* Assigns cash to 0
	* Assigns accountNum to "none"
	*/
	
	public Customer(String email, String password) {
		this.email = email;
		this.password = password;
		firstName = "first name unknown";
		lastName = "last name unknown";
		cash = 0.00;
		accountNum = "none";
	}
	
	/**
	* Creates a new Customer with no cash
	* @param firstName member first name
	* @param lastName member last name
	* Assigns cash to 0
	* Calls getAccountSeed and assigns accountNum to this value
	* after converting it to a String BY USING CONCATENATION (easiest way)
	* Hint: Make sure you get no warnings or you did not call getAccountSeed
	* correctly!
	*/
	public Customer(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		cash = 0.00;
		accountNum = "" + MutualFundAccount.getAccountSeed();
		
	}
	
	/**
	* Creates a new Customer when all information is known
	* @param firstName member first name
	* @param lastName member last name
	* @param cash the starting amount of cash
	* @param al the MutualFundAccounts owned by this Customer
	* (Hint: add these to the BSTs)
	* Calls getAccountSeed and assigns accountNum to this value
	* after converting it to a String BY USING CONCATENATION (easiest way)
	* Hint: Make sure you get no warnings or you did not call getAccountSeed
	* correctly!
	*/
	public Customer(String firstName, String lastName, String email, String password, double cash, ArrayList<MutualFundAccount> al) {
		NameComparator c1 = new NameComparator();
		ValueComparator c2 = new ValueComparator();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.cash = cash;
		for (int i = 0; i < al.size(); i++) {
			funds_name.insert(al.get(i), c1);
			funds_value.insert(al.get(i), c2);
		}
		accountNum = "" + MutualFundAccount.getAccountSeed();	
	}
	
	/**ACCESORS*/
	
	/**
	* Accesses the customer first name
	* @return the first name
	*/
	public String getFirstName() {
		return firstName;
	}
	
	/**
	* Accesses the customer last name
	* @return the last name
	*/
	public String getLastName() {
		return lastName;
	}
	
	/**
	* Accesses the user's account number
	* @return the account number
	*/
	public String getAccountNum() {
		return accountNum;
	}
	
	/**
	* Accesses the email address
	* @return the email address
	*/
	public String getEmail() {
		return email;
	}
	
	/** Determines whether a given password matches the customer password 
	* @param anotherPassword the password to compare 
	* @return whether the two passwords match 
	*/ 
	public boolean passwordMatch(String anotherPassword) { 
		if(password.equals(anotherPassword)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	* Accesses a specific fund
	* @param name the chosen fund
	* @return the specified mutual fund
	*/
	public MutualFundAccount getAccountByName(String name) {
		NameComparator c1 = new NameComparator();
		ValueComparator c2 = new ValueComparator();
		MutualFund mf1 = new MutualFund(name, "ticker");
			//System.out.println("\nThis is mf1: " + mf1);
		MutualFundAccount mfa1 = new MutualFundAccount(mf1, 0);
			//System.out.println("This is mfa1: " + mfa1);
			//System.out.println(funds_name.search(mfa1, false, c1));
		return funds_name.search(mfa1, false, c1);
	}
	
	/**
	* Accesses the amount of cash in your account
	* @return the amount of cash
	*/
	public double getCash() {
		return cash;
	}
	
	/**
	* Accesses whether any accounts exist
	* for this customer
	* @return whether the customer currently
	* holds any accounts
	*/
	public boolean hasOpenAccounts() {
		return !funds_name.isEmpty();
	}
	
	/**MUTATORS*/
	
	/**
	* Updates the customer first name
	* @param firstName a new first name
	*/
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	* Updates the customer last name
	* @param lastName a new last name
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	* Updates the value of the email address
	* @param name the Customer's email address
	*/
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	* Updates the value of the password
	* @param name the Customer password
	*/
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	* Increases/Decreases the amount of cash
	* by adding to the existing cash
	* @param cash the amount of cash to add
	*/
	public void updateCash(double cash) {
		this.cash += cash;
	}
	
	/**
	* Adds a new mutual fund to the user's 
	* list of funds or updates a fund to
	* increase the number of shares held
	* @param shares the desired number of shares
	* @param mf a new or existing mutual fund
	* @return whether the fund was added
	* to the customer's account - i.e. the 
	* customer had sufficient cash to add
	* the MutualFund
	* Decreases the amount of cash if purchase made
	*/
	public boolean addFund(double shares, MutualFund mf) {	
		NameComparator c1 = new NameComparator();
		ValueComparator c2 = new ValueComparator();
		if (shares * mf.getPricePerShare() > cash) {
			System.out.println("You don't have enough cash to purchase that fund.\n" + 
					"Please add cash to make a purchase");
			return false;
		} else {
			MutualFundAccount mfa = new MutualFundAccount(mf, 0);
			if (funds_name.search(mfa, false, c1) == null) {
				mfa.updateShares(shares);
				funds_name.insert(mfa, c1);
				funds_value.insert(mfa, c2);
				updateCash(-shares * mf.getPricePerShare());
				return true;
			} else {
				MutualFundAccount mfa1 = new MutualFundAccount(mf, 0);
				int total = (int) (funds_name.search(mfa1, false, c1).getNumShares() + shares);
				int total2 = (int) funds_name.search(mfa1, false, c1).getNumShares();
				/*String tkr = funds_name.search(mfa1, false, c1).getMf().getTicker();
				double pPP = funds_name.search(mfa1, false, c1).getMf().getPricePerShare();*/
				MutualFundAccount mfa2 = new MutualFundAccount(mf, total);
				funds_name.search(mfa2, true, c1);
				
				MutualFundAccount mfa3 = new MutualFundAccount(mf, total2); // ORIGINAL MFA TO BE REMOVED
				funds_value.remove(mfa3, c2);
				funds_value.insert(mfa2, c2);
				
				updateCash(-shares * mf.getPricePerShare());
				return true;
			}
		}
	}
	 
	/**
	* Sells a Mutual Fund and
	* returns (the price per share
	* times the number of shares) 
	* to cash minus the fee
	* fee is % * price per share * number of shares
	* @param name the name of the fund
	*/
	public void sellFund(String name) {
		NameComparator c1 = new NameComparator();
		ValueComparator c2 = new ValueComparator();
		MutualFund mf1 = new MutualFund(name, "ticker");
			//System.out.println("\nThis is mf1:\n" + mf1);
		MutualFundAccount mfa1 = new MutualFundAccount(mf1, 0);
			//System.out.println("\nThis is mfa1:\n" + mfa1);
			//System.out.println(funds_name.search(mfa1, false, c1));								 
		int total = (int) funds_name.search(mfa1, false, c1).getNumShares();
			//System.out.println("\nTotal: " + total + "\n");
		String tkr = funds_name.search(mfa1, false, c1).getMf().getTicker();
		double pPP = funds_name.search(mfa1, false, c1).getMf().getPricePerShare();
		double fee = funds_name.search(mfa1, false, c1).getMf().getTradingFee();
	 	MutualFund mf2 = new MutualFund(name, tkr, pPP, fee);
			//System.out.println("\nThis is mf2:" + mf2);
		MutualFundAccount mfa2 = new MutualFundAccount(mf2, total);
			//System.out.println("\nThis is mfa2" + mfa2); 
		funds_name.remove(funds_name.search(mfa1, false, c1), c1);
			//System.out.println(account_value.search(mfa2, false, c2));
		funds_value.remove(funds_value.search(mfa2, false, c2), c2);
		System.out.println();
		updateCash(total * pPP * (1 - fee)); 	///TEST!!!
	
	}
	
	/**
	* Sells a Mutual Fund and
	* returns (the price per share
	* times the number of shares) 
	* to cash minus the fee
	* fee is % * price per share * number of shares
	* @param name the name of the fund
	*/
	public void sellShares(String name, double shares) {
		NameComparator c1 = new NameComparator();
		ValueComparator c2 = new ValueComparator();
		MutualFund mf1 = new MutualFund(name, "ticker");
		MutualFundAccount mfa1 = new MutualFundAccount(mf1, 0);
		//System.out.println(funds_name.search(mfa1, false, c1));
		int total = (int) (funds_name.search(mfa1, false, c1).getNumShares() - shares);
		int total2 = (int) funds_name.search(mfa1, false, c1).getNumShares();
		String tkr = funds_name.search(mfa1, false, c1).getMf().getTicker();
		double pPP = funds_name.search(mfa1, false, c1).getMf().getPricePerShare();
		double tF = funds_name.search(mfa1, false, c1).getMf().getTradingFee();
		MutualFund mf2 = new MutualFund(name, tkr, pPP, tF);
		MutualFundAccount mfa2 = new MutualFundAccount(mf2, total);
		funds_name.search(mfa2, true, c1);	
		
		int shares2 = (int) funds_name.search(mfa1, false, c1).getNumShares();
		double sellValue = shares2 * funds_name.search(mfa1, false, c1).getMf().getPricePerShare();
		updateCash(sellValue * (1 - tF));
		//System.out.println("\nSell value is: " + sellValue);

		MutualFundAccount mfa3 = new MutualFundAccount(mf2, total2);
		funds_value.remove(mfa3, c2);
		funds_value.insert(mfa2, c2);
		
		
		//UPDATE CASH
	}
	
	
	/**ADDITIONAL OPERATIONS*/
	
	/**
	* Creates a String of customer information
	* in the form
	* Name: <firstName> <lastName>
	* Account Number: <accountNum>
	* Total Cash: $<cash>
	* Note that cash is formatted $XXX,XXX,XXX.XX
	*/
	
	@Override public String toString() {
		return "Name: "  + firstName + " " + lastName + "\nAccount Number: " + accountNum + "\nTotal Cash: $" + df.format(cash);
	}
	
	/**
	* Prints out all the customer accounts
	* alphabetized by name
	*/
	public void printAccountsByName() {
		funds_name.inOrderPrint();
	}
	
	/**
	* Prints out all the customer accounts
	* in increasing order of value
	*/
	public void printAccountsByValue() {
		funds_value.inOrderPrint();
	}
	
	/**
	* Compares this Customer to another
	* Object for equality
	* You must use the formula presented
	* in class for full credit. (See Lesson 4)
	* @param o another Object
	* @return true if o is a Customer
	* and has a matching email and password
	* to this Customer
	*/
	@Override public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof Customer)) {
			return false;
		} else {
			Customer c = (Customer) o;
			return (this.email.equals(c.email)) && (this.password.equals(c.password));
		}
	}
	
	/**
	* Returns a consistent hash code for
	* each Customer by summing the Unicode values
	* of each character in the key
	* Key = email + password
	* @return the hash code
	*/
	@Override public int hashCode() {
	int sum = 0;
	String key1 = email + password;
	for(int i = 0; i < key1.length(); i ++) {
		char x = key1.charAt(i);
		sum += (int)x;
	}
	return sum;
	
	}

}