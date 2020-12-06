import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher {
    public Teacher(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinTable(name = "courses",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    public Collection<Course> teacherId = new ArrayList<Course>();

    private String name;
    private Integer salary;
    private Integer age;

}
