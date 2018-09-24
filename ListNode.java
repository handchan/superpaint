/*
 * Name: Hengcheng Yu
 * Date: 2017/04/27
 * Desc: This class defines the ListNode.
 * 
 */
public class ListNode<T> {
    private T data;
    private ListNode next;
    
    // Constructor: No reference to next node provided so make it null 
    public ListNode( T nodeData ) {
        this( nodeData, null);
    }
    
    // Constructor: Set data and reference to next node.
    public ListNode( T nodeData, ListNode nodeNext ) {
        data = nodeData;
        next = nodeNext;
    }
    
    // Accessor: Return the data for current ListNode object
    public T getData() {
        return data;
    }
    
    // Accessor: Return reference to next ListNode object
    public ListNode getNext() {
        return next;
    }
    
    // Mutator: Set new data for current ListNode object
    public void setData( T newData ) {
        data = newData;
    } 
    
    // Mutator: Set new reference to the next node object
    public void setNext( ListNode newNext ) {
        next = newNext;
    }
}