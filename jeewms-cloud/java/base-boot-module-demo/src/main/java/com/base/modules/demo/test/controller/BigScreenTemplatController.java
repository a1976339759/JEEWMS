package com.base.modules.demo.test.controller;

import com.base.modules.demo.test.entity.BaseDemo;
import com.base.modules.demo.test.service.IBaseDemoService;
import lombok.extern.slf4j.Slf4j;
import com.base.common.system.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 大屏预览入口
 * @Author: scott
 * @Date:2019-12-12
 * @Version:V1.0
 */
@Slf4j
@Controller
@RequestMapping("/test/bigScreen/templat")
public class BigScreenTemplatController extends BaseController<BaseDemo, IBaseDemoService> {

    /**
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

    /**
     * 生产销售监控模版
     * @param modelAndView
     * @return
     */
    @RequestMapping("/index1")
    public ModelAndView index1(ModelAndView modelAndView) {
        modelAndView.setViewName("/bigscreen/template1/index");
        return modelAndView;
    }

    /**
     * 智慧物流监控模版
     * @param modelAndView
     * @return
     */
    @RequestMapping("/index2")
    public ModelAndView index2(ModelAndView modelAndView) {
        modelAndView.setViewName("/bigscreen/template2/index");
        return modelAndView;
    }

}
