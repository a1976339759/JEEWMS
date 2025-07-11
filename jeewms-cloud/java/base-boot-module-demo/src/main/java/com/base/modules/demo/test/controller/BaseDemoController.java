package com.base.modules.demo.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.api.vo.Result;
import com.base.common.aspect.annotation.AutoLog;
import com.base.common.aspect.annotation.PermissionData;
import com.base.common.system.base.controller.BaseController;
import com.base.common.util.DateUtils;
import com.base.common.util.RedisUtil;
import com.base.modules.demo.test.entity.BaseDemo;
import com.base.modules.demo.test.service.IBaseDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 单表示例
 * @Author: base-boot
 * @Date:2018-12-29
 * @Version:V2.0
 */
@Slf4j
@Api(tags = "单表DEMO")
@RestController
@RequestMapping("/test/baseDemo")
public class BaseDemoController extends BaseController<BaseDemo, IBaseDemoService> {
    @Autowired
    private IBaseDemoService baseDemoService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页列表查询
     *
     * @param baseDemo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @ApiOperation(value = "获取Demo数据列表", notes = "获取所有Demo数据列表")
    @GetMapping(value = "/list")
    @PermissionData(pageComponent = "base/BaseDemoList")
    public Result<?> list(BaseDemo baseDemo, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                          HttpServletRequest req) {
        QueryWrapper<BaseDemo> queryWrapper = QueryGenerator.initQueryWrapper(baseDemo, req.getParameterMap());
        Page<BaseDemo> page = new Page<BaseDemo>(pageNo, pageSize);

        IPage<BaseDemo> pageList = baseDemoService.page(page, queryWrapper);
        log.info("查询当前页：" + pageList.getCurrent());
        log.info("查询当前页数量：" + pageList.getSize());
        log.info("查询结果数量：" + pageList.getRecords().size());
        log.info("数据总数：" + pageList.getTotal());
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param baseDemo
     * @return
     */
    @PostMapping(value = "/add")
    @AutoLog(value = "添加测试DEMO")
    @ApiOperation(value = "添加DEMO", notes = "添加DEMO")
    public Result<?> add(@RequestBody BaseDemo baseDemo) {
        baseDemoService.save(baseDemo);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param baseDemo
     * @return
     */
    @PutMapping(value = "/edit")
    @ApiOperation(value = "编辑DEMO", notes = "编辑DEMO")
    @AutoLog(value = "编辑DEMO", operateType = CommonConstant.OPERATE_TYPE_3)
    public Result<?> edit(@RequestBody BaseDemo baseDemo) {
        baseDemoService.updateById(baseDemo);
        return Result.OK("更新成功！");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "删除测试DEMO")
    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "通过ID删除DEMO", notes = "通过ID删除DEMO")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        baseDemoService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "批量删除DEMO", notes = "批量删除DEMO")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.baseDemoService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/queryById")
    @ApiOperation(value = "通过ID查询DEMO", notes = "通过ID查询DEMO")
    public Result<?> queryById(@ApiParam(name = "id", value = "示例id", required = true) @RequestParam(name = "id", required = true) String id) {
        BaseDemo baseDemo = baseDemoService.getById(id);
        return Result.OK(baseDemo);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    @RequestMapping(value = "/exportXls")
    @PermissionData(pageComponent = "base/BaseDemoList")
    public ModelAndView exportXls(HttpServletRequest request, BaseDemo baseDemo) {
        //获取导出表格字段
        String exportFields = baseDemoService.getExportFields();
        //分sheet导出表格字段
        return super.exportXlsSheet(request, baseDemo, BaseDemo.class, "单表模型",exportFields,500);
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BaseDemo.class);
    }

    // =====Redis 示例===============================================================================================

    /**
     * redis操作 -- set
     */
    @GetMapping(value = "/redisSet")
    public void redisSet() {
        redisUtil.set("name", "张三" + DateUtils.now());
    }

    /**
     * redis操作 -- get
     */
    @GetMapping(value = "/redisGet")
    public String redisGet() {
        return (String) redisUtil.get("name");
    }

    /**
     * redis操作 -- setObj
     */
    @GetMapping(value = "/redisSetObj")
    public void redisSetObj() {
        BaseDemo p = new BaseDemo();
        p.setAge(10);
        p.setBirthday(new Date());
        p.setContent("hello");
        p.setName("张三");
        p.setSex("男");
        redisUtil.set("user-zdh", p);
    }

    /**
     * redis操作 -- setObj
     */
    @GetMapping(value = "/redisGetObj")
    public Object redisGetObj() {
        return redisUtil.get("user-zdh");
    }

    /**
     * redis操作 -- get
     */
    @GetMapping(value = "/redis/{id}")
    public BaseDemo redisGetBaseDemo(@PathVariable("id") String id) {
        BaseDemo t = baseDemoService.getByIdCacheable(id);
        log.info(t.toString());
        return t;
    }

    // ===Freemaker示例================================================================================

    /**
     * freemaker方式 【页面路径： src/main/resources/templates】
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/html")
    public ModelAndView ftl(ModelAndView modelAndView) {
        modelAndView.setViewName("demo3");
        List<String> userList = new ArrayList<String>();
        userList.add("admin");
        userList.add("user1");
        userList.add("user2");
        log.info("--------------test--------------");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }


    // ==========================================动态表单 JSON接收测试===========================================
    @PostMapping(value = "/testOnlineAdd")
    public Result<?> testOnlineAdd(@RequestBody JSONObject json) {
        log.info(json.toJSONString());
        return Result.OK("添加成功！");
    }

    /*----------------------------------------外部获取权限示例------------------------------------*/

    /**
     * 【数据权限示例 - 编程】mybatisPlus java类方式加载权限
     *
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/mpList")
    @PermissionData(pageComponent = "base/BaseDemoList")
    public Result<?> loadMpPermissonList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                         HttpServletRequest req) {
        QueryWrapper<BaseDemo> queryWrapper = new QueryWrapper<BaseDemo>();
        //编程方式，给queryWrapper装载数据权限规则
        QueryGenerator.installAuthMplus(queryWrapper, BaseDemo.class);
        Page<BaseDemo> page = new Page<BaseDemo>(pageNo, pageSize);
        IPage<BaseDemo> pageList = baseDemoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 【数据权限示例 - 编程】mybatis xml方式加载权限
     *
     * @param baseDemo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @GetMapping(value = "/sqlList")
    @PermissionData(pageComponent = "base/BaseDemoList")
    public Result<?> loadSqlPermissonList(BaseDemo baseDemo, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                          HttpServletRequest req) {
        IPage<BaseDemo> pageList = baseDemoService.queryListWithPermission(pageSize, pageNo);
        return Result.OK(pageList);
    }
    /*----------------------------------------外部获取权限示例------------------------------------*/
}
