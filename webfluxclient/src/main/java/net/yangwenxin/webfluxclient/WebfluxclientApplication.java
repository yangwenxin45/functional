package net.yangwenxin.webfluxclient;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import yang.interfaces.ProxyCreator;
import yang.proxys.JDKProxyCreator;

@SpringBootApplication
public class WebfluxclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxclientApplication.class, args);
    }

    /**
     * 创建jdk代理工具类
     *
     * @return
     */
    @Bean
    ProxyCreator jdkProxyCreator() {
        return new JDKProxyCreator();
    }

    @Bean
    FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator) {
        return new FactoryBean<IUserApi>() {

            /**
             * 返回代理对象
             * @return
             */
            @Override
            public IUserApi getObject() throws Exception {
                return (IUserApi) proxyCreator.createProxy(this.getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }
}
