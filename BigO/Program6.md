ข้อ 6: การค้นหาอาร์เรย์ย่อยที่มีผลรวมสูงสุด (Maximum Subarray Sum)

ข้อ 1. คำอธิบายแนวคิดของอัลกอริทึม
โจทย์กำหนด

กำหนดอาร์เรย์ A ที่มีจำนวนเต็มไม่ซ้ำกันจำนวน n ค่า และสมาชิกเรียงจากน้อยไปมากแล้ว พร้อมจำนวนเต็ม k ให้นักศึกษาเขียนโปรแกรมค้นหาสมาชิกสองค่าที่มีผลรวมเท่ากับ k

ตัวอย่าง

A = [2, 4, 7, 11, 15, 20]
k = 18

ผลลัพธ์

Pair found : 7 and 11
อัลกอริทึมที่ 1 : Brute Force (findPairBruteForce)

แนวคิด

ตรวจสอบสมาชิกทุกคู่ที่เป็นไปได้ภายในอาร์เรย์ โดยใช้ลูปซ้อนกัน 2 ชั้น นำสมาชิกแต่ละคู่มาหาผลรวม หากพบว่าผลรวมเท่ากับ k ให้รายงานคู่ที่พบและหยุดการทำงานทันที แต่หากตรวจสอบครบทุกคู่แล้วยังไม่พบ จะสรุปว่าไม่มีคู่ที่มีผลรวมเท่ากับ k

อัลกอริทึมที่ 2 : Recursive Two-Pointer (findPairRecursive)

แนวคิด

ใช้อาร์เรย์ที่เรียงลำดับแล้วร่วมกับตัวชี้ 2 ตัว

left = 0
right = n - 1

คำนวณผลรวม

A[left] + A[right]

จากนั้นดำเนินการดังนี้

หากผลรวมเท่ากับ k ให้รายงานคู่ที่พบ
หากผลรวมน้อยกว่า k ให้เพิ่มค่า left
หากผลรวมมากกว่า k ให้ลดค่า right
เรียกเมธอดแบบ Recursive กับช่วงข้อมูลใหม่

ทำซ้ำจนกว่าจะพบคำตอบหรือจนกว่าตัวชี้จะสวนกัน

อัลกอริทึมที่ 3 : Binary Search (findPairBinarySearch)

แนวคิด

เลือกสมาชิก A[i] ทีละตัว จากนั้นคำนวณค่าที่ต้องการหา

target = k - A[i]

แล้วใช้ Binary Search ค้นหา target ในสมาชิกที่เหลือของอาร์เรย์

หากพบค่า target แสดงว่าพบสมาชิกสองตัวที่มีผลรวมเท่ากับ k แต่หากค้นหาครบทุกสมาชิกแล้วยังไม่พบ จะสรุปว่าไม่มีคำตอบ

ข้อ 2. Pseudocode
อัลกอริทึมที่ 1 : Brute Force
Algorithm findPairBruteForce(A, k)

For i = 0 to n - 2

    For j = i + 1 to n - 1

        If A[i] + A[j] == k Then
            Return True
        End If

    End For

End For

Return False

End Algorithm
อัลกอริทึมที่ 2 : Recursive Two-Pointer
Algorithm findPairRecursive(A, k, left, right)

If left >= right Then
    Return False
End If

sum = A[left] + A[right]

If sum == k Then
    Return True

Else If sum < k Then
    Return findPairRecursive(A, k, left + 1, right)

Else
    Return findPairRecursive(A, k, left, right - 1)

End If

End Algorithm
อัลกอริทึมที่ 3 : Binary Search
Algorithm findPairBinarySearch(A, k)

For i = 0 to n - 1

    target = k - A[i]

    Perform Binary Search
    in remaining elements

    If target is found Then
        Return True
    End If

End For

Return False

End Algorithm

---
ข้อ 3. โปรแกรมภาษา Java
import java.util.Arrays;

public class PairSumFinder {

    // อัลกอริทึมที่ 1 : Brute Force
    public static boolean findPairBruteForce(int[] a, int k) {

        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {

                if (a[i] + a[j] == k) {
                    System.out.println("Pair found: " + a[i] + " and " + a[j]);
                    return true;
                }

            }
        }

        System.out.println("Pair not found");
        return false;
    }

    // อัลกอริทึมที่ 2 : Recursive Two-Pointer
    public static boolean findPairRecursive(int[] a, int k, int left, int right) {

        if (left >= right) {
            System.out.println("Pair not found");
            return false;
        }

        int sum = a[left] + a[right];

        if (sum == k) {
            System.out.println("Pair found: " + a[left] + " and " + a[right]);
            return true;
        }

        if (sum < k) {
            return findPairRecursive(a, k, left + 1, right);
        }

        return findPairRecursive(a, k, left, right - 1);
    }

    // อัลกอริทึมที่ 3 : Binary Search
    public static boolean findPairBinarySearch(int[] a, int k) {

        for (int i = 0; i < a.length; i++) {

            int target = k - a[i];

            int left = i + 1;
            int right = a.length - 1;

            while (left <= right) {

                int mid = (left + right) / 2;

                if (a[mid] == target) {
                    System.out.println("Pair found: " + a[i] + " and " + a[mid]);
                    return true;
                }

                if (a[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println("Pair not found");
        return false;
    }

    public static void main(String[] args) {

        int[] A = {2, 4, 7, 11, 15, 20};
        int k = 18;

        System.out.println("Input : " + Arrays.toString(A));
        System.out.println("k = " + k);

        System.out.println("\nBrute Force");
        findPairBruteForce(A, k);

        System.out.println("\nRecursive Two-Pointer");
        findPairRecursive(A, k, 0, A.length - 1);

        System.out.println("\nBinary Search");
        findPairBinarySearch(A, k);

    }
}
---
4. ข้อมูลน้ำเข้า&ออก
```
ข้อมูลนำเข้า (Input)
A = [2, 4, 7, 11, 15, 20]
k = 18

อัลกอริทึมที่ 1 : Brute Force
Output
Pair found: 7 and 11

อัลกอริทึมที่ 2 : Recursive Two-Pointer
Output
Pair found: 7 and 11

อัลกอริทึมที่ 3 : Binary Search
Output
Pair found: 7 and 11

---
ข้อ 5. วิเคราะห์ Time Complexity

อัลกอริทึมที่ 1 : Brute Force
Time Complexity : O(n²)

เหตุผล   อัลกอริทึมใช้ลูปซ้อนกัน 2 ชั้น เพื่อตรวจสอบสมาชิกทุกคู่ที่เป็นไปได้ในอาร์เรย์ หากอาร์เรย์มีสมาชิก n ตัว จะต้องเปรียบเทียบประมาณ n(n−1)/2 คู่ ดังนั้นเมื่อจำนวนข้อมูลเพิ่มขึ้น เวลาในการทำงานจะเพิ่มขึ้นแบบกำลังสอง จึงมี Time Complexity เท่ากับ O(n²)

อัลกอริทึมที่ 2 : Recursive Two-Pointer
Time Complexity : O(n)

เหตุผล   อัลกอริทึมใช้ตัวชี้ left และ right เคลื่อนเข้าหากัน โดยในแต่ละรอบจะเลื่อนตัวชี้เพียงหนึ่งตำแหน่ง ทำให้สมาชิกแต่ละตัวถูกตรวจสอบไม่เกินหนึ่งครั้ง จึงใช้เวลาในการทำงานเป็น O(n)

อัลกอริทึมที่ 3 : Binary Search
Time Complexity : O(n log n)

เหตุผล   อัลกอริทึมจะเลือกสมาชิกในอาร์เรย์ทีละตัว แล้วใช้ Binary Search ค้นหาค่าที่เหลือ (k - A[i]) ซึ่ง Binary Search ใช้เวลา O(log n) และมีการทำซ้ำทั้งหมด n รอบ จึงมี Time Complexity เท่ากับ O(n log n)
---
ข้อ 6. วิเคราะห์ Space Complexity

อัลกอริทึมที่ 1 : Brute Force
Space Complexity : O(1)

เหตุผล   อัลกอริทึมใช้เพียงตัวแปรสำหรับเก็บดัชนีและตัวแปรชั่วคราวในการเปรียบเทียบข้อมูล ไม่มีการสร้างอาร์เรย์ใหม่หรือใช้หน่วยความจำเพิ่มเติมตามจำนวนข้อมูล จึงมี Space Complexity เท่ากับ O(1)

อัลกอริทึมที่ 2 : Recursive Two-Pointer
Space Complexity : O(n)

เหตุผล   อัลกอริทึมไม่มีการสร้างอาร์เรย์ใหม่ แต่ใช้การเรียกเมธอดแบบ Recursive ทำให้เกิดการใช้หน่วยความจำใน Call Stack ตามจำนวนครั้งที่เรียกเมธอด ซึ่งในกรณีมากที่สุดมีขนาดเป็น O(n)

อัลกอริทึมที่ 3 : Binary Search
Space Complexity : O(1)

เหตุผล   อัลกอริทึมใช้เพียงตัวแปรสำหรับเก็บตำแหน่งของดัชนี (left, right, mid) และตัวแปร target โดยไม่มีการสร้างอาร์เรย์ใหม่ จึงมี Space Complexity เท่ากับ O(1)
---
ข้อ 7. วิเคราะห์เพิ่มเติม
เหตุใด Two-Pointer จึงใช้ได้เมื่ออาร์เรย์เรียงลำดับแล้ว

อัลกอริทึม Two-Pointer ใช้ได้เมื่ออาร์เรย์เรียงลำดับจากน้อยไปมาก เพราะสามารถเปรียบเทียบผลรวมของสมาชิกที่ตำแหน่ง left และ right แล้วตัดสินใจเลื่อนตัวชี้ได้อย่างถูกต้อง

หากผลรวมน้อยกว่า k ให้เลื่อน left ไปทางขวา เพื่อเพิ่มค่าผลรวม
หากผลรวมมากกว่า k ให้เลื่อน right มาทางซ้าย เพื่อลดค่าผลรวม
หากผลรวมเท่ากับ k แสดงว่าพบคำตอบและหยุดการค้นหาได้ทันที

วิธีนี้ช่วยลดจำนวนการเปรียบเทียบข้อมูล ทำให้ค้นหาได้รวดเร็วภายในเวลา O(n)

หากอาร์เรย์ยังไม่เรียงลำดับจะเกิดอะไรขึ้น

หากอาร์เรย์ยังไม่เรียงลำดับ การเลื่อนตัวชี้ left หรือ right จะไม่สามารถบอกได้ว่าผลรวมจะเพิ่มขึ้นหรือลดลง ทำให้อาจข้ามคู่ข้อมูลที่เป็นคำตอบ หรือได้ผลลัพธ์ที่ไม่ถูกต้อง ดังนั้นจึงควรเรียงข้อมูลก่อน หรือเลือกใช้อัลกอริทึมอื่น เช่น Brute Force

การเปรียบเทียบลักษณะการทำงาน
Brute Force ตรวจสอบสมาชิกทุกคู่ จึงเหมาะกับข้อมูลขนาดเล็กและเข้าใจได้ง่าย แต่ใช้เวลามาก
Recursive Two-Pointer ลดขอบเขตการค้นหาด้วยการเลื่อนตัวชี้จากทั้งสองด้าน ทำให้ทำงานได้รวดเร็วเมื่อข้อมูลเรียงลำดับแล้ว
Binary Search เลือกสมาชิกทีละตัว แล้วค้นหาค่าที่เหลือด้วย Binary Search จึงเหมาะกับอาร์เรย์ที่เรียงลำดับและมีประสิทธิภาพดีกว่า Brute Force
---
### 8. การเปรียบเทียบข้อดี ข้อจำกัด และสรุปผล (สำหรับคัดลอก)


| ประเด็นการเปรียบเทียบ | Brute Force | Recursive Two-Pointer | Binary Search |
| :--- | :--- | :--- | :--- |
| ข้อดี | เขียนโปรแกรมง่าย ตรวจสอบสมาชิกทุกคู่ | ค้นหาได้รวดเร็ว ใช้เวลา O(n) และลดขอบเขตการค้นหาทีละตำแหน่ง | ค้นหาได้รวดเร็วกว่าการตรวจสอบทุกคู่ โดยใช้ Binary Search |
| ข้อจำกัด | ใช้เวลามากเมื่อข้อมูลมีขนาดใหญ่ | ใช้ได้เฉพาะอาร์เรย์ที่เรียงลำดับแล้ว และใช้ Call Stack จากการเรียกซ้ำ | ใช้ได้เฉพาะอาร์เรย์ที่เรียงลำดับแล้ว และต้องค้นหาซ้ำสำหรับสมาชิกแต่ละตัว |
| Time Complexity | O(n²) | O(n) | O(n log n) |
| Space Complexity | O(1) | O(n) | O(1) |

สรุป: Recursive Two-Pointer เป็นอัลกอริทึมที่เหมาะสมที่สุดสำหรับโจทย์นี้ เพราะสามารถค้นหาคู่ของสมาชิกที่มีผลรวมเท่ากับ k ได้ในเวลา O(n) เมื่ออาร์เรย์เรียงลำดับแล้ว ส่วน Binary Search มีประสิทธิภาพดีกว่า Brute Force แต่ยังใช้เวลามากกว่า Recursive Two-Pointer ขณะที่ Brute Force แม้จะเขียนง่าย แต่ต้องตรวจสอบสมาชิกทุกคู่ จึงใช้เวลามากที่สุดเมื่อข้อมูลมีขนาดใหญ่.Kadane's Algorithm เป็นอัลกอริทึมที่ดีที่สุดในการแก้ปัญหา Maximum Subarray Sum ด้วย Time Complexity O(n) และ Space Complexity O(1)
