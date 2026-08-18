package SmartParking;

import java.util.*;

public class SmartParking {
    public static void main(String[] args) {
        Deque<Vehicle> parkingLot = new ArrayDeque<>();
        ParkingManager manager = new ParkingManager();
        Scanner sc = new Scanner(System.in);

        // ข้อมูลตัวอย่างเริ่มต้น 4 คัน ตาม Case Story [3]
        parkingLot.push(new Vehicle("01", "Somchai", "08:00", "Car"));
        parkingLot.push(new Vehicle("02", "Somsak", "08:30", "SUV"));
        parkingLot.push(new Vehicle("03", "Somying", "09:00", "Car"));
        parkingLot.push(new Vehicle("04", "Somyod", "10:00", "Car"));

        while (true) {
            System.out.println("\n=== Smart Parking System (Group 1) ===");
            System.out.println("Current Parking State (Top to Bottom):");
            if (parkingLot.isEmpty()) {
                System.out.println("[Notice] The parking lot is currently empty."); // การตรวจสอบ Stack ว่าง [4, 5]
            } else {
                parkingLot.forEach(System.out::println);
            }

            System.out.print("\nEnter License Plate to remove (or 'exit'): ");
            String target = sc.next();
            if (target.equalsIgnoreCase("exit")) break;

            System.out.println("Choose Method:");
            System.out.println("1. Iteration (A)");
            System.out.println("2. Recursion (B)");
            System.out.println("3. Compare Both (Show side-by-side)"); // ตัวเลือกที่เพิ่มใหม่
            
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter 1, 2, or 3.");
                sc.next(); continue;
            }
            int choice = sc.nextInt();

            if (choice == 1) {
                AlgorithmResult result = manager.removeIterative(parkingLot, target);
                printSingleResult("Iteration (A)", result, target);
            } 
            else if (choice == 2) {
                AlgorithmResult result = manager.removeRecursive(parkingLot, target);
                printSingleResult("Recursion (B)", result, target);
            } 
            else if (choice == 3) {
                // สร้างสำเนาของ Stack เพื่อให้ทั้งสองวิธีเริ่มทำงานที่จุดเดียวกัน [2]
                Deque<Vehicle> copyForA = new ArrayDeque<>(parkingLot);
                Deque<Vehicle> copyForB = new ArrayDeque<>(parkingLot);

                AlgorithmResult resA = manager.removeIterative(copyForA, target);
                AlgorithmResult resB = manager.removeRecursive(copyForB, target);

                System.out.println("\n--- PERFORMANCE COMPARISON ---");
                System.out.printf("%-15s | %-15s | %-15s\n", "Metric", "Iteration (A)", "Recursion (B)");
                System.out.println("-------------------------------------------------------------");
                System.out.printf("%-15s | %-15s | %-15s\n", "Status", 
                    (resA.success ? "Found" : "Not Found"), (resB.success ? "Found" : "Not Found"));
                System.out.printf("%-15s | %-15d | %-15d\n", "Time (ns)", resA.executionTime, resB.executionTime);
                System.out.printf("%-15s | %-15d | %-15d\n", "Push Ops", resA.pushCount, resB.pushCount);
                System.out.printf("%-15s | %-15d | %-15d\n", "Pop Ops", resA.popCount, resB.popCount);
                
                // อัปเดต Stack จริงหากพบรถ (ใช้ผลจากอันใดอันหนึ่งที่สำเร็จ)
                if (resA.success) parkingLot = copyForA; 
            } 
            else {
                System.out.println("Invalid choice!");
            }
        }
        sc.close();
        System.out.println("System closed.");
    }

    // Helper method สำหรับแสดงผลแบบเดี่ยว
    private static void printSingleResult(String method, AlgorithmResult res, String target) {
        if (res.success) {
            System.out.println(">> Success: Vehicle " + target + " removed using " + method);
        } else {
            System.out.println(">> Failed: Vehicle " + target + " not found.");
        }
        System.out.println("Time: " + res.executionTime + " ns | Push: " + res.pushCount + " | Pop: " + res.popCount);
    }
}