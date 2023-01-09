package nam.gor.bookkeeping.wage.dao;

import nam.gor.bookkeeping.wage.model.entity.Office;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeDao extends ReactiveMongoRepository<Office, String> {
}
