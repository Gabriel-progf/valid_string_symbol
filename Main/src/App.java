import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Type a text: ");
        String text = sc.nextLine();
        sc.close();

        String symbols = filterSymbol(text);

        boolean result = isValidResult(symbols);

        if (result) {
            System.out.println("The text you typed is correct!");
        } else {
            System.out.println("The text you typed not is correct. Try again.");
        }

    }

    public static String filterSymbol(String text) {

        String[] symbolList = { "[", "]", "(", ")", "{", "}", "\'", "\"" };

        String filteredText = "";

        for (int i = 0; i < symbolList.length; i++) {
            String character = Character.toString(text.charAt(i));

            if (Arrays.asList(symbolList).contains(character)) {
                filteredText += character;
            }
        }

        return filteredText;

    }

    public static boolean isValidResult(String symbols) {

        Map<String, String> dictSymbols = new HashMap<>();
        dictSymbols.put("(", ")");
        dictSymbols.put("{", "}");
        dictSymbols.put("[", "]");
        dictSymbols.put("\"", "\"");
        dictSymbols.put("\'", "\'");

        Stack<String> stackSymbols = new Stack<>();
        Set<String> openSymbols = dictSymbols.keySet();

        for (int i = 0; i < symbols.length(); i++) {
            String character = Character.toString(symbols.charAt(i));

            if (openSymbols.contains(character)) {
                stackSymbols.push(character);
            } else if (dictSymbols.containsValue(character)) {

                if (stackSymbols.empty() || dictSymbols.get(stackSymbols.pop()) != character) {
                    return false;
                }

            }
        }

        return stackSymbols.empty();

    }
}
