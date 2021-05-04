package entity.jpa;

public abstract class Device {

    private String deviceName;
    private String localization;

    public Device() {
    }

    public Device(String deviceName, String localization) {
        this.deviceName = deviceName;
        this.localization = localization;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }
}
