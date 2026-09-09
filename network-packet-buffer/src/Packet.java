/**
 * Packet.java
 * Represents a single Network Packet stored in the router buffer.
 */
public class Packet {
    private String packetId;
    private int arrivalTime;
    private int size;      // in KB
    private int priority;  // 1 = highest priority

    public Packet(String packetId, int arrivalTime, int size, int priority) {
        this.packetId = packetId;
        this.arrivalTime = arrivalTime;
        this.size = size;
        this.priority = priority;
    }

    public String getPacketId() { return packetId; }
    public int getArrivalTime() { return arrivalTime; }
    public int getSize() { return size; }
    public int getPriority() { return priority; }

    @Override
    public String toString() {
        return packetId;
    }
}
