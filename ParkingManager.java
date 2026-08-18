package SmartParking;

import java.util.ArrayDeque;
import java.util.Deque;

public class ParkingManager {
    private int pushCount;
    private int popCount;

    // Algorithm A: Iterative Method (ใช้ Temporary Stack)
    public AlgorithmResult removeIterative(Deque<Vehicle> parkingStack, String targetPlate) {
        pushCount = 0; popCount = 0;
        Deque<Vehicle> tempStack = new ArrayDeque<>();
        boolean found = false;
        long startTime = System.nanoTime();

        while (!parkingStack.isEmpty()) {
            popCount++;
            Vehicle current = parkingStack.pop();
            if (current.getLicensePlate().equalsIgnoreCase(targetPlate)) {
                found = true;
                break;
            }
            tempStack.push(current);
            pushCount++;
        }

        // นำรถกลับเข้าที่เดิม
        while (!tempStack.isEmpty()) {
            parkingStack.push(tempStack.pop());
            pushCount++;
            popCount++;
        }

        long endTime = System.nanoTime();
        return new AlgorithmResult(found, (endTime - startTime), pushCount, popCount);
    }

    // Algorithm B: Recursive Method (เรียกซ้ำและ Backtracking)
    public AlgorithmResult removeRecursive(Deque<Vehicle> stack, String targetPlate) {
        pushCount = 0; popCount = 0;
        long startTime = System.nanoTime();
        
        boolean found = recursiveHelper(stack, targetPlate);
        
        long endTime = System.nanoTime();
        return new AlgorithmResult(found, (endTime - startTime), pushCount, popCount);
    }

    private boolean recursiveHelper(Deque<Vehicle> stack, String targetPlate) {
        if (stack.isEmpty()) return false;

        popCount++;
        Vehicle current = stack.pop();

        if (current.getLicensePlate().equalsIgnoreCase(targetPlate)) {
            return true;
        }

        boolean result = recursiveHelper(stack, targetPlate);

        // นำรถคันที่ขวางอยู่กลับเข้า Stack
        stack.push(current);
        pushCount++;
        
        return result;
    }
}