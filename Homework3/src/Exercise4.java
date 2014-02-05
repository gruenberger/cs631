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
		if(start >= end){
			
			return true;
		}else{
			isPalindrome(isIt,start+1,end-1);
		}
	}

}
