import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


    @Embeddable
    @Data
   public class KeyLink implements Serializable {

        @OneToOne(cascade = CascadeType.ALL)
        private Course courseId;

        @OneToOne(cascade = CascadeType.ALL)
        private Student studentId;

        public KeyLink(Student studentId, Course courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }


    }

