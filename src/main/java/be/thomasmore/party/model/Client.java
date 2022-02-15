package be.thomasmore.party.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Client {
    @Id
    private Integer id;
    private String name;
    private Date birthDate;
    private String gender;
    private Date startDate;

    public Client(String name, Date birthDate, String gender, Date startDate) {
        this.name = name;
        this.birthDate = birthDate;
        if (gender.equals("m") || gender.equals("f")){
            this.gender = gender;
        }
        this.startDate = startDate;
    }

    public Client() {
    }
}
