package simulator;

import java.util.Random;
import java.util.UUID;

public class Utils {

	public static final Random random = new Random();
	public static final int upperLimit = 100;
	
	public static float randomFloat() {
		return random.nextFloat() * upperLimit;
	}
	
	public static int randomInt() {
		return random.nextInt() * upperLimit;
	}
	
	public static String randomString() {
		return UUID.randomUUID().toString();
	}
}
