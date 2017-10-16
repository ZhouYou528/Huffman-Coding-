package huffman;

public class PairNode {
	String ch;  
    int freq;  
    PairNode leftChild, next, prev, left, right;  
  
    PairNode(String ch, int freq, PairNode left, PairNode right) {  
        this.ch    = ch;  
        this.freq  = freq;  
        this.leftChild  = null;  
        this.next  = null;  
        this.prev  = null;  
        this.left  = left;
        this.right  = right;
    }  
  
    // is the node a leaf node?  
    boolean isLeaf() {  
    	return (left == null && right == null); 
    }  
}
