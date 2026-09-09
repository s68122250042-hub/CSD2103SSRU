/**
 * LinearQueueBuffer.java
 * Algorithm A: Linear (array-based) Queue.
 *
 * front and rear only move forward. Once rear reaches capacity-1,
 * the buffer is treated as FULL even if slots before "front" are empty.
 * This is the classic "False Overflow" problem.
 */
public class LinearQueueBuffer {
    private Packet[] buffer;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public LinearQueueBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new Packet[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // O(1)
    public boolean enqueue(Packet p) {
        if (rear == capacity - 1) {
            // False overflow may occur here even if front slots are free
            System.out.println("[DROP] Buffer FULL (rear at end). Packet " + p + " dropped.");
            return false;
        }
        rear++;
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
        front++;
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

    // O(n)
    public void display() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = front; i <= rear; i++) {
            if (buffer[i] != null) {
                sb.append(buffer[i]);
                if (i != rear) sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println("Queue: " + sb + "  (front=" + front + ", rear=" + rear + ")");
    }
}
