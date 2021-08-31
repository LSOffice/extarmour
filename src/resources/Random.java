package resources;

public class Random {

		public static int randInt(int firstNumber, int endNumber) {
			int number = (int) (Math.random() * (endNumber - firstNumber + 1)) + firstNumber;
			return number;
		}
}
