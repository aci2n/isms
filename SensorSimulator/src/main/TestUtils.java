package main;

import java.util.Random;
import java.util.UUID;

public class TestUtils {

	public static final Random random = new Random();
	public static final Integer upperLimit = 100;
	
	public static Float randomFloat() {
		return random.nextFloat() * upperLimit;
	}
	
	public static Integer randomInt() {
		return random.nextInt() * upperLimit;
	}
	
	public static String randomString() {
		return UUID.randomUUID().toString();
	}
}
