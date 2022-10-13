package jm.task.core.jdbc.model;
import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User(ResultSet resultSet) {
        try {
            this.name = resultSet.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.lastName = resultSet.getString("last_name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.age = resultSet.getByte("age");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.id = resultSet.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
