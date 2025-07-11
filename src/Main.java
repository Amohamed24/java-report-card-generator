import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        System.out.println("Welcome to the Report Card Generator!");

        while (true) {
            System.out.println("******************************************************");
            System.out.println("Enter student name (or type 'q' to quit and view all report cards): ");
            String nameInput = scanner.nextLine();

            if (nameInput.equalsIgnoreCase("q")) {
                break;
            }

            double grade = -1; // initialize grade so always has value and doesn't cause errors
            while (true) {
                System.out.println("Enter grade for " + nameInput + " (0 - 100): ");
                String gradeInput = scanner.nextLine();

                if (gradeInput.equalsIgnoreCase("q")) {
                    grade = -1;
                    break;
                }

                try {
                    grade = Double.parseDouble(gradeInput);
                    if (grade < 0 || grade > 100) {
                        System.out.println("Grade must be between 0 and 100.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric grade.");
                }
            }

            if (grade == -1) {
                continue; // skips to next student and doesn't return anything incorrect
            }

            if (nameInput.equalsIgnoreCase("q")) {
                break;
            }

            Student student = new Student(nameInput, grade);
            students.add(student);

            String letterGrade = student.calculateLetterGrade();
            student.printLetterGradeMessage(letterGrade);
        }

        System.out.println("\n========== Report Cards ==========");
        for (Student s : students) {
            System.out.printf("%s: %.2f -> %s%n", s.getName(), s.getGrade(), s.calculateLetterGrade());
        }

        System.out.println("Thank you for using the Report Card Generator!");

        scanner.close();
    }

    static class Student {
        private String name;
        private double grade;

        public Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public double getGrade() {
            return grade;
        }

        public String calculateLetterGrade() {
            if (grade <= 59) return "F";
            if (grade <= 69) return "D";
            if (grade <= 79) return "C";
            if (grade <= 89) return "B";
            return "A";
        }

        public void printLetterGradeMessage(String letterGrade) {
            switch (letterGrade) {
                case "F" -> System.out.println("Boo! You failed. Better luck next time 🤮");
                case "D" -> System.out.println("Well, D is better than failing... 🤷");
                case "C" -> System.out.println("C's get degrees 😎");
                case "B" -> System.out.println("Nice work " + name + "! You got a B on the exam!! 👏");
                case "A" -> System.out.println("WOW!! AN A?! You're a genius " + name + " 😱");
                default -> System.out.println("Invalid grade.");
            }
        }
    }
}
