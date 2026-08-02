ข้อ 2 การตรวจสอบ Palindrome
1. คำอธิบายแนวคิดของอัลกอริทึม
การจัดการสตริงก่อนประมวลผล (Preprocessing)

เพื่อให้โปรแกรมสามารถตรวจสอบ Palindrome ได้ถูกต้องตามเงื่อนไขที่กำหนด จึงมีการสร้างเมธอด cleanString(String s) สำหรับแปลงตัวอักษรทั้งหมดให้เป็นตัวพิมพ์เล็ก และลบอักขระที่ไม่ใช่ตัวอักษรภาษาอังกฤษและตัวเลขออกด้วย Regular Expression replaceAll("[^a-zA-Z0-9]", "") เพื่อให้การเปรียบเทียบไม่คำนึงถึงตัวพิมพ์เล็ก–ใหญ่ ช่องว่าง และเครื่องหมายวรรคตอน

อัลกอริทึมที่ 1 : Reverse and Compare (isPalindromeByReverse)

แนวคิด: นำสตริงที่ผ่านการทำความสะอาดแล้วมาสร้างสตริงย้อนกลับด้วย StringBuilder.reverse() จากนั้นเปรียบเทียบสตริงย้อนกลับกับสตริงเดิมโดยใช้ equals() หากข้อความทั้งสองเหมือนกัน แสดงว่าสตริงนั้นเป็น Palindrome และคืนค่า true มิฉะนั้นคืนค่า false

อัลกอริทึมที่ 2 : Recursive Two-Pointer (isPalindromeRecursive)

แนวคิด: ใช้วิธีเปรียบเทียบตัวอักษรจากทั้งสองด้านของสตริง โดยตรวจสอบตัวอักษรตำแหน่งซ้ายสุด (left) และขวาสุด (right) หากตัวอักษรตรงกันจะขยับตัวชี้เข้าหากันทีละตำแหน่งและเรียกเมธอดซ้ำ จนกว่าจะตรวจสอบครบทุกคู่

Base Case 1: หาก left >= right แสดงว่าตรวจสอบจนถึงกึ่งกลางของสตริงแล้วโดยไม่พบความแตกต่าง ให้คืนค่า true

Base Case 2: หากตัวอักษรที่ตำแหน่ง left และ right ไม่ตรงกัน ให้คืนค่า false ทันที (Early Exit)

Recursive Case: หากตัวอักษรตรงกัน ให้ขยับตัวชี้ `left + 1` และ `right - 1` เพื่อเวียนเกิดเข้าไปตรวจสอบคู่ถัดไป

2. Pseudocode
// เมธอดช่วยทำความสะอาดสตริง
Algorithm cleanString(s)

If s is null Then
    Return ""
End If

Remove all non-alphanumeric characters from s
Convert s to lowercase

Return cleaned s

End Algorithm

// 1. Reverse and Compare
Algorithm isPalindromeByReverse(s)

cleanS = cleanString(s)

reversedS = Reverse cleanS using StringBuilder

Return cleanS equals reversedS

End Algorithm

// 2. Recursive Two-Pointer
Algorithm isPalindromeRecursive(cleanS, left, right)

If left >= right Then
    Return true
End If

If cleanS[left] != cleanS[right] Then
    Return false
End If

Return isPalindromeRecursive(cleanS, left + 1, right - 1)

End Algorithm
3. โปรแกรมภาษา Java

public class PalindromeChecker {
    /
      เมธอดช่วยสำหรับทำความสะอาดสตริง
      ลบช่องว่าง เครื่องหมายวรรคตอน และแปลงเป็นตัวพิมพ์เล็ก
     /
    private static String cleanString(String s) {
        if (s == null) {
            return ""; // ตรวจสอบป้องกัน NullPointerException
        }
        // ลบตัวอักษรที่ไม่ใช่ a-z, A-Z, 0-9 ออก แล้วแปลงเป็นตัวพิมพ์เล็ก
        return s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    /
      อัลกอริทึมที่ 1: Reverse and Compare
      สร้างสตริงย้อนกลับ แล้วนำมาเปรียบเทียบกับสตริงเดิม
     /
    public static boolean isPalindromeByReverse(String s) {
        if (s == null) return false;
        String clean = cleanString(s);
        // สร้างสตริงย้อนกลับด้วย StringBuilder
        String reversed = new StringBuilder(clean).reverse().toString();
        return clean.equals(reversed);
    }

    /
      อัลกอริทึมที่ 2: Recursive Two-Pointer (เมธอดเวียนเกิดหลัก)
      Base Case 1: left >= right คืนค่า true
      Base Case 2: s[left] != s[right] คืนค่า false (Early Exit)
      Recursive Case: ขยับ left + 1 และ right - 1
     /
    public static boolean isPalindromeRecursive(String s, int left, int right) {
        // Base Case 1: ตรวจสอบจนชนหรือสวนกันตรงกลาง แสดงว่าเป็น Palindrome
        if (left >= right) {
            return true;
        }
        // Base Case 2: พบตัวอักษรคู่ที่ไม่ตรงกัน หยุดทำงานทันที
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        // Recursive Case: เวียนเกิดเข้าไปตรวจคู่ถัดไป
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    /
      เมธอดครอบ (Wrapper Method) สำหรับ Recursive Two-Pointer
      ช่วยทำความสะอาดข้อมูล และส่งค่า left, right เริ่มต้นให้อัตโนมัติ
     /
    public static boolean isPalindromeRecursiveWrapper(String s) {
        if (s == null) return false;
        String clean = cleanString(s);
        if (clean.isEmpty()) return true; // สตริงว่างถือว่าเป็น Palindrome
        return isPalindromeRecursive(clean, 0, clean.length() - 1);
    }

    public static void main(String[] args) {
        // 1. ทดสอบกรณีทั่วไปและกรณีตามโจทย์
        String[] testCases = {
            "racecar",
            "level",
            "algorithm",
            "gohangasalamiimalasagnahog",
            "A man, a plan, a canal: Panama" // ละเว้นช่องว่างและเครื่องหมายวรรคตอน
        };

        System.out.println("=== Testing Normal & Advanced Cases ===");
        for (String test : testCases) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("  Reverse & Compare:   " + isPalindromeByReverse(test));
            System.out.println("  Recursive 2-Pointer: " + isPalindromeRecursiveWrapper(test));
            System.out.println("----------------------------------------");
        }

        // 2. ทดสอบกรณีพิเศษ (Edge Cases)
        System.out.println("\n=== Testing Edge Cases ===");
        System.out.println("Null Input:   " + isPalindromeByReverse(null));
        System.out.println("Empty String: " + isPalindromeRecursiveWrapper(""));
        System.out.println("Single Char:  " + isPalindromeRecursiveWrapper("a"));
    }
}

4. ข้อมูลนำเข้าและผลลัพธ์
Input: "racecar"                        -> Result: true
Input: "level"                          -> Result: true
Input: "algorithm"                      -> Result: false
Input: "gohangasalamiimalasagnahog"     -> Result: true
Input: "A man, a plan, a canal: Panama" -> Result: true
5. วิเคราะห์ Time Complexity
อัลกอริทึมที่ 1 (Reverse and Compare): O(n)

เหตุผลสนับสนุน: มีการประมวลผลสตริงทุกตัวอักษรในการทำความสะอาดข้อมูล จากนั้นสร้างสตริงย้อนกลับ และเปรียบเทียบสตริงทั้งสอง ซึ่งแต่ละขั้นตอนใช้เวลาเชิงเส้นตามจำนวนตัวอักษร ดังนั้น Time Complexity รวมจึงเป็น O(n) ทั้งในกรณี Best-case และ Worst-case

อัลกอริทึมที่ 2 (Recursive Two-Pointer)

Best-case Time Complexity: O(1)

เหตุผลสนับสนุน: หากตัวอักษรคู่แรกที่เปรียบเทียบไม่ตรงกัน อัลกอริทึมจะคืนค่า false และหยุดการทำงานทันทีโดยไม่ต้องตรวจสอบตัวอักษรที่เหลือ

Worst-case Time Complexity: O(n)

เหตุผลสนับสนุน: หากสตริงเป็น Palindrome หรือความแตกต่างอยู่ช่วงท้าย จะต้องเปรียบเทียบตัวอักษรจนถึงกึ่งกลางของสตริง ทำให้ใช้เวลาเป็นสัดส่วนกับจำนวนตัวอักษรทั้งหมด

6. วิเคราะห์ Space Complexity
อัลกอริทึมที่ 1 (Reverse and Compare): O(n)

เหตุผลสนับสนุน: ต้องใช้พื้นที่หน่วยความจำสำหรับเก็บสตริงที่ผ่านการทำความสะอาดและสตริงที่ถูกกลับลำดับ จึงใช้พื้นที่เพิ่มเติมตามขนาดของข้อมูล

อัลกอริทึมที่ 2 (Recursive Two-Pointer): O(n)

เหตุผลสนับสนุน: แม้ว่าจะไม่มีการสร้างสตริงย้อนกลับ แต่มีการเรียกเมธอดแบบ Recursion ทำให้เกิด Call Stack สูงสุดประมาณครึ่งหนึ่งของความยาวสตริง ซึ่งยังคงคิดเป็น O(n)

7. วิเคราะห์เพิ่มเติม
กรณีที่สตริงเป็น Palindrome

Reverse and Compare จะต้องสร้างสตริงย้อนกลับทั้งหมดก่อนจึงเปรียบเทียบกับสตริงเดิม ทำให้ใช้เวลา O(n) ส่วน Recursive Two-Pointer จะเปรียบเทียบตัวอักษรจากทั้งสองด้านจนถึงกึ่งกลางของสตริง ใช้เวลา O(n) เช่นเดียวกัน

กรณีที่ตัวอักษรคู่แรกไม่ตรงกัน

Reverse and Compare ยังคงต้องสร้างสตริงย้อนกลับทั้งหมดก่อน จึงจะสามารถเปรียบเทียบและคืนค่าได้ ทำให้ยังใช้เวลา O(n)

Recursive Two-Pointer สามารถตรวจพบความแตกต่างตั้งแต่การเปรียบเทียบครั้งแรก และคืนค่า false ได้ทันที จึงมี Best-case Time Complexity เท่ากับ O(1)

ความสามารถในการหยุดทำงานก่อนครบทุกตัวอักษร (Early Exit)

Reverse and Compare ไม่สามารถหยุดการทำงานก่อนสร้างสตริงย้อนกลับเสร็จได้

Recursive Two-Pointer สามารถหยุดการทำงานได้ทันทีเมื่อพบว่าตัวอักษรคู่ใดคู่หนึ่งไม่ตรงกัน จึงช่วยลดเวลาประมวลผลในกรณีที่ข้อมูลไม่ใช่ Palindrome

## 8. การเปรียบเทียบข้อดี ข้อจำกัด และสรุปผล

| ประเด็นการเปรียบเทียบ | อัลกอริทึมที่ 1: Reverse and Compare | อัลกอริทึมที่ 2: Recursive Two-Pointer |
| :--- | :--- | :--- |
| ข้อดี | โค้ดเข้าใจง่าย ใช้การกลับลำดับสตริงแล้วเปรียบเทียบผลลัพธ์โดยตรง | สามารถหยุดการทำงานได้ทันทีเมื่อพบตัวอักษรที่ไม่ตรงกัน (Early Exit) จึงมีประสิทธิภาพมากขึ้นในหลายกรณี |
| ข้อจำกัด | ต้องสร้างสตริงย้อนกลับและเปรียบเทียบครบทุกตัวอักษรเสมอ แม้จะพบความแตกต่างตั้งแต่ต้น | ใช้การเรียกเมธอดแบบ Recursion ทำให้มีการใช้ Call Stack และอาจเกิด StackOverflowError หากสตริงมีขนาดใหญ่มาก |
| Best-case Time Complexity | O(n) | O(1) |
| Worst-case Time Complexity | O(n) | O(n) |
| Space Complexity | O(n) | O(n) |

**สรุป:** โดยภาพรวม Recursive Two-Pointer Algorithm เหมาะสมกับการตรวจสอบ Palindrome มากกว่า เนื่องจากสามารถหยุดการทำงานได้ทันทีเมื่อพบว่าตัวอักษรคู่ใดคู่หนึ่งไม่ตรงกัน ทำให้ในกรณีที่ข้อมูลไม่ใช่ Palindrome ซึ่งพบได้บ่อยในการใช้งานจริง สามารถลดเวลาในการประมวลผลได้มากกว่าวิธี Reverse and Compare แม้ว่าทั้งสองอัลกอริทึมจะมี Worst-case Time Complexity และ Space Complexity เท่ากันก็ตาม
