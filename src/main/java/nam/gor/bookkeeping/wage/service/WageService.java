package nam.gor.bookkeeping.wage.service;

import nam.gor.bookkeeping.wage.model.dto.WageDto;
import nam.gor.bookkeeping.wage.dao.WageDao;
import nam.gor.bookkeeping.wage.model.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class WageService {
    private final WageDao dao;

    @Autowired
    public WageService(WageDao dao) {
        this.dao = dao;
    }

    public Flux<WageDto> findAll() {
        return dao
                .findAll()
                .map(EntityMapper::convertToDTO);
    }

    public Mono<WageDto> findById(String id) {
        return dao
                .findById(id)
                .map(EntityMapper::convertToDTO);
    }

    public Flux<WageDto> findByUser(String userId) {
        return dao
                .findAllByUserId(userId)
                .map(EntityMapper::convertToDTO);
    }

    public Flux<WageDto> findByUserAndDate(String userId,
                                           LocalDateTime from,
                                           LocalDateTime to) {
        return dao
                .findAllByUserAndDate(userId, from, to)
                .map(EntityMapper::convertToDTO);
    }

    public Flux<WageDto> findByUserAndCompany(String userId,
                                              String officeId) {
        return dao
                .findAllByUserAndCompany(userId, officeId)
                .map(EntityMapper::convertToDTO);
    }

    //TODO: add user-existence check and office-existence check.
    public Mono<WageDto> save(Mono<WageDto> dto) {
        return dto
                .map(EntityMapper::convertToEntity)
                .flatMap(dao::save)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<WageDto> update(Mono<WageDto> dto,
                                String idFromDb) {
        return dao
                .findById(idFromDb)
                .flatMap(wage -> dto
                          .map(EntityMapper::convertToEntity)
                          .doOnNext(e -> e.setId(idFromDb)))
                .flatMap(dao::save)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<Void> delete(String id) {
        return dao.deleteById(id);
    }
}
