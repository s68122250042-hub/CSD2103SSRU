 ข้อ 1 การกลับลำดับสตริง (Reverse String)

 1. คำอธิบายแนวคิดของอัลกอริทึม

 โจทย์กำหนด

รับค่าสตริง 1 ข้อความ แล้วสร้างสตริงใหม่ที่มีลำดับตัวอักษรย้อนกลับจากข้อความเดิม

**ตัวอย่าง**

Input: `pots&pans`

Output: `snap&stop`

 อัลกอริทึมที่ 1: Recursive Algorithm (`reverseRecursive`)

อัลกอริทึมนี้ประกอบด้วยองค์ประกอบหลัก 2 ส่วน ได้แก่ **Base Case** (เงื่อนไขหยุดการเรียกซ้ำ) และ **Recursive Step** (ขั้นตอนการเรียกซ้ำ)

**แนวคิด:** ใช้หลักการ Recursion โดยนำตัวอักษรตัวสุดท้ายของสตริงในแต่ละรอบมาต่อไว้ด้านหน้าของผลลัพธ์ จากนั้นเรียกเมธอดเดิมซ้ำอีกครั้งโดยส่งสตริงส่วนที่เหลือ (ตั้งแต่ตัวแรกจนถึงตัวก่อนสุดท้าย) ไปประมวลผล ทำเช่นนี้ต่อเนื่องจนกว่าจะถึงเงื่อนไขหยุด

**Base Case:** หากสตริงเป็น `null` หรือมีความยาวไม่เกิน 1 ตัวอักษร ให้คืนค่าสตริงนั้นกลับทันที

 อัลกอริทึมที่ 2: Iterative Algorithm (`reverseIterative`)

**แนวคิด:** ใช้การวนลูปอ่านตัวอักษรของสตริงจากตำแหน่งสุดท้าย (`s.length() - 1`) ย้อนกลับมาจนถึงตำแหน่งแรก (`0`) แล้วนำตัวอักษรแต่ละตัวไปต่อใน `StringBuilder` ซึ่งเป็นชนิดข้อมูลแบบ Mutable จึงสามารถแก้ไขข้อมูลเดิมได้โดยไม่ต้องสร้าง String ใหม่ทุกครั้ง ทำให้มีประสิทธิภาพสูงกว่า

---

 2. Pseudocode

```text
// 1. Recursive Algorithm
Algorithm reverseRecursive(s)

If s is null or length of s <= 1 Then
    Return s
End If

lastChar = character at index (length of s - 1)
remaining = substring of s from index 0 to (length of s - 2)

Return lastChar + reverseRecursive(remaining)

End Algorithm
```

```text
// 2. Iterative Algorithm
Algorithm reverseIterative(s)

If s is null Then
    Return null
End If

Create empty StringBuilder sb
For i = (length of s - 1) DownTo 0 Do
    Append character at s[i] to sb
End For
Return sb.toString()
End Algorithm
```

---

 3. โปรแกรมภาษา Java

```java
public class StringReverser {
    /**
     * อัลกอริทึมที่ 1: Recursive Algorithm
     * Base Case: หากสตริงเป็น null หรือความยาว <= 1 ให้คืนค่ากลับทันที
     * Recursive Case: ดึงตัวอักษรตัวสุดท้าย + เรียกเมธอดซ้ำกับสตริงที่เหลือ
     */
    public static String reverseRecursive(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return s.charAt(s.length() - 1)
                + reverseRecursive(s.substring(0, s.length() - 1));
    }
    /**
     * อัลกอริทึมที่ 2: Iterative Algorithm
     * ใช้ลูปอ่านตัวอักษรจากตัวสุดท้ายย้อนกลับมาตัวแรก
     * ใช้ StringBuilder เพื่อเพิ่มประสิทธิภาพในการต่อข้อความ
     */
    public static String reverseIterative(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String input = "pots&pans";
        System.out.println("Input: " + input);
        System.out.println("Recursive Output: " + reverseRecursive(input));
        System.out.println("Iterative Output: " + reverseIterative(input));
        System.out.println();
        System.out.println("--- Edge Cases ---");
        System.out.println("Null: " + reverseIterative(null));
        System.out.println("Empty: \"" + reverseIterative("") + "\"");
        System.out.println("Single Character: \"" + reverseIterative("A") + "\"");
    }
}
```

---

 4. ข้อมูลนำเข้าและผลลัพธ์

```
Input: pots&pans

Recursive Output: snap&stop

Iterative Output: snap&stop
```

---

 5. วิเคราะห์ Time Complexity

 Recursive Algorithm: O(n²)

**เหตุผลสนับสนุน:** เมธอดเรียกตัวเองซ้ำทั้งหมดประมาณ `n` ครั้งตามความยาวของสตริง โดยในแต่ละรอบมีการเรียก `substring()` ซึ่งต้องสร้างสตริงใหม่ใช้เวลา `O(n)` และมีการใช้เครื่องหมาย `+` ในการต่อข้อความซึ่งต้องสร้างออบเจ็กต์ `String` ใหม่ทุกครั้ง จึงได้สมการ

`T(n) = T(n-1) + O(n)`

เมื่อนำมาแก้สมการจะได้ Time Complexity เท่ากับ **O(n²)**

 Iterative Algorithm: O(n)

**เหตุผลสนับสนุน:** ใช้การวนลูปเพียงครั้งเดียวตามจำนวนตัวอักษรของสตริง โดยในแต่ละรอบใช้ `charAt()` และ `StringBuilder.append()` ซึ่งทำงานในเวลา `O(1)` ดังนั้นเวลารวมจึงเป็น `n × O(1) = O(n)`

---

 6. วิเคราะห์ Space Complexity

 Recursive Algorithm: O(n²)

**เหตุผลสนับสนุน:** มีการเรียกเมธอดแบบ Recursion ทำให้เกิด Call Stack ลึกสูงสุด `n` ชั้น และในแต่ละชั้นมีการสร้างสตริงใหม่จาก `substring()` และการต่อข้อความด้วย `+` ส่งผลให้ใช้พื้นที่หน่วยความจำสะสมรวมประมาณ **O(n²)**

 Iterative Algorithm: O(n)

**เหตุผลสนับสนุน:** ไม่มีการใช้ Call Stack เพิ่มเติม ใช้พื้นที่หลักสำหรับเก็บผลลัพธ์ใน `StringBuilder` ซึ่งมีขนาดตามจำนวนตัวอักษรของสตริง จึงมี Space Complexity เท่ากับ **O(n)**

---

 7. วิเคราะห์เพิ่มเติม

 จำนวนครั้งที่แต่ละอัลกอริทึมประมวลผลตัวอักษร

ทั้ง Recursive และ Iterative มีการอ่านตัวอักษรของสตริงครบทุกตัว หรือประมาณ `n` ครั้งเท่ากัน แต่ Recursive มีค่าใช้จ่ายเพิ่มเติมจากการเรียกเมธอดซ้ำและการสร้างสตริงใหม่

 ผลกระทบจากการต่อสตริงด้วยเครื่องหมาย +

เนื่องจาก `String` ในภาษา Java เป็นชนิดข้อมูลแบบ **Immutable** ทุกครั้งที่ใช้เครื่องหมาย `+` จะมีการสร้างออบเจ็กต์ `String` ใหม่และคัดลอกข้อมูลเดิมทั้งหมด ส่งผลให้ใช้เวลาประมวลผลมากขึ้นและใช้หน่วยความจำเพิ่มขึ้น โดยเฉพาะเมื่อมีการต่อข้อความหลายครั้ง

 ความแตกต่างระหว่าง String และ StringBuilder

`String` เป็นข้อมูลแบบ **Immutable** เมื่อมีการเปลี่ยนแปลงค่าจะต้องสร้างออบเจ็กต์ใหม่ทุกครั้ง ส่วน `StringBuilder` เป็นข้อมูลแบบ **Mutable** สามารถแก้ไขข้อมูลเดิมได้โดยไม่ต้องสร้างออบเจ็กต์ใหม่ ทำให้การใช้ `append()` มีประสิทธิภาพสูงกว่าและเหมาะสำหรับงานที่มีการต่อข้อความหลายครั้ง

 ผลการทดสอบกับขนาดสตริง

| จำนวนตัวอักษร | Recursive | Iterative |
| :--- | :--- | :--- |
| 10 | ทำงานรวดเร็ว | ทำงานรวดเร็ว |
| 100 | ทำงานรวดเร็ว | ทำงานรวดเร็ว |
| 1,000 | เริ่มช้าลงอย่างเห็นได้ชัด | ยังคงทำงานได้รวดเร็ว |
| 10,000 | มีโอกาสเกิด StackOverflowError หรือใช้หน่วยความจำสูง | ทำงานได้อย่างมีประสิทธิภาพ |

---

 8. การเปรียบเทียบข้อดี ข้อจำกัด และสรุปผล

| ประเด็นการเปรียบเทียบ | อัลกอริทึมที่ 1: Recursive | อัลกอริทึมที่ 2: Iterative |
| :--- | :--- | :--- |
| ข้อดี | โค้ดกระชับ เข้าใจหลักการ Recursion ได้ง่าย | ทำงานรวดเร็ว ใช้หน่วยความจำได้อย่างมีประสิทธิภาพ เหมาะกับข้อมูลขนาดใหญ่ |
| ข้อจำกัด | ใช้หน่วยความจำมาก และอาจเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่ | เขียนโค้ดยาวกว่าวิธี Recursive เล็กน้อย |
| Time Complexity | O(n²) | O(n) |
| Space Complexity | O(n²) | O(n) |

**สรุป:** Iterative Algorithm เหมาะสำหรับการใช้งานจริงมากกว่า เนื่องจากมี Time Complexity เท่ากับ **O(n)** และ Space Complexity เท่ากับ **O(n)** ทำงานได้รวดเร็ว ใช้หน่วยความจำอย่างมีประสิทธิภาพ และไม่เสี่ยงต่อการเกิด StackOverflowError เมื่อประมวลผลสตริงขนาดใหญ่ ส่วน Recursive Algorithm เหมาะสำหรับการศึกษาแนวคิดของการเรียกซ้ำมากกว่า