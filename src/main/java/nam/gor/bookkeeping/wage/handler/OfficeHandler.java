package nam.gor.bookkeeping.wage.handler;

import nam.gor.bookkeeping.wage.model.dto.OfficeDto;
import nam.gor.bookkeeping.wage.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class OfficeHandler {
    private final OfficeService service;

    @Autowired
    public OfficeHandler(OfficeService service) {
        this.service = service;
    }

    public Mono<ServerResponse> findAll(ServerRequest req) {
        Flux<OfficeDto> dto = service.findAll();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(dto, OfficeDto.class));
    }

    public Mono<ServerResponse> findById(ServerRequest req) {
        Mono<OfficeDto> dto = service
                     .findById(req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(dto, OfficeDto.class));
    }

    public Mono<ServerResponse> save(ServerRequest req) {
        Mono<OfficeDto> body = req.bodyToMono(OfficeDto.class);
        Mono<OfficeDto> saved = service.save(body);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(saved, OfficeDto.class));
    }

    public Mono<ServerResponse> update(ServerRequest req) {
        Mono<OfficeDto> body = req.bodyToMono(OfficeDto.class);
        Mono<OfficeDto> updated = service
                .update(body, req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(updated, OfficeDto.class));
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
