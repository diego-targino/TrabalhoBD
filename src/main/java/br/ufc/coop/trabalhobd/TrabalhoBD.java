package br.ufc.coop.trabalhobd;

import Managers.StudentManager;
import br.ufc.coop.trabalhobd.Entities.Student;

public class TrabalhoBD {

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();

        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +"\n");
        }
        
        System.out.println("--------------------------------------");
        
        Student student = new Student("João das tapiocas", "joãozinhogameplays@gmail.com");

        studentManager.addStudent(student);

        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +"\n");
        }
        
        System.out.println("--------------------------------------");
        
        student = new Student(2, "Pedro Bala", "pedros2@gmail.com");
        
        studentManager.updateStudent(student);
        
        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +"\n");
        }
        
        System.out.println("--------------------------------------");
        
        studentManager.deleteStudent(student);
        
        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +"\n");
        }
        
        System.out.println("--------------------------------------");
    }
}
