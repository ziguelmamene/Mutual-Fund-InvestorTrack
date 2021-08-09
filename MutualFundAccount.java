/**
* MutualFundAccount.java
* @author Zachary Iguelmamene
* @authoer Edgar Aguilar
* CIS 22C, Lab 5
*/

import java.text.DecimalFormat;
import java.util.Comparator;

public class MutualFundAccount {
   private double numShares;
   private MutualFund mf;
   private static int accountSeed = 100000000;


   /**CONSTRUCTORS*/

	/**
	 * One-argument constructor
	 * @param mf the mutual fund for this account
	 */
   public MutualFundAccount(MutualFund mf) {
      this.mf = mf;
   }

	/**
	 * Two-argument constructor
	 * @param numShares total shares of the mutual fund
	 * @param mf the mutual fund
	 */
   public MutualFundAccount(double numShares, MutualFund mf) {
      this.numShares = numShares;
      this.mf = mf;
   }

	/**
    * Two-argument constructor
    * @param mf the mutual fund
    * @param numShares total shares of the mutual fund
    */
   public MutualFundAccount(MutualFund mf, double numShares) {
	   this.numShares = numShares;
	   this.mf = mf;    
	}

   /**ACCESSORS*/

   /**
   * Accesses the total number of shares
   * @return the total shares
   */
   public double getNumShares() {
      return numShares;
   }

   /**
   * Accesses the mutual fund
   * @return the mutual fund
   */
   public MutualFund getMf() {
      return mf;
   }
   
	/**
	 * Increments the account seed and returns
	 * the new value
	 * @return the incremented account seed
	 */
	public static int getAccountSeed() {
		accountSeed++;
		return accountSeed;
	}

   /**MUTATORS*/

	/**
	 * Increases/Decreases the total shares
	 * by the given amount
	 * @param numShares the amount to increase
	 * or decrease
	 */
   public void updateShares(double numShares) {
      this.numShares += numShares;
   }

	/**
	 * Creates a String of the mutual fund
	 * information along with the following:
	 * 
	 * Total Shares: <numShares>
	 * Value: $<numShares>*<pricePerShare>
	 */
  @Override public String toString() {
     return mf + "\n" + "Total Shares: " + numShares + "\n" + "Value: $" + String.format("%.2f", numShares*mf.getPricePerShare()) + "\n";
   }
} //end class MutualFundAccount

class NameComparator implements Comparator<MutualFundAccount> {
    /**
   * Compares the two mutual fund accounts by name of the fund
   * uses the String compareTo method to make the comparison
   * @param account1 the first MutualFundAccount
   * @param account2 the second MutualFundAccount
   */
   @Override public int compare(MutualFundAccount account1, MutualFundAccount account2) {
      return account1.getMf().getFundName().compareTo(account2.getMf().getFundName());
   }
}  //end class NameComparator

class ValueComparator implements Comparator<MutualFundAccount> {
	   /**
	   * Compares the two mutual fund accounts by total value
	   * determines total value as number of shares times price
	   * per share
	   * uses the static Double compare method to make the
	   * comparison
	   * @param account1 the first MutualFundAccount
	   * @param account2 the second MutualFundAccount
	   */
   @Override public int compare(MutualFundAccount account1, MutualFundAccount account2) {
	   double value1 = account1.getMf().getPricePerShare()*account1.getNumShares();
	   double value2 = account2.getMf().getPricePerShare()*account2.getNumShares();
      return Double.compare(value1, value2);
   }
}