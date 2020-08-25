package shen_qian_copy.qian_copy;

public class Subject {
    private String name;

    public Subject(String name) {
        this.name = name;
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
