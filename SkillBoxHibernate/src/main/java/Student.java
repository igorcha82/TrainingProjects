
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="students")
@Data
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @OneToMany(mappedBy = "PurchaseKey.students", fetch = FetchType.LAZY)
//    private List<Purchaselist.PurchaseKey> purchases;

//    @ManyToOne
    @Column(name = "name")
    private String name;
    private Integer age;

    @Column(name = "registration_date")
    private Date registrationDate;

//    @OneToMany
//    @JoinTable(name = "linkedpurchaselist",
//            joinColumns = @JoinColumn(name = "Student_id"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
//    public Collection<LinkedPurchaseList> studentName = new ArrayList<LinkedPurchaseList>();

}