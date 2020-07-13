import java.util.NoSuchElementException;

/**
 * PriorityQueue class implemented via the binary heap.
 */
public class PriorityQueue<AnyType extends Comparable<? super AnyType>>
{

    private static int INITSIZE = 100;

    private int currentSize;   // Number of elements in heap
    private AnyType [ ] array; // The heap array
    
	
    /**
     * Construct an empty PriorityQueue.
     */
    public PriorityQueue( )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ INITSIZE + 1 ];
    }
    
    
    /**
     * Inserts an item to this PriorityQueue.
     * @param x any object.
     * @return true.
     */
    public boolean push( AnyType x )
    {
        if( currentSize + 1 == array.length )
            expandArray( );

            // Percolate up
        int hole = ++currentSize;
        array[ 0 ] = x;
        
        for( ; x.compareTo(array[ hole / 2 ] ) > 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
        
        return true;
    }
    
    /**
     *  isEmpty() indicates whether the heap is empty.
	 *  @return true if the list is empty, false otherwise.
	 **/

    public boolean isEmpty() {
    	return currentSize == 0;
    }
    
    /**
     * Returns the number of items in this PriorityQueue.
     * @return the number of items in this PriorityQueue.
     */
    public int size( )
    {
        return currentSize;
    }
    
    /**
     * Make this PriorityQueue empty.
     */
    public void clear( )
    {
        currentSize = 0;
    }
    
    /**
     * Returns the largest item in the priority queue.
     * @return the largest item.
     * @throws NoSuchElementException if empty.
     */
    public AnyType top( )
    {
        if( isEmpty( ) )
            throw new NoSuchElementException( );
        return array[ 1 ];
    }
    
    /**
     * Removes the largest item in the priority queue.
     * @return the largest item.
     * @throws NoSuchElementException if empty.
     */
    public AnyType pop( )
    {
        AnyType minItem = top( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }


    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    public void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }


    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo(array[ child ] ) > 0 )
                child++;
            if( array[ child ].compareTo(tmp ) > 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    
    

    /**
	 *  expandArray(): internal method to extend array.
	 *  creates a new array with larger size (twice)
	 */
	private void expandArray() {
        AnyType [ ] newArray;

        newArray = (AnyType []) new Comparable[ array.length * 2 ];
        for( int i = 0; i < array.length; i++ )
            newArray[ i ] = array[ i ];
        array = newArray;
    }
    
    /*public static void main( String [ ] args )
    {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );
        int min = 1000000;
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) {
        	if (min > i)
        		min = i;
        	queue.push(  i  );
            if( queue.top( ) != min )
                System.out.println( "Push error! "+i+"   "+queue.top( ));
        }

        for( int i = 1; i < NUMS; i++ )
             if( queue.pop( ) != i )
                 System.out.println( "Pop error!" );
    }*/
}