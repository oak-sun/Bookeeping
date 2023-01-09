package nam.gor.bookkeeping.wage.service;

import nam.gor.bookkeeping.wage.model.dto.OfficeDto;
import nam.gor.bookkeeping.wage.dao.OfficeDao;
import nam.gor.bookkeeping.wage.model.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OfficeService {
    private final OfficeDao dao;

    @Autowired
    public OfficeService(OfficeDao dao) {
        this.dao = dao;
    }

    public Flux<OfficeDto> findAll() {
        return dao
                .findAll()
                .map(EntityMapper::convertToDTO);
    }

    public Mono<OfficeDto> findById(String id) {
        return dao
                .findById(id)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<OfficeDto> save(Mono<OfficeDto> dto) {
        return dto
                .map(EntityMapper::convertToEntity)
                .flatMap(dao::save)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<OfficeDto> update(Mono<OfficeDto> dto,
                                  String idFromDb) {
        return dao
                .findById(idFromDb)
                .flatMap(off ->
                         dto
                        .map(EntityMapper::convertToEntity)
                        .doOnNext(e-> e.setId(idFromDb)))
                .flatMap(dao::save)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<Void> delete(String id) {
        return dao.deleteById(id);
    }
}
