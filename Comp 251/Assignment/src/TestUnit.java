public class TestUnit{
    public static void main(String[]args){
        ArrayBasedDeque<String> deque = new ArrayBasedDeque<>();
        
        
        System.out.println("Is the Deque empty?: " + deque.isEmpty());
        for(int i = 0; i<10; i++){// testing if the deque is expanded upon reaching max size
            deque.addLast("Bread");
        }
        deque.addFirst("Milk");
        System.out.println(deque);//toString
        System.out.println("First Item in deque: " + deque.getFirst());
        System.out.println("Last Item in deque: " + deque.getLast());
        System.out.println("Item at index:" + 9 + " is " + deque.get(9));
        for(int i = 9; i <20; i++){
            deque.addLast("Bread");
        }

        System.out.println("Size of the deque: " + deque.size());
        deque.front = 0;// logically resetting the deque
        for(int i = deque.rear; i>11; i--){
            System.out.println(deque.removeLast());
        }
        System.out.println();
        for(int i = deque.rear; i>0; i--){
            System.out.println(deque.removeFirst());
        }

        

    }
}
