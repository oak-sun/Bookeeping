package nam.gor.bookkeeping.wage.router;

import nam.gor.bookkeeping.wage.handler.OfficeHandler;
import nam.gor.bookkeeping.wage.handler.RegionHandler;
import nam.gor.bookkeeping.wage.handler.BonusHandler;
import nam.gor.bookkeeping.wage.handler.WageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class BookkeepingRouter {

    @Bean
    public RouterFunction<ServerResponse> regionRoute(RegionHandler handler) {
        return RouterFunctions
                .route(GET("/aloha").and(accept(APPLICATION_JSON)),
                        handler::aloha)
                .andRoute(GET("/regions").and(accept(APPLICATION_JSON)),
                        handler::findAll)
                .andRoute(GET("/regions/{id}").and(accept(APPLICATION_JSON)),
                        handler::findById)
                .andRoute(POST("/regions/new").and(accept(APPLICATION_JSON)),
                        handler::save)
                .andRoute(PUT("regions/update/{id}").and(accept(APPLICATION_JSON)),
                        handler::update)
                .andRoute(DELETE("regions/delete/{id}"),
                        handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> officeRoute(OfficeHandler handler) {
        return RouterFunctions
                .route(GET("/offices").and(accept(APPLICATION_JSON)),
                        handler::findAll)
                .andRoute(GET("/offices/{id}").and(accept(APPLICATION_JSON)),
                        handler::findById)
                .andRoute(POST("/offices/new").and(accept(APPLICATION_JSON)),
                        handler::save)
                .andRoute(PUT("offices/update/{id}").and(accept(APPLICATION_JSON)),
                        handler::update)
                .andRoute(DELETE("offices/delete/{id}"),
                        handler::delete);
    }
    @Bean
    public RouterFunction<ServerResponse> bonusRoute(BonusHandler handler) {
        return RouterFunctions
                .route(GET("/bonuses").and(accept(APPLICATION_JSON)),
                        handler::findAll)
                .andRoute(GET("/bonuses/{id}").and(accept(APPLICATION_JSON)),
                        handler::findById)
                .andRoute(POST("/bonuses/new").and(accept(APPLICATION_JSON)),
                        handler::save)
                .andRoute(PUT("bonuses/update/{id}").and(accept(APPLICATION_JSON)),
                        handler::update)
                .andRoute(DELETE("bonuses/delete/{id}"),
                        handler::delete);
    }

    @Bean
    public RouterFunction<ServerResponse> wageRoute(WageHandler handler) {
        return RouterFunctions
                .route()
                .path("/wage",
                        builder -> builder
                        .GET("/", accept(APPLICATION_JSON),
                                handler::findAll)
                        .GET("", accept(APPLICATION_JSON),
                                handler::findAll)
                        .GET("/{id}", accept(APPLICATION_JSON),
                                handler::findById)
                        .GET("/find/user", accept(APPLICATION_JSON),
                                handler::findByUser)
                        .GET("/find/user-date", accept(APPLICATION_JSON),
                                handler::findByUserDate)
                        .GET("/find/user-office", accept(APPLICATION_JSON),
                                handler::findByUserCompany)
                        .POST("/new", accept(APPLICATION_JSON),
                                handler::save)
                        .PUT("/update/{id}", accept(APPLICATION_JSON),
                                handler::update)
                        .DELETE("/delete/{id}", accept(APPLICATION_JSON),
                                handler::delete))
                .build();
    }

}
