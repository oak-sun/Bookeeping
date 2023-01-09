package nam.gor.bookkeeping.wage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nam.gor.bookkeeping.wage.model.entity.Region;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "taxes")
public class Tax {
    @Id
    private String id;

    private String name;

    private Region region;

    private int taxRate;

    private float wageFrom;

    private float wageTo;

    private String description;
}
