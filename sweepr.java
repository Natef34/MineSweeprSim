import java.util.*;

public class sweepr {
	public static void main (String args[]) {
		
		int drawX=0, drawY=0; //loop controls
		int numMines; //loop control for number of mines
		int mx, my; //mine x and y values
		int gridSizeX,gridSizeY; //grid size variables
		int givenGridSize;
		boolean playing = true;
		String replay; 
		
		Random rand = new Random(); //initializes random numbers for mines
		Scanner input = new Scanner(System.in); //scanner for user input
		
		while (playing == true) {
			while (true) {
				//gets user input on grid size
				System.out.printf ("What size would you like the mine field to be?\nEnter an integer greater than 2: \n");
				givenGridSize = input.nextInt();
				if (givenGridSize > 2) {
					break;
				}
				else{
					System.out.printf("Invalid. Must be greater than 2.\n");
				}
			}
			
			gridSizeX = givenGridSize;
			gridSizeY = givenGridSize; //sets x & y values equal to given
				
			//assigns grid size to the 2d array according to user input
			String grid[][] = new String[gridSizeX][gridSizeY]; //2D array
			
			//assigns value of "0" to each part of the grid for "blank" grid
			for (drawX=0; drawX<gridSizeX; drawX++) {
				for (drawY=0; drawY<gridSizeY; drawY++) {
					grid[drawX][drawY] = ".";
				}
			}
			System.out.printf ("Printing blank board...\n");
			print (drawY,gridSizeY,drawX,gridSizeX,grid); //method that prints grid

			//These generate random x and y values for 5 mines
			for (numMines=0; numMines<5; numMines++) {
				mx = random (gridSizeX, rand);
				my = random (gridSizeY, rand);
				if (grid[mx][my] == "M") { //reduces loop control by 1 if there is a duplicate
					numMines--;
				}
				grid[mx][my] = calcAdjacent(grid,mx,my,gridSizeY,gridSizeX);
			}
			
			System.out.printf ("Printing mines and number indicators...\n");				
			print (drawY,gridSizeY,drawX,gridSizeX,grid); //method that prints grid
			while (true) {
				System.out.printf ("Would you like to run another simulation? y/n\n");
				replay = input.next();
				if (replay.equals("n")) {
					playing = false;
					break;
				}
				else if (replay.equals("y")) {
					playing = true;
					break;
				}
				else
				{
					System.out.printf("Invalid answer. Please enter y or n.\n");
				}
			}
		}
	}
		
	public static int random (int gridSize, Random rand) {
		//Generates random value for x and y coordinates
		int randomValue=0;
		randomValue = rand.nextInt ((gridSize-1) + 1) + 0; //generates random value
		return randomValue;
	}
	public static String calcAdjacent (String[][] grid, int mx, int my, int gridSizeY, int gridSizeX) {
		if (grid[mx][my] != "M") {
				grid[mx][my] = "M";
				
				//These if statements generate adjacent numbers if the adjacent space isn't a mine
				if (mx != 0) { //builds adjacents on x-axis to the left
					if (grid[mx-1][my] == "3" && grid[mx-1][my] != "M") {
						grid[mx-1][my] = "4";
					}
					if (grid[mx-1][my] == "2" && grid[mx-1][my] != "M") {
						grid[mx-1][my] = "3";
					}
					if (grid[mx-1][my] == "1") {
						grid[mx-1][my] = "2";
					}else if (grid[mx-1][my] == ".") {
						grid[mx-1][my] = "1";
						}	
				}
				if (mx != gridSizeX-1) { //builds adjacents on x-axis to the right
					if (grid[mx+1][my] == "3") {
						grid[mx+1][my] = "4";
					}
					if (grid[mx+1][my] == "2") {
						grid[mx+1][my] = "3";
					}
					if (grid[mx+1][my] == "1") {
						grid[mx+1][my] = "2";
					} else if (grid[mx+1][my] == ".") {
						grid[mx+1][my] = "1";
						}
				}
				if (my != 0) { //builds adjacents on the y-axis upward
					if (grid[mx][my-1] == "3") {
						grid[mx][my-1] = "4";
					}
					if (grid[mx][my-1] == "2") {
						grid[mx][my-1] = "3";
					}
					if (grid[mx][my-1] == "1") {
						grid[mx][my-1] = "2";
					}else if (grid[mx][my-1] == ".") {
						grid[mx][my-1] = "1";
						}	
				}
				if (my != gridSizeY-1) { //builds adjacents on the y-axis downward
					if (grid[mx][my+1] == "3") {
						grid[mx][my+1] = "4";
					}
					if (grid[mx][my+1] == "2") {
						grid[mx][my+1] = "3";
					}
					if (grid[mx][my+1] == "1") {
						grid[mx][my+1] = "2";
					} else if (grid[mx][my+1] == ".") {
						grid[mx][my+1] = "1";
						}
				}
		}
		return grid[mx][my];//returns assigned grid values
	}	
	public static void print (int drawY, int gridSizeY,int drawX,int gridSizeX,String[][] grid) {
		//prints grid
		for (drawY=0; drawY<gridSizeY; drawY++) {
			for (drawX=0; drawX<gridSizeX; drawX++) {
				System.out.printf ("%s", grid[drawX][drawY]);
			}
			System.out.printf ("\n");
		}
		
	}
}