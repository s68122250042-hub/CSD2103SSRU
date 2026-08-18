ข้อมูลเริ่มต้น (Initial State)
ในลานจอดรถ (parkingLot) รถถูก push เข้าไปตามลำดับ 01 -> 02 -> 03 -> 04 ดังนั้นสถานะ Stack จาก บนลงล่าง (Top to Bottom) คือ
:
ทะเบียน 04 (Somyod) - อยู่บนสุด/ใกล้ทางออก
ทะเบียน 03 (Somying)
ทะเบียน 02 (Somsak)
ทะเบียน 01 (Somchai) - อยู่ล่างสุด
กรณีที่ 1: กรณีปกติ (Normal Case) - Algorithm A (Iteration)
เป้าหมาย: ต้องการนำรถทะเบียน "03" ออกจากลานจอด
เริ่มต้น: parkingStack =
, tempStack = [ว่าง]
Step 1: pop() รถคันแรก "04" ออกจาก parkingStack และ push() ลงใน tempStack
สถานะ: parkingStack =
, tempStack =
Step 2: pop() รถคันถัดไป "03" ออกมาตรวจสอบ พบว่า "ตรงกับเป้าหมาย" จึงยุติการวนลูปและไม่เก็บลง tempStack
สถานะ: parkingStack =
, tempStack =
Step 3 (การคืนรถ): ดึงรถจาก tempStack ("04") กลับเข้าสู่ parkingStack เพื่อรักษาลำดับเดิม
สถานะสุดท้าย: parkingLot =
 (รถ "03" ถูกนำออกสำเร็จ และลำดับรถที่เหลือยังคงเดิม)
กรณีที่ 2: กรณีเลวร้ายที่สุด (Worst Case) - Algorithm B (Recursion)
เป้าหมาย: ต้องการนำรถทะเบียน "01" (คันล่างสุด) ออกด้วยการเรียกซ้ำ
Step 1 (Call 1): pop() "04" ออก และเรียกฟังก์ชันตัวเองซ้ำ (Recursive Call)
Step 2 (Call 2): pop() "03" ออก และเรียกฟังก์ชันตัวเองซ้ำ
Step 3 (Call 3): pop() "02" ออก และเรียกฟังก์ชันตัวเองซ้ำ
Step 4 (Call 4): pop() "01" ตรวจสอบแล้ว "พบเป้าหมาย" คืนค่า true และไม่นำรถคันนี้กลับเข้า Stack (Base Case)
Step 5 (Backtracking): ระบบจะย้อนกลับตามลำดับ Call stack เพื่อนำรถที่ขวางอยู่วางกลับลงไป
:
Backtrack 3: push("02") กลับเข้า Stack
Backtrack 2: push("03") กลับเข้า Stack
Backtrack 1: push("04") กลับเข้า Stack
สถานะสุดท้าย: parkingLot =
 (เป้าหมายคันล่างสุดถูกนำออกสำเร็จ)
การวิเคราะห์จำนวน Operation จากตัวอย่าง (บทที่ 7)
ข้อมูลนี้สามารถนำไปกรอกในตารางผลการทดลองของคุณได้ทันที

| กรณีทดสอบ (Target) | วิธีการ (Method) | Pop Count | Push Count | ผลลัพธ์ (Success) |
| :--- | :---: | :---: | :---: | :--- |
| คันที่ 2 จากบน ("03") | Iteration (A) | 2 | 1 | true |
| คันที่ 4 จากบน ("01") | Recursion (B) | 4 | 3 | true |