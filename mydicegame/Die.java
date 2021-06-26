// ANTHONY MASSAAD
// 101150282
package mydicegame; 
import java.util.Random;

/**
 * Die class :) 
*/
public class Die {
	private int numberOfSides;
	private Random rn;
	
	/**
	 * default constructor for Die :) 
	*/
	public Die(){
		this(6);
	}
	
	/**
	 * Constructor for Die :) 
	 * @param numberOfSides Integer, the number of sides the Die has
	*/
	public Die(int numberOfSides){
		this.numberOfSides = numberOfSides;
		this.rn = new Random();
	}
	
	/**
	 * roll method to roll the die and give a value in range from 1 to numberOfSides
	 * @return Integer, the value the Die rolls on
	*/
	public int roll(){
		return rn.nextInt(this.numberOfSides) + 1; 
	}
	
}
