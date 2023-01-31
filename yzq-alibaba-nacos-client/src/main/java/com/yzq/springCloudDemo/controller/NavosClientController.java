package com.yzq.springCloudDemo.controller;

import com.yzq.springCloudDemo.service.NacosClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/nacos-client")
public class NavosClientController {
    @Autowired
    private NacosClientService nacosClientService;

    /**
     * 根据serviceId 获取所有的实列信息
     * @param serviceId
     * @return
     */
    @GetMapping("/service-instance")
    public List<ServiceInstance> logNacosClintInfo(
            @RequestParam(defaultValue = "yzq-nacos-client")String serviceId){
        log.info("coming in log nacos client info: [{}]",serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);

    }

}
