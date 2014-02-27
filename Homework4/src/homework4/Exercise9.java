package homework4;

public class Exercise9 {

	public Exercise9(){
		
	}
	
	public int search(Node findMe){
		Node end = list;
		Node curr = list.next();
		int index = 0;
		
		while(curr.next().compareTo(end) != 0){
			if(curr.data.compareTo(findMe.data)==0)
				return index;
			else{
				index++;
				curr = curr.next();
			}
			
			return 0;
		}
	}

}
