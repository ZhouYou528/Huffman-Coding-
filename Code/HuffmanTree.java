//package huffman;

import java.util.*;

public class HuffmanTree {

    public hNode root;

    public HuffmanTree(){
        this.root = new hNode();
    }

    public void add(String data, String sequence){

        hNode temp = this.root;
        int i = 0;
        for(i = 0; i < sequence.length() - 1; i++){

          if(sequence.charAt(i) == '0'){
                if(temp.left == null){
                    temp.left = new hNode();
                    temp = temp.left;
                }
                else{
                   temp = (hNode) temp.left;
                }
          }
          else if(sequence.charAt(i) == '1'){
        	  if(temp.right == null){
        		  temp.right = new hNode();
        		  temp = temp.right;
        	  }
        	  else{
                    temp = (hNode) temp.right;
        	  }
           }
        }

        if(sequence.charAt(i) == '0'){

            temp.left = new hNode(data); 
           }
        else{
            temp.right = new hNode(data); 

        }
    }
    public List<String> getDecodedMessage(byte[] encoded){

        List<String> output = new ArrayList<String>();
        hNode temp = this.root;
        
        for(int i = 0; i < encoded.length; i++){
        	int bitCount = 0;
        	for(int j = 0; j < 8; j++) {
        		if(encoded[i] != (encoded[i] | 1 << 7 - bitCount)) {
                    temp = temp.left;
                    bitCount++;
        		
                    if(temp.left == null && temp.right == null){
                    	output.add(temp.getData());
                    	temp = this.root;
                    	
                    }
        		} else {
        			temp = temp.right;
        			bitCount++;
        			if(temp.left == null && temp.right == null){
        				output.add(temp.getData());
        				temp = this.root;  
        				
        			}
        		}      
            }
        }
        //HuffmanTree HT = new HuffmanTree();
        //HT.traversal(this.root);
        return output;
        
    }
    public void traversal(hNode node){

        if(node == null)
              return;
        System.out.println(node.getData());
        traversal(node.left);
        traversal(node.right);

    }
}
