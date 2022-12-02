package netty.controller;

import netty.bean.AjaxResult;
import netty.bean.MessageBean;
import netty.client.ClientBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * netty web层controller
 * @author gyl
 * @date 2022/5/11 - 17:14
 */
@RestController
@RequestMapping("netty")
public class NettyController {
    @Autowired
    ClientBoot clientBoot;
    @GetMapping("sendMsg")
    public AjaxResult sendMsg(String msg) throws InterruptedException {
        clientBoot.sendMsg(new MessageBean(msg));
        return AjaxResult.success();
    }

}
