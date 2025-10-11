

class LinkedList{

class Node {
    int data;
    Node next;

    public Node(int data ){
        this.data = data ;
        this.next = null;  
    }

}

    public static Node head ; 
    public static Node tail; 
     
    public stattic void main(String args []){
        LinkedList ll = new LinkedList();
        ll.head = new Node(1);
        ll.head.next = new Node(2); 

    }
}