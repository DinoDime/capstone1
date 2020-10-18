import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean upperCase = false;
        boolean titleCase = false;
        Scanner JavaInput = new Scanner(System.in);
        System.out.println("Welcome to the Pig Latin Translator!");
        while (true) {
            System.out.println("Enter a line to be translated: ");
            String stuffToTranslate = JavaInput.nextLine();
            if (stuffToTranslate.equals("")) { // Reject lines with no input
                System.out.println("This is an invalid line.");
            }
            StringBuilder outputPigLatinWords = new StringBuilder();
            String[] splitWords = stuffToTranslate.split("\\s+");
            // check case and ensure it's maintained, check on a word-by-word basis
            for (String oneStr : splitWords) {
                upperCase = checkUpperCase(oneStr);
                titleCase = checkTitleCase(oneStr);
                if (upperCase) {
                    outputPigLatinWords.append(translateToPigLatin(oneStr).toUpperCase());
                }
                else if (titleCase) {
                    String tempString = translateToPigLatin(oneStr);
                    tempString = tempString.toLowerCase();
                    tempString = tempString.substring(0,1).toUpperCase() + tempString.substring(1).toLowerCase();
                    outputPigLatinWords.append(tempString);
                }
                else {
                    outputPigLatinWords.append(translateToPigLatin(oneStr));
                }
                outputPigLatinWords.append(" ");
            }
            System.out.println(outputPigLatinWords);
            System.out.println("Translate another line? (y/n):");
            String translateAnotherLine = JavaInput.nextLine();
            if (translateAnotherLine.equals("n")) { //break while loop if user enters n
                break;
            }
        }
    }
    // check if words are upper case
    public static boolean checkUpperCase(String word) {
        int upperCaseCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if(Character.isUpperCase(word.charAt(i))) {
                upperCaseCount++;
            }
            if (upperCaseCount == word.length()) {
                return true;
            }
        }
        return false;
    }
    //check if words are title case. If not upper case or title case, must be lower case
    public static boolean checkTitleCase(String word) {
        if(Character.isUpperCase(word.charAt(0))) {
            return true;
        }
        return false;
    }
    public static String translateToPigLatin(String word) {
        if (word.startsWith("a") || word.startsWith("e") || word.startsWith("i") || word.startsWith("o") || word.startsWith("u")) {
            word = word + "way";
        } else {
            String vowels = "aeiouAEIOU";
            for (int i = 0; i < word.length(); i++) {
                if (vowels.contains("" + word.charAt(i))) {
                    String prefix = word.substring(0, i);
                    String suffix = word.substring(i);
                    word = suffix + prefix + "ay";
                    break;
                }
            }
        }
        return word;
    }
}