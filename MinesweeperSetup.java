/* 
 * CSCD 350 - Assignment 1, Team Coding Exercise (Minesweeper)
 * 
 * Individual Solution
 * Author: C. William Bafus
 * 
 * Team Members
 * C. William Bafus
 * Austin Hall
 * Steve Zuelke
 * 
 * Revision Number: 1.0
 * Revision Author: N/A
 * 
 * Description: Minesweeper 
 * 
 */

package Minesweeper;

import java.util.Scanner;

public class MinesweeperSetup 
{
	static char[][] Field;
	static int FieldNumber = 0;
	
	static String OutputField = "";
	
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
		int rows = -999;
		int columns = -999;
		
        String inputLine;
        String dimensionLineInput = "";
                
        while(!(rows == 0 && columns == 0)) {
            inputLine = scanner.nextLine();
            
            for(int a = 0; a < inputLine.length(); a++) {
                if (Character.isDigit(inputLine.charAt(a))) {
                	dimensionLineInput += inputLine.charAt(a);
                } else if(inputLine.charAt(a) == ' ') {
                    rows = Integer.parseInt(dimensionLineInput);
                    dimensionLineInput = "";
                }
                
                if(a == inputLine.length() - 1) {
                    columns = Integer.parseInt(dimensionLineInput);
                    dimensionLineInput = "";
                }
            }
            
            if (!(rows == 0 && columns == 0)) {
                FieldNumber++;

                Field = new char[rows][columns];
                for (int a = 0; a < rows; a++) {
                	inputLine = scanner.nextLine();
                	                	
                    for (int b = 0; b < columns; b++) {
                        Field[a][b] = inputLine.charAt(b);
                    }
                }
                
                CalculateField();
                GenerateOutput();
            } 
        }
        
        System.out.println(OutputField);
        scanner.close();
	}

	/*
	 * GenerateOutput()
	 * 
	 * Constructs an output stream based on the content of the Field[][] Array 
	 */
	
	private static void GenerateOutput() {
        OutputField += "\nField: " + FieldNumber + "\n";
        
        for(int a = 0; a < Field.length; a++) {
            for(int b = 0; b < Field[0].length; b++) {
                OutputField += Field[a][b];
            }
            
            OutputField += "\n";
        }
	}

	/*
	 * CalculateField()
	 * 
	 * Loops through the Field[][] Array, replacing any '.' characters with their appropriate value
	 * as determined by CalculateAdjacentMineCount(row, col)
	 */
	
	private static void CalculateField() {
        for(int a = 0; a < Field.length; a++) {
            for(int b = 0; b < Field[0].length; b++) {
                if(Field[a][b] == '.') {
                    Field[a][b] = CalculateAdjacentMineCount(a, b); 
                }
            }
        }
	}

	/*
	 * CalculateAdjacentMineCount()
	 * 
	 * Calculates passed in field's (as determined by it's row/col) value by looping through adjacent fields
	 * and totaling up the total number of mines encountered.
	 */
	
	private static char CalculateAdjacentMineCount(int row, int col) {
        int mineCount = 0;

        for(int a = row - 1; a <= row + 1; a++) {
            for(int b = col - 1; b <= col + 1; b++) {
                if(a >= 0 && b >= 0 && a < Field.length && b < Field[0].length) {
                    if (Field[a][b] == '*') {
                    	mineCount++;
                    }
                }
            }
        }
        
        return (char)(mineCount + '0');
	}
}
