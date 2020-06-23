
public class AVLNode<AnyType> {

	public AVLNode(AnyType theElement) {
		this(theElement, null, null);
	}
	
	public AVLNode(AnyType theElement, AVLNode<AnyType> lt, AVLNode<AnyType> rt ) {
		element = theElement;
		left = lt;
		right = rt;
		height = 0;
		
	}
	
	AnyType element;		// The data in the node
	AVLNode<AnyType> left;			// Left child
	AVLNode<AnyType> right;			// Right child
	int height;				// Height of the node
	
	public static <AnyType> int height(AVLNode<AnyType> t) {
		if (t == null ) return -1;
		return t.height;
	}
	
	
}
