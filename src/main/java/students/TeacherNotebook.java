package students;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeacherNotebook {
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public List<String> findFailingStudents(){
        return students.stream().filter(student -> findAverage(student.getGrades()) < 2).map(student -> student.getName()).toList();
    }

    public double findAverage(List<Integer> grades){
        return grades.stream().mapToDouble(a -> a).average().orElseThrow(()-> new IllegalArgumentException("Empty grades"));
    }

    public void readFromFile(Path path ){
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                students.add(parseLine(line));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    public Student parseLine(String line){
        String[] parts = line.split(";");
        String studentName = parts[0];
        String className = parts[1];
        Student student = new Student(studentName,className);
        int grade;
        for( int i=2; i<parts.length; i++){
            grade = Integer.parseInt(parts[i]);
            student.addGrade(grade);
        }
       return student;
    }
}
