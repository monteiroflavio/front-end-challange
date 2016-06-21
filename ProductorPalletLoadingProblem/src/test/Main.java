package test;

import java.util.Scanner;

import classes.Pallet;

public class Main {
	private static Scanner reader;
	public static void main(String[] args) {
		reader = new Scanner(System.in);
		System.out.println("Enter truck's pallet length: ");
		int length = reader.nextInt(); // Scans the next token of the input as an int.
		System.out.println("Enter truck's pallet width: ");
		int width = reader.nextInt(); // Scans the next token of the input as an int.
		System.out.println("Enter boxes' length: ");
		int boxLength = reader.nextInt(); // Scans the next token of the input as an int.
		System.out.println("Enter boxes' width: ");
		int boxWidth = reader.nextInt(); // Scans the next token of the input as an int.
		Pallet pallet = new Pallet(length, width, boxLength, boxWidth);
		System.out.println("Optimal solution: "+pallet.optimalSolution());
		System.out.println("Found solution: "+pallet.foundSolution());
		System.out.println("Box placements: \n");
		System.out.println(pallet.getBoxesPlacement());
	}
}
