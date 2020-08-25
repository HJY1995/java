package shen_qian_copy.shen_copy;


public class Test {
    public static void main(String[] args) {
        Subject subjectA = new Subject("yuwen");
        Student studentA = new Student(10, "stuA", subjectA);

        Student studentB = (Student) studentA.clone();
        studentB.setAge(12);
        studentB.setName("stuB");

        Subject subjectB = studentB.getSubject();
        subjectB.setName("shuxue");

        System.out.println("studentA: " + studentA.toString());
        System.out.println("studentB: " + studentB.toString());
    }
}
