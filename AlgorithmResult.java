package SmartParking;

public class AlgorithmResult {
    public boolean success;
    public long executionTime;
    public int pushCount;
    public int popCount;

    public AlgorithmResult(boolean success, long time, int push, int pop) {
        this.success = success;
        this.executionTime = time;
        this.pushCount = push;
        this.popCount = pop;
    }
}