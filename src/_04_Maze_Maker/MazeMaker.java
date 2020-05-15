package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);
		// 4. select a random cell to start
		int randX = randGen.nextInt(width);
		int randY = randGen.nextInt(height);
		boolean bool = randGen.nextBoolean();
		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cell[randX][randY]);
		if (bool) {
			int randomY = randGen.nextInt(maze.cell[0].length);
			maze.cell[randomY][0].setWestWall(false);
			randomY = randGen.nextInt(maze.cell.length);
			maze.cell[maze.cell[0].length - 1][randomY].setEastWall(false);
		} else {
			int randomX = randGen.nextInt(maze.cell[0].length);
			maze.cell[randomX][0].setNorthWall(false);
			randomX = randGen.nextInt(maze.cell.length);
			maze.cell[randomX][maze.cell[0].length - 1].setSouthWall(false);
		}
		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. Get an ArrayList of unvisited neighbors using the current cell and the
		// method below
		ArrayList<Cell> unvisitedN = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (unvisitedN.size() > 0) {
			// C1. select one at random. AND C2. push it to the stack
			Cell cell = unvisitedN.get(randGen.nextInt(unvisitedN.size()));
			uncheckedCells.push(cell);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, cell);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = cell;
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
			// D. if all neighbors are visited
		} else {
			// D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				Cell cell = uncheckedCells.pop();
				// D1b. make that the current cell
				currentCell = cell;
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() + 1 == c2.getX()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		} else if (c1.getX() - 1 == c2.getX()) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		} else if (c1.getY() + 1 == c2.getY()) {
			c2.setNorthWall(false);
			c1.setSouthWall(false);
		} else if (c1.getY() - 1 == c2.getY()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisitedCells = new ArrayList<Cell>();
		int x = c.getX();
		int y = c.getY();
		if (x != maze.cell.length - 1 && maze.getCell(x + 1, y).hasBeenVisited() == false) {
			unvisitedCells.add(maze.getCell(x + 1, y));
		}
		if (y != 0 && maze.getCell(x, y - 1).hasBeenVisited() == false) {
			unvisitedCells.add(maze.getCell(x, y - 1));
		}

		if (x != 0 &&maze.getCell(x - 1, y).hasBeenVisited() == false) {
			unvisitedCells.add(maze.getCell(x - 1, y));
		}
		if (y != maze.cell.length - 1 && maze.getCell(x, y + 1).hasBeenVisited() == false) {
			unvisitedCells.add(maze.getCell(x, y + 1));
		}
		return unvisitedCells;
	}
}
