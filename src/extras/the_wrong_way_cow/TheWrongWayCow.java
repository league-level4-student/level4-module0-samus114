//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

public class TheWrongWayCow {

	public static int[] findWrongWayCow(final char[][] field) {
		int[] xAndY1 = null;
		// Fill in the code to return the x,y coordinate position of the
		// head (letter 'c') of the wrong way cow!
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(field[i][j] == 'w') {
					if (field[j].length != i || field[i + 1][j] != 'o') {		
						xAndY1 = new int[] { i, j};
					}
				}
			}
		}
		return xAndY1;
	}
}
/*for (int j2 = 1; j2 < 4; j2++) {
if (field[i][j + j2] != 'c') {

} else {
	xAndY1 = new int[] { j, i};
	return xAndY1;
}
}
*/