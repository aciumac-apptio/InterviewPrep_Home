import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        String s1 = "awaglknagawunagwkwagl";
        String s2 = "abcd";

        System.out.println(subStringsKDist(s1, 4));
        System.out.println(subStringsKDist(s2, 3));

        String s3 = "babad";
        String s4 = "cbbd";
        String s5 = "abb";

        System.out.println(longestPalindrome(s3));
        System.out.println(longestPalindrome(s4));
        System.out.println(longestPalindrome(s5));

        System.out.println(letterCombinations("23"));

    }

    public static List<String> letterCombinations(String digits) {

        // take digit "2" -> available choices "a", "b", "c"
            // choose "a"
                // take digit 3 - > available choices "d", "e", "f"
                    // choose "d"
                        // store choice "ad" in a list (because no more digits to process)
                        // unchoose d
                    // choose "e"
                        // store choice "ae" in a list
                        // unchoose d
                    // choose "f"
                        // store choice "af" in a list
                        // unchoose d
                    // no more choices -> exit
                // unchoose a
            // choose "b"
                    //repeat
            // choose "c"
                    // repeat


        // public/private pair
        // build a map for digits
        String alphabet = "abcdefghijklmno";
        Map<String, String> digitsToLetters = new HashMap<>();
        for (int i = 2; i < 7; i++) {
            digitsToLetters.put(i + "", alphabet.substring(i * 3 - 6, (i - 1) * 3));
        }
        digitsToLetters.put("7", "pqrs");
        digitsToLetters.put("8", "tuv");
        digitsToLetters.put("9", "wxyz");

        // keep track of digits to process "23", current choices List
        List<String> chosen = new ArrayList<>();
        letterCombinations(digits, "", chosen, digitsToLetters);
        return chosen;
    }

    private static void letterCombinations(String digits, String currentChoice, List<String> chosen, Map<String, String> digitsToLetters) {
        if (digits.length() == 0) {
            chosen.add(currentChoice);
        } else {
            String choicesAvailable = digitsToLetters.get(digits.substring(0,1));
            for (int i = 0; i < choicesAvailable.length(); i++) {
                char c = choicesAvailable.charAt(i); // choose
                letterCombinations(digits.substring(1, digits.length()), currentChoice + c, chosen, digitsToLetters);
            }
        }
    }



    public static String longestPalindrome(String s) {
        // Check if the whole string "babad" 01234 is palindrome ->
            // NO

        // Check substrings baba and abad
            // baba is a palindrome -> return it


        //PSEUDO Code
        // if isPalindrome(s) -> return s

        //while substring size not equals to 1
            // for i = 0 jump by size of substring so long as i + sizeofsubstring < length of input
                // check for each s.substring(i, i+substringsize) if is palindrome
                    // return if so


        //return ""

        if (isPalindrome(s)) return s;

        int substringSize = s.length() - 1;
        while (substringSize != 1) {
            for (int i = 0; i <= s.length() - substringSize ; i++) {
                if (isPalindrome(s.substring(i, i + substringSize))) return s.substring(i, i + substringSize);

            }
            substringSize--;
        }
        return s.substring(0, 1);
    }

    private static boolean isPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return true;
        } else {
            return s.charAt(0) == s.charAt(s.length() - 1) && isPalindrome(s.substring(1, s.length() - 1));
        }
    }

    public static List<String> subStringsKDist(String inputStr, int num) {

        //abcd, 3 comes in, expect [abc, bcd] to come out
        //awaglknagawunagwkwagl, 4 comes in expect wagl,aglk,

        // PROCESS all characters one by one
            // process a -> "a"
                // length: 1
            // process w -> "aw"
                // length: 2
            // process a -> "awa"
              // STOP a is already present in this substring
              // discard this substring
                 // move starting point until we have a substring with distinct characters "wa"
                 // reset length to length of the substring -> length: 2
            // process g -> "wag"
            // process l -> "wagl"
               // STOP: all characters are unique and length is num
               // store this substring in a collection
                 // move starting point once to the right -> "agl"
                 // reset length to length of the substring

        // Words should be stored in a collection that does not store duplicates
        // Take these words and write them to a list which will be returned


        //PSEUDO CODE
        // Create a set of words with unique characters
        Set<String> words = new HashSet<>();
        // Store starting index of current substring
        int currentIndex = 0;

        // For each character c in the string s from 0 to s.length() - 1
            // If c is present in substring from currentIndex to i
                // move the currentIndex to the right until c is no longer present in substring from currentIndex to i
                // update length of current substring
            // Else if length of the current substring == num
                // save substring to the set of words
                // move the currentIndex to the right once
                // update length of the current substring (NO NEED -> length can be calculated [current index to i+1])

        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            if (inputStr.substring(currentIndex, i).contains(c + "")) {
                while (inputStr.substring(currentIndex, i).contains(c + "") || currentIndex != i) {
                    currentIndex++;
                }
            } else if(inputStr.substring(currentIndex, i + 1).length() == num) {
                words.add(inputStr.substring(currentIndex, i + 1));
                currentIndex++;
            }
        }

        // Create a list of words
        // Write all elements from set to list
        List<String> list = new ArrayList<>();
        for (String word : words) {
            list.add(word);
        }
        return list;
    }

}
