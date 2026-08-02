ข้อ 3: การเปรียบเทียบจำนวนสระและพยัญชนะ

1. คำอธิบายแนวคิดของอัลกอริทึม
เงื่อนไขของโปรแกรม
นับสระภาษาอังกฤษ ได้แก่ a, e, i, o, u
ไม่สนใจความแตกต่างระหว่างตัวพิมพ์เล็กและตัวพิมพ์ใหญ่
ตัวอักษรภาษาอังกฤษที่ไม่ใช่สระให้นับเป็นพยัญชนะ
ไม่นับตัวเลข ช่องว่าง และเครื่องหมายพิเศษ
อัลกอริทึมที่ 1: Recursive Counting (hasMoreVowelsRecursive)

แนวคิด: ใช้วิธีเรียกเมธอดซ้ำเพื่ออ่านตัวอักษรทีละตำแหน่ง เริ่มตั้งแต่ตัวแรกของสตริงจนถึงตัวสุดท้าย พร้อมส่งค่าจำนวนสระและพยัญชนะที่นับได้ไปยังการเรียกครั้งถัดไป

Base Case (เงื่อนไขหยุด): เมื่อดัชนีมีค่าเท่ากับความยาวของสตริง แสดงว่าตรวจสอบครบทุกตัวอักษรแล้ว จึงนำจำนวนสระและพยัญชนะมาเปรียบเทียบ หากจำนวนสระมากกว่าจะคืนค่า true มิฉะนั้นคืนค่า false

Recursive Case: ตรวจสอบตัวอักษรปัจจุบัน หากเป็นสระให้เพิ่มตัวนับสระ หากเป็นพยัญชนะให้เพิ่มตัวนับพยัญชนะ แล้วเรียกเมธอดซ้ำโดยเลื่อนดัชนีไปตำแหน่งถัดไป

อัลกอริทึมที่ 2: Iterative Counting (hasMoreVowelsIterative)

แนวคิด: ใช้ลูป for อ่านตัวอักษรทุกตัวในสตริงทีละตัว จากนั้นตรวจสอบว่าเป็นสระหรือพยัญชนะแล้วเพิ่มค่าตัวนับที่เกี่ยวข้อง เมื่อวนลูปครบทุกตำแหน่งแล้วจึงเปรียบเทียบจำนวนสระกับจำนวนพยัญชนะและคืนค่าผลลัพธ์

2. Pseudocode
// ตรวจสอบว่าเป็นสระหรือไม่
Algorithm isVowel(ch)
    Convert ch to lowercase
    If ch is 'a' or 'e' or 'i' or 'o' or 'u'
        Return true
    Else
        Return false
End Algorithm

// ตรวจสอบว่าเป็นพยัญชนะหรือไม่
Algorithm isConsonant(ch)
    Convert ch to lowercase
    If ch is alphabet and not isVowel(ch)
        Return true
    Else
        Return false
End Algorithm

// Recursive Algorithm
Algorithm countRecursive(s, index, vowels, consonants)
    If index == length of s Then
        Return vowels > consonants
    End If

    ch = s[index]

    If isVowel(ch) Then
        vowels = vowels + 1
    Else If isConsonant(ch) Then
        consonants = consonants + 1
    End If

    Return countRecursive(s, index + 1, vowels, consonants)
End Algorithm

Algorithm hasMoreVowelsRecursive(s)
    If s is null Then
        Return false
    End If

    Return countRecursive(s, 0, 0, 0)
End Algorithm

// Iterative Algorithm
Algorithm hasMoreVowelsIterative(s)
    If s is null Then
        Return false
    End If

    vowels = 0
    consonants = 0

    For i = 0 To length of s - 1
        ch = s[i]

        If isVowel(ch)
            vowels = vowels + 1
        Else If isConsonant(ch)
            consonants = consonants + 1
        End If
    End For

    Return vowels > consonants
End Algorithm
3. ภาษา Java

public class VowelConsonantCounter {

    // เมธอดช่วยตรวจสอบว่าอักขระเป็นสระหรือไม่
    private static boolean isVowel(char ch) {
        char letter = Character.toLowerCase(ch);
        return letter == 'a' || letter == 'e' || letter == 'i'
                || letter == 'o' || letter == 'u';
    }

    // เมธอดช่วยตรวจสอบว่าอักขระเป็นพยัญชนะหรือไม่
    private static boolean isConsonant(char ch) {
        char letter = Character.toLowerCase(ch);
        return letter >= 'a' && letter <= 'z' && !isVowel(letter);
    }

    /**
     * อัลกอริทึมที่ 1 : Recursive Counting
     * Base Case : เมื่ออ่านครบทุกตัวอักษร
     * Recursive Case : ตรวจสอบตัวอักษรปัจจุบัน แล้วเรียกเมธอดกับตำแหน่งถัดไป
     */
    private static boolean countRecursive(String text, int position,
                                          int vowelTotal, int consonantTotal) {

        // Base Case
        if (position == text.length()) {
            return vowelTotal > consonantTotal;
        }

        char current = text.charAt(position);

        if (isVowel(current)) {
            vowelTotal++;
        } else if (isConsonant(current)) {
            consonantTotal++;
        }

        // Recursive Case
        return countRecursive(text, position + 1,
                vowelTotal, consonantTotal);
    }

    // เมธอดหลักสำหรับ Recursive
    public static boolean hasMoreVowelsRecursive(String text) {
        if (text == null) {
            return false;
        }
        return countRecursive(text, 0, 0, 0);
    }

    /**
     * อัลกอริทึมที่ 2 : Iterative Counting
     * ใช้ลูปตรวจสอบตัวอักษรทีละตำแหน่ง
     */
    public static boolean hasMoreVowelsIterative(String text) {

        if (text == null) {
            return false;
        }

        int vowelTotal = 0;
        int consonantTotal = 0;

        for (int index = 0; index < text.length(); index++) {

            char current = text.charAt(index);

            if (isVowel(current)) {
                vowelTotal++;
            } else if (isConsonant(current)) {
                consonantTotal++;
            }
        }

        return vowelTotal > consonantTotal;
    }

    public static void main(String[] args) {

        // ทดสอบกรณีปกติ
        String sample1 = "education";
        System.out.println("Input: \"" + sample1 + "\"");
        System.out.println("Recursive: " + hasMoreVowelsRecursive(sample1));
        System.out.println("Iterative: " + hasMoreVowelsIterative(sample1));

        // ทดสอบกรณีมีตัวเลข ช่องว่าง และเครื่องหมายพิเศษ
        String sample2 = "Hello World 123!!!";
        System.out.println("\nInput: \"" + sample2 + "\"");
        System.out.println("Recursive: " + hasMoreVowelsRecursive(sample2));
        System.out.println("Iterative: " + hasMoreVowelsIterative(sample2));

        // ทดสอบกรณีพิเศษ
        System.out.println("\n=== Edge Cases ===");
        System.out.println("Null Input: " + hasMoreVowelsIterative(null));
        System.out.println("Empty String: " + hasMoreVowelsRecursive(""));
        System.out.println("Numbers Only: " + hasMoreVowelsIterative("123456"));
    }
}

4. ข้อมูลนำเข้าและข้อมูลส่งออก
Input: "education"
Vowels: 5
Consonants: 4
Result: true

Input: "Hello World 123!!!"
Vowels: 3
Consonants: 7
Result: false

Input: "aeiou"
Vowels: 5
Consonants: 0
Result: true

Input: "123456"
Vowels: 0
Consonants: 0
Result: false
5. วิเคราะห์ Time Complexity
อัลกอริทึมที่ 1 (Recursive Counting): O(n)

เหตุผลสนับสนุน: เมธอดจะเรียกตัวเองหนึ่งครั้งต่อหนึ่งตัวอักษรในสตริง การตรวจสอบว่าเป็นสระหรือพยัญชนะใช้เวลา O(1) ในแต่ละรอบ ดังนั้นเมื่อมีตัวอักษรทั้งหมด n ตัว จะใช้เวลารวม O(n)

อัลกอริทึมที่ 2 (Iterative Counting): O(n)

เหตุผลสนับสนุน: ใช้ลูปอ่านตัวอักษรตั้งแต่ต้นจนจบสตริงเพียงครั้งเดียว โดยแต่ละรอบตรวจสอบประเภทของตัวอักษรด้วยเวลา O(1) จึงมีเวลาในการทำงานรวม O(n)

6. วิเคราะห์ Space Complexity
อัลกอริทึมที่ 1 (Recursive Counting): O(n)

เหตุผลสนับสนุน: การเรียกเมธอดแบบ Recursive จะสร้าง Call Stack เพิ่มขึ้นตามจำนวนตัวอักษรในสตริง ทำให้พื้นที่หน่วยความจำเพิ่มขึ้นตามความยาวของข้อมูล

อัลกอริทึมที่ 2 (Iterative Counting): O(1)

เหตุผลสนับสนุน: ใช้เพียงตัวแปรสำหรับเก็บจำนวนสระและพยัญชนะ ไม่มีการสร้างโครงสร้างข้อมูลเพิ่มเติมที่ขึ้นกับขนาดของข้อมูล จึงใช้พื้นที่คงที่

7. วิเคราะห์เพิ่มเติม

จำนวนครั้งของการเรียก Recursive:

เมธอด Recursive จะถูกเรียกทั้งหมดประมาณ n + 1 ครั้ง โดยครั้งสุดท้ายใช้สำหรับตรวจสอบเงื่อนไขหยุดของการเวียนเกิด

ความเสี่ยงในการเกิด StackOverflowError:

Recursive Counting มีโอกาสเกิด StackOverflowError หากสตริงมีความยาวมาก เนื่องจากมีการสร้าง Call Stack จำนวนมากตามจำนวนตัวอักษร

Iterative Counting ไม่มีความเสี่ยงดังกล่าว เพราะใช้การวนลูปภายในเมธอดเดียวตลอดการทำงาน

ขนาดข้อมูลที่เหมาะสม:

Recursive Counting เหมาะสำหรับสตริงขนาดเล็กหรือใช้เพื่อศึกษาหลักการเวียนเกิด

Iterative Counting เหมาะกับการประมวลผลข้อมูลทุกขนาด โดยเฉพาะข้อมูลที่มีความยาวมาก เนื่องจากใช้หน่วยความจำต่ำกว่า

8. การเปรียบเทียบข้อดี ข้อจำกัด และสรุปผล
| ประเด็นการเปรียบเทียบ | อัลกอริทึมที่ 1: Recursive Counting | อัลกอริทึมที่ 2: Iterative Counting |
| :--- | :--- | :--- |
| ข้อดี | โครงสร้างโปรแกรมเข้าใจหลักการเวียนเกิดได้ง่าย และแบ่งการทำงานเป็นลำดับ | ทำงานได้รวดเร็ว ใช้หน่วยความจำคงที่ และไม่มีปัญหา Call Stack |
| ข้อจำกัด | ใช้พื้นที่ Call Stack เพิ่มตามความยาวของสตริง และอาจเกิด StackOverflowError | โค้ดมีการใช้ตัวแปรและลูปมากกว่าวิธี Recursive |
| Time Complexity | O(n) | O(n) |
| Space Complexity | O(n) | O(1) |
| ความเสี่ยง Stack Overflow | มี | ไม่มี |

สรุป: Iterative Counting เหมาะสำหรับการใช้งานจริงมากกว่า เพราะใช้หน่วยความจำเพียง O(1) และสามารถทำงานกับสตริงที่มีขนาดใหญ่ได้โดยไม่เสี่ยงต่อการเกิด StackOverflowError ขณะที่ Recursive Counting เหมาะสำหรับการศึกษาหลักการทำงานของการเรียกเมธอดแบบเวียนเกิดมากกว่า