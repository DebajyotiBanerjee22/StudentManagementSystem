package student.management.system;
public class StudentSubject {
    private String rollNo;
    private int subjectId;

    public StudentSubject(String rollNo, int subjectId) {
        this.rollNo = rollNo;
        this.subjectId = subjectId;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "StudentSubject{" +
                "rollNo='" + rollNo + '\'' +
                ", subjectId=" + subjectId +
                '}';
    }
}
