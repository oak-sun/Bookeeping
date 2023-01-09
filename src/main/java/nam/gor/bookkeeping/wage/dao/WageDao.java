package nam.gor.bookkeeping.wage.dao;

import nam.gor.bookkeeping.wage.model.entity.Wage;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import java.time.LocalDateTime;

@Repository
public interface WageDao extends ReactiveMongoRepository<Wage, String> {
    @Query("{'userId': ?0}")
    Flux<Wage> findAllByUserId(String userId);

    @Query("{'userId' :  ?0, 'shiftStart' : {$gte: ?1, $lte: ?2}}")
    Flux<Wage> findAllByUserAndDate(String userId, LocalDateTime from, LocalDateTime to);

    @Query("{'userId' :  ?0, 'officeId' : ?1}")
    Flux<Wage> findAllByUserAndCompany(String userId, String officeId);

}
