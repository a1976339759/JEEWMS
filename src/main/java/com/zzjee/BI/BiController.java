package com.zzjee.BI;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaoleilu.hutool.http.HtmlUtil;
import com.zzjee.wmutil.wmUtil;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.Highchart;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 大屏
 */

/**
 * Demo class
 *
 * @author admin
 * @date 2016/10/31
 */
@Controller
@RequestMapping("/BiController")
public class BiController extends BaseController {
    @Autowired
    private SystemService systemService;

    /**
     * @param request
     * @return
     */
    @RequestMapping(params = "homebi")
    public ModelAndView bi(HttpServletRequest request) {
        String num1 = "0";
        String num2 = "0";
        String num3 = "0";
        String num4 = "0";
        String num5 = "0";   //库存转移计划中的数量

        String ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_im_notice_i where bin_pre = 'N'";
        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_im_notice_i i ,wm_im_notice_h h where bin_pre = 'N' " +
                    " and h.notice_id = i.im_notice_id and h.cus_code = '" + cusCode +
                    "' ";
        }
        List<Map<String, Object>> ymaplist1 = systemService.findForJdbc(ysql);
        for (Map<String, Object> object : ymaplist1) {
            Map<String, Object> obj = object;

            try {
                num1 = obj.get("linecount").toString();
            } catch (Exception e) {

            }
        }
        ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_in_qm_i where bin_sta = 'N'";

        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_in_qm_i where bin_sta = 'N' and cus_code = '" + cusCode +
                    "' ";
        }
        List<Map<String, Object>> ymaplist2 = systemService.findForJdbc(ysql);
        for (Map<String, Object> object : ymaplist2) {
            Map<String, Object> obj = object;

            try {
                num2 = obj.get("linecount").toString();
            } catch (Exception e) {

            }
        }
        ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_om_qm_i where bin_sta = 'I'";
        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_om_qm_i where bin_sta = 'I' and cus_code = '" + cusCode +
                    "' ";
        }
        List<Map<String, Object>> ymaplist3 = systemService.findForJdbc(ysql);
        for (Map<String, Object> object : ymaplist3) {
            Map<String, Object> obj = object;

            try {
                num3 = obj.get("linecount").toString();
            } catch (Exception e) {

            }
        }
        ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_om_qm_i where bin_sta = 'N'";

        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_om_qm_i where bin_sta = 'N' and cus_code = '" + cusCode +
                    "' ";
        }
        List<Map<String, Object>> ymaplist4 = systemService.findForJdbc(ysql);
        for (Map<String, Object> object : ymaplist4) {
            Map<String, Object> obj = object;

            try {
                num4 = obj.get("linecount").toString();
            } catch (Exception e) {

            }
        }

        ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_to_move_goods where move_Sta = '计划中'";
        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            ysql = "select CONVERT( sum(base_goodscount),DECIMAL(10,0)) as linecount from wm_to_move_goods where move_Sta = '计划中' and cus_code = '" + cusCode +
                    "' ";
        }
        List<Map<String, Object>> ymaplist5 = systemService.findForJdbc(ysql);
        for (Map<String, Object> object : ymaplist5) {
            Map<String, Object> obj = object;

            try {
                num5 = obj.get("linecount").toString();
            } catch (Exception e) {

            }
        }

        request.setAttribute("num1", num1);
        request.setAttribute("num2", num2);
        request.setAttribute("num3", num3);
        request.setAttribute("num4", num4);
        request.setAttribute("num5", num5);

        return new ModelAndView("com/zzjee/BI/home/reportbi");
    }

    /**
     * highchart
     *
     * @return
     */
    @RequestMapping(params = "dayCount")
    @ResponseBody
    public List<Highchart> dayCountmonth(HttpServletRequest request, String reportType, HttpServletResponse response) {
        // 清除所有HTML标签，但是不删除标签内的内容
        reportType = HtmlUtil.cleanHtmlTag(reportType);

        List<Highchart> list = new ArrayList<Highchart>();
        Highchart hc = new Highchart();
        String sql = "select * from ( " +
                "SELECT  " +
                "    DATE_FORMAT(create_date, '%Y-%m-%d') as create_date, " +
                "    floor(sum(BASE_GOODSCOUNT)) as amount " +
                "FROM" +
                "    wm_to_down_goods where ORDER_ID <> 'ZY'   " +
                "group by DATE_FORMAT(create_date, '%Y-%m-%d') " +
                "order by DATE_FORMAT(create_date, '%Y-%m-%d') desc " +
                "limit 7)  temptable order by create_date  ";
        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            sql = "select * from ( " +
                    "SELECT  " +
                    "    DATE_FORMAT(create_date, '%Y-%m-%d') as create_date, " +
                    "    floor(sum(BASE_GOODSCOUNT)) as amount " +
                    "FROM" +
                    "    wm_to_down_goods where ORDER_ID <> 'ZY'  and cus_code = '" + cusCode +
                    "'  " +
                    "group by DATE_FORMAT(create_date, '%Y-%m-%d') " +
                    "order by DATE_FORMAT(create_date, '%Y-%m-%d') desc " +
                    "limit 7)  temptable order by create_date  ";
        }

        List<Map<String, Object>> maplist = systemService.findForJdbc(sql);
        List lt = new ArrayList();
        hc.setName("近七日下架数量");
        hc.setType(reportType);
        Map<String, Object> map;

        if (maplist.size() > 0) {
            for (Map<String, Object> object : maplist) {
                map = new HashMap<String, Object>(1024);
                Map<String, Object> obj = object;
                try {

                    map.put("name", obj.get("create_date").toString());
                    map.put("y", (int) Double.parseDouble(obj.get("amount").toString()));
                } catch (Exception e) {

                }
                lt.add(map);
            }
        }

        hc.setData(lt);
        list.add(hc);
        return list;
    }


    /**
     * highchart
     *
     * @param request
     * @param reportType
     * @param response
     * @return
     */
    @RequestMapping(params = "cpNameupCount")
    @ResponseBody
    public List<Highchart> studentCountmonth(HttpServletRequest request, String reportType, HttpServletResponse response) {
        List<Highchart> list = new ArrayList<Highchart>();
        Highchart hc = new Highchart();
        String sql = "select * from ( " +
                "SELECT  " +
                "    GOODS_name as goodsid, " +
                "    floor(sum(BASE_GOODSCOUNT)) as amount " +
                "FROM" +
                "    wm_to_up_goods where ORDER_ID <> 'ZY'   " +
                "group by GOODS_ID   " +
                ")  temptable  order by amount desc limit 6  ";
        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            sql = "select * from ( " +
                    "SELECT  " +
                    "    GOODS_name as goodsid, " +
                    "    floor(sum(BASE_GOODSCOUNT)) as amount " +
                    "FROM" +
                    "    wm_to_up_goods where ORDER_ID <> 'ZY'  and cus_code = '" + cusCode +
                    "' " +
                    "group by GOODS_ID   " +
                    ")  temptable  order by amount desc limit 6  ";
        }

        List<Map<String, Object>> maplist = systemService.findForJdbc(sql);

        List lt = new ArrayList();
        hc.setName("上架数量前6");
        hc.setType(reportType);
        Map<String, Object> map;

        if (maplist.size() > 0) {
            for (Map<String, Object> object : maplist) {
                map = new HashMap<String, Object>(1024);
                Map<String, Object> obj = object;
                map.put("name", obj.get("goodsid").toString());
                map.put("y", (int) Double.parseDouble(obj.get("amount").toString()));
                lt.add(map);
            }
        }

        hc.setData(lt);
        list.add(hc);
        return list;
    }

    /**
     * highchart
     *
     * @return
     */
    @RequestMapping(params = "cpNamedownCount")
    @ResponseBody
    public List<Highchart> studentCount(HttpServletRequest request, String reportType, HttpServletResponse response) {
        List<Highchart> list = new ArrayList<Highchart>();
        Highchart hc = new Highchart();
        String sql = "select * from ( " +
                "SELECT  " +
                "    GOODS_name as goodsid, " +
                "    floor(sum(BASE_GOODSCOUNT)) as amount " +
                "FROM" +
                "    wm_to_down_goods where ORDER_ID <> 'ZY'   " +
                "group by GOODS_ID   " +
                ")  temptable   order by amount desc limit 6 ";

        if (StringUtil.isNotEmpty(wmUtil.getCusCode())) {
            String cusCode = wmUtil.getCusCode();
            sql = "select * from ( " +
                    "SELECT  " +
                    "    GOODS_name as goodsid, " +
                    "    floor(sum(BASE_GOODSCOUNT)) as amount " +
                    "FROM" +
                    "    wm_to_down_goods where ORDER_ID <> 'ZY'  and cus_code = '" + cusCode +
                    "'  " +
                    "group by GOODS_ID   " +
                    ")  temptable   order by amount desc limit 6 ";

        }

        List<Map<String, Object>> maplist = systemService.findForJdbc(sql);
        List lt = new ArrayList();
        hc.setName("下架数量前6");
        hc.setType(reportType);
        Map<String, Object> map;

        if (maplist.size() > 0) {
            for (Map<String, Object> object : maplist) {
                map = new HashMap<String, Object>(1024);
                Map<String, Object> obj = object;
                map.put("name", obj.get("goodsid").toString());
                map.put("y", (int) Double.parseDouble(obj.get("amount").toString()));
                lt.add(map);
            }
        }

        hc.setData(lt);
        list.add(hc);
        return list;
    }

}
