import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="Courses")
@Setter
@Getter

public class Course implements Serializable {
    public Course(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;


    private String name;
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    public Teacher teacher;


    @Column(name = "students_count")
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    private Float pricePerHour;

//    @OneToMany
//    @JoinTable(name = "linkedpurchaselist",
//            joinColumns = @JoinColumn(name = "Course_id"),
//            inverseJoinColumns = @JoinColumn(name = "id"))
//    public Collection<LinkedPurchaseList> courseName = new ArrayList<LinkedPurchaseList>();






}
