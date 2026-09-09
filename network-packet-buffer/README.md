# Network Packet Buffer — Linear Queue vs Circular Queue

**หัวข้อ:** งานปฏิบัติการกลุ่ม Queue Algorithm Design & Analysis with Java
**Case Study:** กลุ่มที่ 6 — Network Packet Buffer (ระบบ Queue สำหรับ Network Packets)

## Case Story
Router รับ Network Packet เข้ามาเร็วกว่าความสามารถในการส่งออก จึงต้องเก็บ Packet ไว้ใน Buffer Queue
ที่มีความจุจำกัดสูงสุด 5 Packets

## Algorithms ที่พัฒนา
| Algorithm | โครงสร้างข้อมูล | จุดเด่น |
|---|---|---|
| **A: Linear Queue** | Array-based, front/rear เดินหน้าทางเดียว | เข้าใจง่าย แต่เกิด **False Overflow** ได้ |
| **B: Circular Queue** | Array-based, front/rear wrap-around ด้วย modulo | ใช้พื้นที่ buffer ได้เต็มประสิทธิภาพ ไม่เกิด False Overflow |

## โครงสร้างโปรเจกต์
```
network-packet-buffer/
├── src/
│   ├── Packet.java              # Packet data model
│   ├── LinearQueueBuffer.java   # Algorithm A
│   ├── CircularQueueBuffer.java # Algorithm B
│   ├── Main.java                # รัน Scenario บังคับ + trace front/rear/queue
│   ├── TestCases.java           # 6 Test Cases ตามข้อกำหนด
│   └── Experiment.java          # วัด Execution Time ที่ n = 100,1000,10000,50000
├── docs/
│   └── report.md                # รายงานฉบับเต็ม (ส่วนที่ 1–10)
└── README.md
```

## วิธีรันโปรแกรม
ต้องมี JDK ติดตั้งไว้ (Java 11 ขึ้นไป)

```bash
cd src
javac *.java

# รัน Scenario บังคับ (trace front/rear/queue ทุก step)
java Main

# รัน Test Cases (6 กรณี)
java TestCases

# รัน Experiment วัดเวลา (n = 100, 1000, 10000, 50000)
java Experiment
```

## Scenario บังคับ (Buffer capacity = 5, รวม 12 operations)
```
ENQUEUE P1
ENQUEUE P2
ENQUEUE P3
DEQUEUE
DEQUEUE
ENQUEUE P4
ENQUEUE P5
ENQUEUE P6
DEQUEUE
ENQUEUE P7
DEQUEUE
ENQUEUE P8
```
- **Linear Queue:** ENQUEUE P6, P7 และ P8 ถูก **Drop ทั้งหมด** เพราะ `rear` ถึงจุดสิ้นสุด array
  (index 4) ตั้งแต่ step 8 แล้วไม่มีทางขยับกลับได้อีก (False Overflow) แม้ว่า DEQUEUE ใน step 9 และ 11
  จะปลดปล่อย slot เพิ่มขึ้นเรื่อย ๆ ก็ตาม — จบ scenario เหลือ packet จริงในระบบแค่ 1 ตัวจาก capacity 5
- **Circular Queue:** ENQUEUE P6, P7 และ P8 สำเร็จทั้งหมด เพราะ `rear` วนกลับไปใช้ index ที่ว่างด้วย
  modulo ได้เรื่อย ๆ — ตลอด 12 operations ไม่มี packet ถูก drop เลยแม้แต่ครั้งเดียว

รายละเอียด trace แบบ step-by-step (front/rear/queue ทุก step) อยู่ใน [`docs/report.md`](docs/report.md) ส่วนที่ 3

## Time Complexity สรุป
| Operation | Linear Queue | Circular Queue |
|---|---|---|
| enqueue | O(1) | O(1) |
| dequeue | O(1) | O(1) |
| peek | O(1) | O(1) |
| search | O(n) | O(n) |
| display | O(n) | O(n) |

รายละเอียดการวิเคราะห์ทั้งหมด (Problem Analysis, Pseudocode, Trace, Correctness, Space Complexity,
Test Cases, Experimental Comparison, สรุปเปรียบเทียบ) อยู่ใน [`docs/report.md`](docs/report.md)

## สมาชิกกลุ่ม
| ชื่อ | หน้าที่ |
|---|---|
| (ใส่ชื่อ 1) | Problem Analysis + Queue Design |
| (ใส่ชื่อ 2) | Pseudocode Algorithm A & B |
| (ใส่ชื่อ 3) | Queue Trace |
| (ใส่ชื่อ 4) | Correctness + Complexity Analysis |
| (ใส่ชื่อ 5) | Java Implementation + Test Cases |
| (ใส่ชื่อ 6) | Experimental Comparison |
| (ใส่ชื่อ 7) | สรุปเปรียบเทียบ + รายงาน + Slide + GitHub |

## บันทึกการใช้ Generative AI
ดูรายละเอียดใน [`docs/ai-usage-log.md`](docs/ai-usage-log.md)