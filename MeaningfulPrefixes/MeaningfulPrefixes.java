import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class MeaningfulPrefixes{

  public static void main(String args[]) {

    final long startReadTime = System.currentTimeMillis();
    final Set<String> dictionary = readLinesFromFile("dictionary.txt");
    final long endReadTime = System.currentTimeMillis();
    System.out.println("Read dictionary in time (ms): " + (endReadTime - startReadTime));

    final long startProcessTime = System.currentTimeMillis();
    final String result = findLongestValidWordSubstring(dictionary, "");
    final long endProcessTime = System.currentTimeMillis();

    System.out.println("Result is: " + result);
    System.out.println("Time to find result (ms): " + (endProcessTime - startProcessTime));
  }


  private static String findLongestValidWordSubstring(final Set<String> dictionary, final String endString) {
    
    // Exit condition for Recursion. Exit the loop when the given string is not a valid word.
    if (!dictionary.contains(endString) && !"".equals(endString)) {
      return "";
    }

    String maxString = endString;

    for(char c='a'; c<='z'; c++) {
      // Try prefixing the given word with all possible characters, and call the method again.
      // This will return (after a recursion stack), the longest dictionary word that ends with given string.
      String largestSubstringWithPrefix = findLongestValidWordSubstring(dictionary, c + endString);

      // If there is some prefix that makes the word longer than the current length, consider it.
      if (largestSubstringWithPrefix.length() > maxString.length()) {
        maxString = largestSubstringWithPrefix;
      }
    }

    return maxString;
  }

  private static Set<String> readLinesFromFile(String fileName) {

    final Set<String> linesFromFile = new HashSet<>();

    try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {

      String line = fileReader.readLine();
      while(line != null) {

        linesFromFile.add(line);
        line = fileReader.readLine();
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      // Rethrow? Return empty set?
    }

    return linesFromFile;
  }
}
