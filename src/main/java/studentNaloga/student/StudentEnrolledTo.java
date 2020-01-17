package studentNaloga.student;

import javax.persistence.*;

@Entity
@Table(name = "Student_enrolled_to_class")
public class StudentEnrolledTo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "class_id")
    private String classId;


    public StudentEnrolledTo() {}

    public StudentEnrolledTo(Integer studentId, String classId) {
        super();
        this.studentId = studentId;
        this.classId = classId;
    }

    public Integer getStudentId(){
        return studentId;
    }
    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }

    public String getClassId(){
        return classId;
    }
    public void setClassId(String classId){
        this.classId = classId;
    }
}

