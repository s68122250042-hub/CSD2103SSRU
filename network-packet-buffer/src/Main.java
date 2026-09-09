/**
 * Main.java
 * Runs the mandatory scenario from the assignment:
 *   ENQUEUE P1, ENQUEUE P2, ENQUEUE P3, DEQUEUE, DEQUEUE,
 *   ENQUEUE P4, ENQUEUE P5, ENQUEUE P6
 * on both LinearQueueBuffer and CircularQueueBuffer (capacity = 5)
 * and prints front/rear/queue at every step for comparison.
 */

public class Main {

    public static void main(String[] args) {
        int capacity = 5;

        System.out.println("================ ALGORITHM A: LINEAR QUEUE ================");
        LinearQueueBuffer linear = new LinearQueueBuffer(capacity);
        runScenarioLinear(linear);

        System.out.println();
        System.out.println("================ ALGORITHM B: CIRCULAR QUEUE ================");
        CircularQueueBuffer circular = new CircularQueueBuffer(capacity);
        runScenarioCircular(circular);
    }

    private static void runScenarioLinear(LinearQueueBuffer q) {
        step(1, "ENQUEUE P1", () -> q.enqueue(new Packet("P1", 1, 120, 2)));
        q.display();
        step(2, "ENQUEUE P2", () -> q.enqueue(new Packet("P2", 2, 80, 1)));
        q.display();
        step(3, "ENQUEUE P3", () -> q.enqueue(new Packet("P3", 3, 200, 3)));
        q.display();
        step(4, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(5, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(6, "ENQUEUE P4", () -> q.enqueue(new Packet("P4", 6, 60, 2)));
        q.display();
        step(7, "ENQUEUE P5", () -> q.enqueue(new Packet("P5", 7, 150, 1)));
        q.display();
        step(8, "ENQUEUE P6", () -> q.enqueue(new Packet("P6", 8, 90, 2)));
        q.display();
        System.out.println(">> Note: rear has reached capacity-1 even though front slots are free -> FALSE OVERFLOW.");
        step(9, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(10, "ENQUEUE P7", () -> q.enqueue(new Packet("P7", 10, 70, 3)));
        q.display();
        step(11, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(12, "ENQUEUE P8", () -> q.enqueue(new Packet("P8", 12, 110, 1)));
        q.display();
        System.out.println(">> Note: even after freeing more front slots, rear stays stuck at capacity-1 -> every later ENQUEUE keeps being dropped.");
    }

    private static void runScenarioCircular(CircularQueueBuffer q) {
        step(1, "ENQUEUE P1", () -> q.enqueue(new Packet("P1", 1, 120, 2)));
        q.display();
        step(2, "ENQUEUE P2", () -> q.enqueue(new Packet("P2", 2, 80, 1)));
        q.display();
        step(3, "ENQUEUE P3", () -> q.enqueue(new Packet("P3", 3, 200, 3)));
        q.display();
        step(4, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(5, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(6, "ENQUEUE P4", () -> q.enqueue(new Packet("P4", 6, 60, 2)));
        q.display();
        step(7, "ENQUEUE P5", () -> q.enqueue(new Packet("P5", 7, 150, 1)));
        q.display();
        step(8, "ENQUEUE P6", () -> q.enqueue(new Packet("P6", 8, 90, 2)));
        q.display();
        System.out.println(">> Note: freed slots (index 0,1) were reused via modulo -> NO false overflow.");
        step(9, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(10, "ENQUEUE P7", () -> q.enqueue(new Packet("P7", 10, 70, 3)));
        q.display();
        step(11, "DEQUEUE", () -> { Packet p = q.dequeue(); System.out.println("Output: " + p); });
        q.display();
        step(12, "ENQUEUE P8", () -> q.enqueue(new Packet("P8", 12, 110, 1)));
        q.display();
        System.out.println(">> Note: buffer keeps wrapping around indefinitely -> full 5-slot capacity stays usable forever.");
    }

    private static void step(int n, String op, Runnable action) {
        System.out.println("\nStep " + n + " [" + op + "]");
        action.run();
    }
}