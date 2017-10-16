//package huffman;

import java.util.*;

public class MinHeap {
  
    final static int ROOT_INDEX = 0;  
    final static int PRE_ROOT_INDEX = ROOT_INDEX - 1;  

    List<Node> heap;//store elements

    public MinHeap() {  
            heap = new ArrayList<Node>();  
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
      return (index - PRE_ROOT_INDEX) / 2 + PRE_ROOT_INDEX; 
      //return (index - 1) / 2;
    }  

    public String toString() {  
            return heap.toString();  
    }  
    public void clear() {  
        heap.clear();  
    }  
     
    public boolean isEmpty() {  
            return heap.isEmpty();  
    }  

    
    public int size() {  
            return heap.size();  
    }  
       
    public Node peek() throws RuntimeException{  
      if (isEmpty())  
           throw new RuntimeException();  
       return heap.get(0);  
     }  
    
    public Node poll() throws RuntimeException{//poll
        if (isEmpty())  
           throw new RuntimeException();  

        int index = heap.size() - 1;//index of last element  
        Node least;  
        if(index==0){  
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

                 
    public void stepDownHeap(int index){  
    	int p = index;  
        int c = 2*p + 1;//left child index  
        Node temp = heap.get(p);//  
        while(c < heap.size()){  
        	if(c + 1 < heap.size() && compare(heap.get(c + 1), heap.get(c)) < 0) c = c + 1;//get the smaller one of the child nodes  
            if(compare(temp, heap.get(c)) <= 0)//no need to swap  
              	break;  
            else {  
            	heap.set(p,heap.get(c));//put smaller child up 
                p = c;  
                c = 2*p + 1;//continue the process 
            }  
        }  
        heap.set(p, temp);//put temp to p  
    }  
}
