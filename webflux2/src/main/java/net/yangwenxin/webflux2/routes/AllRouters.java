package net.yangwenxin.webflux2.routes;

import net.yangwenxin.webflux2.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        // 相当于类上面的 @RequestMapping("/user")
        return RouterFunctions.nest(RequestPredicates.path("/user"),
                // 下面的相当于类里面(方法上面)的 @RequestMapping
                RouterFunctions.route(RequestPredicates.GET("/"),
                        handler::getAllUser)
                        .andRoute(RequestPredicates.POST("/")
                                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
                                handler::createUser)
                        .andRoute(RequestPredicates.DELETE("/{id}"), handler::deleteUserById));
    }
}
