package nam.gor.bookkeeping.wage.handler;

import nam.gor.bookkeeping.wage.model.entity.Region;
import nam.gor.bookkeeping.wage.model.dto.RegionDto;
import nam.gor.bookkeeping.wage.service.RegionService;
import nam.gor.bookkeeping.wage.model.EntityMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RegionHandler {
    private final RegionService service;

    @Autowired
    public RegionHandler(RegionService service) {
        this.service = service;
    }

    public Mono<ServerResponse> aloha(ServerRequest req) {
        BodyInserter<String,
                ReactiveHttpOutputMessage> body =
                BodyInserters.fromValue("Aloha, Spring!");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    public Mono<ServerResponse> findAll(ServerRequest req) {
        Flux<Region> regions = service.findAll();
        Flux<RegionDto> dto = regions.map(EntityMapper::convertToDTO);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(dto, RegionDto.class));
    }

    public Mono<ServerResponse> findById(ServerRequest req) {
        Mono<RegionDto> dto = service
                .findById(req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(dto, RegionDto.class));
    }

    @SneakyThrows
    public Mono<ServerResponse> save (ServerRequest req) {
        Mono<RegionDto> body = req.bodyToMono(RegionDto.class);
        Mono<RegionDto> saved = service.save(body);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(saved, RegionDto.class));
    }

    public Mono<ServerResponse> update(ServerRequest req) {
        Mono<RegionDto> body = req.bodyToMono(RegionDto.class);
        Mono<RegionDto> updated = service
                .update(body, req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(updated, RegionDto.class));
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        Mono<Void> deleted = service
                .delete(req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleted,
                        Void.class));
    }
}
