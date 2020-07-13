import java.util.*;

public class SList<AnyType> {
  private SListNode<AnyType> head;    // First node in list.
  private SListNode<AnyType> tail;    // last node in list.
  private int size;                   // Number of items in list.

  public SList() {     // Here's how to create an empty list.
    clear();
  }
  
  /**
   * @return the number of items in this list
   */
  public int size() {
	  return size;
  }
  
  /**
   * Tests if the list contains no element
   * @return true if the list contains no element
   */
  public boolean isEmpty(){
	  return (size() == 0);
  }
  
  /**
   * Removes all of the elements from this list.
   */
  public void clear(){
	  head = null;
	  tail = null;
	  size = 0;
  }
  
  /**
   * Adds an item to this list, at the front
   * @param x any object
   */
  public void addFirst(AnyType x) {
	  head = new SListNode<AnyType>(x, head);
	  size++;
  }
  
  /** 
   * Adds an item to this list, at the end
   * @param x any object
   */
  public void addLast(AnyType x) {
	// First case: list is empty
	if (tail == null){
		tail = new SListNode<AnyType>(x);	
		head = tail;
	} 
	// Second case: list is not empty
	else{
		tail.next = new SListNode<AnyType>(x);
		tail = tail.next;
	}
	size++;
  }
  
  /*addLast without using tail*/
  /** 
   * Adds an item to this list, at the end
   * @param x any object
   */
  /*
  public void addLast(AnyType x) {
	  if (head == null) 
		  head = new SListNode(x);
	  else {
		  SListNode<AnyType> current = head;
		  while (current.next != null) {
			  current = current.next;
		  }
		  current.next = new SListNode<AnyType>(x);
	  }
	  size++;
  }
  */
  
  /** 
   * Adds an item to this list, at the end
   * @param x any object
   */
  public void add(AnyType x) {
	  this.addLast(x);
  }
  
  /**
   * Adds an item to this list at the specified position
   * @param x any object
   * @param index position to insert x
   * @throws IndexOutOfBoundsException if index is not
   * 	between 0 and size()
   */
  public void add(int index, AnyType x) throws IndexOutOfBoundsException {
	  if (index < 0 || index > size())
		 throw new IndexOutOfBoundsException("add at index: "+index);  

	  // First case: add to the end of list
	  if (index == size())
		  addLast(x);
	  // Second case: add to index 0
	  else if (index == 0) 
		  addFirst(x);
	  else {
	  // Third case: 0 < index < size
		  SListNode<AnyType> prev = head;
		  int prev_ind = 0;
		  while (prev_ind < index - 1) {
			  prev = prev.next;
			  prev_ind++;
		  }
		  //new node should be inserted after prev and before prev.next
		  SListNode<AnyType> newNode = new SListNode<AnyType>(x, prev.next);
		  prev.next = newNode;
		  size++;
	  }
  }
  
  /**
   * Returns the item at position index
   * @param index the index to search in
   * @return return the data of the item in index
   * @throws IndexOutOfBoundsException if index is not
   * 	between 0 and size()
   */
  public AnyType get(int index) throws IndexOutOfBoundsException {
	  if (index < 0 || index > size())
		  throw new IndexOutOfBoundsException("get index: "+index); 
	  SListNode<AnyType> current = head;
	  int curr_ind = 0;
	  while (curr_ind < index) {
		  current = current.next;
		  curr_ind++;
	  }
	  return current.item;
  }
  
  /**
   * Returns the index of first item matching x
   * in this list, or -1 if not found.
   * @param x any object
   * @return the index of first item matching x
   * 	or -1 if not found.
   */
  public int indexOf(Object x) {
	  SListNode<AnyType> current = head;
	  int curr_ind = 0;
	  while (current != null) {
		  if (x.equals(current.item))
			  return curr_ind;
		  current = current.next;
		  curr_ind++;
	  }
	  return -1;
  }
  
  /**
   * Tests if some item is in this list
   * @param x any object
   * @return true if this list contains an item equal to x
   */
  public boolean contains(Object x) {
	  return indexOf(x) != -1;
  }
  
  /**
   * Removes an item from this list
   * @param index the index of the object
   * @return the item that was removed from the list
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public AnyType remove(int index) throws IndexOutOfBoundsException {
	  if (index < 0 || index >= size())
		  throw new IndexOutOfBoundsException("remove index: "+index);
	  // First case: if list has one element
	  if (size() == 1) {
		  AnyType item = head.item;
		  clear();
		  return item;
	  }
	  // Second case: remove the index 0
	  if (index == 0) {
		  AnyType item = head.item;
		  head = head.next;
		  size--;
		  return item;
	  }
	  // Third case: index > 0
	  SListNode<AnyType> prev = head;
	  int prev_ind = 0;
	  while (prev_ind < index - 1) {
		  prev = prev.next;
		  prev_ind++;
	  }
	  //prev.next is the node that should be removed!
	  AnyType item = prev.next.item;
	  //update the tail, in case prev.next is tail!
	  if (prev.next == tail) 
		  tail = prev;
	  prev.next = prev.next.next;
	  size--;
	  return item;
  }
  
  /**
   * Removes and returns the first element from this list. 
   * @return the first element of the list
   * @throws NoSuchElementException if list is empty
   */
  public AnyType removeFirst() throws NoSuchElementException {
	  if (isEmpty())
		  throw new NoSuchElementException("Try to remove the first element of an empty list");
	  return remove(0);
  }
  
  /**
   * Removes and returns the last element from this list. 
   * @return the last element of the list
   * @throws NoSuchElementException if list is empty
   */
  public AnyType removeLast() throws NoSuchElementException {
	  if (isEmpty())
		  throw new NoSuchElementException("Try to remove the last element of an empty list");
	  return remove(size()-1);
  }
  
  /**
   * Removes the first occurrence of the specified element from this list, 
   * 	if it is present. If this list does not contain the element, 
   * 	it is unchanged. 
   * @param x any object that should be removed from the list
   * @return true if this list contained the specified element,
   * 	otherwise return false
   */
  public boolean remove(AnyType x)  {
	  // Put your code here
	  SListNode<AnyType> prev;
	  SListNode<AnyType> curr;
	  curr = head;
	  prev = head;
	  if(head == null){
		  return false;
	  }
	  else{
		  
		while(curr.next != null){
			if(head.item.equals(x)){
				head.next =  curr.next;
				size--;
				return true;
			}
			if(curr.next.item.equals(x)){
			  prev.next = curr.next;
			  size--;
			  return true;
  
			}
		}
	  }
	  
	  return false;
  }
  
	public String toString() {
		// Put your code here!
		SListNode<AnyType> curr;
		String result = "";
		curr = head;
		while(curr != null){
			result = result + curr.item + ", ";
			curr = curr.next;
		}
		return result;
	}
	

  
	public static void main( String [ ] args ) {
		SList<String> groceryList = new SList<String>();
		groceryList.add(0, "milk");
		groceryList.add(0, "bread");
		groceryList.add(0, "cheese");
		groceryList.add(0, "fruit");
		System.out.println("The size of list is: "+groceryList.size());
		groceryList.remove(1);
		System.out.println("List was updated, the size is: "+groceryList.size());
		System.out.println("index 1 is: "+groceryList.get(1));
		System.out.println("The whole list is: "+ groceryList); 
		// the output should look like this: The whole list is: [ fruit bread milk ]
		
		System.out.println("index of value 5 is: "+groceryList.indexOf(5));
		System.out.println("The grocery list contains 'milk'? "+groceryList.contains("milk"));
		groceryList.remove(0);
		System.out.println("The grocery list contains 'milk'? "+groceryList.contains("milk"));
		groceryList.remove(0);
		System.out.println("The grocery list contains 'milk'? "+groceryList.contains("milk"));
		groceryList.remove(0);
		System.out.println("The grocery list contains 'milk'? "+groceryList.contains("milk"));

		
    }

}
