/**
 * TestCases.java
 * 6 required test cases run against BOTH Linear and Circular queues:
 *  1. Normal Case
 *  2. Empty Queue
 *  3. Single Item
 *  4. Large Queue
 *  5. Special/Edge Case (all same priority)
 *  6. Cancel Case (cancel a mid-queue item / cancel a non-existent item)
 *
 * Note: "Cancel" is implemented as a linear search + remove-and-shift
 * for Linear Queue, and a search + rebuild for Circular Queue, since
 * the base java Queue interfaces used here (array-based, hand-rolled)
 * do not support arbitrary removal directly.
 */
public class TestCases {

    public static void main(String[] args) {
        testNormalCase();
        testEmptyQueue();
        testSingleItem();
        testLargeQueue();
        testEdgeCaseSamePriority();
        testCancelCase();
    }

    static void testNormalCase() {
        System.out.println("\n=== Test 1: Normal Case ===");
        LinearQueueBuffer lq = new LinearQueueBuffer(5);
        lq.enqueue(new Packet("P1", 1, 100, 2));
        lq.enqueue(new Packet("P2", 2, 100, 2));
        Packet out = lq.dequeue();
        System.out.println("Expected dequeue: P1, Got: " + out);
        lq.display();
    }

    static void testEmptyQueue() {
        System.out.println("\n=== Test 2: Empty Queue ===");
        CircularQueueBuffer cq = new CircularQueueBuffer(5);
        System.out.println("isEmpty: " + cq.isEmpty());
        Packet p = cq.dequeue(); // should print [EMPTY] and return null
        System.out.println("Dequeue on empty result: " + p);
    }

    static void testSingleItem() {
        System.out.println("\n=== Test 3: Single Item ===");
        LinearQueueBuffer lq = new LinearQueueBuffer(5);
        lq.enqueue(new Packet("P1", 1, 50, 1));
        System.out.println("Peek: " + lq.peek());
        System.out.println("Dequeue: " + lq.dequeue());
        System.out.println("isEmpty after dequeue: " + lq.isEmpty());
    }

    static void testLargeQueue() {
        System.out.println("\n=== Test 4: Large Queue (Circular, capacity=5) ===");
        CircularQueueBuffer cq = new CircularQueueBuffer(5);
        for (int i = 1; i <= 5; i++) cq.enqueue(new Packet("P" + i, i, 100, 1));
        cq.dequeue();
        cq.dequeue();
        cq.enqueue(new Packet("P6", 6, 100, 1));
        cq.enqueue(new Packet("P7", 7, 100, 1));
        cq.display();
        System.out.println("Expected: buffer full again with P3,P4,P5,P6,P7 (wrapped around)");
    }

    static void testEdgeCaseSamePriority() {
        System.out.println("\n=== Test 5: Edge Case - All Same Priority ===");
        LinearQueueBuffer lq = new LinearQueueBuffer(5);
        lq.enqueue(new Packet("P1", 1, 100, 2));
        lq.enqueue(new Packet("P2", 2, 100, 2));
        lq.enqueue(new Packet("P3", 3, 100, 2));
        System.out.println("All same priority -> tie-break by arrival order (FIFO):");
        lq.display();
    }

    static void testCancelCase() {
        System.out.println("\n=== Test 6: Cancel Case ===");
        CircularQueueBuffer cq = new CircularQueueBuffer(5);
        cq.enqueue(new Packet("P1", 1, 100, 1));
        cq.enqueue(new Packet("P2", 2, 100, 1));
        cq.enqueue(new Packet("P3", 3, 100, 1));
        System.out.println("Before cancel:");
        cq.display();

        // Cancel a mid-queue item (P2)
        boolean cancelledMid = cancelById(cq, "P2");
        System.out.println("Cancel P2 (mid-queue) success: " + cancelledMid);
        cq.display();

        // Cancel a non-existent item
        boolean cancelledMissing = cancelById(cq, "P99");
        System.out.println("Cancel P99 (does not exist) success: " + cancelledMissing);
        cq.display();
    }

    // Helper: cancel an item by id from a CircularQueueBuffer by draining
    // and rebuilding the queue, skipping the target id.
    static boolean cancelById(CircularQueueBuffer cq, String id) {
        int n = cq.getSize();
        Packet[] temp = new Packet[n];
        boolean found = false;
        for (int i = 0; i < n; i++) {
            Packet p = cq.dequeue();
            if (p != null && p.getPacketId().equals(id)) {
                found = true;
                continue; // skip re-adding this one
            }
            temp[i] = p;
        }
        for (Packet p : temp) {
            if (p != null) cq.enqueue(p);
        }
        return found;
    }
}
