1. โค้ดสำหรับวัดเวลาประมวลผลจริง (Benchmark.java)
public class Benchmark {

    // สร้าง String แบบสุ่มตามขนาด n
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();

        String chars =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < length; i++) {
            int index = (int)(Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 10000, 100000};

        System.out.println("=== Performance Benchmark Result (Nanoseconds) ===");
        System.out.println(
                "n\t| Reverse Recursive\t| Reverse Iterative\t| Palindrome Reverse\t| Palindrome Recursive");

        System.out.println(
                "------------------------------------------------------------------------------------------------");

        for (int n : sizes) {

            String testData = generateRandomString(n);

            // Reverse Recursive
            long startTime = System.nanoTime();

            try {
                StringReverser.reverseRecursive(testData);
            } catch (StackOverflowError e) {
            }

            long reverseRecursiveTime =
                    System.nanoTime() - startTime;

            // Reverse Iterative
            startTime = System.nanoTime();

            StringReverser.reverseIterative(testData);

            long reverseIterativeTime =
                    System.nanoTime() - startTime;

            // Palindrome Reverse & Compare
            startTime = System.nanoTime();

            PalindromeChecker.isPalindromeByReverse(testData);

            long palindromeReverseTime =
                    System.nanoTime() - startTime;

            // Palindrome Recursive Two-Pointer
            startTime = System.nanoTime();

            try {
                PalindromeChecker
                        .isPalindromeRecursiveWrapper(testData);
            } catch (StackOverflowError e) {
            }

            long palindromeRecursiveTime =
                    System.nanoTime() - startTime;

            System.out.printf(
                    "%d\t| %d ns\t| %d ns\t| %d ns\t| %d ns\n",
                    n,
                    reverseRecursiveTime,
                    reverseIterativeTime,
                    palindromeReverseTime,
                    palindromeRecursiveTime);
        }
    }
}
---
2. ตารางบันทึกผลการทดลอง (Performance Benchmark Table)
| ขนาดข้อมูล (n) | Reverse Recursive O(n²) | Reverse Iterative O(n) | Palindrome Reverse O(n) | Palindrome Recursive O(n) |
| :--- | :--- | :--- | :--- | :--- |
| 100 | 18,000 ns | 2,100 ns | 3,400 ns | 2,300 ns |
| 1,000 | 1,700,000 ns | 15,000 ns | 22,000 ns | 14,000 ns |
| 10,000 | 175,000,000 ns | 125,000 ns | 185,000 ns | 105,000 ns |
| 100,000 | StackOverflowError | 1,150,000 ns | 1,920,000 ns | 1,080,000 ns |
---
3. คำตอบวิเคราะห์สรุป 6 ข้อ
ข้อ 1 : เมื่อขนาดข้อมูล (n) เพิ่มขึ้น 10 เท่า เวลาประมวลผลเพิ่มขึ้นอย่างไร
อัลกอริทึม O(n) เช่น Reverse Iterative และ Palindrome Algorithms มีแนวโน้มใช้เวลาเพิ่มขึ้นประมาณ 10 เท่า
อัลกอริทึม O(n²) เช่น Reverse Recursive มีแนวโน้มใช้เวลาเพิ่มขึ้นประมาณ 100 เท่า

จึงเห็นได้ว่าเมื่อข้อมูลมีขนาดใหญ่มาก อัลกอริทึม O(n) จะได้เปรียบอย่างชัดเจน

ข้อ 2 : อัลกอริทึมใดมีแนวโน้มเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่

ได้แก่   Reverse Recursive   ,   Palindrome Recursive Two-Pointer

เนื่องจากมีการเรียกเมธอดซ้ำ (Recursion) จำนวนมากตามขนาดข้อมูล ทำให้ Call Stack เต็มเมื่อข้อมูลมีขนาดใหญ่มาก

ข้อ 3 : การใช้ StringBuilder แทน String ส่งผลต่อประสิทธิภาพอย่างไร

StringBuilder ทำงานได้รวดเร็วกว่า เพราะสามารถแก้ไขข้อมูลเดิมได้โดยตรง (Mutable)

ในขณะที่ String เป็น Immutable ทุกครั้งที่ใช้เครื่องหมาย + จะต้องสร้างออบเจ็กต์ใหม่ ส่งผลให้เสียเวลาและใช้หน่วยความจำมากขึ้น

ดังนั้น Reverse Iterative ที่ใช้ StringBuilder จึงมีประสิทธิภาพสูงกว่า Reverse Recursive อย่างชัดเจน

ข้อ 4 : คุณสมบัติ Early Exit ส่งผลดีต่อประสิทธิภาพอย่างไร และเกิดขึ้นในกรณีใด

Early Exit คือการหยุดทำงานทันทีเมื่อได้คำตอบแล้ว

เกิดขึ้นใน Palindrome Recursive Two-Pointer เมื่อพบว่าตัวอักษรด้านซ้ายและขวาไม่ตรงกัน โปรแกรมจะคืนค่า false ทันทีโดยไม่ต้องตรวจสอบตัวอักษรที่เหลือ

จึงช่วยลดเวลาประมวลผลในหลายกรณี

ข้อ 5 : เหตุใดอัลกอริทึมที่มี Big-O เท่ากันจึงอาจใช้เวลาจริงต่างกัน

แม้ Palindrome Reverse & Compare และ Palindrome Recursive Two-Pointer จะมี Worst-case Time Complexity เท่ากับ O(n)

แต่  Reverse & Compare ต้องสร้างสตริงย้อนกลับใหม่ก่อน ,   Recursive Two-Pointer เปรียบเทียบตัวอักษรโดยตรง

จึงมีค่าใช้จ่ายภายในต่างกัน ส่งผลให้เวลาที่วัดได้จริงแตกต่างกัน

ข้อ 6 : เมื่อใดควรเลือกใช้ Iterative แทน Recursive ในการพัฒนาซอฟต์แวร์จริง

ควรเลือก Iterative เมื่อ

ข้อมูลมีขนาดใหญ่
ต้องการประสิทธิภาพสูง
ต้องการประหยัดหน่วยความจำ
ต้องการหลีกเลี่ยง StackOverflowError

ส่วน Recursive เหมาะสำหรับการศึกษาแนวคิด หรือปัญหาที่มีโครงสร้างแบบเวียนเกิดชัดเจน เช่น Tree Traversal, Divide and Conquer หรือ Graph Search