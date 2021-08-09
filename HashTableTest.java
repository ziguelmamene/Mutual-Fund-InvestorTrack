
public class HashTableTest {

	public static void main(String[] args) {
		
		/*
		MutualFund m1 = new MutualFund("Alphabet", "GOOG", 1621);	//"Alphabet", "GOOG", 1621
		MutualFund m2 = new MutualFund("Amazon", "AMZN", 3036);		//"Apple", "AAPL", 108
		MutualFund m3 = new MutualFund("Apple", "AAPL", 108);		//"Amazon", "AMZN", 3036
		MutualFund m4 = new MutualFund("Boeing", "BA", 158);
		MutualFund m5 = new MutualFund("Caterpillar", "CAT", 161);
		MutualFund m6 = new MutualFund("Cisco", "CSCO", 38);
		MutualFund m7 = new MutualFund("Facebook", "FB", 261);
		
		MutualFund m8 = new MutualFund("AAPL", "Apple", 108);
		MutualFund m9 = new MutualFund("CSCO", "Cisco", 38);
		MutualFund m10 = new MutualFund("BA", "Boeing", 158);*/
		
		//HashTable<MutualFund> hf = new HashTable<>(10);
		
		//hf.put(m1);

		/*hf.printTable();
		System.out.println("TEST 2\n");
		System.out.println(hf);*/
		//System.out.println();
		//hf.printBucket(hf.hash(m1)); // HOW TO GET THE BUCKET(INDEX)???
		
		//System.out.println("Number of object at bucket 1 is: " + hf.countBucket(1));
		
		//System.out.println("\nThe value at key m1 is: " + hf.get(m1));
		
		//System.out.println("\nThe hash table contains m1: " + hf.contains(m1));
		/*
		hf.put(m2);
		hf.put(m3);
		hf.put(m4);
		hf.put(m5);
		hf.put(m6);
		hf.put(m7);
		hf.put(m8);
		hf.put(m9);
		hf.put(m10);
		
		//hf.printTable();
		
		hf.remove(m8);
		
		//System.out.println(hf);
		
		hf.clear();
		
		System.out.println("AOWIEHJR AOIEJR OISJER' PJ");
		
		System.out.println(hf);*/
		
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
		
		HashTable<MutualFund> hf = new HashTable<>(10);
		
		hf.put(m1);
		hf.put(m2);
		hf.put(m3);
		hf.put(m4);
		hf.put(m5);
		hf.put(m6);
		hf.put(m7);
		hf.put(m8);
		hf.put(m9);
		hf.put(m10);
		
		System.out.println(m3);
		System.out.println(m7);
				
		hf.printTable();

	}

}
