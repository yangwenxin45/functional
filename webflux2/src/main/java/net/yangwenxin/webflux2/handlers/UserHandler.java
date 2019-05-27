package net.yangwenxin.webflux2.handlers;

import net.yangwenxin.webflux2.domain.User;
import net.yangwenxin.webflux2.repository.UserRepository;
import net.yangwenxin.webflux2.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final UserRepository repository;

    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * 得到所有用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.repository.findAll(), User.class);
    }

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
//        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
//                .body(this.repository.saveAll(user), User.class);
        // 校验代码需要放在这里
        return user.flatMap(u -> {
            // 校验代码需要放在这里
            CheckUtil.checkName(u.getName());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(this.repository.save(u), User.class);
        });
    }

    /**
     * 根据id删除用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.repository.findById(id)
                .flatMap(user -> this.repository.delete(user)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
