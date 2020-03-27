package shen_qian_copy.shen_copy;

import shen_qian_copy.shen_copy.Subject;

public class Student implements Cloneable{
    //基础数据类型
    private int age;
    private String name;
    //引用类型
    private Subject subject;
    public Student(int age, String name, Subject subject){
        this.age=age;
        this.name=name;
        this.subject=subject;
    }

    /**
     * 深拷贝，重写clone()，每个引用类型的变量都调用 各自重写的clone()
     * @return
     */
    public Object clone(){
        Student student=null;
        try {
            student= (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        student.subject= (Subject) subject.clone();
        return student;
    }

    public String toString(){
        return String.format("{Student:%d, subject:%s, name:%s, age:%d}",this.hashCode(),this.subject.toString(),this.name,this.age);
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
