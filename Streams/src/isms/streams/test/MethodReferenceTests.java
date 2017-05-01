package isms.streams.test;

import java.util.Locale;
import java.util.UUID;
import java.util.function.Supplier;

public class MethodReferenceTests {
	
	private interface StringOperation {
		public String run(String s);
	}
	
	private interface NoArgOperation {
		public String run();
	}
	
	private interface StringAndLocaleOperation {
		public String run(String s, Locale l);
	}
	
	public class StringOperatorOne implements StringOperation {
		public String run(String s) {
			return s.toUpperCase();
		}
	}

	public static void main(String[] args) {
		String s = "test1";
		
		System.out.println(doStringOperation(s, String::toUpperCase));
		System.out.println(doStringOperation(s, MethodReferenceTests::toUpperCase));
		System.out.println(doNoArgOperation(s::toUpperCase));
		System.out.println(doNoArgOperation(MethodReferenceTests::randomString));
		System.out.println(doStringAndLocaleOperation(s, Locale.CANADA, String::toUpperCase));
		System.out.println(new MethodReferenceTests().constructorMethodReference(s));
	}
	
	private String constructorMethodReference(String s) {
		return doStringOperation(s, StringOperatorOne::new);		
	}
	
	private static String doStringOperation(String s, Supplier<StringOperation> opFactory) {
		return opFactory.get().run(s);
	}
	
	private static String toUpperCase(String s) {
		return s.toUpperCase();
	}
	
	private static String randomString() {
		return UUID.randomUUID().toString();
	}
	
	private static String doStringOperation(String s, StringOperation op) {
		return op.run(s);
	}
	
	private static String doNoArgOperation(NoArgOperation op) {
		return op.run();
	}
	
	private static String doStringAndLocaleOperation(String s, Locale l, StringAndLocaleOperation op) {
		return op.run(s, l);
	}

}
