package homework4;

import java.util.Iterator;
import java.util.LinkedList;

public class Exercise7 {
	
	private LinkedList<Integer> newList = new LinkedList();
	
	public Exercise7(LinkedList<Integer> first, LinkedList<Integer> second){
		createList(first, second);
	}
	
	public LinkedList<Integer> getList(){
		return newList;
	}
	
	private void createList(LinkedList<Integer> first, LinkedList second){
		Iterator<Integer> firstIt = first.iterator();
		Iterator<Integer> secondIt = second.iterator();
		Integer tempFirst, tempSecond;
		int firstSize = first.size(), secondSize = second.size();
		
		if(firstSize < secondSize){
			tempFirst = firstIt.next();
			tempSecond = secondIt.next();
			
			
			while(firstIt.hasNext()){
				switch (checkNodes(tempFirst,tempSecond)){
				case 1:	tempFirst = firstIt.next();
						break;
				case 2:	tempFirst = firstIt.next();
						tempSecond = secondIt.next();
						break;
				case 3:	tempSecond=secondIt.next();
						break;
				}
			}
			while(secondIt.hasNext()){
				newList.add(new Integer(secondIt.next()));
			}
		}else if(secondSize < firstSize){
			tempFirst = firstIt.next();
			tempSecond = secondIt.next();			
			
			while(secondIt.hasNext()){
				switch (checkNodes(tempFirst,tempSecond)){
				case 1:	tempFirst = firstIt.next();
						break;
				case 2:	tempFirst = firstIt.next();
						tempSecond = secondIt.next();
						break;
				case 3:	tempSecond=secondIt.next();
						break;
				}
			}
			while(firstIt.hasNext()){
				newList.add(new Integer(firstIt.next()));
			}
		}
		
	}
	
	private int checkNodes(Integer first, Integer second){
		 if(first.compareTo(second)< 0){
			newList.add(new Integer(first));
			return 1;
		}else if(first.compareTo(second)==0){
			newList.add(new Integer(first));
			return 2;
		}else if(first.compareTo(second)>0){
			newList.add(new Integer(second));
			return 3;
		}else{
			return 0;
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<Integer> first = new LinkedList<Integer>();
		LinkedList<Integer> second = new LinkedList<Integer>();
		
		first.add(1);first.add(3);first.add(5);first.add(7);first.add(9);
		second.add(2);second.add(4);second.add(6);second.add(8);second.add(10);second.add(11);
		
		LinkedList<Integer> mergedList = new LinkedList<Integer>();
		mergedList = new Exercise7(first,second).getList();
		
		System.out.println(mergedList.toString());
		
		

	}

}
