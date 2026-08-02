# ข้อ 5: การแบ่งอาร์เรย์ตามค่า k (Partition Array Around Pivot k)

ข้อ 1. คำอธิบายแนวคิดของอัลกอริทึม
โจทย์กำหนด
กำหนดอาร์เรย์ของจำนวนเต็มที่ยังไม่ได้เรียงลำดับ A และจำนวนเต็ม k ให้นำสมาชิกในอาร์เรย์มาจัดเรียงใหม่ โดยให้สมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ k อยู่ด้านหน้า และสมาชิกที่มีค่ามากกว่า k อยู่ด้านหลัง โดยไม่จำเป็นต้องเรียงลำดับค่าภายในแต่ละกลุ่ม
ตัวอย่าง

A = [12, 4, 7, 15, 3, 10, 8]
k = 8

ผลลัพธ์ที่เป็นไปได้

[8, 4, 7, 3, 15, 10, 12]
อัลกอริทึมที่ 1 : Recursive Partition (partitionRecursive)

แนวคิด

อัลกอริทึมนี้ใช้หลักการ Divide and Conquer ร่วมกับการเรียกเมธอดแบบ Recursive โดยใช้ตัวชี้ 2 ตัว คือ left และ right ในการตรวจสอบสมาชิกของอาร์เรย์จากทั้งสองด้าน

หากค่าทางด้านซ้ายมีค่าน้อยกว่าหรือเท่ากับ k ให้เลื่อนตัวชี้ left ไปทางขวา
หากค่าทางด้านขวามีค่ามากกว่า k ให้เลื่อนตัวชี้ right มาทางซ้าย
หากพบว่าด้านซ้ายมีค่ามากกว่า k และด้านขวามีค่าน้อยกว่าหรือเท่ากับ k จะทำการสลับข้อมูลทั้งสองตำแหน่ง
จากนั้นเรียกเมธอดเดิมซ้ำจนกว่าตัวชี้ทั้งสองจะสวนกัน

Base Case

เมื่อ left >= right ให้หยุดการทำงาน

อัลกอริทึมที่ 2 : Iterative Partition (partitionIterative)

แนวคิด

อัลกอริทึมนี้ใช้หลักการเดียวกับ Recursive Partition แต่เปลี่ยนจากการเรียกเมธอดซ้ำมาใช้ลูป while แทน ทำให้ไม่เกิดการใช้ Call Stack เพิ่มขึ้น

การทำงานจะใช้ตัวชี้ left และ right

เลื่อน left ไปจนกว่าจะพบค่าที่มากกว่า k
เลื่อน right มาจนกว่าจะพบค่าที่น้อยกว่าหรือเท่ากับ k
หากตัวชี้ยังไม่สวนกัน ให้สลับข้อมูล
ทำซ้ำจนกว่าตัวชี้จะสวนกัน

วิธีนี้สามารถแบ่งข้อมูลได้โดยไม่ต้องสร้างอาร์เรย์ใหม่ และใช้หน่วยความจำเพิ่มเติมน้อย

อัลกอริทึมที่ 3 : Sorting-Based Algorithm (partitionBySorting)

แนวคิด

อัลกอริทึมนี้เริ่มจากการเรียงลำดับข้อมูลในอาร์เรย์ก่อน จากนั้นจึงหาตำแหน่งสุดท้ายของสมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ k แล้วแบ่งข้อมูลออกเป็นสองส่วน คือ กลุ่มที่มีค่าน้อยกว่าหรือเท่ากับ k และกลุ่มที่มีค่ามากกว่า k

วิธีนี้สามารถแบ่งข้อมูลได้ถูกต้อง แต่ใช้เวลาในการทำงานมากกว่าวิธี Recursive และ Iterative เนื่องจากต้องเสียเวลาในการเรียงลำดับข้อมูลก่อน

---

ข้อ 2. Pseudocode
อัลกอริทึมที่ 1 : Recursive Partition
Algorithm partitionRecursive(A, k, left, right)

If left >= right Then
    Return
End If

While left < right AND A[left] <= k
    left = left + 1
End While

While left < right AND A[right] > k
    right = right - 1
End While

If left < right Then
    Swap A[left] and A[right]
End If

partitionRecursive(A, k, left + 1, right - 1)

End Algorithm
อัลกอริทึมที่ 2 : Iterative Partition
Algorithm partitionIterative(A, k)

left = 0
right = length(A) - 1

While left < right

    While left < right AND A[left] <= k
        left = left + 1
    End While

    While left < right AND A[right] > k
        right = right - 1
    End While

    If left < right Then
        Swap A[left] and A[right]
        left = left + 1
        right = right - 1
    End If

End While

End Algorithm
อัลกอริทึมที่ 3 : Sorting-Based Algorithm
Algorithm partitionBySorting(A, k)

Sort A in ascending order

Return A

End Algorithm

---
## 3. ภาษา Java
import java.util.Arrays;

public class PartitionArray {

    // อัลกอริทึมที่ 1 : Recursive Partition
    public static void partitionRecursive(int[] a, int k, int left, int right) {

        if (a == null || left >= right) {
            return;
        }

        while (left < right && a[left] <= k) {
            left++;
        }

        while (left < right && a[right] > k) {
            right--;
        }

        if (left < right) {
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
        }

        partitionRecursive(a, k, left + 1, right - 1);
    }

    // อัลกอริทึมที่ 2 : Iterative Partition
    public static void partitionIterative(int[] a, int k) {

        if (a == null || a.length <= 1) {
            return;
        }

        int left = 0;
        int right = a.length - 1;

        while (left < right) {

            while (left < right && a[left] <= k) {
                left++;
            }

            while (left < right && a[right] > k) {
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

    // อัลกอริทึมที่ 3 : Sorting-Based Algorithm
    public static void partitionBySorting(int[] a, int k) {

        Arrays.sort(a);
    }

    public static void main(String[] args) {

        int[] input = {12, 4, 7, 15, 3, 10, 8};
        int k = 8;

        System.out.println("Original Input : " + Arrays.toString(input));

        int[] arr1 = input.clone();
        partitionRecursive(arr1, k, 0, arr1.length - 1);
        System.out.println("Recursive Partition : "
                + Arrays.toString(arr1));

        int[] arr2 = input.clone();
        partitionIterative(arr2, k);
        System.out.println("Iterative Partition : "
                + Arrays.toString(arr2));

        int[] arr3 = input.clone();
        partitionBySorting(arr3, k);
        System.out.println("Sorting-Based Algorithm : "
                + Arrays.toString(arr3));
    }
}
---
## 4. ข้อมูลน้ำเข้า&ออก
```
ข้อมูลนำเข้า (Input)
A = [12, 4, 7, 15, 3, 10, 8]
k = 8
อัลกอริทึมที่ 1 : Recursive Partition

Output

[8, 4, 7, 3, 15, 10, 12]

คำอธิบาย

สมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ 8 ถูกจัดให้อยู่ด้านหน้า ส่วนสมาชิกที่มีค่ามากกว่า 8 อยู่ด้านหลัง โดยไม่จำเป็นต้องเรียงลำดับข้อมูลภายในแต่ละกลุ่ม

อัลกอริทึมที่ 2 : Iterative Partition

Output

[8, 4, 7, 3, 15, 10, 12]

คำอธิบาย

ใช้หลักการเดียวกับ Recursive Partition แต่เปลี่ยนเป็นการใช้ลูป while จึงได้ผลลัพธ์ลักษณะเดียวกัน และยังคงแบ่งข้อมูลออกเป็น 2 กลุ่มตามค่า k

อัลกอริทึมที่ 3 : Sorting-Based Algorithm

Output

[3, 4, 7, 8, 10, 12, 15]
```
---
ข้อ 5. วิเคราะห์ Time Complexity

Time Complexity คือ การวิเคราะห์ระยะเวลาที่อัลกอริทึมใช้ในการทำงานเมื่อจำนวนข้อมูล (n) เพิ่มขึ้น โดยอัลกอริทึมทั้ง 3 วิธีมีรายละเอียดดังนี้
อัลกอริทึมที่ 1 : Recursive Partition
Time Complexity : O(n)

เหตุผล   อัลกอริทึมใช้ตัวชี้ left และ right ตรวจสอบข้อมูลจากทั้งสองด้านของอาร์เรย์ โดยสมาชิกแต่ละตัวจะถูกตรวจสอบไม่เกินหนึ่งครั้ง และการสลับข้อมูลใช้เวลา O(1) ดังนั้นเมื่อจำนวนข้อมูลเพิ่มขึ้น เวลาในการทำงานจะเพิ่มขึ้นตามจำนวนสมาชิกในอาร์เรย์ จึงมี Time Complexity เท่ากับ O(n)

อัลกอริทึมที่ 2 : Iterative Partition

Time Complexity : O(n)

เหตุผล   อัลกอริทึมใช้ลูป while ร่วมกับตัวชี้สองตัวในการแบ่งข้อมูล สมาชิกแต่ละตัวจะถูกตรวจสอบเพียงครั้งเดียว และไม่มีการย้อนกลับไปตรวจสอบข้อมูลเดิม จึงมีเวลาในการทำงานเป็น O(n)

อัลกอริทึมที่ 3 : Sorting-Based Algorithm

Time Complexity : O(n log n)

เหตุผล   อัลกอริทึมนี้ต้องเรียงลำดับข้อมูลก่อนโดยใช้ Arrays.sort() ซึ่งใช้เวลาเฉลี่ย O(n log n) จากนั้นจึงสามารถแบ่งข้อมูลตามค่า k ได้ ดังนั้นเวลาส่วนใหญ่จึงขึ้นอยู่กับขั้นตอนการเรียงลำดับ ทำให้มี Time Complexity เท่ากับ O(n log n)
---
ข้อ 6. วิเคราะห์ Space Complexity
Space Complexity คือ การวิเคราะห์หน่วยความจำเพิ่มเติมที่อัลกอริทึมใช้ระหว่างการทำงาน โดยไม่นับพื้นที่ของอาร์เรย์ข้อมูลเดิม
อัลกอริทึมที่ 1 : Recursive Partition

Space Complexity : O(n)

เหตุผล   อัลกอริทึมไม่สร้างอาร์เรย์ใหม่ แต่ใช้การเรียกเมธอดแบบ Recursive ทำให้เกิดการใช้หน่วยความจำใน Call Stack ตามจำนวนครั้งที่เรียกเมธอด ในกรณีมากที่สุดจึงมี Space Complexity เท่ากับ O(n)

อัลกอริทึมที่ 2 : Iterative Partition

Space Complexity : O(1)

เหตุผล   อัลกอริทึมใช้เพียงตัวแปร left, right และ temp สำหรับสลับข้อมูล โดยไม่มีการสร้างอาร์เรย์ใหม่ จึงใช้หน่วยความจำเพิ่มเติมแบบคงที่ หรือ O(1)

อัลกอริทึมที่ 3 : Sorting-Based Algorithm

Space Complexity : O(log n)

เหตุผล   อัลกอริทึมใช้ Arrays.sort() สำหรับเรียงลำดับข้อมูล ซึ่งในการทำงานจะมีการใช้พื้นที่เพิ่มเติมจากการเรียกซ้ำภายในของอัลกอริทึมเรียงลำดับ โดยทั่วไปมี Space Complexity ประมาณ O(log n)
---

7. วิเคราะห์เพิ่มเติม
การเปลี่ยนแปลงอาร์เรย์เดิม
Recursive Partition: เปลี่ยนแปลงข้อมูลในอาร์เรย์เดิมทันที (In-place)
Iterative Partition: เปลี่ยนแปลงข้อมูลในอาร์เรย์เดิมทันที (In-place)
Sorting-Based Algorithm: เปลี่ยนแปลงข้อมูลในอาร์เรย์เดิม โดยเรียงลำดับข้อมูลก่อนแบ่งอาร์เรย์
จำนวนครั้งของการสแกน (Passes)
Recursive Partition: สแกนข้อมูลจากทั้งสองด้านจนกว่าตัวชี้จะสวนกัน โดยข้อมูลแต่ละตัวถูกตรวจสอบไม่เกิน 1 ครั้ง
Iterative Partition: สแกนข้อมูลเพียง 1 รอบ (Single Pass) โดยใช้ตัวชี้ left และ right
Sorting-Based Algorithm: ต้องเรียงลำดับข้อมูลทั้งหมดก่อน จึงใช้หลายรอบในการทำงานของอัลกอริทึมเรียงลำดับ
ความสัมพันธ์กับขั้นตอน Partition ใน Quick Sort
Recursive Partition: ใช้หลักการเดียวกับขั้นตอน Partition ของ Quick Sort และทำงานแบบ Recursive
Iterative Partition: ใช้หลักการเดียวกับขั้นตอน Partition ของ Quick Sort แต่เปลี่ยนเป็นการใช้ลูป while
Sorting-Based Algorithm: ไม่ใช่ขั้นตอน Partition ของ Quick Sort เพราะต้องเรียงลำดับข้อมูลก่อน
การทำงานแบบ In-place
Recursive Partition: เป็น In-place Algorithm เพราะสลับข้อมูลภายในอาร์เรย์เดิม
Iterative Partition: เป็น In-place Algorithm เพราะสลับข้อมูลภายในอาร์เรย์เดิม
Sorting-Based Algorithm: เป็น In-place เมื่อใช้ Arrays.sort() กับอาร์เรย์ชนิด int[]

---
### 8. การเปรียบเทียบข้อดี ข้อจำกัด และสรุปผล (สำหรับคัดลอก)

| ประเด็นการเปรียบเทียบ | Recursive Partition | Iterative Partition | Sorting-Based Algorithm |
| :--- | :--- | :--- | :--- |
| ข้อดี | โครงสร้างเข้าใจง่าย ใช้หลักการเดียวกับ Partition ใน Quick Sort | ทำงานรวดเร็ว ใช้หน่วยความจำน้อย และไม่ใช้ Call Stack | เขียนโปรแกรมง่าย และได้ข้อมูลที่เรียงลำดับจากน้อยไปมาก |
| ข้อจำกัด | ใช้ Call Stack เพิ่มขึ้น และอาจเกิด Stack Overflow เมื่อข้อมูลมีขนาดใหญ่ | ไม่สามารถแสดงขั้นตอนการเรียกซ้ำแบบ Recursive ได้ | ใช้เวลาในการเรียงลำดับข้อมูลก่อน จึงใช้เวลามากกว่าวิธี Partition |
| Time Complexity | O(n) | O(n) | O(n log n) |
| Space Complexity | O(n) | O(1) | O(log n) |

สรุป: Iterative Partition เป็นอัลกอริทึมที่เหมาะสมที่สุดสำหรับโจทย์นี้ เพราะสามารถแบ่งอาร์เรย์ตามค่า k ได้โดยตรง ใช้เวลา O(n) ใช้หน่วยความจำเพียง O(1) และทำงานแบบ In-place ส่วน Recursive Partition ให้ผลลัพธ์ใกล้เคียงกันแต่ใช้ Call Stack เพิ่มขึ้น ขณะที่ Sorting-Based Algorithm ต้องเรียงลำดับข้อมูลก่อน จึงใช้เวลา O(n log n) มากกว่าสองวิธีแรก แม้จะได้ข้อมูลที่เรียงลำดับแล้วก็ตาม
