package com.base.modules.cloud.feign.feign;

import com.base.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 动态feign接口定义
 */
public interface BaseTestClientDyn {

    @GetMapping(value = "/test/getMessage")
    Result<String> getMessage(@RequestParam("name") String name);
}
