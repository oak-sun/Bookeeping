package nam.gor.bookkeeping.wage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nam.gor.bookkeeping.wage.model.Role;
import nam.gor.bookkeeping.wage.model.entity.Bonus;
import nam.gor.bookkeeping.wage.model.entity.Region;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirm;
    private String occupation;
    private Region residence;

    //saves only IDs of some salary, it won't be inner object in Mongo
    private List<String> salaryList;
    private float totalWage;
    private float totalTax;
    private float netPay;
    private LocalDate dateOfBirth;
    private Set<Role> roles;
    private Set<Bonus> bonuses;
}
