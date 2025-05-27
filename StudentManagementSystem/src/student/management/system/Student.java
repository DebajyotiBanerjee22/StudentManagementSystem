/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;

/**
 *
 * @author debaj
 */
public class Student {
    private String rollNo;
    private String name;
    private int semester;
    private int yearOfAdmission;
    private int expectedPassingYear;
    private int streamId;

    public Student(String rollNo, String name, int semester, int yearOfAdmission, int expectedPassingYear, int streamId) {
        this.rollNo = rollNo;
        this.name = name;
        this.semester = semester;
        this.yearOfAdmission = yearOfAdmission;
        this.expectedPassingYear = expectedPassingYear;
        this.streamId = streamId;
    }

    public Student(String rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public int getYearOfAdmission() { return yearOfAdmission; }
    public void setYearOfAdmission(int yearOfAdmission) { this.yearOfAdmission = yearOfAdmission; }

    public int getExpectedPassingYear() { return expectedPassingYear; }
    public void setExpectedPassingYear(int expectedPassingYear) { this.expectedPassingYear = expectedPassingYear; }

    public int getStreamId() { return streamId; }
    public void setStreamId(int streamId) { this.streamId = streamId; }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo='" + rollNo + '\'' +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                ", yearOfAdmission=" + yearOfAdmission +
                ", expectedPassingYear=" + expectedPassingYear +
                ", streamId=" + streamId +
                '}';
    }
}