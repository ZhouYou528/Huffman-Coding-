//package huffman;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.*;

public class decoder {
		
	public static void main(String args[]) throws Exception {
                int totalTime = 0;
		long start = System.currentTimeMillis();
		String pathname = args[1]; //“code_table.txt";
		File file = new File(pathname) ;
		InputStream input = new FileInputStream(file) ;
		byte[] b = new byte[(int)file.length()] ;
		input.read(b) ;
		String str = new String(b) ;
		String[] number = str.split("\n") ;
		input.close();
		HuffmanTree HT = new HuffmanTree();
		for(int i = 0; i < number.length; i++) {
			String[] temp =  number[i].split(" ");
			HT.add(temp[0], temp[1]);
			//System.out.println(temp[0] + " " + temp[1]);
		}
		File encoded = new File(args[0]);        
		InputStream input1 = new FileInputStream(encoded) ;  
		byte[] in = new byte[(int)encoded.length()] ;
		input1.read(in) ;
        input1.close(); 
		List<String> decoded = new ArrayList<String>();
		decoded = HT.getDecodedMessage(in);
		File Decoded = new File("decoded.txt"); 
		Decoded.createNewFile();   
        BufferedWriter out = new BufferedWriter(new FileWriter(Decoded));  
        for(String i : decoded) out.write(i  + "\n"); 
        out.flush();   
        out.close(); 
        long end = System.currentTimeMillis();
        totalTime += end - start;
        System.out.println("Successfully generated: decoded.txt");
        System.out.println("Total decoding time: " + totalTime + "ms");
	}
}
