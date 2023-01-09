package nam.gor.bookkeeping.wage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OfficeDto {
    private String id;
    private String name;
    private String description;
}
