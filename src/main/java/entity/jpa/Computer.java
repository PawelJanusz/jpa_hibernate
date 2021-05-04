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
}
