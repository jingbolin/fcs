package netty;


import lombok.extern.slf4j.Slf4j;
import netty.annotation.EnableNetty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author gyl
 * @date 2022/5/7 - 16:31
 */
@SpringBootApplication
@Slf4j
@EnableNetty
@EnableWebSocket
public class NettyApplication {
    public static void main(String[] args){
        SpringApplication.run(NettyApplication.class, args);
    }
}
