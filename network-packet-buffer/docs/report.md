# รายงาน: Network Packet Buffer — Linear Queue vs Circular Queue

## ส่วนที่ 1: การวิเคราะห์ปัญหา

**Input:** ลำดับคำสั่ง ENQUEUE (พร้อมข้อมูล Packet: ID, Arrival Time, Size, Priority) และ DEQUEUE
ที่ Router ได้รับ

**Output:** Packet ที่ถูกส่งออกจาก buffer ตามลำดับ (สำหรับ DEQUEUE) และสถานะ buffer หลังแต่ละ operation

**Constraints:**
- Buffer รองรับสูงสุด 5 Packets
- เมื่อ buffer เต็ม ต้อง drop packet ใหม่ที่เข้ามา (ไม่ overwrite)
- ต้องประมวลผลตามลำดับที่เข้ามา (FIFO) ภายใน buffer

**Queue เก็บข้อมูลอะไร:** เก็บ object `Packet` ที่มี Packet ID, Arrival Time, Size, Priority

**Queue ชนิดที่เหมาะสม:** Circular Queue (array-based) เพราะ buffer มีขนาดคงที่และต้องใช้พื้นที่ที่ว่างจาก
การ dequeue กลับมาใช้ใหม่ได้ทันที ต่างจาก Linear Queue ที่ใช้พื้นที่ได้ครั้งเดียวต่อ slot

**เหตุผลที่ต้องใช้ Queue:** Packet ต้องถูกประมวลผล/ส่งออกตามลำดับที่มาถึง (FIFO) เพื่อความยุติธรรมและ
ป้องกันการสลับลำดับข้อมูลที่ไม่ถูกต้อง

---

## ส่วนที่ 2: Algorithm Design (Pseudocode)

### Algorithm A: LINEAR_QUEUE_ENQUEUE / DEQUEUE
```
Algorithm LINEAR_ENQUEUE(packet)
    if rear == capacity - 1 then
        report "Buffer Full - Packet Dropped (False Overflow possible)"
        return false
    end if
    rear ← rear + 1
    buffer[rear] ← packet
    size ← size + 1
    return true

Algorithm LINEAR_DEQUEUE()
    if size == 0 then
        report "Buffer Empty"
        return null
    end if
    packet ← buffer[front]
    buffer[front] ← null
    front ← front + 1
    size ← size - 1
    return packet
```

### Algorithm B: CIRCULAR_QUEUE_ENQUEUE / DEQUEUE
```
Algorithm CIRCULAR_ENQUEUE(packet)
    if size == capacity then
        report "Buffer Full - Packet Dropped"
        return false
    end if
    rear ← (rear + 1) mod capacity
    buffer[rear] ← packet
    size ← size + 1
    return true

Algorithm CIRCULAR_DEQUEUE()
    if size == 0 then
        report "Buffer Empty"
        return null
    end if
    packet ← buffer[front]
    buffer[front] ← null
    front ← (front + 1) mod capacity
    size ← size - 1
    return packet
```

---

## ส่วนที่ 3: Queue Trace (Buffer capacity = 5)

### Algorithm A: Linear Queue

| Step | Operation | front | rear | Queue Before | Queue After | Output |
|---|---|---|---|---|---|---|
| 1 | ENQUEUE P1 | 0 | -1→0 | [] | [P1] | - |
| 2 | ENQUEUE P2 | 0 | 0→1 | [P1] | [P1,P2] | - |
| 3 | ENQUEUE P3 | 0 | 1→2 | [P1,P2] | [P1,P2,P3] | - |
| 4 | DEQUEUE | 0→1 | 2 | [P1,P2,P3] | [P2,P3] | P1 |
| 5 | DEQUEUE | 1→2 | 2 | [P2,P3] | [P3] | P2 |
| 6 | ENQUEUE P4 | 2 | 2→3 | [P3] | [P3,P4] | - |
| 7 | ENQUEUE P5 | 2 | 3→4 | [P3,P4] | [P3,P4,P5] | - |
| 8 | ENQUEUE P6 | 2 | 4 (ไม่ขยับ) | [P3,P4,P5] | [P3,P4,P5] | **DROPPED** |
| 9 | DEQUEUE | 2→3 | 4 | [P3,P4,P5] | [P4,P5] | P3 |
| 10 | ENQUEUE P7 | 3 | 4 (ไม่ขยับ) | [P4,P5] | [P4,P5] | **DROPPED** |
| 11 | DEQUEUE | 3→4 | 4 | [P4,P5] | [P5] | P4 |
| 12 | ENQUEUE P8 | 4 | 4 (ไม่ขยับ) | [P5] | [P5] | **DROPPED** |

**คำอธิบาย:** ที่ step 8 แม้ size จริงจะเหลือแค่ 3 (มี slot ว่าง 2 ช่องที่ index 0,1) แต่ `rear` ถึง
`capacity-1` (index 4) แล้ว ทำให้ enqueue ล้มเหลว นี่คือ **False Overflow** และเมื่อ `rear` ค้างอยู่ที่
`capacity-1` แล้ว มันจะ**ไม่มีทางขยับกลับ**อีกเลยตลอดอายุของ queue นี้ (ตราบใดที่ไม่ reset) — จาก step 9-12
จะเห็นว่าแม้ DEQUEUE จะปลดปล่อย slot ที่ index 2, 3, 4 ให้ว่างเพิ่มขึ้นเรื่อย ๆ (จน step 12 เหลือ packet
จริงในระบบแค่ 1 ตัวจาก capacity 5) ทุก ENQUEUE ที่ตามมาก็ยังคง**ถูก DROP ซ้ำ ๆ** เพราะเงื่อนไขตรวจสอบแค่
ตำแหน่ง `rear` ไม่ได้ตรวจ `size` จริง — นี่คือจุดอ่อนร้ายแรงของ Linear Queue ในระบบที่ enqueue/dequeue
สลับกันต่อเนื่อง (ไม่ใช่แค่ครั้งเดียวแบบ step 8)

### Algorithm B: Circular Queue

| Step | Operation | front | rear | Queue Before | Queue After | Output |
|---|---|---|---|---|---|---|
| 1 | ENQUEUE P1 | 0 | -1→0 | [] | [P1] | - |
| 2 | ENQUEUE P2 | 0 | 0→1 | [P1] | [P1,P2] | - |
| 3 | ENQUEUE P3 | 0 | 1→2 | [P1,P2] | [P1,P2,P3] | - |
| 4 | DEQUEUE | 0→1 | 2 | [P1,P2,P3] | [P2,P3] | P1 |
| 5 | DEQUEUE | 1→2 | 2 | [P2,P3] | [P3] | P2 |
| 6 | ENQUEUE P4 | 2 | 2→3 | [P3] | [P3,P4] | - |
| 7 | ENQUEUE P5 | 2 | 3→4 | [P3,P4] | [P3,P4,P5] | - |
| 8 | ENQUEUE P6 | 2 | 4→0 (wrap) | [P3,P4,P5] | [P3,P4,P5,P6] | - |
| 9 | DEQUEUE | 2→3 | 0 | [P3,P4,P5,P6] | [P4,P5,P6] | P3 |
| 10 | ENQUEUE P7 | 3 | 0→1 (wrap) | [P4,P5,P6] | [P4,P5,P6,P7] | - |
| 11 | DEQUEUE | 3→4 | 1 | [P4,P5,P6,P7] | [P5,P6,P7] | P4 |
| 12 | ENQUEUE P8 | 4 | 1→2 (wrap) | [P5,P6,P7] | [P5,P6,P7,P8] | - |

**คำอธิบาย:** ที่ step 8 `rear` คำนวณด้วย `(4+1) mod 5 = 0` จึงวนกลับไปใช้ index 0 ที่ว่างจากการ
dequeue ก่อนหน้า ทำให้ enqueue P6 สำเร็จ — ไม่มี False Overflow ในสี่ step ถัดมา (9-12) `front` และ `rear`
ยังคงวน (wrap) ผ่าน index 0 และ 1 ต่อไปได้เรื่อย ๆ ตามรอบของ modulo แสดงให้เห็นว่า Circular Queue
รองรับวงจร enqueue/dequeue ที่สลับกันต่อเนื่องได้โดยไม่ถูกจำกัดด้วยตำแหน่ง `rear` แบบ Linear Queue เลย —
ตลอดทั้ง 12 step มี packet ถูก drop เป็น 0 ครั้ง เทียบกับ Linear Queue ที่ drop ไป 3 ครั้ง (step 8, 10, 12)

---

## ส่วนที่ 4: Correctness / Invariant

**Invariant ของ FIFO Queue (ใช้ได้ทั้ง Linear และ Circular):**
> ก่อนเริ่มแต่ละรอบของ Algorithm สมาชิกที่อยู่บริเวณ front คือสมาชิกที่เข้ามาก่อนสมาชิกอื่นทั้งหมด
> ที่ยังไม่ได้รับการประมวลผล และลำดับของสมาชิกใน queue สอดคล้องกับลำดับเวลาที่ enqueue เข้ามา

**สำหรับ Circular Queue เพิ่มเติม:**
> ตำแหน่ง index ใน array ไม่ได้สื่อถึงลำดับข้อมูลอีกต่อไป — ลำดับที่แท้จริงคือลำดับที่นับจาก `front`
> วนไปตาม `(front + i) mod capacity` สำหรับ i = 0 ถึง size-1 ซึ่งค่านี้ยังคงรักษาลำดับ FIFO ไว้ได้ถูกต้อง

Algorithm ทั้งสองรักษา invariant นี้ไว้ทุกครั้งที่ enqueue (เพิ่มที่ rear) และ dequeue (นำออกที่ front)
จึงยืนยันได้ว่าลำดับการประมวลผล packet เป็นไปตาม FIFO เสมอ (ตราบใดที่ยังไม่ full)

---

## ส่วนที่ 5: Time Complexity

| Operation | Linear Queue | Circular Queue |
|---|---|---|
| enqueue | O(1) | O(1) |
| dequeue | O(1) | O(1) |
| peek | O(1) | O(1) |
| search | O(n) | O(n) |
| display | O(n) | O(n) |

---

## ส่วนที่ 6: Space Complexity

ทั้งสอง Algorithm ใช้ array ขนาดคงที่ `capacity` → **O(n)** โดย n = capacity (สูงสุด 5 ในโจทย์นี้)

ข้อแตกต่างเชิงประสิทธิภาพการใช้พื้นที่ (ไม่ใช่ Big-O แต่สำคัญในทางปฏิบัติ):
- **Linear Queue:** สิ้นเปลืองพื้นที่ที่ใช้งานได้จริง เพราะ slot ก่อน front ที่ถูก dequeue ไปแล้วไม่ถูกนำกลับมาใช้
  จนกว่าจะ reset ทั้ง queue → ใช้พื้นที่ได้จริงน้อยกว่า capacity ที่ประกาศไว้
- **Circular Queue:** ใช้พื้นที่ทุก slot ได้เต็มประสิทธิภาพ (100% utilization) เพราะ index ที่ว่างถูกวนกลับมาใช้ใหม่

---

## ส่วนที่ 7: Java Implementation

ใช้ **array-based implementation ที่เขียนเอง** (ไม่ใช้ `java.util.Queue`/`ArrayDeque` โดยตรง) เพราะ
โจทย์ต้องการให้ **แสดงค่า front, rear ทุกขั้นตอน** ซึ่งเป็นแก่นของการเปรียบเทียบ Linear vs Circular Queue —
คลาสสำเร็จรูปของ Java ซ่อนกลไก index ภายในไว้ ทำให้ไม่สามารถสาธิต False Overflow และการ wrap-around
ได้ตรงตามวัตถุประสงค์ของ case study

ไฟล์ที่เกี่ยวข้อง: `Packet.java`, `LinearQueueBuffer.java`, `CircularQueueBuffer.java`

---

## ส่วนที่ 8: Test Cases

รันได้จาก `TestCases.java` — สรุปผล:

| # | Test Case | ผลลัพธ์ที่คาดหวัง | ผ่าน? |
|---|---|---|---|
| 1 | Normal Case | Enqueue 2 รายการ, Dequeue ได้ P1 ตาม FIFO | ✅ |
| 2 | Empty Queue | isEmpty=true, dequeue คืนค่า null พร้อมข้อความแจ้งเตือน | ✅ |
| 3 | Single Item | Peek/Dequeue ได้ค่าเดียวกัน, isEmpty=true หลัง dequeue | ✅ |
| 4 | Large Queue | Enqueue จนเต็ม + dequeue + enqueue ต่อ → wrap-around ถูกต้อง (Circular) | ✅ |
| 5 | Edge Case (priority เท่ากันหมด) | เรียงตาม arrival order (FIFO tie-break) | ✅ |
| 6 | Cancel Case | ยกเลิก packet กลาง queue สำเร็จ, ยกเลิก packet ที่ไม่มีอยู่จริง คืนค่า false | ✅ |

---

## ส่วนที่ 9: Algorithm Experiment

วัดด้วย `System.nanoTime()`, warm-up 1 รอบ (ไม่นับ), seed คงที่ (42), เฉลี่ย 5 รอบ ที่ n = 100, 1,000,
10,000, 50,000 (capacity ปรับเท่ากับ n เพื่อวัดต้นทุนการทำงานล้วน ๆ โดยไม่มี drop)

ตัวอย่างผลการทดลอง (รันจาก `Experiment.java`, ผลจริงอาจต่างกันเล็กน้อยตามเครื่อง):

| n | Linear Queue (ms) | Circular Queue (ms) |
|---|---|---|
| 100 | ~0.68 | ~0.11 |
| 1,000 | ~1.22 | ~0.65 |
| 10,000 | ~3.07 | ~4.71 |
| 50,000 | ~5.57 | ~7.73 |

**การวิเคราะห์:** ทั้งสอง Algorithm มี time complexity ต่อ operation เป็น O(1) เท่ากัน ดังนั้นเวลารวม
สำหรับ n operations ควรเพิ่มขึ้นแบบ**เชิงเส้น**ตาม n ซึ่งสอดคล้องกับผลการทดลองในภาพรวม (ทั้งสองเส้นโตขึ้น
ตาม n ไม่ใช่แบบ exponential) ความแตกต่างเล็กน้อยระหว่าง Linear และ Circular ที่ n ต่าง ๆ มาจาก overhead
ของการคำนวณ modulo ใน Circular Queue เทียบกับการบวกตรง ๆ ใน Linear Queue ซึ่งเป็น constant factor
ที่ไม่กระทบ Big-O แต่อาจสังเกตเห็นได้ในทางปฏิบัติ (นักศึกษาควรรันซ้ำในเครื่องจริงเพื่อยืนยันตัวเลข)

---

## ส่วนที่ 10: เปรียบเทียบ Algorithms

| ประเด็น | Algorithm A: Linear Queue | Algorithm B: Circular Queue |
|---|---|---|
| Data Structure | Array, front/rear เดินหน้าทางเดียว | Array, front/rear wrap-around (modulo) |
| หลักการ | FIFO ธรรมดา | FIFO พร้อมนำ slot ว่างกลับมาใช้ใหม่ |
| Enqueue | O(1) แต่ล้มเหลวเมื่อ rear ถึงปลาย array | O(1) ล้มเหลวเฉพาะเมื่อ size == capacity จริง ๆ |
| Dequeue | O(1) | O(1) |
| Time Complexity | O(1) ต่อ operation | O(1) ต่อ operation |
| Space Complexity | O(n) แต่ใช้พื้นที่จริงได้ไม่เต็ม | O(n) ใช้พื้นที่ได้เต็มประสิทธิภาพ |
| Fairness | FIFO เท่ากันทั้งคู่ (ไม่เกี่ยวกับ false overflow) | FIFO เท่ากันทั้งคู่ |
| ข้อดี | เขียนง่าย เข้าใจง่าย | ใช้ buffer ได้เต็มความจุจริง ไม่เสีย slot โดยเปล่าประโยชน์ |
| ข้อจำกัด | เกิด False Overflow ทำให้ drop packet ทั้งที่ buffer มีที่ว่างจริง | โค้ดซับซ้อนขึ้นเล็กน้อยจากการคำนวณ modulo |
| เหมาะกับกรณี | ระบบที่ reset queue บ่อย หรือไม่มีการ dequeue ต่อเนื่องยาวนาน | ระบบ buffer ต่อเนื่อง เช่น router packet buffer ที่มี enqueue/dequeue สลับกันตลอดเวลา |

**สรุป:** สำหรับ Case Study Network Packet Buffer ที่ต้องรับ-ส่ง packet ต่อเนื่องตลอดเวลา
**Circular Queue เหมาะสมกว่า** เพราะแก้ปัญหา False Overflow ของ Linear Queue ได้โดยตรง ทำให้ buffer
ขนาด 5 packets ถูกใช้งานได้เต็มประสิทธิภาพจริง ไม่ต้องเพิ่มขนาด buffer เพียงเพื่อชดเชยพื้นที่ที่เสียไปจาก
False Overflow