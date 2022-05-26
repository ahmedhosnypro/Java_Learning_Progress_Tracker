import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String originalAlphabet = scanner.nextLine();
        String resultingAlphabet = scanner.nextLine();

        Map<Character, Character> symbolsEncode = new HashMap<>();
        Map<Character, Character> symbolsDecode = new HashMap<>();

        for (int i=0; i< originalAlphabet.length(); i++){
            symbolsEncode.put(originalAlphabet.charAt(i), resultingAlphabet.charAt(i));
            symbolsDecode.put(resultingAlphabet.charAt(i), originalAlphabet.charAt(i));
        }

        String toEncode = scanner.nextLine();
        String toDecode = scanner.nextLine();

        StringBuilder toEncodeResult = new StringBuilder();
        StringBuilder toDecodeResult = new StringBuilder();

        for (char c: toEncode.toCharArray()){
            toEncodeResult.append(symbolsEncode.get(c));
        }

        for (char c: toDecode.toCharArray()){
            toDecodeResult.append(symbolsDecode.get(c));
        }

        System.out.println(toEncodeResult);
        System.out.println(toDecodeResult);
    }
}