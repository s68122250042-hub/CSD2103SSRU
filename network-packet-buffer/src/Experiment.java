import java.util.Random;

/**
 * Experiment.java
 * Measures enqueue+dequeue execution time for Linear vs Circular Queue
 * at n = 100, 1000, 10000, 50000.
 *
 * Methodology (per course requirement):
 *  - System.nanoTime() for timing
 *  - 1 warm-up run (discarded) before measured runs
 *  - Fixed random seed for reproducibility
 *  - Average of 5 measured rounds
 *
 * NOTE: Buffer capacity is set equal to n in this experiment so that
 * enqueue never gets dropped (we are timing raw operation cost, not
 * drop behavior — drop behavior is already demonstrated in Main.java).
 */
public class Experiment {

    static final int ROUNDS = 5;
    static final long SEED = 42L;

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 50000};

        System.out.printf("%-10s %-20s %-20s%n", "n", "Linear Queue (ms)", "Circular Queue (ms)");
        for (int n : sizes) {
            // warm-up (discarded)
            runLinear(n);
            runCircular(n);

            double linearTotal = 0;
            double circularTotal = 0;
            for (int r = 0; r < ROUNDS; r++) {
                linearTotal += runLinear(n);
                circularTotal += runCircular(n);
            }
            double linearAvgMs = (linearTotal / ROUNDS) / 1_000_000.0;
            double circularAvgMs = (circularTotal / ROUNDS) / 1_000_000.0;

            System.out.printf("%-10d %-20.4f %-20.4f%n", n, linearAvgMs, circularAvgMs);
        }

        System.out.println("\nExpected theory: both enqueue/dequeue are O(1) per operation,");
        System.out.println("so total time for n operations should scale ~linearly with n for both.");
        System.out.println("Circular Queue avoids false overflow, so it can sustain long enqueue/dequeue");
        System.out.println("cycles without needing a larger buffer, unlike Linear Queue.");
    }

    // returns elapsed nanoseconds for n enqueue + n dequeue operations
    private static long runLinear(int n) {
        Random rand = new Random(SEED);
        LinearQueueBuffer q = new LinearQueueBuffer(n);
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            q.enqueue(new Packet("P" + i, i, rand.nextInt(500), rand.nextInt(4) + 1));
        }
        for (int i = 0; i < n; i++) {
            q.dequeue();
        }
        return System.nanoTime() - start;
    }

    private static long runCircular(int n) {
        Random rand = new Random(SEED);
        CircularQueueBuffer q = new CircularQueueBuffer(n);
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            q.enqueue(new Packet("P" + i, i, rand.nextInt(500), rand.nextInt(4) + 1));
        }
        for (int i = 0; i < n; i++) {
            q.dequeue();
        }
        return System.nanoTime() - start;
    }
}
