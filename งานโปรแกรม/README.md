ใบงานทบทวนภาษา Java
คำชี้แจงสำหรับนักศึกษา
ให้นักศึกษาทำใบงานนี้เพื่อทบทวนความรู้ภาษา Java ที่จำเป็นต่อการเรียนเรื่องอัลกอริทึม ได้แก่ ตัว
แปร เงื่อนไข วนซ้ำา อาร์เรย์ เมธอด และการเขียนโปรแกรมแก้ปัญหาเบื้องต้น
ให้นักศึกษาเขียนโปรแกรมด้วยภาษา Java และส่งไฟล์.java หรืออัปโหลดลง GitHub ตามที่ผู้สอน
กำหนด
จุดประสงค์การเรียนรู้
หลังจากทำใบงานนี้ นักศึกษาสามารถ
1. เขียนโปรแกรม Java รับข้อมูลและแสดงผลได้
2. ใช้คำสั่ง if-else เพื่อตัดสินใจได้
3. ใช้คำสั่ง for และ while เพื่อวนซ ้าได้
4. ใช้ Array ในการเก็บและประมวลผลข้อมูลได้
5. เขียน Method เพื่อแบ่งการทำงานของโปรแกรมได้
6. อธิบายขั้นตอนการทำงานของโปรแกรมในรูปแบบ Pseudocode ได้

ตอนที่ 1: ทบทวนคำสั่งพื้นฐาน Java 
คำสั่ง 
ให้นักศึกษาเติมคำตอบให้ถูกต้อง 
1.1 คำสั่งแสดงผลข้อความในภาษา Java คืออะไร 
// ตอบ:  ใช้คำสั่ง System.out.println(); สำหรับแสดงข้อความแล้วขึ้นบรรทัดใหม่ แต่ System.out.print(); แสดงข้อความแบบไม่ขึ้นบรรทัดใหม่

1.2 คำสั่งรับค่าจากแป้นพิมพ์โดยใช้ Scanner ต้อง import อะไร

// ตอบ: import java.util.Scanner;

1.3 คำสั่งใดใช้ตรวจสอบเงื่อนไข

// ตอบ: ใช้คำสั่ง if, if-else หรือ switch-case

1.4 คำสั่งใดใช้วนซ้ำเมื่อทราบจำนวนรอบแน่นอน

// ตอบ: ใช้คำสั่ง for เพื่อวนซ้ำ

1.5 คำสั่งใดใช้วนซ้ำเมื่อยังไม่ทราบจำนวนรอบแน่นอน

// ตอบ: ใช้คำสั่ง while loop หรือ do-while 


ตอนที่ 2 วิเคราะห์โค้ด Java

2.1 โปรแกรมนี้วนซ้ำทั้งหมดกี่รอบ

// ตอบ: วนซ้ำทั้งหมด 5 รอบ

2.2 ค่าของตัวแปร sum หลังจบการทำงานคือเท่าใด

// ตอบ: มีค่าเท่ากับ 15 

2.3 ผลลัพธ์ที่แสดงออกหน้าจอคืออะไร

// ตอบ: เเสดงที่เเสดงหน้าจอคือ 15

2.4 โปรแกรมนี้ทำหน้าที่อะไร

// ตอบ: ใช้หาผลรวมของตัวเลขตั้งแต่ 1 ถึง 5 แล้วแสดงผลรวมออกมาทางหน้าจอ

ตอนที่ 3 เขียนโปรแกรมตรวจสอบเลขคู่หรือเลขคี่

3.1 Pseudocode

// ตอบ: START
    INPUT number
    IF number MOD 2 == 0 THEN
        OUTPUT "Even number"
    ELSE
        OUTPUT "Odd number"
    ENDIF
END

3.2 Java Code

// ตอบ: import java.util.Scanner;
        public class EvenOddChecker {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number: ");
        int number = scanner.nextInt();
        if (number % 2 == 0) {
            System.out.println("Even number");
        } else {
            System.out.println("Odd number");
        }
        scanner.close();
    }
}

ตอนที่ 4 เขียนโปรแกรมคำนวณคะแนนรวมและตัดสินผลผ่าน / ไม่ผ่าน

4.1 Pseudocode

// ตอบ: START
    INPUT midtermScore
    INPUT finalScore
    totalScore = midtermScore + finalScore
    OUTPUT "Total score = " + totalScore
    IF totalScore >= 50 THEN
        OUTPUT "Pass"
    ELSE
        OUTPUT "Fail"
    ENDIF
END
4.2 Java Code

// ตอบ: import java.util.Scanner;
        public class GradeChecker {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter midterm score: ");
        int midterm = scanner.nextInt();
        System.out.print("Enter final score: ");
        int finalScore = scanner.nextInt();
        int totalScore = midterm + finalScore;
        System.out.println("Total score = " + totalScore);
        if (totalScore >= 50) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
        scanner.close();
    }
}

ตอนที่ 5 เขียนโปรแกรมหาค่ามากที่สุดจากตัวเลข 3 จำนวน

5.1 Pseudocode

// ตอบ: START
    INPUT number1
    INPUT number2
    INPUT number3
    max = number1
    IF number2 > max THEN
        max = number2
    ENDIF
    IF number3 > max THEN
        max = number3
    ENDIF
    OUTPUT "Maximum number = " + max
END
5.2 Java Code

// ตอบ:import java.util.Scanner;
        public class MaxNumber {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number 1: ");
        int number1 = scanner.nextInt();
        System.out.print("Enter number 2: ");
        int number2 = scanner.nextInt();
        System.out.print("Enter number 3: ");
        int number3 = scanner.nextInt();
        int max = number1;
        if (number2 > max) {
            max = number2;
        }
        if (number3 > max) {
            max = number3;
        }
        System.out.println("Maximum number = " + max);
        scanner.close();
    }
}

ตอนที่ 6 ทบทวน Array

6.1 Pseudocode

// ตอบ: START
    CREATE Array scores ขนาด 5
    sum = 0
    FOR i = 0 TO 4
        INPUT scores[i]
        sum = sum + scores[i]
    ENDFOR
    average = sum / 5.0
    OUTPUT "Total score = " + sum
    OUTPUT "Average score = " + average
END

6.2 Java Code

// ตอบ: import java.util.Scanner;
        public class ScoreArray {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] scores = new int[5];
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            System.out.print("Enter score " + (i + 1) + ": ");
            scores[i] = scanner.nextInt();
            sum += scores[i];
        }
        double average = (double) sum / scores.length;
        System.out.println("Total score = " + sum);
        System.out.println("Average score = " + average);
        scanner.close();
    }
}

ตอนที่ 7 ค้นหาข้อมูลใน Array

7.1 Pseudocode

// ตอบ: START
    CREATE Array names = {"Somchai", "Somsri", "Sompong", "Sommai", "Somboon"}
    INPUT searchName
    found = false
    FOR i = 0 TO 4
        IF names[i] EQUALS searchName THEN
            found = true
            BREAK
        ENDIF
    ENDFOR
    IF found == true THEN
        OUTPUT "Found"
    ELSE
        OUTPUT "Not Found"
    ENDIF
END

7.2 Java Code

// ตอบ:import java.util.Scanner;
        public class SearchName {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = {"Somchai", "Somsri", "Sompong", "Sommai", "Somboon"};
        System.out.print("Enter name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(searchName)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }
        scanner.close();
    }
}

ตอนที่ 8 เขียน Method เพื่อหาค่ามากที่สุด

8.1 Java Code 

//ตอบ: import java.util.Scanner;
       public class ReviewMethod {
       public static int findMax(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number 1: ");
        int number1 = scanner.nextInt();
        System.out.print("Enter number 2: ");
        int number2 = scanner.nextInt();
        int max = findMax(number1, number2);
        System.out.println("Maximum number = " + max);
        scanner.close();
    }
}

ตอนที่ 9 Debug โปรแกรม

9.1 โปรแกรมนี้ผิดพลาดที่บรรทัดใด

// ตอบ: ผิดที่บรรทัด for (int i = 0; i <= numbers.length; i++)

9.2 เพราะเหตุใดจึงเกิดข้อผิดพลาด

// ตอบ: เพราะ Array ใน Java มี Index ตั้งแต่ 0 ถึง length - 1 แต่โค้ดใช้ <= numbers.length ทำให้ลูปเข้าถึงตำแหน่งที่ไม่มีอยู่ จึงเกิดข้อผิดพลาด ArrayIndexOutOfBoundsException

9.3 ควรแก้ไขอย่างไร

// ตอบ: เปลี่ยนเงื่อนไขจาก i <= numbers.length เป็น i < numbers.length เพื่อไม่ให้ Index เกินขนาดของ Array

ตอนที่ 10 Mini Challenge

10.1 Pseudocode

// ตอบ: START
    CREATE Array numbers ขนาด 10
    FOR i = 0 TO 9
        INPUT numbers[i]
    ENDFOR
    min = numbers[0]
    max = numbers[0]
    FOR i = 1 TO 9
        IF numbers[i] < min THEN
            min = numbers[i]
        ENDIF
        IF numbers[i] > max THEN
            max = numbers[i]
        ENDIF
    ENDFOR
    OUTPUT "Minimum number = " + min
    OUTPUT "Maximum number = " + max
END

10.2 Java Code

// ตอบ: import java.util.Scanner;
        public class MiniChallenge {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }
        int min = numbers[0];
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        System.out.println("Minimum number = " + min);
        System.out.println("Maximum number = " + max);
        scanner.close();
    }
}

ตอนที่ 11: ใช้ GenAI ช่วยตรวจโค้ด

คำสั่ง
หลังจากเขียนโปรแกรมเสร็จ ให้นักศึกษาใช้ GenAI ช่วยตรวจสอบโค้ด 1 โปรแกรม แล้วตอบคำถาม
ต่อไปนี้
Prompt ที่ใช้ถาม AI
"ช่วยตรวจสอบโค้ด Java นี้เหมาะสำหรับผู้เริ่มต้นหรือไม่ เพราะอะไร และควรปรับปรุงตรงไหนให้เข้าใจง่ายขึ้น โดยไม่ต้องเขียนโค้ดใหม่ แต่แนะนำวิธีพัฒนาต่อ"

1: AI พบข้อผิดพลาดอะไรหรือไม่
// ตอบ: AI ไม่พบข้อผิดพลาดที่ทำให้โปรแกรมใช้งานไม่ได้ แต่แนะนำให้ปรับการตั้งชื่อตัวแปรและจัดรูปแบบโค้ดให้อ่านง่ายขึ้น

2: คำแนะนำของ AI ถูกต้องหรือไม่ เพราะเหตุใด
// ตอบ: ผมคิดว่าถูกต้อง เพราะคำแนะนำช่วยให้โค้ดดูเป็นระเบียบ เข้าใจได้ง่าย และเหมาะกับคนที่เพิ่งเริ่มเรียน Java

3: นักศึกษาแก้ไขโค้ดตาม AI หรือไม่
// ตอบ: แก้ไขครับ โดยปรับการตั้งชื่อตัวแปรและจัดรูปแบบโค้ดให้อ่านง่ายขึ้น แต่ไม่ได้เปลี่ยนการทำงานของโปรแกรม

4: นักศึกษาได้เรียนรู้อะไรจากการใช้ AI ตรวจโค้ด
// ตอบ: ได้เรียนรู้ว่าการเขียนโค้ดที่ดีไม่ใช่แค่ให้โปรแกรมทำงานได้แต่ควรเขียนให้คนอื่นอ่านและเข้าใจได้ง่ายรวมถึงสามารถนำไปพัฒนาต่อได้

5: มีข้อควรระวังอะไรในการใช้ AI ช่วยเขียนโปรแกรม
// ตอบ: ไม่ควรเชื่อคำตอบของ AI ทั้งหมดควรอ่านทำความเข้าใจและทดลองรันโปรแกรมเองเพื่อให้แน่ใจว่าโค้ดถูกต้องและตรงกับโจทย์




งานที่ต้องส่ง
ให้นักศึกษาส่งงานดังต่อไปนี้
1. ไฟล์ Java อย่างน้อย 4 โปรแกรม
o ตรวจสอบเลขคู่/เลขคี่
o คำนวณคะแนนรวมและผ่าน/ไม่ผ่าน
o โปรแกรมเกี่ยวกับ Array
o Mini Challenge 1 โปรแกรม

2. เอกสารสั้นหรือ README ประกอบด้วย
o ชื่อโปรแกรม
//    EvenOddChecker
o Input
//    int number รับค่าจำนวนเต็มจากผู้ใช้ผ่าน Scanner
o Process
//    รับค่าจากผู้ใช้
        ใช้เงื่อนไข if-else
        ตรวจสอบ number % 2 == 0
        ถ้าเป็นจริง แสดง "Even number"
        ถ้าเป็นเท็จ แสดง "Odd number"
o Output
//    Even number or Odd number
o วิธีรันโปรแกรม
//    เปิดโปรแกรมที่สามารถรัน code ได้เช่น visual studio code เเล้วสร้างไฟล์ เขียนไฟล์ละลงท้ายด้วย .java ละนำ code ไปใส่ละก็ กดปุ่มรัน ตรงเเถบด้านบน หรือ หาปุ่มลัดอื่นที่สามารถกดรันได้
o สิ่งที่ได้เรียนรู้
//    ได้ความรู้จากการที่ได้เรียนรู้ทบทวน code ที่เคยได้เรียน

3. คำตอบ Reflection จากตอนที่ 11
Reflection หลังทำใบงาน
ให้นักศึกษาตอบคำถามต่อไปนี้
1. ส่วนใดของ Java ที่นักศึกษายังไม่มั่นใจมากที่สุด
ตอบ: Array หลายมิติ 
2. โจทย์ข้อใดยากที่สุด เพราะเหตุใด
ตอบ: Mini Challenge เพราะต้องคิดหาแก้ปัญหาเอง
3. การทบทวน Java ครั้งนี้ช่วยเตรียมตัวเรียน Algorithm อย่างไร
ตอบ: ทำให้ได้ทบทวนพื้นฐานการเขียนโปรแกรมที่เคยเรียนมา
4. นักศึกษาคิดว่า Java ส่วนใดสำคัญที่สุดต่อการเรียน Algorithm
ตอบ: การใช้ if-else เพราะมีการได้ใช้ในหลายๆโปรแกรมที่ทำมักพบได้บ่อย
5. นักศึกษาจะฝึกเพิ่มเติมเรื่องใดก่อนเรียนสัปดาห์ถัดไป
ตอบ: ศึกษาการใช้ code ต่างๆเพื่อให้คุ้นชินกับการเอามาประยุกต์ใช้