package nam.gor.bookkeeping.wage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "wage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wage {
    @Id
    private String id;
    private String userId;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private double workedHours;
    private double ratePerHour;
    private String officeId;
}
