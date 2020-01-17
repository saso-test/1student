package studentNaloga.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentEnrolledToService {

    @Autowired
    StudentEntrolledToRepository studentEntrolledToRepository;
    private List<StudentEnrolledTo> studentsEnrolledTo = new ArrayList<>();


    public List<StudentEnrolledTo> getAllStudentsEnrolledTo() {
        return studentsEnrolledTo;
    }

    public void addStudentEnrolledTo(StudentEnrolledTo studentEnrolledTo) {
        studentsEnrolledTo.add(studentEnrolledTo);
    }
}
