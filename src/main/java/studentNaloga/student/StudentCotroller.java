package studentNaloga.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentCotroller {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    private List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student retrieveStudent(@PathVariable Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getStudentId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/students/{studentId}/{classId}")
    public String enrollStudentToClass(Student student, @PathVariable Integer studentId, @PathVariable Integer classId) {
        return studentService.enrollStudentToClass(studentId, classId);
    }

    @DeleteMapping("/students/{studentId}/{classId}")
    public String deleteEnrolledStudentToClass(Student student, @PathVariable Integer studentId, @PathVariable Integer classId) {
        return studentService.deleteEnrolledStudentToClass(studentId, classId);
    }
}
