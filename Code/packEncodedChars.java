//package huffman;

import java.util.ArrayList;
import java.util.Map;

public class packEncodedChars {
	public static byte[] zhuan(String[] bitStrings, Map<String, String> codetable) {
		ArrayList<Byte> bits = new ArrayList<Byte>();
	    byte target = 0;
	    int bitCount = 0;
	    
	for(String s : bitStrings) {
	    for (char bit : codetable.get(s).toCharArray()) {
	    	if (bit == '1') {
	    		target |= 1 << (7 - bitCount);
	    	}
	    	bitCount++;
	    	if (bitCount >= 8) {
	    		bitCount = 0;
	    		bits.add(target);
	    		target = 0;
	    	}
	    }
	}
	  //  if(bitCount != 0) bits.add(target);
	   
	    byte[] bitstring = new byte[bits.size()];
	    for (int i = 0; i < bits.size(); i++) {
	        bitstring[i] = bits.get(i);
	    }
	    return bitstring;
	}
//	public static void main(String args[]) {
//		byte[] b = zhuan("100100111");
//		for(int i = 0; i < b.length; i++) {
//		 System.out.println(Integer.toBinaryString(b[i]));
//		}
//	}
}
