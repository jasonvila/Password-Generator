import java.util.*;
import java.io.*;

public class findAPassword
{   

    public static String findFile(Scanner stdin){
        System.out.print("Enter name of the file to be scanned in with the .txt extension: ");
        String fileName = stdin.nextLine();
        File chosenFile = new File("wordsEn.txt");
        return chosenFile.getAbsolutePath();
    }

    public static String nameNewFile(String orgText, Scanner stdin){
        System.out.print("Enter name of file to be processed, including the .txt extension: ");
        orgText = stdin.nextLine();
        return orgText;
    }

    public static String filterText(Scanner stdin, String filter){
        System.out.print("Enter the letters you want to include in the word with no spaces in between. i.e 'abcd' :");
        filter = stdin.nextLine();
        for(int i = 0; i < filter.length(); i++){
            if(Character.isLetter(filter.charAt(i)) != true){
                System.out.print("Enter the letters you want to include in the word with no spaces in between. i.e 'abcd' :");
                filter = stdin.nextLine();
            }
        }
        return filter;
    }

    public static int textLength(Scanner stdin, String textlength){
        System.out.print("Enter the number of letters you want to include in the word. i.e '4' :");
        textlength = stdin.nextLine();
        for(int i = 0; i < textlength.length(); i++){
            if(Character.isDigit(textlength.charAt(i)) != true){
                System.out.print("Enter the number of letters you want to include in the word. i.e '4' :");
                textlength = stdin.nextLine();
            }
        }
        int newLength = 0;
        int count = 0;
        for(int i = textlength.length() -1; i == 0; i--){
            newLength += Character.getNumericValue(textlength.charAt(i))*(Math.pow(10,i));
        }
        return newLength;
    }

    public static List<String> stringToList(Scanner fileRead, String filter, int lengthText){
        String currentText = "";
        List<String> stringList = new ArrayList<String>();
        while(fileRead.hasNextLine() == true){
            currentText = fileRead.nextLine();
            if(currentText.length() == lengthText){
                if(textCheckerCharacter(currentText, filter) == true){
                    stringList.add(currentText);
                }
            }
        }
        return stringList;
    }

    public static boolean textCheckerCharacter(String currentText, String filter){
        int[] correct = new int[filter.length()];
        for(int i = 0; i < filter.length(); i++){
            for(int counter = 0; counter < currentText.length(); counter ++){
                if(currentText.charAt(counter) == filter.charAt(i)){
                    correct[i] += 1;
                }
            }
        }
        for(int i = 0; i < filter.length(); i++){
            if(correct[i] < 1){
                return false;
            }
        }
        return true;
    }

    public static void printText(List<String> newText, int counter, String curText, PrintStream output){
        for(int i = 0; i < newText.size(); i++){
            output.println(newText.get(i));
        }
    }

    public static void main (String[] argv) throws FileNotFoundException{
        Scanner stdin = new Scanner (System.in);
        Scanner fileRead = new Scanner(new File(findFile(stdin)));
        String name = nameNewFile("", stdin);
        PrintStream output = new PrintStream(new File(name));
        String filter = filterText(stdin, "");
        int length = textLength(stdin, "");
        List<String> newString = stringToList(fileRead, filter, length);
        printText(newString, 0, "", output);
        fileRead.close();
        output.close();
    }
}
