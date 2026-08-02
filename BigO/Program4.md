ข้อ 4: การจัดกลุ่มจำนวนคู่และจำนวนคี่
ข้อ 1. คำอธิบายแนวคิดของอัลกอริทึม
อัลกอริทึมที่ 1 : Recursive Two-Pointer (rearrangeRecursive)

แนวคิด

ใช้อัลกอริทึมแบบ Recursive ร่วมกับตัวชี้ (Pointer) จำนวน 2 ตัว คือ left และ right โดย left เริ่มต้นที่ตำแหน่งแรกของอาร์เรย์ และ right เริ่มต้นที่ตำแหน่งสุดท้ายของอาร์เรย์ จากนั้นตรวจสอบข้อมูลจากทั้งสองด้านพร้อมกัน

หากค่าทางซ้ายเป็นเลขคู่ ให้เลื่อน left ไปทางขวา
หากค่าทางขวาเป็นเลขคี่ ให้เลื่อน right มาทางซ้าย
หากพบเลขคี่ทางซ้ายและเลขคู่ทางขวา จะทำการสลับตำแหน่งของข้อมูล แล้วเรียกเมธอดเดิมซ้ำ (Recursive) เพื่อดำเนินการกับช่วงข้อมูลที่เหลือ

Base Case (เงื่อนไขหยุด)

เมื่อ left >= right หมายความว่าตัวชี้ทั้งสองพบกันหรือสวนกันแล้ว จึงหยุดการทำงาน

Recursive Step

ตรวจสอบเลขคู่ทางด้านซ้าย
ตรวจสอบเลขคี่ทางด้านขวา
สลับข้อมูลเมื่อจำเป็น
เรียกเมธอดซ้ำจนกว่าจะถึง Base Case
อัลกอริทึมที่ 2 : Iterative Two-Pointer (rearrangeTwoPointer)

แนวคิด

ใช้อัลกอริทึมแบบ Two-Pointer เช่นเดียวกับ Recursive แต่เปลี่ยนจากการเรียกเมธอดซ้ำมาใช้ลูป while ควบคุมการทำงานแทน

เริ่มต้นด้วยตัวชี้ 2 ตัว คือ left และ right

เลื่อน left ไปจนกว่าจะพบเลขคี่
เลื่อน right มาจนกว่าจะพบเลขคู่
หากยังไม่สวนกัน ให้สลับข้อมูล แล้วเลื่อนตัวชี้ทั้งสองเข้าหากัน
ทำซ้ำจนกว่าตัวชี้จะสวนกัน

วิธีนี้ไม่ต้องใช้การเรียกเมธอดซ้ำ จึงใช้หน่วยความจำน้อยกว่า Recursive

อัลกอริทึมที่ 3 : Extra Array (rearrangeExtraArray)

แนวคิด

สร้างอาร์เรย์ใหม่ขึ้นมาเพื่อเก็บผลลัพธ์ จากนั้นวนอ่านข้อมูลในอาร์เรย์เดิมทั้งหมด 2 รอบ

รอบแรก นำเลขคู่ทั้งหมดใส่ลงในอาร์เรย์ใหม่ก่อน
รอบที่สอง นำเลขคี่ทั้งหมดมาต่อท้าย

เนื่องจากเพิ่มข้อมูลตามลำดับที่พบในอาร์เรย์เดิม จึงสามารถรักษาลำดับเดิมของเลขคู่และเลขคี่ไว้ได้ (Stable Algorithm)

2. Pseudocode
อัลกอริทึมที่ 1 : Recursive Two-Pointer (rearrangeRecursive)
Algorithm rearrangeRecursive(A, left, right)

If left >= right Then
    Return
End If

While left < right AND A[left] is even
    left = left + 1
End While

While left < right AND A[right] is odd
    right = right - 1
End While

If left < right Then
    Swap A[left] and A[right]
End If

rearrangeRecursive(A, left + 1, right - 1)

End Algorithm

อัลกอริทึมที่ 2 : Iterative Two-Pointer (rearrangeTwoPointer)
Algorithm rearrangeTwoPointer(A)

left = 0
right = length(A) - 1

While left < right

    While left < right AND A[left] is even
        left = left + 1
    End While

    While left < right AND A[right] is odd
        right = right - 1
    End While

    If left < right Then
        Swap A[left] and A[right]
        left = left + 1
        right = right - 1
    End If

End While

End Algorithm

อัลกอริทึมที่ 3 : Extra Array (rearrangeExtraArray)
Algorithm rearrangeExtraArray(A)

Create result array

index = 0

For each value in A
    If value is even Then
        result[index] = value
        index = index + 1
    End If
End For

For each value in A
    If value is odd Then
        result[index] = value
        index = index + 1
    End If
End For

Return result

End Algorithm

3. โปรแกรมภาษา Java

import java.util.Arrays;

public class EvenOddRearranger {

    /**
     * อัลกอริทึมที่ 1 : Recursive Two-Pointer
     * Base Case : left >= right
     * In-place Algorithm
     */
    public static void rearrangeRecursive(int[] a, int left, int right) {
        if (a == null || left >= right) {
            return;
        }

        while (left < right && a[left] % 2 == 0) {
            left++;
        }

        while (left < right && a[right] % 2 != 0) {
            right--;
        }

        if (left < right) {
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
        }

        rearrangeRecursive(a, left + 1, right - 1);
    }

    /**
     * อัลกอริทึมที่ 2 : Iterative Two-Pointer
     * In-place Algorithm
     */
    public static void rearrangeTwoPointer(int[] a) {

        if (a == null || a.length <= 1) {
            return;
        }

        int left = 0;
        int right = a.length - 1;

        while (left < right) {

            while (left < right && a[left] % 2 == 0) {
                left++;
            }

            while (left < right && a[right] % 2 != 0) {
                right--;
            }

            if (left < right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;

                left++;
                right--;
            }
        }
    }

    /**
     * อัลกอริทึมที่ 3 : Extra Array
     * Stable Algorithm
     */
    public static int[] rearrangeExtraArray(int[] a) {

        if (a == null) {
            return new int[0];
        }

        int[] result = new int[a.length];
        int index = 0;

        // รอบที่ 1 : เก็บเลขคู่
        for (int num : a) {
            if (num % 2 == 0) {
                result[index++] = num;
            }
        }

        // รอบที่ 2 : เก็บเลขคี่
        for (int num : a) {
            if (num % 2 != 0) {
                result[index++] = num;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] input = {7, 2, 9, 4, 1, 6, 3, 8};

        System.out.println("Original Input : " + Arrays.toString(input));

        // Algorithm 1 : Recursive Two-Pointer
        int[] arr1 = input.clone();
        rearrangeRecursive(arr1, 0, arr1.length - 1);
        System.out.println("Recursive Two-Pointer : " + Arrays.toString(arr1));

        // Algorithm 2 : Iterative Two-Pointer
        int[] arr2 = input.clone();
        rearrangeTwoPointer(arr2);
        System.out.println("Iterative Two-Pointer : " + Arrays.toString(arr2));

        // Algorithm 3 : Extra Array
        int[] arr3 = rearrangeExtraArray(input);
        System.out.println("Extra Array : " + Arrays.toString(arr3));

        // ทดสอบการรักษาลำดับ (Stable Algorithm)
        System.out.println("\n=== Testing Stability ===");

        int[] stableInput = {5, 2, 7, 4, 9, 6};

        System.out.println("Input       : " + Arrays.toString(stableInput));
        System.out.println("Extra Array : "
                + Arrays.toString(rearrangeExtraArray(stableInput)));
    }
}

4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์
Input
[7, 2, 9, 4, 1, 6, 3, 8]
Recursive Two-Pointer
[8, 2, 6, 4, 1, 9, 3, 7]
Iterative Two-Pointer
[8, 2, 6, 4, 1, 9, 3, 7]
Extra Array
[2, 4, 6, 8, 7, 9, 1, 3]

ตัวอย่าง Stable

Input

[5,2,7,4,9,6]

Output

[2,4,6,5,7,9]

ข้อ 5. วิเคราะห์ Time Complexity

Time Complexity คือ การวิเคราะห์เวลาที่อัลกอริทึมใช้ในการทำงานเมื่อจำนวนข้อมูล (n) เพิ่มขึ้น โดยในโจทย์นี้ทั้ง 3 อัลกอริทึมมี Time Complexity เป็น O(n) แต่มีวิธีการทำงานที่แตกต่างกัน ดังนี้

อัลกอริทึมที่ 1 : Recursive Two-Pointer (rearrangeRecursive)

Time Complexity : O(n)

เหตุผล   อัลกอริทึมใช้ตัวชี้ left และ right เคลื่อนเข้าหากัน โดยสมาชิกแต่ละตัวในอาร์เรย์จะถูกตรวจสอบไม่เกิน 1 ครั้ง และการสลับข้อมูลแต่ละครั้งใช้เวลา O(1) ดังนั้นเมื่อจำนวนข้อมูลเพิ่มขึ้น เวลาในการทำงานจะเพิ่มขึ้นตามจำนวนสมาชิกในอาร์เรย์ จึงมี Time Complexity เท่ากับ O(n)

อัลกอริทึมที่ 2 : Iterative Two-Pointer (rearrangeTwoPointer)

Time Complexity : O(n)

เหตุผล   อัลกอริทึมใช้ลูป while และตัวชี้สองตัวในการตรวจสอบข้อมูล โดยตัวชี้จะเคลื่อนที่ไปข้างหน้าและถอยหลังเพียงครั้งเดียว ทำให้สมาชิกแต่ละตัวถูกอ่านไม่เกิน 1 ครั้ง แม้ว่าจะมีการใช้ลูปซ้อนกัน แต่ตัวชี้จะไม่ย้อนกลับไปตรวจสอบข้อมูลเดิม จึงใช้เวลาในการทำงานเป็น O(n)

อัลกอริทึมที่ 3 : Extra Array (rearrangeExtraArray)

Time Complexity : O(n)

เหตุผล  อัลกอริทึมวนอ่านข้อมูลทั้งหมด 2 รอบ โดยรอบแรกใช้สำหรับเก็บเลขคู่ และรอบที่สองใช้สำหรับเก็บเลขคี่ ถึงแม้จะวนทั้งหมด 2n ครั้ง แต่เมื่อวิเคราะห์แบบ Big O จะตัดค่าคงที่ออก ดังนั้นจึงเหลือ O(n)

6. วิเคราะห์ Space Complexity
Recursive Two-Pointer

Space Complexity = O(n)

ไม่มีการสร้างอาร์เรย์ใหม่ แต่ใช้ Call Stack จากการเรียก Recursive ซึ่งในกรณีเลวร้ายที่สุดอาจมีขนาด O(n)

Iterative Two-Pointer

Space Complexity = O(1)

ใช้เพียงตัวแปร left, right และ temp เท่านั้น จึงใช้หน่วยความจำเพิ่มเติมแบบคงที่

Extra Array

Space Complexity = O(n)

ต้องสร้างอาร์เรย์ใหม่ขนาดเท่ากับอาร์เรย์เดิม จึงใช้พื้นที่เพิ่ม O(n)

7. วิเคราะห์เพิ่มเติม

7. วิเคราะห์เพิ่มเติม
การเปลี่ยนแปลงอาร์เรย์เดิม
Recursive Two-Pointer: เปลี่ยนแปลงอาร์เรย์เดิมทันที (In-place) โดยใช้การสลับตำแหน่งของข้อมูล
Iterative Two-Pointer: เปลี่ยนแปลงอาร์เรย์เดิมทันที (In-place) โดยใช้การสลับตำแหน่งของข้อมูล
Extra Array: ไม่แก้ไขอาร์เรย์เดิม แต่สร้างอาร์เรย์ใหม่แล้วคืนผลลัพธ์กลับมา
จำนวนครั้งของการสลับข้อมูล
Recursive Two-Pointer: มีการสลับข้อมูลเมื่อพบเลขคี่ทางซ้ายและเลขคู่ทางขวา โดยจำนวนครั้งของการสลับในกรณีมากที่สุดไม่เกิน n/2 ครั้ง
Iterative Two-Pointer: มีหลักการสลับข้อมูลเหมือนกับ Recursive โดยจำนวนครั้งของการสลับในกรณีมากที่สุดไม่เกิน n/2 ครั้ง
Extra Array: ไม่มีการสลับข้อมูล เนื่องจากใช้วิธีคัดลอกข้อมูลลงในอาร์เรย์ใหม่
ความเป็น Stable Algorithm
Recursive Two-Pointer: Unstable เนื่องจากมีการสลับตำแหน่งของข้อมูล ทำให้ลำดับเดิมของสมาชิกภายในกลุ่มเลขคู่และเลขคี่อาจเปลี่ยนแปลงได้
Iterative Two-Pointer: Unstable เนื่องจากมีการสลับตำแหน่งของข้อมูลเช่นเดียวกับ Recursive ทำให้ลำดับเดิมของสมาชิกอาจเปลี่ยนแปลง
Extra Array: Stable สามารถรักษาลำดับเดิมของสมาชิกภายในแต่ละกลุ่มไว้ได้ เช่น Input [5, 2, 7, 4, 9, 6] จะได้ผลลัพธ์เป็น [2, 4, 6, 5, 7, 9] ซึ่งลำดับของเลขคู่และเลขคี่ยังคงเหมือนเดิม

สามารถรักษาลำดับเดิมของเลขคู่และเลขคี่ไว้ได้ เพราะนำข้อมูลมาเรียงใหม่ตามลำดับที่พบ
8. การเปรียบเทียบข้อดี ข้อจำกัด และสรุปผล
| ประเด็นการเปรียบเทียบ | อัลกอริทึมที่ 1: Recursive Two-Pointer | อัลกอริทึมที่ 2: Iterative Two-Pointer | อัลกอริทึมที่ 3: Extra Array |
| :--- | :--- | :--- | :--- |
| ข้อดี | โครงสร้างการเวียนเกิดเข้าใจง่าย และไม่ต้องสร้างอาร์เรย์ใหม่ | ทำงานรวดเร็ว ใช้หน่วยความจำเพียงเล็กน้อย และเป็น In-place | รักษาลำดับเดิมของข้อมูลได้ (Stable) และผลลัพธ์อ่านเข้าใจง่าย |
| ข้อจำกัด | ใช้ Call Stack เพิ่มขึ้น และอาจเกิด StackOverflowError เมื่อข้อมูลมีขนาดใหญ่ | ไม่สามารถรักษาลำดับเดิมของสมาชิกภายในอาร์เรย์ได้ | ต้องสร้างอาร์เรย์ใหม่ ทำให้ใช้พื้นที่หน่วยความจำเพิ่ม |
| Time Complexity | O(n) | O(n) | O(n) |
| Space Complexity | O(n) | O(1) | O(n) |
| Stability | Unstable | Unstable | Stable |

สรุป: หากต้องการประหยัดหน่วยความจำและประมวลผลได้อย่างมีประสิทธิภาพ วิธี Iterative Two-Pointer เป็นตัวเลือกที่เหมาะสมที่สุด แต่หากต้องการรักษาลำดับเดิมของสมาชิกภายในกลุ่มเลขคู่และเลขคี่ ควรเลือกใช้ Extra Array เนื่องจากเป็นอัลกอริทึมแบบ Stable แม้จะต้องใช้พื้นที่หน่วยความจำเพิ่มเติมก็ตาม