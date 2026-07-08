import java.util.Stack;
public class StackCaseStudy {
    public static void main(String[] args) {
        Stack<String> cmStack = new Stack<>();
        cmStack.push("Type Data");
        cmStack.push("Type Structure");
        cmStack.push("Delete Structure");
        cmStack.push("Type Algorithm");
        cmStack.push("Type Java");
        System.out.println("All commands : " + cmStack);
        System.out.println("Undo Process --->");

        for (int i = 1; i <= 2; i++) {
            if (!cmStack.isEmpty()) {
                String undocm = cmStack.pop();
                System.out.println("Undo No " + i + ": Undo " + undocm);
            } else {
                System.out.println("Cannot undo because the undo stack is empty");
            }
        }
        System.out.println("Stack After undo: " + cmStack);
    }
}

//LIFO (Last-In,First-Out) คือข้อมูลที่ถูกเพิ่มเข้าโปรแกรมล่าสุดจะถูกนำมายกเลิกเป็นลำดับแรก