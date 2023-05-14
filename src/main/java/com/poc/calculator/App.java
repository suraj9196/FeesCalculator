package com.poc.calculator;

import java.util.Scanner;

import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
		// JSON entity as a string
		String jsonStr = "{\"Exam Fee\":{\"INDIAN\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":400}}},\"FOREIGN\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":100}}},\"NRI\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":600}}},\"SAARC\":{\"ALL_COURSES\":{\"ALL_LEVEL\":{\"amount\":600}}}},\"Application Fee\":{\"INDIAN\":{\"ALL_COURSES\":{\"UG\":{\"amount\":200},\"UG-DIPLOMA\":{\"amount\":300},\"PG\":{\"amount\":500}}},\"FOREIGN\":{\"ALL_COURSES\":{\"UG\":{\"amount\":400},\"UG-DIPLOMA\":{\"amount\":400},\"PG\":{\"amount\":700}}}}}}";

		JSONObject jsonObj = new JSONObject(jsonStr);
		

		Scanner scanner = new Scanner(System.in);
		System.out.println("Select a fee (Exam Fee or Application Fee):");
		String feeType = scanner.nextLine();
		System.out.println("Select a nationality (INDIAN, FOREIGN, NRI, SAARC):");
		String nationality = scanner.nextLine().toUpperCase();
		System.out.println("Select a ALL_COURSES");
		String course = scanner.nextLine();
		System.out.println("Select a LEVEL");
		String level = scanner.nextLine().toUpperCase();

		jsonObj=jsonObj.getJSONObject(feeType).getJSONObject(nationality).getJSONObject("ALL_COURSES");
		jsonObj=feeType.equals("Exam Fee")?jsonObj.getJSONObject("ALL_LEVEL"):jsonObj.getJSONObject(getLevelKey(level));
		
		int feeAmount=jsonObj.getInt("amount");
//		 Display the fee amount
		System.out.println("The fee amount for " + feeType + " - " + nationality + " - " + course + " - " + level+ " is: " + feeAmount);
	
    }

	private static String getLevelKey(String level) {
		// If level is ALL_LEVEL, substitute with one of the four options
		if (level.equals("ALL_LEVEL")) {
			System.out.println("Select a level from the following options:");
			System.out.println("1. UG");
			System.out.println("2. PG");
			System.out.println("3. DIPLOMA");
			System.out.println("4. Ph.D");
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				return "UG";
			case 2:
				return "PG";
			case 3:
				return "DIPLOMA";
			case 4:
				return "Ph.D";
			default:
				System.out.println("Invalid option selected. Defaulting to UG.");
				return "UG";

			}

		} else {
			return level;
		}

	}
}
