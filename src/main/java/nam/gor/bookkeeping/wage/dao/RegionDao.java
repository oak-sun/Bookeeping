package nam.gor.bookkeeping.wage.dao;

import nam.gor.bookkeeping.wage.model.entity.Region;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionDao extends ReactiveMongoRepository<Region, String> {
}
