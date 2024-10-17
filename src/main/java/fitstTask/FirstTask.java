package fitstTask;

import java.util.Scanner;

public class FirstTask {
    private static final int MIN_GRADE = 3;
    private static final int MAX_GRADE = 5;
    private static final int MAX_EXAMS = 10;
    private static final double HIGH_SCHOLARSHIP_THRESHOLD = 4.5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = getExamCount(scanner);
            if (n == -1) break;

            int[] grades = getGrades(scanner, n);
            String scholarshipType = determineScholarship(grades);
            System.out.println(scholarshipType);
        }

        scanner.close();
    }

    private static int getExamCount(Scanner scanner) {
        System.out.print("Enter the number of exams (type 'exit' to quit): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Exiting the program...");
            return -1;
        }

        try {
            int n = Integer.parseInt(input);
            if (n < 1 || n > MAX_EXAMS) {
                System.out.println("Please enter a number of exams between 1 and 10.");
                return getExamCount(scanner);
            }
            return n;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return getExamCount(scanner);
        }
    }

    private static int[] getGrades(Scanner scanner, int n) {
        int[] grades = new int[n];
        System.out.println("Enter the grades (3, 4, 5):");

        for (int i = 0; i < n; i++) {
            grades[i] = getGrade(scanner);
        }
        return grades;
    }

    private static int getGrade(Scanner scanner) {
        while (true) {
            try {
                int grade = Integer.parseInt(scanner.nextLine());
                if (grade < MIN_GRADE || grade > MAX_GRADE) {
                    System.out.println("Please enter a grade of 3, 4, or 5.");
                } else {
                    return grade;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid grade (3, 4, 5).");
            }
        }
    }

    private static String determineScholarship(int[] grades) {
        boolean hasThree = false;
        double sum = 0;

        for (int grade : grades) {
            if (grade == MIN_GRADE) {
                hasThree = true;
            }
            sum += grade;
        }

        double average = sum / grades.length;

        if (hasThree) {
            return "None";
        } else if (isAllFives(grades)) {
            return "Named";
        } else if (average >= HIGH_SCHOLARSHIP_THRESHOLD) {
            return "High";
        } else {
            return "Common";
        }
    }

    private static boolean isAllFives(int[] grades) {
        for (int grade : grades) {
            if (grade != MAX_GRADE) {
                return false;
            }
        }
        return true;
    }


    /*
    ----------- This is only solution of this task ----------

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] marks = new int[n];
        int totalMarks = 0;
        boolean hasThree = false;
        boolean allFives = true;

        for (int i = 0; i < n; i++) {
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
            if (marks[i] == 3) hasThree = true;
            if (marks[i] != 5) allFives = false;
        }

        double average = (double) totalMarks / n;

        if (hasThree) {
            System.out.println("None");
        } else if (allFives) {
            System.out.println("Named");
        } else if (average >= 4.5) {
            System.out.println("High");
        } else {
            System.out.println("Common");
        }

        scanner.close();
    }*/
}
