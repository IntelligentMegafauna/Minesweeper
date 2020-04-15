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

	private static void GenerateOutput() {
        OutputField += "\nField: " + FieldNumber + "\n";
        
        for(int a = 0; a < Field.length; a++) {
            for(int b = 0; b < Field[0].length; b++) {
                OutputField += Field[a][b];
            }
            
            OutputField += "\n";
        }
	}

	private static void CalculateField() {
        for(int a = 0; a < Field.length; a++) {
            for(int b = 0; b < Field[0].length; b++) {
                if(Field[a][b] == '.') {
                    Field[a][b] = CalculateAdjacentMineCount(a, b); 
                }
            }
        }
	}

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
