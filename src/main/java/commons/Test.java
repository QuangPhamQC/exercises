package commons;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
	int firstName;

	public static void main(String[] args) {
		int[] student = { 10, 20 };
		List<String> list = new ArrayList<String>(10);
		list.add("01");
		list.add("02");
		System.out.println(student.length + " - " + list.size());

		String a = null;
		String b = "";
		if (a == "") {
			System.out.println("aaaa");
		}
		
		if(b=="") {
			System.out.println("bbb");
		}

	}

	public static void total() {

	}

	private static String getDynamicXpathLocator(String dynamicXpathLocator, String... dynamicValues) {
		return String.format(dynamicXpathLocator, (Object[]) dynamicValues);
	}

}
