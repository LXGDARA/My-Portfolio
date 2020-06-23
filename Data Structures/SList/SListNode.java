public class SListNode<AnyType> { 
    public AnyType item;
    public SListNode<AnyType> next;
    
    public SListNode(AnyType d, SListNode<AnyType> next) {
    	this.item = d;
    	this.next = next;
    }
    
    public SListNode(AnyType d) {
    	this(d, null);
    }
}