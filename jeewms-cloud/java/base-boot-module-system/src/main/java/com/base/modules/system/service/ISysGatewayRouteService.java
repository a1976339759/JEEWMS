package com.base.modules.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.base.modules.system.entity.SysGatewayRoute;

/**
 * @Description: gateway路由管理
 * @Author: base-boot
 * @Date:   2020-05-26
 * @Version: V1.0
 */
public interface ISysGatewayRouteService extends IService<SysGatewayRoute> {

    /**
     * 添加所有的路由信息到redis
     * @param key
     */
     void addRoute2Redis(String key);

    /**
     * 删除路由
     * @param id
     */
     void deleteById(String id);

    /**
     * 保存路由配置
     * @param array
     */
    void updateAll(JSONObject array);

    /**
     * 清空redis中的route信息
     */
    void clearRedis();

}
