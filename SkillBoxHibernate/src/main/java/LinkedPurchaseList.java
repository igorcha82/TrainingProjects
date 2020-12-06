import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList implements Serializable {

    private Integer studentId;

    private Integer courseId;

    @Column(name = "price")
    private Integer coursePrice;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "student_name")
    private String studentName;


    @EmbeddedId
    private KeyLink keyLink;

    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(KeyLink keyLink) {
        this.keyLink = keyLink;
    }

}



