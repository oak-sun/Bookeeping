package nam.gor.bookkeeping.wage.service;

import nam.gor.bookkeeping.wage.model.dto.BonusDto;
import nam.gor.bookkeeping.wage.dao.BonusDao;
import nam.gor.bookkeeping.wage.model.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BonusService {
    private final BonusDao dao;

    @Autowired
    public BonusService(BonusDao dao) {
        this.dao = dao;
    }

    public Flux<BonusDto> findAll() {
        return dao
                .findAll()
                .map(EntityMapper::convertToDTO);
    }

    public Mono<BonusDto> findById(String id) {
        return dao
                .findById(id)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<BonusDto> save(Mono<BonusDto> dto) {
        return dto
                .map(EntityMapper::convertToEntity)
                .flatMap(dao::save)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<BonusDto> update(Mono<BonusDto> dto,
                                 String idFromDb) {
        return dao
                .findById(idFromDb)
                .flatMap(p -> dto
                        .map(EntityMapper::convertToEntity)
                        .doOnNext(e -> e.setId(idFromDb)))
                .flatMap(dao::save)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<Void> delete(String id) {
        return dao.deleteById(id);
    }
}
