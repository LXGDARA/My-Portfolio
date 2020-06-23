
/******************************************************************************
 *  Under Linux:
 *  Compilation:  javac LangValidator.java
 *  Execution:    java LangValidator file.txt
 *  
 *  Reads in a text file and checks to see if the strings (one string per line)
 *  belongs to the following language:
 *  L={w$w' | w is possibly an empty string of characters except $, and w' = reverse(w)}
 *
 *  % java  java LangValidator
 *  $
 *  true
 *
 *  % java LangValidator
 *  aab$aba
 *  false
 *
 ******************************************************************************/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;


public class LangValidator {
	private static final char delimeter    = '$';
    public static boolean isValidString(String s) {
        boolean isValid = false;
        //Hint: use a stack of characters:
        
        Stack<Character> stack = new Stack<Character>();
        
    	for(int i = 0; i<s.length(); i++){
            if(s.charAt(i) == delimeter && i == 0){// if delimeter is as zero, we treat W like it is an empty string; coming before and after delimeter
                isValid = true;
            }
            else if( s.charAt(i) == delimeter && s.charAt(i + 1) == delimeter){// if we encounter 2 or more delimeters in a row in a string it is false
                isValid = false;
                break;
            }else if(s.charAt(i) == delimeter){
                while(!stack.empty() && i != (s.length()-1)){
                    if(s.charAt(++i) == stack.pop()){
                        isValid = true;
                    }else{
                        isValid = false;
                    }
                }
                if(i < (s.length()-1)){
                    isValid = false;
                }
                if(!stack.empty() && i == (s.length()-1)){
                    isValid = false;
                }
            }
            else{
                stack.push(s.charAt(i));
            }           
        }
        return isValid;
    }

	
    public static void main(String[] args) throws FileNotFoundException {
           Scanner fin = new Scanner(new FileReader("../input.txt"));
           
        while (fin.hasNext()) {
            String str = fin.nextLine();
            System.out.println(str);
            System.out.println(isValidString(str));
            System.out.println();
        }

    }

	
	
}
