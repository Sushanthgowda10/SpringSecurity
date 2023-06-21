package stringNonRepeated;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {

		System.out.println("  Please  enter  the  input  string  :");
		Scanner in = new Scanner(System.in); // read from System input
		String input = in.nextLine();
		System.out.println(input);
		Character firstNonRepeatedChar = logic(input);
		System.out.println("The  first  non  repeated  character  is  :    " + firstNonRepeatedChar);
		in.close();
	}

	private static Character logic(String input) {
		Character result = input.chars() // string stream
				.mapToObj(i -> Character.toLowerCase(Character.valueOf((char) i))) // convert to lowercase & then to
																					// Character object
				.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // store in a map with the count
																												
				.entrySet().stream() // entryset stream
				.filter(entry -> entry.getValue() == 1L).map(entry -> entry.getKey()).findFirst().get();

		return result;
	}
}

//chars(): Converts the input string to an IntStream of characters.

//mapToObj(): Maps each integer value (representing a character) to a Character object in lowercase.

//collect(): Collects the stream into a LinkedHashMap, grouping the characters by their counts.

//entrySet().stream(): Converts the entrySet of the map to a stream of Map.Entry objects.

//filter(): Filters the entries by checking if the count of the character is equal to 1.

//map(): Maps the filtered entries to get the key (Character) of each entry.

//findFirst().get(): Returns the first non-repeated character as a Character object.






