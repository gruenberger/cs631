
public class Exercise4 {

	public static void main(String[] args) {
		
		
		//System.out.println(ex4(5,25));
		//writeLine('x',10);
		//writeBlock('x',2,3);
		isPalindrome((" lonely, tylenol. ").replaceAll("\\w", "").replaceAll(".", "")
				.replaceAll(",", ""));


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
	
	public static boolean isPalindrome(String isIt){
		if(){
			
		}else(){
			
		}
	}

}
