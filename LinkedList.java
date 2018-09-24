/*
 * Name: Hengcheng Yu
 * Date: 2017/04/27
 * Desc: This class implements LinkedListADT.
 * 
 */
class LinkedList<T> implements LinkedListADT<T> {
    private ListNode<T> front = null;
    private int numberOfNodes = 0; 
    
    
    
    // Returns true if the linked list has no nodes, or false otherwise.
    @Override
    public boolean isEmpty() {
        return (front == null);
    }
    // Deletes all of the nodes in the linked list.
    // Note: ListNode objects will be automatically garbage collected by JVM.
    @Override
    public void clear() {
        front = null;
        numberOfNodes = 0;
    }
    
    // Returns the number of nodes in the linked list
    @Override
    public int size() {
        return numberOfNodes;
    }
    
    // Adds a node to the front of the linked list.
    @Override
    public void addFirst( T element ) {
        front = new ListNode<T>( element, front );
        numberOfNodes++;
    }
    
    // Returns a reference to the data in the first node, or null if the list is empty.
    @Override
    public T peekFirst() {
        if ( isEmpty() ) 
            return null;
        
        return front.getData();
    }
    
    // Removes a node from the front of the linked list (if there is one).
    // Returns a reference to the data in the first node, or null if the list is empty.
    @Override
    @SuppressWarnings("unchecked")
    public T removeFirst() {
        T tempData;
        
        if ( isEmpty() ) 
            return null;
        
        tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }
    
    // Returns true if the linked list contains a certain element, or false otherwise.
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains( T key ) {
        ListNode<T> searchNode;
        searchNode = front;
        while ( ( searchNode != null ) && ( !key.equals( searchNode.getData() ) ) ) {
            searchNode = searchNode.getNext();
        }
        return ( searchNode != null );
    }
    
    // Return String representation of the linked list.
    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        ListNode<T> node;
        String linkedList = "FRONT ==> ";
        
        node = front;
        while (node != null) {
            linkedList += "[ " + node.getData() + " ] ==> ";
            node = node.getNext();
        }
        
        return linkedList + "NULL";
    }
     // Add an element to the end of the linked list
    @SuppressWarnings("unchecked")
    public void addLast( T element ) {
        if (isEmpty()){
            addFirst(element);
        }
        else{
            ListNode<T> last = front;
            for (int counter = 1; counter<size();counter++){
                last = last.getNext();
            }
            last.setNext(new ListNode<T>( element));
            numberOfNodes++;
        }
    }
    
    // Remove an element to the end of the linked list
    @SuppressWarnings("unchecked")
    public T removeLast() {
        if (size()<=1){
            return removeFirst();
        }
        else{
            ListNode temp;
            ListNode secLast = front;
            for (int counter = 2 ; counter < size() ; counter++){
                secLast = secLast.getNext();
            }
            temp = secLast.getNext();
            secLast.setNext(null);
            numberOfNodes--;
            return (T)temp.getData();
        }
    }
    
}