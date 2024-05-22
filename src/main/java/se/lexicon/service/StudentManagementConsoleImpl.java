package se.lexicon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.data_access.StudentDao;
import se.lexicon.model.Student;
import se.lexicon.util.UserInputService;

import java.util.List;

@Service
public class StudentManagementConsoleImpl implements StudentManagement {
    private final UserInputService userInputService;
    private final StudentDao studentDao;

    @Autowired
    public StudentManagementConsoleImpl(UserInputService userInputService, StudentDao studentDao) {
        this.userInputService = userInputService;
        this.studentDao = studentDao;
    }

    @Override
    public Student create() {
        System.out.println("Enter student ID:");
        int id = userInputService.getInt();
        System.out.println("Enter student name:");
        String name = userInputService.getString();

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        return student;
    }

    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public Student find(int id) {
        return studentDao.find(id);
    }

    @Override
    public Student edit(Student student) {
        Student existingStudent = studentDao.find(student.getId());
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            studentDao.save(existingStudent);
        }
        return existingStudent;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student remove(int id) {
        Student student = studentDao.find(id);
        if (student != null) {
            studentDao.delete(id);
        }
        return student;
    }
}
