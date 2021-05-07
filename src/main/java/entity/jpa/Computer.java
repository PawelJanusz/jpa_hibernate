package entity.jpa;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Device{

    private Long id;
    private String serialNumber;
    private List<Student> students = new ArrayList<>();

    public Computer() {
    }

    public Computer(String deviceName, String localization, String serialNumber) {
        super(deviceName, localization);
        this.serialNumber = serialNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
