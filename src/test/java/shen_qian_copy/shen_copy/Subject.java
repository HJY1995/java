package shen_qian_copy.shen_copy;

public class Subject implements Cloneable {
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    /**
     * 重写clone()，每个对象都调用父类的clone() 方法
     *
     * @return
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return String.format("[Subject: %d, name: %s]", this.hashCode(), this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
