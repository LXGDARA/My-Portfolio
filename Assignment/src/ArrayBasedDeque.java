import java.util.NoSuchElementException;

public class ArrayBasedDeque<AnyType>{
    private AnyType[] items;
    public int front;
    public int rear;
    public int size;

    public int MAX_SIZE = 10;
    @SuppressWarnings("unchecked")
    public ArrayBasedDeque(){
        items = (AnyType []) new Object[MAX_SIZE];
        front = 0;
        rear = 0;
    }
    /**
     * Add an object (of type AnyType) to the front of the deque
     * @param item
     */
    public void addFirst(AnyType item){
        if(front == 0){
            if(rear < (MAX_SIZE-1)){// if we wraparound and there is space at the end of the dequeue
                front = (MAX_SIZE-1);
                items[front] = item;
            }
            else{
                expand();
                front = MAX_SIZE - 1;
                items[front] = item;
            }
        }else{
            items[--front] = item; 
        }
        size++;
    };
    /**
     * Add an object (of type AnyType) to the rear of the deque
     * @param item
     */
    public void addLast(AnyType item){
        if(isEmpty()){
            items[front] = item;
        }else{
            if(rear+1 != (MAX_SIZE-1)){
                items[++rear] = item;// we increment rear while adding the item to the end
            }
            else {// what if there's no more space at the end of the dequeue
                expand();
                items[++rear] = item;
            }
        }
        size++;
    };

    /**
     * Remove and return the item at the front of the deque. If no such item exists, throw an exception
     * @return AnyType
     */
    public AnyType removeFirst(){
        @SuppressWarnings("unchecked")
        AnyType x = (AnyType) new Object();
        if(!isEmpty()){
            if(front == (MAX_SIZE-1)){
                front = 0;
            }
            else{
                x = items[front];
                items[front] = null;
                ++front;
            }
        }
        else{
            throw new NoSuchElementException("No Elements In deque");
        }
        size--;
        return x;
    };

    /**
     * Remove and return the item at the rear of the deque. If no such item exists, throw an exception
     * @return AnyType
     */
    public AnyType removeLast(){
        @SuppressWarnings("unchecked")
        AnyType x = (AnyType) new Object();;
        if(!isEmpty()){
            if(rear == 0){
                rear = (MAX_SIZE-1);
            }else{
                x = items[rear];
                items[rear] = null;
                --rear;
            }
        }else{
            throw new NoSuchElementException("No Elements In deque");
        }
        size--;
        return x;
    };
    /**
     * Check if the deque is empty (return true if yes)
     * @return boolean
     */
    public boolean isEmpty(){
        return size == 0 ;
    };

    /**
     * Return the number of items in the deque
     * @return int
     */
    public int size(){
        return size;
    };
    /**
     * Get the item at the front of the deque. If no such item exists, throw an exception
     * @return AnyType
     */
    public AnyType getFirst(){
        AnyType x ;
        if(!isEmpty()){
            x = items[0];
        }else{
            throw new NoSuchElementException("No Elements In deque");
        }
        return x;
    };

    /**
     * Get the item at the rear of the deque. If no such item exists, throw an exception
     * @return AnyType
     */
    public AnyType getLast(){
        AnyType x;
        if(!isEmpty()){
            x = items[MAX_SIZE-1];
        }else{
            throw new NoSuchElementException("No Elements In deque");
        }
        return x;
    };

    /**
     * Get the item at the given index where 0 is the front, 1 is the next item, and so forth. If no such index exists, throw an exception
     * @param index
     * @return AnyType
     */
    public AnyType get(int index){
        AnyType x;
        if(index >= front || index <= rear){
            x = items[index];
        }
        else{
            throw new ArrayIndexOutOfBoundsException();
        }
        return x;
    };
    /**
     * Return a string representation of this collection (in order, from the front to the rear)
     * @return String
     */
    public String toString(){
        String strRep = "[";

        for(int i = 0; i<MAX_SIZE; i++){
            strRep = strRep + items[i] + " ";
        }
        return strRep + "]";
    };

    /**
     * expands the array by double
     */
    @SuppressWarnings("unchecked")
    public void expand(){
        AnyType[] newArray;

        newArray = (AnyType[]) new Object[MAX_SIZE*2];

        for(int i = 0; i < MAX_SIZE; i++){

            newArray[i] = items[front];

            if(++front == MAX_SIZE){
                front = 0;
            }

        }
        MAX_SIZE*=2;
        if(rear <= front){
            front = ((MAX_SIZE) - (size-(rear+1)));
        }
        rear = size-1;
        items = newArray;
        
        
    };

}