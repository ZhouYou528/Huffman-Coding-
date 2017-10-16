package huffman;

public class PairHeap {
	private PairNode root; 
	private PairNode [] treeArray = new PairNode[5];
	int heapsize = 0;
	/* Constructor */
	public PairHeap() {
		root = null;
	}
	/* Check if heap is empty */
	public boolean isEmpty() {
		return root == null;
	}
	public int size() {
		return heapsize;
	}
	/* Make heap logically empty */ 
	public void makeEmpty() {
		root = null;
		heapsize = 0;
	}
	/* Function to insert data */
	public void add(PairNode x) {
		PairNode newNode = x;
		if (root == null) {
			root = newNode;
			heapsize++;
		}
	    else {
	    	root = compareAndLink(root, newNode);
	    	heapsize++;
	    }
	}
	/* Function compareAndLink */
	private PairNode compareAndLink(PairNode first, PairNode second) {
		if (second == null)
			return first;
	 
	    if (second.freq < first.freq) {
	    	/* Attach first as leftmost child of second */
	        second.prev = first.prev;
	        first.prev = second;
	        first.next = second.leftChild;
	        if (first.next != null) first.next.prev = first;
	        second.leftChild = first;
	        return second;
	    }
	    else {
	        /* Attach second as leftmost child of first */
	        second.prev = first;
	        first.next = second.next;
	        if (first.next != null) first.next.prev = first;
	        second.next = first.leftChild;
	        if (second.next != null) second.next.prev = second;
	        first.leftChild = second;
	        return first;
	    }
    }
	    private PairNode combineSiblings(PairNode firstSibling) {
	        if(firstSibling.next == null) return firstSibling;
	        /* Store the subtrees in an array */
	        int numSiblings = 0;
	        for ( ; firstSibling != null; numSiblings++) {
	            treeArray = doubleIfFull(treeArray, numSiblings);
	            treeArray[numSiblings] = firstSibling;
	            /* break links */
	            firstSibling.prev.next = null;  
	            firstSibling = firstSibling.next;
	        }
	        treeArray = doubleIfFull(treeArray, numSiblings);
	        treeArray[numSiblings] = null;
	        /* Combine subtrees two at a time, going left to right */
	        int i = 0;
	        for ( ; i + 1 < numSiblings; i += 2)
	            treeArray[i] = compareAndLink(treeArray[i], treeArray[i + 1]);
	        int j = i - 2;
	        /* j has the result of last compareAndLink */
	        /* If an odd number of trees, get the last one */
	        if (j == numSiblings - 3)
	            treeArray[j] = compareAndLink( treeArray[j], treeArray[j + 2] );
	        /* Now go right to left, merging last tree with */
	        /* next to last. The result becomes the new last */
	        for ( ; j >= 2; j -= 2)
	            treeArray[j - 2] = compareAndLink(treeArray[j-2], treeArray[j]);
	        return treeArray[0];
	    }
	    private PairNode[] doubleIfFull(PairNode [] array, int index) {
	        if (index == array.length) {
	            PairNode [] oldArray = array;
	            array = new PairNode[index * 2];
	            for( int i = 0; i < index; i++ )
	                array[i] = oldArray[i];
	        }
	        return array;
	    }
	    /* Delete min element */
	    public PairNode poll() throws RuntimeException {
	        if (isEmpty())
	        	throw new RuntimeException();  
	        PairNode x = root;
	        if (root.leftChild == null)
	            root = null;
	        else
	            root = combineSiblings(root.leftChild);
	        heapsize--;
	        return x;
	    }
}
