package datastructures;

import java.util.NoSuchElementException;

// FIFO approach
public class Queue<T> implements IQueue<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;
	
	private static class Node<T>{
		private T item;
		private Node<T> next;
	}
	
	public Queue() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.first == null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T peek() {
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		return this.first.item;
	}

	@Override
	public void enqueue(T item) {
		Node<T> oldLast = this.last;
		this.last = new Node<T>();
		this.last.item = item;
		this.last.next = null;
		if(isEmpty()) this.first = this.last;
		else oldLast.next = this.last;
		this.size++;
	}

	@Override
	public T dequeue() {
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		T item = this.first.item;
		this.first = this.first.next;
		this.size--;
		if(isEmpty()) this.last = null;
		return item;
	}
	public boolean delete(T item) {
		if(isEmpty()) throw new NoSuchElementException("Queue underflow");
		Node<T> current = this.first;
		Node<T> previous = null;
		while(current != null) {
			if(current.item.equals(item)) {
				if(previous == null) {
					this.first = current.next;
				}else {
					previous.next = current.next;
				}
				this.size--;
				return true;
			}
			previous = current;
			current = current.next;
		}
		return false;
	}
}
