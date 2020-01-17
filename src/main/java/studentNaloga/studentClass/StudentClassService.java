package studentNaloga.studentClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentClassService {

    @Autowired
    StudentClassRepository studentClassRepository;

    public List<StudentClass> getAllStudentClasses() {
        List<StudentClass> studentClasses = new ArrayList<StudentClass>();
        studentClassRepository.findAll().forEach(studentClass -> studentClasses.add(studentClass));
        return studentClasses;
    }

    public List<StudentClass> getStudentClassesByName(String studentClassName){
        List<StudentClass>  studentClasses = studentClassRepository.findStudentClassesByName(studentClassName);
        return studentClasses;
    }
}
