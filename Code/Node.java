//package huffman;

public class Node {
	String ch;  
    int freq;  
    Node left, right;  
  
    Node(String ch, int freq, Node left, Node right) {  
        this.ch    = ch;  
        this.freq  = freq;  
        this.left  = left;  
        this.right = right;  
    }  
  
    // is the node a leaf node?  
    boolean isLeaf() {  
        //assert (left == null && right == null) || (left != null && right != null);  
        return (left == null && right == null);  
    }  
 
}
