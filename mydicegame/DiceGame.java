// ANTHONY MASSAAD
// 101150282
package mydicegame; 
import java.util.Scanner; 
import java.util.*;

/**
 * DiceGame Class
*/
public class DiceGame {
	private Die die; 
	private int playerMoney;
	private int CPUMoney; 
	private int numberOfSides;
	private int numberOfDice; 
	
	/**
	 * Default constructor for dicegame
	*/
    public DiceGame(){
		this(6,2);
	}
	
	/**
	 * Constructor for DiceGame
	 * @param numberOfSides Integer, the number of sides
	 * @param numberOfDice Integer, the number of dice in the game
	*/
	public DiceGame(int numberOfSides, int numberOfDice){
		this.die = new Die(numberOfSides);
		this.numberOfDice = numberOfDice;
		this.numberOfSides = numberOfSides;
		this.playerMoney = 100;
		this.CPUMoney = 100;
	}
	
	/**
	 * the play method. This is where the game will run and do all its displaying of text
	*/
	public void play(){
		int amount; 
		int diceRollingValue;
		int playerSum = 0; 
		int CPUSum = 0; 
		System.out.println("Welcome to the Game of Dice");
		System.out.println("Starting a game with " + this.numberOfDice + " " + this.numberOfSides + "-sided dice");
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("---------------------------------");
			System.out.println("You have $" + this.playerMoney);
			System.out.println("The computer has $" + this.CPUMoney);
			while (true){
				try{
					System.out.print("Place your bet (-1 to quit playing) >>> ");
					amount = sc.nextInt();
					break;
				}
				catch (InputMismatchException e){
					System.out.println("**Invalid input**");
					sc.next();
				}	
			}
			if (amount == -1){
				System.out.println("Tahnk you for playing, now terminating program ");
				System.exit(1);
				
			}
			else if (amount < 0){
				System.out.println("Cannot enter negative amount");
			}
			else if (amount > this.playerMoney){
				System.out.println("You don't have enough money, please input something less than your remaining money");
				
			}
			else if (amount <= this.playerMoney){
				System.out.print("" + this.numberOfDice + " Dice :: You rolled > {");
				diceRollingValue = this.die.roll();
				playerSum += diceRollingValue;
				System.out.print(diceRollingValue);
				for (int i=0; i<this.numberOfDice-1; i++){
					diceRollingValue = this.die.roll();
					System.out.print(", " + diceRollingValue);
					playerSum += diceRollingValue;
				}
				System.out.println("}");
				System.out.print("" + this.numberOfDice + " Dice :: CPU rolled > {");
				diceRollingValue = this.die.roll();
				CPUSum += diceRollingValue;
				System.out.print(diceRollingValue);
				for (int i=0; i<this.numberOfDice-1; i++){
					diceRollingValue = this.die.roll();
					System.out.print(", " + diceRollingValue);
					CPUSum += diceRollingValue;
				}
				System.out.println("}");
				
				if (playerSum == CPUSum){
					System.out.println("It's a draw");
				}
				else if (playerSum < CPUSum){
					this.CPUMoney += amount; 
					this.playerMoney -= amount;
					if (this.playerMoney <= 0){
						System.out.println("Oop, you're out of money! CPU Wins! Thank you for playing!");
						System.exit(1);
					}
					System.out.println("you lost this round!");
				}
				else if (playerSum > CPUSum){
					this.CPUMoney -= amount; 
					this.playerMoney += amount;
					if (this.CPUMoney <= 0){
						System.out.println("Looks like the CPU is out of money! You Win! Thank you for playing!");
						System.exit(1);
					}
					System.out.println("you win this round!");
				}
			}
			playerSum = 0;
			CPUSum = 0;
		}
	}
	
	/**
	 * Main method that runs the DiceGame class and runs the method Play if applicable
	 * Checks the args length, ensuring that it meets requirement for the game to start
	*/
    public static void main(String[] args) {
		if (args.length > 0){
			if (args.length == 2){	
				try{
					if (Integer.parseInt(args[0]) >= 2 && Integer.parseInt(args[1]) >= 1){
						DiceGame dg = new DiceGame(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
						dg.play();
					}
				}
				catch(NumberFormatException e){
					System.out.println("Parameters must be an integers, number of sides must be >= 2 and number of dice must be >= 1");
					System.out.println("Terminating program");
					System.exit(1);
				}
			}
			else{
				System.out.println("You either have too many or too few parameters for the game to run. Max parameters is 2 and Minimum is 0");
				System.exit(1);
			}
		}
		else{
			DiceGame dg = new DiceGame();
			dg.play();
		}
    }
}
