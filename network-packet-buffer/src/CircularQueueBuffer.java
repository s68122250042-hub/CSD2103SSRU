/**
 * CircularQueueBuffer.java
 * Algorithm B: Circular Queue.
 *
 * front and rear wrap around using modulo arithmetic, so freed slots
 * (after dequeue) are reused. This solves the "False Overflow" problem
 * seen in LinearQueueBuffer.
 */
public class CircularQueueBuffer {
    private Packet[] buffer;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueueBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new Packet[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // O(1)
    public boolean enqueue(Packet p) {
        if (size == capacity) {
            System.out.println("[DROP] Buffer truly FULL (" + capacity + " packets). Packet " + p + " dropped.");
            return false;
        }
        rear = (rear + 1) % capacity;
        buffer[rear] = p;
        size++;
        return true;
    }

    // O(1)
    public Packet dequeue() {
        if (size == 0) {
            System.out.println("[EMPTY] No packet to dequeue.");
            return null;
        }
        Packet p = buffer[front];
        buffer[front] = null;
        front = (front + 1) % capacity;
        size--;
        return p;
    }

    // O(1)
    public Packet peek() {
        if (size == 0) return null;
        return buffer[front];
    }

    public int getFront() { return front; }
    public int getRear() { return rear; }
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacity; }

    // O(n)
    public void display() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % capacity;
            sb.append(buffer[idx]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println("Queue: " + sb + "  (front=" + front + ", rear=" + rear + ")");
    }
}
