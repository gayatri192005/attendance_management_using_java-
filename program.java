import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    String name;
    int totalClasses;
    int attendedClasses;

    public Student(String name) {
        this.name = name;
        this.totalClasses = 0;
        this.attendedClasses = 0;
    }

    public void markAttendance(boolean isPresent) {
        totalClasses++;
        if (isPresent) {
            attendedClasses++;
        }
    }

    public double getAttendancePercentage() {
        return (totalClasses == 0) ? 0 : (attendedClasses * 100.0) / totalClasses;
    }

    public void displayAttendance() {
        System.out.printf("%-10s | Total Classes: %-5d | Attended: %-5d | Attendance Percentage: %.2f%%\n", 
                          name, totalClasses, attendedClasses, getAttendancePercentage());
    }
}

public class AttendanceManagementSystem {
    static HashMap<String, Student> students = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Attendance Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    markAttendance();
                    break;
                case 3:
                    viewAttendance();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        if (students.containsKey(name)) {
            System.out.println("Student already exists.");
        } else {
            students.put(name, new Student(name));
            System.out.println("Student added successfully.");
        }
    }

    public static void markAttendance() {
        System.out.println("\n--- Mark Attendance ---");
        for (String name : students.keySet()) {
            System.out.print("Is " + name + " present? (y/n): ");
            char attendance = scanner.next().charAt(0);
            boolean isPresent = (attendance == 'y' || attendance == 'Y');
            students.get(name).markAttendance(isPresent);
        }
    }

    public static void viewAttendance() {
        System.out.println("\n--- Attendance Report ---");
        for (Student student : students.values()) {
            student.displayAttendance();
        }
    }
}
