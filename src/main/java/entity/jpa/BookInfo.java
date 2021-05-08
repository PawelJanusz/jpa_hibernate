package entity.jpa;

public class BookInfo {

    private String title;
    private String studentName;
    private String studentSurname;

    public BookInfo(String title, String studentName, String studentSurname) {
        this.title = title;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                '}';
    }
}
