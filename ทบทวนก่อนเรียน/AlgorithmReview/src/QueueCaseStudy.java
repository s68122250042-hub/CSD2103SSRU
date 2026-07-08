import java.util.LinkedList;
import java.util.Queue;
public class QueueCaseStudy {
    public static void main(String[] args) {
        Queue<String> patientQueue = new LinkedList<>();
        patientQueue.add("P001");
        patientQueue.add("P002");
        patientQueue.add("P003");
        patientQueue.add("P004");
        patientQueue.add("P005");
        System.out.println("Incomeing Queue: ");
        for (int i = 0; i < 2; i++) {
            if (!patientQueue.isEmpty()) {
                String servedPatient = patientQueue.remove();
                System.out.println("Out queue: " + servedPatient);
            }
        }
        patientQueue.add("P006");
        patientQueue.add("P007");
        System.out.println("New Queue Income: P006, P007");
        if (!patientQueue.isEmpty()) {
            System.out.println("Next Queue: " + patientQueue.peek());
        }
        System.out.println("Patients in Queue: " + patientQueue.size());
        System.out.println("Current Queue: " + patientQueue);
    }
}
//FIFO (First-In, First-Out) คือ ข้อมูลที่ถูกเพิ่มเข้าก่อนที่จะถูกนำออกเป็นลำดับแรก