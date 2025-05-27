package student.management.system;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        StreamDAO streamDAO = new StreamDAO();
        SubjectDAO subjectDAO = new SubjectDAO();
        StudentSubjectDAO studentSubjectDAO = new StudentSubjectDAO();
        ContactDAO contactDAO = new ContactDAO();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Department");
            System.out.println("2. Add Stream");
            System.out.println("3. Add Subject");
            System.out.println("4. Add Student with Contact");
            System.out.println("5. View Student Details by Roll No");
            System.out.println("6. View All Students");
            System.out.println("7. Update Student Contact");
            System.out.println("8. Delete Student by Roll No");
            System.out.println("9. Assign Subject to Student");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Department Name: ");
                    String deptName = sc.nextLine();
                    departmentDAO.addDepartment(new Department(0, deptName));
                    System.out.println("Department added.");
                }
                case 2 -> {
                    System.out.print("Enter Stream Name: ");
                    String streamName = sc.nextLine();
                    System.out.print("Enter Department ID: ");
                    int deptId = sc.nextInt();
                    streamDAO.addStream(new Stream(0, streamName, deptId));
                    System.out.println("Stream added.");
                }
                case 3 -> {
                    System.out.print("Enter Subject Name: ");
                    String subjectName = sc.nextLine();
                    subjectDAO.addSubject(new Subject(0, subjectName));
                    System.out.println("Subject added.");
                }
                case 4 -> {
                    System.out.print("Enter Roll No: ");
                    String roll = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Semester: ");
                    int sem = sc.nextInt();
                    System.out.print("Enter Year of Admission: ");
                    int adm = sc.nextInt();
                    System.out.print("Enter Expected Passing Year: ");
                    int pass = sc.nextInt();
                    System.out.print("Enter Stream ID: ");
                    int streamId = sc.nextInt();
                    sc.nextLine();
                    studentDAO.addStudent(new Student(roll, name, sem, adm, pass, streamId));

                    System.out.print("Enter Reg No: ");
                    String reg = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    contactDAO.addContact(new Contact(roll, reg, phone, email));
                    System.out.println("Student and contact added.");
                }
                case 5 -> {
                    System.out.print("Enter Roll No: ");
                    String roll = sc.nextLine();
                    Student student = studentDAO.getStudentByRollNo(roll);
                    Contact contact = contactDAO.getContactByRollNo(roll);
                    if (student != null) {
                        System.out.println("Roll No: " + student.getRollNo());
                        System.out.println("Name: " + student.getName());
                        System.out.println("Semester: " + student.getSemester());
                        System.out.println("Admission Year: " + student.getYearOfAdmission());
                        System.out.println("Passing Year: " + student.getExpectedPassingYear());

                        Stream stream = streamDAO.getStreamById(student.getStreamId());
                        if (stream != null) {
                            System.out.println("Stream: " + stream.getStreamName());
                            Department dept = departmentDAO.getDepartmentById(stream.getDeptId());
                            if (dept != null) {
                                System.out.println("Department: " + dept.getDeptName());
                            }
                        }
                        if (contact != null) {
                            System.out.println("Reg No: " + contact.getRegNo());
                            System.out.println("Phone: " + contact.getPhone());
                            System.out.println("Email: " + contact.getEmail());
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                }
                case 6 -> {
                    System.out.println("\n=== All Students ===");
                    List<Student> allStudents = studentDAO.getAllStudents();
                    for (Student stu : allStudents) {
                        System.out.println("Roll No: " + stu.getRollNo());
                        System.out.println("Name: " + stu.getName());
                        System.out.println("Semester: " + stu.getSemester());
                        System.out.println("Admission Year: " + stu.getYearOfAdmission());
                        System.out.println("Passing Year: " + stu.getExpectedPassingYear());

                        Stream stream = streamDAO.getStreamById(stu.getStreamId());
                        if (stream != null) {
                            System.out.println("Stream: " + stream.getStreamName());
                            Department dept = departmentDAO.getDepartmentById(stream.getDeptId());
                            if (dept != null) {
                                System.out.println("Department: " + dept.getDeptName());
                            }
                        }
                        Contact con = contactDAO.getContactByRollNo(stu.getRollNo());
                        if (con != null) {
                            System.out.println("Phone: " + con.getPhone() + " | Email: " + con.getEmail());
                        }
                        System.out.println("------------------------");
                    }
                }
                case 7 -> {
                    System.out.print("Enter Roll No to Update Contact: ");
                    String roll = sc.nextLine();
                    System.out.print("Enter New Reg No: ");
                    String reg = sc.nextLine();
                    System.out.print("Enter New Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter New Email: ");
                    String email = sc.nextLine();
                    boolean updated = contactDAO.updateContact(new Contact(roll, reg, phone, email));
                    System.out.println(updated ? "Contact updated." : "Update failed.");
                }
                case 8 -> {
                    System.out.print("Enter Roll No to Delete: ");
                    String roll = sc.nextLine();
                    boolean deleted = studentDAO.deleteStudent(roll);
                    System.out.println(deleted ? "Student deleted." : "Deletion failed.");
                }
                case 9 -> {
		    System.out.print("Enter Roll No: ");
		    String roll = sc.nextLine();
		    System.out.print("Enter Subject ID: ");
		    int subId = sc.nextInt();
		    sc.nextLine();
		    StudentSubject studentSubject = new StudentSubject(roll, subId);
		    boolean added = studentSubjectDAO.addStudentSubject(studentSubject);
		    System.out.println(added ? "Subject assigned to student." : "Failed to assign subject.");
		}
                case 10 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
