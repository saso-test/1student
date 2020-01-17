package studentNaloga.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEntrolledToRepository  extends CrudRepository<StudentEnrolledTo, Integer>{

}
