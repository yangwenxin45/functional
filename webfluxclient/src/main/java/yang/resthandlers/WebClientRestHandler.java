package yang.resthandlers;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import yang.beans.MethodInfo;
import yang.beans.ServerInfo;
import yang.interfaces.RestHandler;

public class WebClientRestHandler implements RestHandler {

    private WebClient client;

    /**
     * 初始化webclient
     *
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
        this.client = WebClient.create(serverInfo.getUrl());
    }

    /**
     * 处理rest请求
     *
     * @param methodInfo
     * @return
     */
    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        // 返回结果
        Object result = null;

        WebClient.RequestBodySpec request = this.client
                // 请求方法
                .method(methodInfo.getMethod())
                // 请求url
                .uri(methodInfo.getUrl())
//                .uri(methodInfo.getUrl(), methodInfo.getParams())
                .accept(MediaType.APPLICATION_JSON);

        WebClient.ResponseSpec retrieve = null;
        // 判断是否带了body
        if (methodInfo.getBody() != null) {
            // 发出请求
            retrieve = request
                    .body(methodInfo.getBody(), methodInfo.getBodyElementType())
                    .retrieve();
        } else {
            retrieve = request.retrieve();
        }

        // 处理异常
        retrieve.onStatus(status -> status.value() == 404,
                response -> Mono.just(new RuntimeException("Not Found")));

        // 处理body
        if (methodInfo.isReturnFlux()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }
        return result;
    }
}
