
public class LinkedListPriorityQueue<T> {

    private class QueueNode {

        T data;
        double priority;
        QueueNode next;

        public QueueNode(T data, double priority) {
            this.data = data;
            this.priority = priority;
            this.next = null;
        }
    }

    private QueueNode head;

    public LinkedListPriorityQueue() {
        head = null;
    }

    // Inserts the item with its priority (higher values come first)
    public void enqueue(T data, double priority) {
        QueueNode newNode = new QueueNode(data, priority);
        if (head == null || priority > head.priority) {
            newNode.next = head;
            head = newNode;
        } else {
            QueueNode current = head;
            while (current.next != null && current.next.priority >= priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    // Removes and returns the highest-priority element
    public T dequeue() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        head = head.next;
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
