package se.lexicon;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.lexicon.config.ComponentScanConfig;
import se.lexicon.data_access.StudentDao;
import se.lexicon.model.Student;
import se.lexicon.service.StudentManagement;
import se.lexicon.util.UserInputService;

import java.util.List;

// Note: @Component is a generalized version of @Service, @Controller and @Repository.
// in the code select the one that is most suitable for what the class is to accomplish.

public class App
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);

        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        UserInputService userInputService = context.getBean(UserInputService.class);

        StudentManagement studentManagement = context.getBean(StudentManagement.class);

        Student newStudent = studentManagement.create();
        System.out.println("Created student: " + newStudent.getName());

        Student savedStudent = studentManagement.save(newStudent);
        System.out.println("Saved student: " + savedStudent.getName());

        /*Student newStudent = studentManagement.create();
        System.out.println("Created student: " + newStudent.getName());

        Student savedStudent = studentManagement.save(newStudent);
        System.out.println("Saved student: " + savedStudent.getName());*/

        System.out.println("Enter the id of the student to find:");
        int idToFind = userInputService.getInt();
        Student foundStudent = studentManagement.find(idToFind);
        if (foundStudent != null) {
            System.out.println("Found student: " + foundStudent.getName());
        } else {
            System.out.println("No student found with id: " + idToFind);
        }

        System.out.println("Enter the id of the student to edit:");
        int idToEdit = userInputService.getInt();
        System.out.println("Enter the new name of the student:");
        String newName = userInputService.getString();
        Student studentToEdit = new Student();
        studentToEdit.setId(idToEdit);
        studentToEdit.setName(newName);
        Student editedStudent = studentManagement.edit(studentToEdit);
        if (editedStudent != null) {
            System.out.println("Edited student: " + editedStudent.getName());
        } else {
            System.out.println("No student found with id: " + idToEdit);
        }

        List<Student> allStudents = studentManagement.findAll();
        System.out.println("All students:");
        for (Student student : allStudents) {
            System.out.println("Student id: " + student.getId() + ", name: " + student.getName());
        }

        System.out.println("Enter the id of the student to remove:");
        int idToRemove = userInputService.getInt();
        Student removedStudent = studentManagement.remove(idToRemove);
        if (removedStudent != null) {
            System.out.println("Removed student: " + removedStudent.getName());
        } else {
            System.out.println("No student found with id: " + idToRemove);
        }

        allStudents = studentManagement.findAll();
        System.out.println("All students:");
        for (Student student : allStudents) {
            System.out.println("Student id: " + student.getId() + ", name: " + student.getName());
        }

    }
}
