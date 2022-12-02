package netty.annotation;

import netty.client.ClientBoot;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * netty 客户端启动开关
 * @author gyl
 * @date 2022/5/11 - 16:25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ClientBoot.class)
public @interface EnableNettyClient {
}
