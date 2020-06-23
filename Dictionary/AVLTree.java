import javax.management.InstanceNotFoundException;

public class AVLTree<AnyType extends Comparable<? super AnyType>> {
	private AVLNode<AnyType> root;
	
    public AVLTree() {
        root = null;
    }


    /**
    Returns the height (max root-to-leaf depth) of the tree.
    Uses a recursive helper that recurs down to find
    the height (max depth).
   */
    public int height() {
        return AVLNode.height(root);
    }
    
    protected int height(AVLNode<AnyType> t) {
    	return AVLNode.height(t);
    }
    
 
    
    public void insert(AnyType x) {
    	root = insert(x, root);
    }
    
    private AVLNode<AnyType> insert(AnyType x, AVLNode<AnyType> t) {
    	if( t == null)
    		t = new AVLNode<AnyType>(x);
    	else if (x.compareTo(t.element) < 0) {
			t.left = insert(x, t.left);
			if( height(t.left) - height(t.right) == 2 )
				 if( x.compareTo( t.left.element ) < 0 )
					 t = rotateWithLeftChild( t );
				 else
					 t = doubleWithLeftChild( t );
    	}
		else if( x.compareTo( t.element ) > 0 ) {
			t.right = insert( x, t.right );
			if( height( t.right ) - height( t.left ) == 2 )
				if( x.compareTo( t.right.element ) > 0 )
					t = rotateWithRightChild( t );
				else
					t = doubleWithRightChild( t );
		}
		else
			System.out.println("This word already exists"); // Duplicate; do nothing or you can throw an exception
		t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
		return t; 
   
		
	}
    
    public AnyType findWord(AnyType x){
        return elementAt(find(x, root));
    }

    private AnyType elementAt( AVLNode<AnyType> t )
    {
        return t == null ? null : t.element;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return node containing the matched item.
     */
    private AVLNode<AnyType> find( AnyType x, AVLNode<AnyType> t )
    {
        
        while( t != null )
        {
            if( x.compareTo( t.element ) < 0 )
                t = t.left;
            else if( x.compareTo( t.element ) > 0 )
                t = t.right;
            else
                return t;    // Match
        }
        
        return null;         // Not found
    }

    private AVLNode<AnyType> rotateWithLeftChild( AVLNode<AnyType> k2) {
    	AVLNode<AnyType> k1 = k2.left;
    	k2.left = k1.right;
    	k1.right = k2;
    	k2.height = Math.max( height(k2.left), height(k2.right) ) + 1;
    	k1.height = Math.max( height(k1.left), k2.height ) + 1;
    	return k1;
    }
    
    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
     private AVLNode<AnyType> rotateWithRightChild( AVLNode<AnyType> k1 ) {
    	 AVLNode<AnyType> k2 = k1.right;
    	 k1.right = k2.left;
    	 k2.left = k1;
    	 k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
    	 k2.height = Math.max( height( k2.right ), k1.height ) + 1;
    	 return k2;
     }
     
     /**
      * Double rotate binary tree node: first left child with its right child;
      * then node k3 with new left child.
      * For AVL trees, this is a double rotation for case 2. Update heights and return new root.
      * 
      * @param k3 
      *		 current AVLNode we want to double with left child.
      * @return
      * 	new root of AVL Tree
      */
     private AVLNode<AnyType> doubleWithLeftChild( AVLNode<AnyType> k3 ) {
    	 k3.left = rotateWithRightChild(k3.left);
    	 return rotateWithLeftChild( k3);
	}

     /**
      * Double rotate binary tree node: first right child
      * with its left child; then node k1 with new right child.
      * For AVL trees, this is a double rotation for case 3.
      * Update heights, then return new root.
      */
      private AVLNode<AnyType> doubleWithRightChild( AVLNode<AnyType> k1 ) {
    	  k1.right = rotateWithLeftChild( k1.right );
    	  return rotateWithRightChild( k1 );
      }
      

      public AVLNode<AnyType> getRoot(){
          return root;
      }

      private String preorder(AVLNode<AnyType> t) {
    	String output = "";
    	    if (t!=null){
            output += t.element.toString();
    		output += "\n"+preorder(t.left);
    		output += "\n"+preorder(t.right);
    		
    	}
    	return output;
    }
        public String preorder(){
    	    return preorder(root);
        }

        public void remove(AnyType x) throws InstanceNotFoundException {
            root = remove(x, root);
        }

        /**
         * Internal method to remove from a subtree.
         * 
         * @param x the item to remove.
         * @param t the node that roots the tree.
         * @return the new root.
         * @throws InstanceNotFoundException
         * @throws ItemNotFoundException     if x is not found.
         */
        protected AVLNode<AnyType> remove(AnyType x, AVLNode<AnyType> t) throws InstanceNotFoundException
    {
        if( t == null )
            throw new InstanceNotFoundException(x.toString() + "not found");
        if( x.compareTo( t.element ) < 0 )
            t.left = remove( x, t.left );
            if (height(t.left) - height(t.right) == 2){
                if (x.compareTo(t.left.element) < 0){
                    t = rotateWithLeftChild(t);
                }
                else{
                    t = doubleWithLeftChild(t);
                } 
            }
                
        else if( x.compareTo( t.element ) > 0 ){
            t.right = remove( x, t.right );
             //t.right = insert(x, t.right);
             if (height(t.right) - height(t.left) == 2){
                if (x.compareTo(t.right.element) > 0){
                    t = rotateWithRightChild(t);
                } 
                else{
                    t = doubleWithRightChild(t);
                }
                    
            }
        }
            
           
                
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = removeMin( t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree.
     * 
     * @param t the node that roots the tree.
     * @return the new root.
     * @throws InstanceNotFoundException
     * @throws ItemNotFoundException     if t is empty.
     */
    protected AVLNode<AnyType> removeMin(AVLNode<AnyType> t) throws InstanceNotFoundException
    {
        if( t == null )
        throw new InstanceNotFoundException("Instance not found");
        else if( t.left != null )
        {
            t.left = removeMin( t.left );
            return t;
        }
        else
            return t.right;
    } 

     /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    protected AVLNode<AnyType> findMin( AVLNode<AnyType> t )
    {
        if( t != null )
            while( t.left != null )
                t = t.left;

        return t;
    }

      /*public static void main( String [ ] args )
      {
          AVLTree<Integer> t = new AVLTree<Integer>( );
          final int NUMS = 4000;
          final int GAP  =   37;

          System.out.println( "Checking... (no more output means success)" );

          for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
              t.insert( i );
      }*/
}





















