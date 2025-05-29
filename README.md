# StudentManagementSystem- Java Project
A complete backend project built using Java SE, MySQL, JDBC, and Apache NetBeans (JDK 24) for managing students, their academic and contact details, streams, departments, and subjects. This project follows an MVC-like separation of DAO and Model classes.
Technologies Used
Java JDK 24
NetBeans IDE
MySQL (Tested on version with JDBC driver mysql-connector-java-9.3.0.jar)
JDBC
Mermaid (for UML/Class diagram)

# Project Structure
student-management-system/
│
├── src/student/management/system/
│   ├── Main.java
│   ├── Conn.java
│   ├── Student.java
│   ├── Contact.java
│   ├── Department.java
│   ├── Stream.java
│   ├── Subject.java
|   ├── StudentSubject.java
│   ├── StudentDAO.java
│   ├── ContactDAO.java
│   ├── DepartmentDAO.java
│   ├── StreamDAO.java
│   ├── SubjectDAO.java
│   ├── StudentSubjectDAO.java
│
├── lib/
│   └── mysql-connector-java-9.3.0.jar
├── SQL stmnt Student Database.txt
├── ER model.pdf
└── Class Flow-Chart-2025-05-27-181424.png


# Open MySQL Workbench on your PC then move to your localHost Database Management Space then follow the instructions given below
# Database Schema
Show databases;
use studentdb;
CREATE TABLE department (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(50) NOT NULL
);
CREATE TABLE stream (
    stream_id INT PRIMARY KEY AUTO_INCREMENT,
    stream_name VARCHAR(50) NOT NULL,
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);
CREATE TABLE student (
    roll_no VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    semester INT,
    year_of_admission INT,
    expected_passing_year INT,
    stream_id INT,
    FOREIGN KEY (stream_id) REFERENCES stream(stream_id)
);
CREATE TABLE subject (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_name VARCHAR(100)
);
CREATE TABLE student_subject (
    roll_no VARCHAR(20),
    subject_id INT,
    PRIMARY KEY (roll_no, subject_id),
    FOREIGN KEY (roll_no) REFERENCES student(roll_no),
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
);
CREATE TABLE contact (
    roll_no VARCHAR(20),
    reg_no VARCHAR(20) UNIQUE,
    phone VARCHAR(15),
    email VARCHAR(100),
    PRIMARY KEY (roll_no),
    FOREIGN KEY (roll_no) REFERENCES student(roll_no)
);
show databases;
Select * from contact;
select * from stream;
Select * from Student;
Select * from department;
select * from subject;
select * from student_subject;

# Dont forget to use the query 'Use studentdb'

ER Diagram
The ER diagram for the project is stored in the ER model.pdf file
 1:1 → Student ↔ Contact
 1:N → Department → Stream, Stream → Student
 M:N → Student ↔ Subject (via StudentSubject)

UML Class Diagram
View the class diagram here:
Chart-2025-05-27-181424.png 
This includes:
Entity classes
DAO classes
Main and Connection logic
Relationships and access flows

# Sample Output
Included in SQL stmnt Student Database.txt and shown below:

===== Student Management System =====
1. Add Department
2. Add Stream
3. Add Subject
4. Add Student with Contact
5. View Student Details by Roll No
6. View All Students
7. Update Student Contact
8. Delete Student by Roll No
9. Assign Subject to Student
10. Exit
Enter your choice: 6

=== All Students ===
Roll No: 2025CS001
Name: Alice
Semester: 1
Admission Year: 2025
Passing Year: 2029
Stream: CSE
Department: Computer Science
Phone: 9876543210 | Email: alice@example.com
------------------------
Roll No: 2025EE003
Name: Upendu Pushpak
Semester: 1
Admission Year: 2025
Passing Year: 2029
Stream: CSE
Department: Computer Science
Phone: 898154329 | Email: puspendra@yahoo.in
------------------------
Roll No: 2025EEE002
Name: Juniper
Semester: 1
Admission Year: 2025
Passing Year: 2029
Stream: EEE
Department: Electrical Engineering
Phone: 9999554321 | Email: junip@gmail.com
------------------------

===== Student Management System =====
1. Add Department
2. Add Stream
3. Add Subject
4. Add Student with Contact
5. View Student Details by Roll No
6. View All Students
7. Update Student Contact
8. Delete Student by Roll No
9. Assign Subject to Student
10. Exit
Enter your choice: 8
Enter Roll No to Delete: 2025EE003
Student deleted.

===== Student Management System =====
1. Add Department
2. Add Stream
3. Add Subject
4. Add Student with Contact
5. View Student Details by Roll No
6. View All Students
7. Update Student Contact
8. Delete Student by Roll No
9. Assign Subject to Student
10. Exit
Enter your choice: 6

=== All Students ===
Roll No: 2025CS001
Name: Alice
Semester: 1
Admission Year: 2025
Passing Year: 2029
Stream: CSE
Department: Computer Science
Phone: 9876543210 | Email: alice@example.com
------------------------
Roll No: 2025EEE002
Name: Juniper
Semester: 1
Admission Year: 2025
Passing Year: 2029
Stream: EEE
Department: Electrical Engineering
Phone: 9999554321 | Email: junip@gmail.com
------------------------

===== Student Management System =====
1. Add Department
2. Add Stream
3. Add Subject
4. Add Student with Contact
5. View Student Details by Roll No
6. View All Students
7. Update Student Contact
8. Delete Student by Roll No
9. Assign Subject to Student
10. Exit

# Also includes tested outputs for adding, viewing, updating, deleting students and assigning subjects.

#How to Run
Open the project in NetBeans.
Ensure MySQL server is running and database studentdb is created.
Execute the SQL from SQL stmnt Student Database.txt.
Add the mysql-connector-java-9.3.0.jar in lib/ to your NetBeans project classpath.
Run Main.java.

# ************The .png and .pdf and .txt is packaged in Zip folder**************
# for ease I have also provided the mysql-connector-j-9.3.0.jar file seperately download it and add it in your library with the latest jdk version 24, it also works with jdk 17, I have use Apache Netbeans 21, you may use your familiear ide.
