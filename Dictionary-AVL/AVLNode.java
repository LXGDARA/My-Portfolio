
public class AVLNode extends Word{

	public AVLNode(String word, String meaning) {
		this(word, meaning, null, null);
	}
	
	public AVLNode(String word, String meaning, AVLNode lt, AVLNode rt ) {
		super(word , meaning);
		element = word;
		left = lt;
		right = rt;
		height = 0;
	}
	
	String element;		// The data in the node
	AVLNode left;			// Left child
	AVLNode right;			// Right child
	int height;				// Height of the node
	
	public static  int height(AVLNode t) {
		if (t == null ) return -1;
		return t.height;
	}

	public AVLNode getLeft(){
		return left;
	}
	
	public AVLNode getRight(){
		return right;
	}
	
}
