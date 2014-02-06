import java.util.Scanner;
import java.*;


public class Exercise4 {

	public static void main(String[] args) {
		
		
		//System.out.println(ex4(5,25));
		//writeLine('x',10);
		//writeBlock('x',2,3);
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a String");
		String pal = s.nextLine();
		pal = pal.toLowerCase();
		pal = pal.replaceAll("[^a-zA-Z ]", "");
		pal = pal.replaceAll("\\p{Space}", "");
		System.out.println(pal);
		
		System.out.println(isPalindrome(pal,0,pal.length()-1));
		
		if( isPalindrome("racecar",0,"racecar".length()-1)){
			System.out.println("racecar passed the test");
		}else{
			System.out.println("racecar didn't pass the test");
		}
		
		String biggerTest = " (* (*&)(*& ^)^*)& r #@ A )*!) d ?>?> a ?>?>#R  ";
		String copyOfTest = " (* (*&)(*& ^)^*)& r #@ A )*!) d ?>?> a ?>?>#R  ";
		biggerTest = biggerTest.toLowerCase();
		biggerTest = biggerTest.replaceAll("[^a-zA-Z ]", "");
		biggerTest = biggerTest.replaceAll("\\p{Space}", "");
		
		if(isPalindrome(biggerTest,0,biggerTest.length()-1)){
			System.out.println(copyOfTest +" string worked");
		}else{
			System.out.println(copyOfTest+" did not work");
		}

	}
	
	public static int ex4(int start, int end){
		if(start == end)
			return end;
		else
			return  start+ex4(start+1, end);
	}
	
	public static void writeLine(char ch, int num){
		if(num==0){
			
		}else{
			System.out.print(ch);
			writeLine(ch,num-1);
		}
	}
	
	public static void writeBlock(char ch, int num, int rows){
		if(rows==0){			
			
		}else{			
			writeBlock(ch,num,rows-1);
			writeLine(ch,num);
			System.out.println();
		}
	}
	
	public static boolean isPalindrome(String isIt, int start, int end){
		if(start == end && isIt.charAt(start) == isIt.charAt(end))	
			return true;
		else{					
			if(start < end && isIt.charAt(start) == isIt.charAt(end))
				isPalindrome(isIt,start+1,end-1);
			else
				return false;
		}
		return true;
	}
}
