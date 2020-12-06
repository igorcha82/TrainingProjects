import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
@Getter
@Setter
@EqualsAndHashCode
public class Purchaselist implements Serializable {
    public Purchaselist() {
    }

        @EmbeddedId
        private PurchaseKey purchaseKey;

        @Column(name = "price")
        private Integer price;

        @Column(name = "subscription_date")
        private Date subscriptionDate;


        @Column(name = "students_name", insertable = false, updatable = false)
        private String students;

        @Column(name = "course_name", insertable = false, updatable = false)
        private String course;


        @NoArgsConstructor
        @EqualsAndHashCode(of = {"student", "course"})
        @Embeddable
        @Data
        public static class PurchaseKey implements Serializable {

            @ManyToOne(cascade = CascadeType.ALL)
            @JoinColumn(name = "student_name", referencedColumnName = "name")
            private Student student;

            @ManyToOne(cascade = CascadeType.ALL)
            @JoinColumn(name = "course_name", referencedColumnName = "name")
            private Course course;

        }
    }

