import java.util.Iterator;

public class MyLinkedList<T> {
	Node<T> head = null, tail = null; // head and tail nodes
	private int size = 0; // stores the size of the LL

	public boolean empty() // returns true if the list is empty
	{
		return (size == 0);
	}

	public int size() // returns the size (it is a private int)
	{
		return size;
	}

	public class Node<T> // Each node in the LL is defined as such
	{
		Node(T element) // Node Constructor
		{
			this.element = element;
		}

		T element; // Element of the generic type
		Node<T> next;
		Node<T> previous; // Points to the next node, previous points to the previous node
	}

	public void addFirst(T element) // adds element in the first position
	{
		Node<T> current = new Node<T>(element); // initializes the node for element
		if (empty()) // checks if the current list is empty
			head = tail = current; // if empty set the head & tail to current
		else { // if there is at least 1 element
			current.next = head; // current's next points to head
			head.previous = current; // head's previous points to current
			head = current; // sets head to current
			head.previous = tail; // sets current's previous to tail
			tail.next = head;
		}
		size++; // increase the size
	}

	public void addLast(T element) // adds element in the last position
	{
		Node<T> current = new Node<T>(element); // initializes the node for element
		if (empty()) // checks if the current list is empty
			head = tail = current; // if empty set the head & tail to current
		else {
			tail.next = current; // set's tail's next to current
			current.previous = tail; // sets current's previous to tail
			tail = current; // sets tail to current
			tail.next = head; // current's next points to head
			head.previous = tail;
		}
		size++; // increase the size
	}

	public void add(int index, T element) // adds element to index and pushes the old node back
	{
		if (index == size) {
			addLast(element);
		} else if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			addFirst(element);
		} else {
			Node<T> current = new Node<T>(element); // initializes the node for element
			Node<T> old = get(index);
			current.next = old.next;
			old.next = current;
			current.next.previous = current;
			size++;
		}

	}

	public void add(T element) // adds element to the end
	{
		addLast(element);
	}

	public Node<T> get(int index) // returns the node at index. method made more efficient by "previous"
	{
		if (index == 0) {
			return head;
		} else if (index == size - 1) {
			return tail;
		} else if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		} else if (empty()) {
			return null;
		} else if (index < size() / 2) {
			Node<T> rNode = head;
			for (int i = 0; i < index - 1; i++) {
				rNode = rNode.next;
			}
			return rNode;
		} else {
			Node<T> rNode = tail;
			for (int i = 0; i < size() - index + 1; i++) {
				rNode = rNode.previous;
			}
			return rNode;
		}
	}

	public T removeFirst() // removes the first node and returns the element from the node
	{
		if (head == null)
			return null;
		else if (size() == 1) {
			T element = head.element;
			head = tail = null;
			return element;
		} else {
			T temp = head.element;
			head = head.next;
			head.previous = tail;
			tail.next = head;
			size--;
			return temp;
		}
	}

	public T removeLast() // removes the last node and returns the element from the node
	{
		if (tail == null)
			return null;
		else if (size == 1)
			return removeFirst();
		else {
			T currentElement = tail.element;
			tail = tail.previous;
			tail.next = head;
			head.previous = tail;
			size--;
			return currentElement;
		}
	}

	public T remove(int index) // removes element at index
	{
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else {
			Node<T> previous = head;
			for (int i = 0; i < index - 1; i++)
				previous = previous.next;
			Node<T> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}

	public T set(int index, T element) // sets element at index to a new element
	{
		Node<T> atIndex = get(index);
		T elementAtIndex = atIndex.element;
		atIndex.element = element;
		return elementAtIndex;
	}

	public T getFirst() // returns head's element
	{
		if (empty())
			return null;
		else
			return head.element;
	}

	public T getLast() // returns tail's element
	{
		if (empty())
			return null;
		else
			return tail.element;
	}

	public String toString() // toString override
	{
		StringBuilder sb1 = new StringBuilder();
		Node<T> current = head;
		sb1.append("[");
		for (int i = 0; i < size(); i++) {
			if (i != size - 1)
				sb1.append(current.element + ", ");
			else
				sb1.append(current.element);
			current = current.next;
		}
		sb1.append("]");
		return sb1.toString();
	}

	public Iterator<T> iterator() {
		return new MyIterator();
	}

	
	class MyIterator implements Iterator<T> {
		private Node<T> current = head;

		public boolean hasNext() {
			return (current != null);
		}

		public T next() {
			T tmp = current.element;
			current = current.next;
			return tmp;
		}
	}

}
