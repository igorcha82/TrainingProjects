import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@EqualsAndHashCode
public class Subscription implements Serializable{
    public Subscription(){}

    @EmbeddedId
    public Identy identy;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Integer courseId;

    @Data
    @Embeddable
    public static class Identy implements Serializable {

        @OneToOne (cascade = CascadeType.ALL)
        @JoinColumn(name = "student_id", insertable = false, updatable = false)
        private Student studentId;

        @OneToOne (cascade = CascadeType.ALL)
        @JoinColumn(name = "course_id", insertable = false, updatable = false)
        private Course courseId;

        public Identy (Student studentId, Course courseId) {
            this.studentId=studentId;
            this.courseId=courseId;
        }



    }


}


