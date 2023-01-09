package nam.gor.bookkeeping.wage.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bonuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bonus {
    @Id
    private String id;
    private String name;
}
