package studentNaloga.studentClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentClassController {

    @Autowired
    StudentClassService studentClassService;

    @GetMapping("/studentClass")
    private List<StudentClass> getAllStudentClasses() {
        return studentClassService.getAllStudentClasses();
    }

    @GetMapping("/studentClassName/{studentClassName}")
    public List<StudentClass> getStudentClassesByName(@PathVariable("studentClassName") String studentClassName ) {
        return studentClassService.getStudentClassesByName(studentClassName);
    }
}
