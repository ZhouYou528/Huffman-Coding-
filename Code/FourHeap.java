package huffman;

import java.util.*;

public class FourHeap {
	final static int ROOT_INDEX = 3;  
	final static int PRE_ROOT_INDEX = ROOT_INDEX - 1;  

	List<Node> heap;//store elements

	public FourHeap() {  
		heap = new ArrayList<Node>();  
		heap.add(new Node("", 0, null, null));
		heap.add(new Node("", 0, null, null));
		heap.add(new Node("", 0, null, null));
	}  

	public void add(Node o) {  
		heap.add(o);//add an element to the priority queue  
		int index = heap.size() - 1;//index of last element 
		while (index > ROOT_INDEX) {//max_heapify  
			index = stepUpHeap(index);//step up heap
		}  
	}  

	public void offer(Node o){  
		add(o);  
	}  

	protected int stepUpHeap(int index) {  
		int parentIndex = parent(index);//get parent index  
		Node element = heap.get(index);  
		Node parent  = heap.get(parentIndex);  
		if (compare(parent, element) > 0) { // if parent bigger, swap  
			heap.set(parentIndex, element);  
			heap.set(index, parent);  
			return parentIndex;  // jump to parent index  
	    } else     
	    	return ROOT_INDEX; //no need to swap  
	}  

	   //comparator 
	    protected int compare(Node element, Node other) {  
	    	Node e =  element;  
	        Node o =  other;  
	        if (e.freq > o.freq) return 1;
	        else if (e.freq == o.freq) return 0;
	        else if (e.freq < o.freq) return -1;  
	        return 0;
	    }  

	      
	    protected int parent(int index) {  
	      return (index + 8) / 4; 
	    }  

	    public String toString() {  
	            return heap.toString();  
	    }  

	    public void clear() {  
	    	heap.clear(); 
           	heap.add(new Node("", 0, null, null));
   			heap.add(new Node("", 0, null, null));
   			heap.add(new Node("", 0, null, null));
	    } 
	    public boolean isEmpty() {  
	    	if(heap.size() < 4) return true;
	    	return false;  
	    }  

	    
	    public int size() {  
	    	if(heap.size() > 3) return heap.size() - 3;  
	    	return 0;
	    }  
	       
	    public Node peek() throws RuntimeException{  
	      if (isEmpty())  
	           throw new RuntimeException();  
	       return heap.get(ROOT_INDEX);  
	     }  
	    
	    public Node poll() throws RuntimeException{//poll
	        if (isEmpty())  
	           throw new RuntimeException();  

	        int index = heap.size() - 1;//index of last element  
	        Node least; 
	        if(index == ROOT_INDEX){  
	           least = heap.get(index);  
	           heap.remove(index);  
	        }  
	        else{  
	           Node element = heap.get(index);//get last element  
	           least  = heap.get(ROOT_INDEX);//get root element
	           heap.set(ROOT_INDEX, element);//swap 
	           heap.set(index, least);  
	           heap.remove(index);//delete last element 
	           stepDownHeap(ROOT_INDEX);//step down heap
	        }  
	        return least;  
	    }  

	    public int getSmallestChild(int index) {
	    	int p = index;
	    	int c = 4 * p - 8;
	    	if(c > heap.size() - 1) return -1;
	    	int min = c, temp = c + 1;
	    	while(temp < c + 4 && temp < heap.size()) {
	    		if(compare(heap.get(min), heap.get(temp)) > 0) {
	    			min = temp;
	    			temp++;
	    		}
	    		else temp++;
	    	}
	    	return min;	    	
	    }
	    
	    public void stepDownHeap(int index){  
	    	int p = index;  
	        Node temp = heap.get(p); 
	        int c = getSmallestChild(p);
	        while(c > 0 && c < heap.size()) { 
	        	if(compare(temp, heap.get(getSmallestChild(p))) <= 0)//no need to swap  
	        		break;  
	        	else {  
	        		heap.set(p,heap.get(c));//put smaller child up 
	                p = c;  
	                c = getSmallestChild(p);//continue the process 
	        	}   
	        }
	        heap.set(p, temp);//put temp to p  
	    }  
}
