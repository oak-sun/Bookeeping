package nam.gor.bookkeeping.wage.handler;

import nam.gor.bookkeeping.wage.model.dto.BonusDto;
import nam.gor.bookkeeping.wage.service.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BonusHandler {
    private final BonusService service;

    @Autowired
    public BonusHandler(BonusService service) {
        this.service = service;
    }

    public Mono<ServerResponse> findAll(ServerRequest req) {
        Flux<BonusDto> dto = service.findAll();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(dto, BonusDto.class));
    }

    public Mono<ServerResponse> findById(ServerRequest req) {

        Mono<BonusDto> dto = service
                .findById(req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(dto, BonusDto.class));
    }

    public Mono<ServerResponse> save(ServerRequest req) {
        Mono<BonusDto> body = req.bodyToMono(BonusDto.class);
        Mono<BonusDto> saved = service.save(body);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(saved, BonusDto.class));
    }

    public Mono<ServerResponse> update(ServerRequest req) {
        Mono<BonusDto> body = req.bodyToMono(BonusDto.class);
        Mono<BonusDto> updated = service
                .update(body, req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(updated, BonusDto.class));
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        Mono<Void> deleted = service
                .delete(req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(deleted, Void.class));
    }
}
