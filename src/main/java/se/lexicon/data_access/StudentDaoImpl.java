package se.lexicon.data_access;

import org.springframework.stereotype.Repository;
import se.lexicon.model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {
    private List<Student> students = new ArrayList<>();

    @Override
    public Student save(Student student) {
        students.add(student);
        return student;
    }

    @Override
    public Student find(int id) {
        Optional<Student> foundStudent = students.stream()
            .filter(student -> student.getId() == id)
            .findFirst();
        return foundStudent.orElse(null);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id) {
        students.removeIf(student -> student.getId() == id);
    }
}
