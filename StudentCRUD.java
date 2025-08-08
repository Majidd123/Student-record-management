import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + age + " | " + course;
    }
}

public class StudentCRUD {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Student> students = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Record Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: viewStudentById(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: 
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = getIntInput();
        System.out.print("Enter course: ");
        String course = sc.nextLine();

        Student s = new Student(nextId++, name, age, course);
        students.add(s);
        System.out.println("✅ Student added successfully!");
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("ID | Name | Age | Course");
        System.out.println("-------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static void viewStudentById() {
        System.out.print("Enter student ID: ");
        int id = getIntInput();
        Student s = findStudentById(id);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int id = getIntInput();
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep same): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) s.setName(name);

        System.out.print("Enter new age (0 to keep same): ");
        int age = getIntInput();
        if (age != 0) s.setAge(age);

        System.out.print("Enter new course (leave blank to keep same): ");
        String course = sc.nextLine();
        if (!course.isEmpty()) s.setCourse(course);

        System.out.println("✅ Student updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        int id = getIntInput();
        Student s = findStudentById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("✅ Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    private static int getIntInput() {
        while (true) {
            try {
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
