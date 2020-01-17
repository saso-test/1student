package studentNaloga.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentEntrolledToController {

    @Autowired
    private StudentEnrolledToService studentEnrolledToService;

    @RequestMapping("/enrolledStudentsToClass")
    public List<StudentEnrolledTo> getAllStudentsEnrolledTo(){
        return studentEnrolledToService.getAllStudentsEnrolledTo();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/enrollStudentToClass")
    public void enrollStudent (@RequestBody StudentEnrolledTo studentEnrolledTo) {
        studentEnrolledToService.addStudentEnrolledTo(studentEnrolledTo);
    }
}
