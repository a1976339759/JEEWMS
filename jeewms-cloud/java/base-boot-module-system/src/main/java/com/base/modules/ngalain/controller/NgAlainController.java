package com.base.modules.ngalain.controller;

import com.alibaba.fastjson.JSONObject;
import com.base.common.api.vo.Result;
import com.base.common.system.vo.LoginUser;
import com.base.modules.ngalain.service.NgAlainService;
import com.base.modules.system.service.ISysDictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.DictModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sys/ng-alain")
public class NgAlainController {
    @Autowired
    private NgAlainService ngAlainService;
    @Autowired
    private ISysDictService sysDictService;

    @RequestMapping(value = "/getAppData")
    @ResponseBody
    public JSONObject getAppData(HttpServletRequest request) throws Exception {
       String token=request.getHeader("X-Access-Token");
        JSONObject j = new JSONObject();
        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        JSONObject userObjcet = new JSONObject();
        userObjcet.put("name", user.getUsername());
        userObjcet.put("avatar", user.getAvatar());
        userObjcet.put("email", user.getEmail());
        userObjcet.put("token", token);
        j.put("user", userObjcet);
        j.put("menu",ngAlainService.getMenu(user.getUsername()));
        JSONObject app = new JSONObject();
        app.put("name", "base-boot-angular");
        app.put("description", "base+ng-alain整合版本");
        j.put("app", app);
        return j;
    }

    @RequestMapping(value = "/getDictItems/{dictCode}", method = RequestMethod.GET)
    public Object getDictItems(@PathVariable String dictCode) {
        log.info(" dictCode : "+ dictCode);
        Result<List<DictModel>> result = new Result<List<DictModel>>();
        List<DictModel> ls = null;
        try {
            ls = sysDictService.queryDictItemsByCode(dictCode);
            result.setSuccess(true);
            result.setResult(ls);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            result.error500("操作失败");
            return result;
        }
        List<JSONObject> dictlist=new ArrayList<>();
        for (DictModel l : ls) {
            JSONObject dict=new JSONObject();
                try {
                    dict.put("value",Integer.parseInt(l.getValue()));
                } catch (NumberFormatException e) {
                    dict.put("value",l.getValue());
                }
            dict.put("label",l.getText());
            dictlist.add(dict);
        }
        return dictlist;
    }
    @RequestMapping(value = "/getDictItemsByTable/{table}/{key}/{value}", method = RequestMethod.GET)
    public Object getDictItemsByTable(@PathVariable String table,@PathVariable String key,@PathVariable String value) {
        return this.ngAlainService.getDictByTable(table,key,value);
    }
}
