package nam.gor.bookkeeping.wage.dao;

import nam.gor.bookkeeping.wage.model.entity.Bonus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusDao extends ReactiveMongoRepository<Bonus, String> {
}
