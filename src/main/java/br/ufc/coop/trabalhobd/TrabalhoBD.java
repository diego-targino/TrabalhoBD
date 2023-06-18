package br.ufc.coop.trabalhobd;

import br.ufc.coop.trabalhobd.Managers.StudentManager;
import br.ufc.coop.trabalhobd.Entities.Student;

public class TrabalhoBD {

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();

        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +" " + s.getId() + "\n");
        }
        /*
        System.out.println("--------------------------------------");
        
        Student student = new Student("João das tapiocas", "joãozinhogameplays@gmail.com");

        studentManager.addStudent(student);

        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +"\n");
        }
        */
        
        System.out.println("--------------------------------------");
        
        Student student = new Student(3, "Pedro das Carnaubas", "pedros2@gmail.com");
        
        studentManager.updateStudent(student);
        
        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +"\n");
        }
        /*
        System.out.println("--------------------------------------");
        
        studentManager.deleteStudent(student);
        
        for(Student s : studentManager.getStudents()){
            System.out.println(s.toString() +"\n");
        }
        
        System.out.println("--------------------------------------");*/
    }
}
