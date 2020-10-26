package croclesson2110;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileWorks {

    private static boolean isSeparator(char c) {
        return (c == ' ' || c == ',' || c == '-' || c == '_' || c == '.' || c == '(' || c == ')');
    }

    private static int howMuchWordsIn(String[] s) {
        int words = 0;
        for (String value : s) if (!value.isEmpty()) words++;
        return words;
    }

    private static String[] splitString(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (isSeparator(s.charAt(i))) str.append(' ');
            else str.append(s.charAt(i));
        }
        String[] temp = str.toString().split(" ");
        int n = howMuchWordsIn(temp);
        String[] strings = new String[n];
        int next = 0;
        for (String value : temp) {
            if (!value.isEmpty()) {
                strings[next] = value;
                next++;
            }
        }
        return strings;
    }

    private static int countWords(File file) throws NullPointerException {
        try {
            Scanner fileScanner = new Scanner(file);
            StringBuilder content = new StringBuilder();
            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine());
            }
            return howMuchWordsIn(splitString(content.toString()));
        } catch (NullPointerException e) {
            System.out.print("null file");
        } catch (FileNotFoundException a) {
            System.out.print("file not found, try again");
        }
        return 0;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        byte command;
        File currentFile = null;
        do {
            System.out.print("\n0 - end, 1 - count words, 2 - choose file\n");
            command = in.nextByte();
            switch (command) {
                case 1:
                    try {
                        int words = countWords(currentFile);
                        if (words != 0) {
                            System.out.printf("\nfile %s includes %d words\n", currentFile.toString(), words);
                        }
                    } catch (NullPointerException e) {
                        System.out.print("\nno chosen file, try again\n");
                    }
                    break;
                case 2:
                    System.out.print("\nenter path:");
                    String pathName = in.next();
                    currentFile = new File(pathName);
                    break;
            }
        } while (command != 0);

    }
}
