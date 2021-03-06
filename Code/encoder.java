//package huffman;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class encoder {
	public static Map<String, String> compress(String[] number) throws IOException {   
	    Map<String, Integer> freq = new HashMap<String, Integer>();
	    // get the frequency counts    
	    for (int i = 0; i < number.length; i++) { 
	         int count = 1;
	         if(freq.containsKey(number[i])) count = freq.get(number[i]) + 1;
	         freq.put(number[i], count);
	    }
	    // initialze priority queue using min heap 
	    MinHeap pq = new MinHeap(); 
	    //int totalTime = 0;
	    for(int i = 0; i < 1; i++) {
	    	// start counting time
	    	//long start = System.currentTimeMillis();
	    	// insert the node into priority queue  
	    	for (String s : freq.keySet())   
	            pq.add(new Node(s, freq.get(s), null, null));  
	  
	    	// merge two smallest trees  
	    	while (pq.size() > 1) {  
	    		Node left  = pq.poll();  
	    		Node right = pq.poll();  
	    		Node parent = new Node("", left.freq + right.freq, left, right);  
	    		pq.add(parent);  
	    	}  
	    	// stop counting time
	    	//long end = System.currentTimeMillis();
	    	//totalTime += end - start;
	
	    }
	    //System.out.println("Total running time: " + totalTime + "ms");
	    Node root = pq.poll();  
	  
	    // build code table  
	    Map<String, String> codetable = new HashMap<String, String>();  
	    buildCode(codetable, root, "");  
	   
	    return codetable;  
	}
	public static void buildCode(Map<String, String> codetable, Node x, String s) throws IOException {  
	 
	    if (!x.isLeaf()) {  
	        buildCode(codetable, x.left,  s + '0');  
	        buildCode(codetable, x.right, s + '1');  
	    }  
	    else {  
	    	codetable.put(x.ch, s);
	    }  	
	}
	public static void main(String args[]) throws Exception {
		int totalTime = 0;
		long start = System.currentTimeMillis();
		String pathname = args[0]; //“sample_input_large.txt";
		File file = new File(pathname) ;
		InputStream input = new FileInputStream(file) ;
		byte[] b = new byte[(int)file.length()] ;
		input.read(b) ;
		String str = new String(b) ;
		String[] number = str.split("\n") ;
		input.close(); 
		Map<String, String> Codetable = new HashMap<String, String>(); 
		Codetable = compress(number);
		File CodeTable = new File("code_table.txt"); 
	    CodeTable.createNewFile();   
        BufferedWriter out = new BufferedWriter(new FileWriter(CodeTable));  
        for(String i : Codetable.keySet()) out.write(i + " " + Codetable.get(i) + "\n"); 
        out.flush();   
        out.close(); 
        File encoded = new File("encoded.bin");        
        encoded.createNewFile();   
        DataOutputStream out1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(encoded)));
       
        out1.write(packEncodedChars.zhuan(number, Codetable));
        
        out1.flush();   
        out1.close(); 
        long end = System.currentTimeMillis();
        totalTime += end - start;
        System.out.println("Successfully generated: code_table.txt");
        System.out.println("Successfully generated: encoded.bin");
        System.out.println("Total encoding time: " + totalTime + "ms");
	}
}

