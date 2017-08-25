package org.virgil.jdk;

/**
 * Created by Virgil on 2017/8/21.
 */
class Link {
    public long data;
    public Link next;
    public Link previous;

    public Link(long data) {
        this.data = data;
    }

    public void displayLink() {
        System.out.print(data + " ");
    }
}

public class DoublyLinkedList {

    private Link first;
    private Link last;

    public DoublyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(long dd) {
        Link newLink = new Link(dd);
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    public void insertLast(long dd) {
        Link newLink = new Link(dd);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
    }

    public Link deleteFirst() {
        Link temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public Link deleteLast() {
        Link temp = last;
        if (last.previous == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }

    public boolean insertAfter(long key, long dd) {
        Link current = first;
        while (current.data != key) {
            current = current.next;
            if (current == null) {
                return false;
            }
        }
        Link newLink = new Link(dd);
        if (current == last) {
            newLink.next = null;
            last = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        newLink.previous = current;
        current.next = newLink;
        return true;
    }

    public Link deleteKey(long key) {
        Link current = first;
        while (current.data != key) {
            current = current.next;
            if (current == null) {
                return null;
            }
        }
        if (current == first) {
            first = current.next;
        } else {
            current.previous.next = current.next;
        }
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        return current;
    }

    public void displayForward() {
        System.out.println("first->last");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    public void displayBackward() {
        System.out.println("Last->First");
        Link current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertFirst(10);
        list.insertFirst(3);
        list.insertFirst(78);
        list.insertFirst(2);
        list.displayBackward();
        list.displayForward();
    }
}
