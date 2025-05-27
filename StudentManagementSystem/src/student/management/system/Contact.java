package student.management.system;

public class Contact {
    private String rollNo;
    private String regNo;
    private String phone;
    private String email;


    public Contact(String rollNo, String regNo, String phone, String email) {
        this.rollNo = rollNo;
        this.regNo = regNo;
        this.phone = phone;
        this.email = email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "rollNo='" + rollNo + '\'' +
                ", regNo='" + regNo + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
