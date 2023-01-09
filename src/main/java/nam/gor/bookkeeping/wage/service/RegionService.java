package nam.gor.bookkeeping.wage.service;

import nam.gor.bookkeeping.wage.model.entity.Region;
import nam.gor.bookkeeping.wage.model.dto.RegionDto;
import nam.gor.bookkeeping.wage.dao.RegionDao;
import nam.gor.bookkeeping.wage.model.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RegionService {
    private final RegionDao dao;

    @Autowired
    public RegionService(RegionDao dao) {
        this.dao = dao;
    }

    public Flux<Region> findAll() {
        return dao.findAll();
    }

    public Mono<RegionDto> findById(String id) {
        return dao
                .findById(id)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<RegionDto> save(Mono<RegionDto> dto) {
        return dto
                .map(EntityMapper::convertToEntity)
                .flatMap(dao::save)
                .map(EntityMapper::convertToDTO);
    }

    public Mono<RegionDto> update(Mono<RegionDto> dto,
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
