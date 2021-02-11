package l12bt;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Dungeon {
	String[][] dungeon;
	int numberOfMoves;
	int numberOfVampires;
	boolean vampiresMove;
	
	
	public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
		this.numberOfVampires = vampires;
		this.numberOfMoves = moves;
		this.vampiresMove = vampiresMove;
		dungeon = new String[length][height];
		ArrayList<Integer> m = setUpVampires();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				if(i == 0 && j == 0) {
					dungeon[i][j] = "@";
				}else if(isThereAVampireHere(i, j, m)) {
					dungeon[i][j] = "v";
				}else {
				dungeon[i][j] = ".";
				}
			}
		}
		
	}
	
	private boolean isThereAVampireHere(int x, int y, ArrayList<Integer> m) {
		for(int i = 0; i < m.size(); i+=2) {
			if(m.get(i) == x && m.get(i+1) == y) return true;
		}
		return false;
	}
	
	public void run() {
		Scanner reader= new Scanner(System.in);
		while(this.numberOfMoves > 0) {
			System.out.println(this.numberOfMoves);
			for(String[] row : dungeon) {
				for(String element : row) {
					System.out.print(element);
				}
				System.out.println();
			}
			String movements = reader.nextLine();
			for(int i = 0; i < movements.length(); i++) {
				movePlayer(movements.charAt(i));
				if(this.numberOfVampires == 0) {
					for(String[] row : dungeon) {
						for(String element : row) {
							System.out.print(element);
						}
						System.out.println();
					}
					System.out.println("YOU WIN");
					return;
				}
				if(this.vampiresMove) moveVampires();
			}
			
		}
		System.out.println("YOU LOSE");
		
	}
	
	private ArrayList<Integer> setUpVampires(){
		ArrayList<Integer> m = new ArrayList<Integer>();
		for(int i = 0; i < this.numberOfVampires; i++) {
			Random random = new Random();
			int randomInt = random.nextInt(dungeon.length);
			m.add(randomInt);
			randomInt = random.nextInt(dungeon[0].length);
			m.add(randomInt);
		}
		return m;
	}
	
	private void moveVampires() {
		Random random = new Random();
		for(int i = 0; i < dungeon.length; i++) {
			for(int j = 0; j < dungeon[0].length; j++) {
				if(dungeon[i][j].equals("v")) {
					int randomInt = random.nextInt(4);
					
					switch(randomInt) {
					case 0:
						if(j != dungeon[0].length - 1) {
							dungeon[i][j] = ".";
							if(dungeon[i][j+1].equals("@")) {
								this.numberOfVampires--;
							}else if(!dungeon[i][j+1].equals("v")) {
								dungeon[i][j+1] = "v";
							}
						}
						break;
					case 1:
						if(j != 0) {
							dungeon[i][j] = ".";
							if(dungeon[i][j-1].equals("@")) {
								this.numberOfVampires--;
							}else if(!dungeon[i][j-1].equals("v")) {
								dungeon[i][j-1] = "v";
							}
						}
						break;
					case 2:
						if(i != dungeon.length - 1) {
							dungeon[i][j] = ".";
							if(dungeon[i+1][j].equals("@")) {
								this.numberOfVampires--;
							}else if(!dungeon[i+1][j].equals("v")) {
								dungeon[i+1][j] = "v";
							}
						}
						break;
					case 3:
						if(i != 0) {
							dungeon[i][j] = ".";
							if(dungeon[i-1][j].equals("@")) {
								this.numberOfVampires--;
							}else if(!dungeon[i-1][j].equals("v")) {
								dungeon[i-1][j] = "v";
							}
						}
						break;
						
					}
						
				}
			}
		}
		
	}

	
	private void movePlayer(char move) {
		int x = position().get(0);
		int y = position().get(1);
		switch(move) {
			case 'w':
				if(x != 0) {
				dungeon[x][y] = ".";
				if(dungeon[x-1][y].equals("v")) {
					this.numberOfVampires--;
				}
				dungeon[x-1][y] = "@";
				}
				break;
			case 's':
				if(x != dungeon.length - 1) {
				dungeon[x][y] = ".";
				if(dungeon[x+1][y].equals("v")) {
					this.numberOfVampires--;
				}
				dungeon[x+1][y] = "@";
				}
				break;
			case 'a':
				if(y != 0) {
				dungeon[x][y] = ".";
				if(dungeon[x][y-1].equals("v")) {
					this.numberOfVampires--;
				}
				dungeon[x][y-1] = "@";
				}
				break;
			case 'd':
				if(y != dungeon[0].length) {
				dungeon[x][y] = ".";
				if(dungeon[x][y+1].equals("v")) {
					this.numberOfVampires--;
				}
				dungeon[x][y+1] = "@";
				}
				break;
		}
		this.numberOfMoves--;
	}
	
	private ArrayList<Integer> position(){
		ArrayList<Integer> position = new ArrayList<Integer>();
		for(int i = 0; i < dungeon.length; i++) {
			for(int j = 0; j < dungeon[0].length; j++) {
				if(dungeon[i][j] == "@") {
					position.add(i);
					position.add(j);
					return position;
				}
			}
		}
		return null;
	}

}
