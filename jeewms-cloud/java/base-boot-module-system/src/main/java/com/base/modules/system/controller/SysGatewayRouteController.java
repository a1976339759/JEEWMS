package com.base.modules.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import com.base.common.api.vo.Result;
import com.base.common.system.base.controller.BaseController;
import com.base.common.util.oConvertUtils;
import com.base.modules.system.entity.SysGatewayRoute;
import com.base.modules.system.service.ISysGatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: gateway路由管理
 * @Author: base-boot
 * @Date: 2020-05-26
 * @Version: V1.0
 */
@Api(tags = "gateway路由管理")
@RestController
@RequestMapping("/sys/gatewayRoute")
@Slf4j
public class SysGatewayRouteController extends BaseController<SysGatewayRoute, ISysGatewayRouteService> {

	@Autowired
	private ISysGatewayRouteService sysGatewayRouteService;

    @PostMapping(value = "/updateAll")
    public Result<?> updateAll(@RequestBody JSONObject json) {
        sysGatewayRouteService.updateAll(json);
        return Result.ok("操作成功");
    }

	@GetMapping(value = "/list")
	public Result<?> queryPageList(SysGatewayRoute sysGatewayRoute) {
		LambdaQueryWrapper<SysGatewayRoute> query = new LambdaQueryWrapper<>();
		List<SysGatewayRoute> ls = sysGatewayRouteService.list(query);
		JSONArray array = new JSONArray();
		for(SysGatewayRoute rt: ls){
			JSONObject obj = (JSONObject) JSONObject.toJSON(rt);
			if(oConvertUtils.isNotEmpty(rt.getPredicates())){
				obj.put("predicates", JSONArray.parseArray(rt.getPredicates()));
			}
			if(oConvertUtils.isNotEmpty(rt.getFilters())){
				obj.put("filters", JSONArray.parseArray(rt.getFilters()));
			}
			array.add(obj);
		}
		return Result.ok(array);
	}

	@GetMapping(value = "/clearRedis")
	public Result<?> clearRedis() {
		sysGatewayRouteService.clearRedis();
		return Result.ok("清除成功！");
	}

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    //@RequiresRoles({"admin"})
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        sysGatewayRouteService.deleteById(id);
        return Result.ok("删除路由成功");
    }

}
