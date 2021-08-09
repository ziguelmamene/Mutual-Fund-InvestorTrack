/**
* CustomerInterface.java
* @author Edgar Aguilar
* @authoer Zachary Iguelmamene
* CIS 22C, Lab 6
*/
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInterface {

    public static void main(String[] args) throws IOException{
	final int NUM_MUTUAL_FUNDS = 7;
	final int NUM_CUSTOMERS = 100;
    HashTable<MutualFund> funds = new HashTable<>(NUM_MUTUAL_FUNDS * 2);
	HashTable<Customer> customers = new HashTable<>(NUM_CUSTOMERS);
		
	DecimalFormat df = new DecimalFormat("###,##0.00");
		
	String first, last, email, password, mutualName, ticker, choice, choice2;
	double cash, sharePrice, fee;
	int numFunds, numShares, selection;			

	try {
		File file1 = new File("customers.txt");
		File file2 = new File("mutual_funds.txt");
		
		Scanner input1 = new Scanner(file1);
		Scanner input2 = new Scanner(file2);
		Scanner input3 = new Scanner(System.in);
		
		while (input2.hasNextLine()) {
			mutualName = input2.nextLine();
				//System.out.println("mutualName: " + mutualName);
			ticker = input2.nextLine();
				//System.out.println("Ticker: " + ticker);
			sharePrice = input2.nextDouble();
			input2.nextLine();		// Clear the buffer
				//System.out.println("sharePrice: " + sharePrice);
			fee = input2.nextDouble();
			input2.nextLine();		// Clear the buffer
				//System.out.println("Fee: " + fee);
			MutualFund fund1 = new MutualFund(mutualName, ticker, sharePrice, fee);
			//funds.addLast(fund1);
			funds.put(fund1);
		}
		
		//funds.printTable();
		
		while (input1.hasNextLine()) {
			first = input1.next();
			last = input1.next();
				//System.out.println(first + " " + last);
			email = input1.next();
				//System.out.println(email);
			password = input1.next();
				//System.out.println(password);
			cash = input1.nextDouble();
				//System.out.println(cash);
			input1.nextLine();			// Clear the buffer
			numFunds = input1.nextInt();
				//System.out.println("NumFunds: " + numFunds);
						
			input1.nextLine();// Clear the buffer
			
			ArrayList<MutualFundAccount> a1 = new ArrayList<>();

			for (int i = 0; i < numFunds; i++) {
				ticker = input1.next();
					//System.out.println("Ticker: " + ticker);
				numShares = input1.nextInt();
					//System.out.println("NumShares: " + numShares);
				MutualFund mf2 = new MutualFund(ticker);
				String fundName = funds.get(mf2).getFundName();
				double pricePerShare = funds.get(mf2).getPricePerShare();
				double tradingFee = funds.get(mf2).getTradingFee();

				MutualFund mf3 = new MutualFund(fundName, ticker, pricePerShare, tradingFee);
				
				MutualFundAccount mfa1 = new MutualFundAccount(numShares, mf3);
						
				a1.add(mfa1);
				
				
				if (!input1.hasNextLine()) {
					break;
				}
				input1.nextLine();// Clear the buffer
			} 
			
			Customer c1 = new Customer(first, last, email, password, cash, a1);
			
			customers.put(c1);
			
			//System.out.println();
		}
		
		//customers.printTable();
		
		input1.close();
		input2.close();
		
		System.out.print("Welcome to Mutual Fund InvestorTrack (TM)!\n\n");
		
		System.out.print("Please enter your email address: ");
		
		email = input3.nextLine();
		
		
		System.out.print("Please enter your password: ");
		
		password = input3.nextLine();
		
		Customer c2 = new Customer(email, password);

		if(customers.contains(c2)) {
			System.out.println("\nWelcome, " + customers.get(c2).getFirstName() + " " + customers.get(c2).getLastName() + "!\n");
		}
		else {
			System.out.println("\nWe don't have your account on file... \n\nLet's create an account for you!");
			System.out.print("Enter your first name: ");
			first = input3.nextLine();
			System.out.print("Enter your last name: ");
			last = input3.nextLine();
			Customer c3 = new Customer (first, last, email, password);
			customers.put(c3);
			System.out.println("\nWelcome, " + first + " " + last + "!\n");
		}
		
		boolean entry = true;
		printMenu();
		while (entry) {
			System.out.print("Enter your choice: ");
			
			choice = input3.next();

			while (!(choice.equalsIgnoreCase("X"))) {
				
				if (choice.equalsIgnoreCase("A")) {
					System.out.println("\nPlease select from the options below: ");
					System.out.println(funds);
					
					System.out.print("Enter the ticker of the fund to purchase: ");
					ticker = input3.next();
					
					System.out.print("\nEnter the number of shares to purchase: ");
					numShares = input3.nextInt();
					
					input3.nextLine();
					
					MutualFund mf3 = new MutualFund(ticker);
					customers.get(c2).addFund(numShares, funds.get(mf3));
					
					System.out.print("\nYou successfully added shares of the following fund: \n" + funds.get(mf3));
					
					System.out.print("Number of shares added: " + numShares + "\n");
				}
				
				if(choice.equalsIgnoreCase("B")) {
					if(customers.get(c2).hasOpenAccounts()) {
						System.out.println("\nYou own the following mutual funds: ");
						customers.get(c2).printAccountsByName();
						
						//input3.nextLine();
						
						System.out.print("\nEnter the name of the fund to sell: ");
						mutualName = input3.nextLine();

						String fundName = "";
						
						if(customers.get(c2).getAccountByName(mutualName) != null) {
							fundName = customers.get(c2).getAccountByName(mutualName).getMf().getFundName();
							if(fundName.equals(mutualName)) {
								System.out.print("\nEnter the number of shares to sell or \"all\" to sell everything: ");
								//input3.nextLine();
								choice2 = input3.nextLine();
								if (choice2.equalsIgnoreCase("all")) {
									customers.get(c2).sellFund(mutualName);
								}
								else {
									numShares = Integer.parseInt(choice);
									customers.get(c2).sellShares(mutualName, numShares);
								}
								System.out.println("You own the following funds: ");
								customers.get(c2).printAccountsByName();
								System.out.println("\nYour current cash balance is $" + df.format(customers.get(c2).getCash()) + "\n");
							}
						}
						else {
							System.out.println("\nSorry you don't own an account by that name.");
						}
					}
					else {
						System.out.println("\nYou don't have any funds to sell at this time.");
					}
				}
				
				if(choice.equalsIgnoreCase("C")) {
					System.out.println("\nYour current cash balance is $" + df.format(customers.get(c2).getCash()));
					
					System.out.print("\nEnter the amount of cash to add: $");
					cash = input3.nextDouble();
					input3.nextLine();
					
					customers.get(c2).updateCash(cash);
					System.out.println("\nYour current cash balance is $" + df.format(customers.get(c2).getCash()));
					System.out.println();
				}
				
				if(choice.equalsIgnoreCase("D")) {
					if(customers.get(c2).hasOpenAccounts()) {
						System.out.print("\nView Your Mutual Funds By: \n\n1. Name\n2. Value\n\nEnter your choice (1 or 2): ");
						
						selection = input3.nextInt();
						input3.nextLine();

						if (selection == 1) {
							//System.out.println();
							customers.get(c2).printAccountsByName();
							System.out.println();
						} else if (selection == 2) {
							//System.out.println();
							customers.get(c2).printAccountsByValue();
							System.out.println();
						} else {
							System.out.println("\nInvalid Choice!");
						}
					}
					else {
						System.out.println("\nYou don't have any funds to display at this time.");
					}
				}
				
				//input3.nextLine();
				
				//System.out.println(choice);
				
				if (!(choice.equalsIgnoreCase("a")) && !(choice.equalsIgnoreCase("b")) && !(choice.equalsIgnoreCase("c")) && !(choice.equalsIgnoreCase("d")) && !(choice.equalsIgnoreCase("x"))) {
					System.out.println("\nInvalid menu option. Please enter A-D or X to exit.");
				}

				printMenu();
				System.out.print("Enter your choice: ");
				choice = input3.nextLine();
				
		}
			
			entry = false;
			}
		
		}
	
     catch (FileNotFoundException e) {
		System.out.println(e.getMessage()); 
	}
	
	System.out.println("\nGoodbye!");

}

	public static void printMenu() {
		System.out.println(
				"\nPlease select from the following options: \n\nA. Purchase a Fund\nB. Sell a Fund\nC. Add Cash\nD. Display Your Current Funds\nX. Exit\n");
	}
}










