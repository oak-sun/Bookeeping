package nam.gor.bookkeeping.wage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WageDto {
    private String id;
    private String userId;
    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;
    private double workedHours;
    private double ratePerHour;
    private String officeId;

    public WageDto(String userId,
                   LocalDateTime shiftStart,
                   LocalDateTime shiftEnd,
                   double workedHours,
                   double ratePerHour,
                   String officeId) {
        this.userId = userId;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.workedHours = workedHours;
        this.ratePerHour = ratePerHour;
        this.officeId = officeId;
    }
}
