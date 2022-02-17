
// Taranjot Singh Dhaliwal
// 3112404

public class CircularDoublyLinkedList<E> {
    private static class Node<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n) {
            element=e;
            prev=p;
            next=n;
        }

        public E getElement() { return element;}

        public Node<E> getPrev(){return prev;}

        public Node<E> getNext(){return next;}

        public void setPrev(Node<E> p) { prev=p;}

        public void setNext(Node<E> n) { next=n;}	
    }	
    private  Node<E> tail=null;
    private int size=0;
    boolean forward = true;

    public CircularDoublyLinkedList() {}

    public int size() {return size;}

    public boolean isEmpty() { return size==0;}

    public E first() {
        if(isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if(isEmpty()) return null;
        return tail.getElement();
    }

    public void rotate() {
        if(forward) {
            if(tail!=null)
                tail = tail.getPrev(); 
        }
        else {
            if(tail!=null)
                tail = tail.getNext(); 
        }
    }

    public void addFirst(E e) {
        if(size==0) {
            tail = new Node<>(e,null,null);
            tail.setNext(tail);
            tail.setPrev(tail);

        }
        else {
            addBetween(e, tail, tail.getNext());
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e);
        tail= tail.getNext();
    }

    public E removeFirst() {
        if(isEmpty()) return null;
        Node<E> head = tail.getNext();
        Node<E> z = head.getNext();
        if(head==tail)tail = null;
        else {
            tail.setNext(z);
            z.setPrev(tail);
        }
        size--;
        return head.getElement();
    }

    public boolean reverse() {
        if(forward) {
            forward= false;
            return forward;

        }
        forward = true;
        return forward;
    }

    public void addBetween(E e, Node<E> predecessor,Node<E> successor) {
        Node<E> newest = new Node<>(e,predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
    }

}
