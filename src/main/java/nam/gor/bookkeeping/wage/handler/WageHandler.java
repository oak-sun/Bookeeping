package nam.gor.bookkeeping.wage.handler;

import nam.gor.bookkeeping.wage.model.dto.WageDto;
import nam.gor.bookkeeping.wage.service.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WageHandler {
    private final WageService service;

    @Autowired
    public WageHandler(WageService service) {
        this.service = service;
    }

    public Mono<ServerResponse> findAll(ServerRequest req) {
        Flux<WageDto> wages = service.findAll();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(wages, WageDto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest req) {
        Mono<WageDto> findByIdMono = service
                .findById(req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(findByIdMono, WageDto.class);
    }

    public Mono<ServerResponse> findByUser(ServerRequest req) {

        Flux<WageDto> findByUserFlux = service
                .findByUser(req.queryParam("userId")
                .orElse(""));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(findByUserFlux, WageDto.class);
    }

    public Mono<ServerResponse> findByUserDate(ServerRequest req) {
        var formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm");
        Flux<WageDto> searchResult = service.findByUserAndDate(
                req
                        .queryParam("userId")
                        .orElse(""),
                LocalDateTime.parse(req
                                   .queryParam("from")
                                   .orElse(LocalDateTime.now().toString()),
                                 formatter),
                LocalDateTime.parse(req
                                    .queryParam("to")
                                    .orElse(LocalDateTime.now().toString()),
                                    formatter));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(searchResult, WageDto.class);
    }

    public Mono<ServerResponse> findByUserCompany(ServerRequest req) {
        Flux<WageDto> result = service.findByUserAndCompany(
                req.queryParam("userId").orElse(""),
                req.queryParam("officeId").orElse(""));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, WageDto.class);
    }

    public Mono<ServerResponse> save(ServerRequest req) {
        Mono<WageDto> body = req.bodyToMono(WageDto.class);
        Mono<WageDto> dto = service.save(body);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto, WageDto.class);
    }

    public Mono<ServerResponse> update(ServerRequest req) {
        Mono<WageDto> body = req.bodyToMono(WageDto.class);
        Mono<WageDto> dto = service
                .update(body, req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto, WageDto.class);
    }

    public Mono<ServerResponse> delete(ServerRequest req) {
        Mono<Void> mono = service.delete(req.pathVariable("id"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, Void.class);
    }

    public Mono<ServerResponse> createTestWage(ServerRequest req) {
        Mono<WageDto> fake = Mono.just(new WageDto(
                "testUserId",
                LocalDateTime.of(
                        2023,
                        1,
                        1,
                        7,
                        45),
                LocalDateTime.of(
                        2023,
                        1,
                        1,
                        19,
                        45),
                12,
                16.5,
                "6398ebec79d5714c70b70df9"));
        Mono<WageDto> saved = service.save(fake);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(saved, WageDto.class);
    }
}
