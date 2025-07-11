package com.zzjee.wave.controller;


import com.zzjee.api.ResultDO;
import com.zzjee.md.entity.MdCusEntity;
import com.zzjee.md.entity.MdCusOtherEntity;
import com.zzjee.md.entity.MvGoodsEntity;
import com.zzjee.wave.entity.WaveToDownEntity;
import com.zzjee.wave.service.WaveToDownServiceI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzjee.wm.entity.WmOmNoticeHEntity;
import com.zzjee.wm.entity.WmOmNoticeIEntity;
import com.zzjee.wm.entity.WmOmQmIEntity;
import com.zzjee.wm.entity.WmToDownGoodsEntity;
import org.apache.log4j.Logger;

import org.jeecgframework.core.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import static com.xiaoleilu.hutool.date.DateTime.now;

@Controller
@RequestMapping("/waveToDownController")
public class WaveToDownController extends BaseController {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(WaveToDownController.class);

    @Autowired
    private WaveToDownServiceI waveToDownService;
    @Autowired
    private SystemService systemService;

    /**
     * wave_to_down列表 页面跳转
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wave/waveToDownList");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param request
     */
    @RequestMapping(params = "doPrintpage")
    public ModelAndView doPrint(String waveid, HttpServletRequest request) {
        // 定义一个HQL查询语句，用于从WmOmQmIEntity表中查询waveId等于传入的waveid的记录
        String hql = "from WmOmQmIEntity where waveId = ? ";
        List<WmOmQmIEntity> wavelist = systemService.findHql(hql, waveid);
        // 将当前日期转换为字符串格式，并设置到request的属性"kprq"中
        request.setAttribute("kprq", DateUtils.date2Str(DateUtils.date_sdf));
        // 从配置文件中获取公司名称，并设置到request的属性"comname"中
        request.setAttribute("comname", ResourceUtil.getConfigByName("comname"));
        // 将传入的waveid设置到request的属性"waveid"中
        request.setAttribute("waveid", waveid);
        // 定义另一个HQL查询语句，用于从WaveToDownEntity表中查询waveId等于传入的waveid的记录
        String hqlwave = "from WaveToDownEntity where waveId = ?";
        List<WaveToDownEntity> wmOmQmIEntityList = systemService.findHql(hqlwave, waveid);
        // 将查询到的WaveToDownEntity列表设置到request的属性"wmOmQmIList"中
        request.setAttribute("wmOmQmIList", wmOmQmIEntityList);
        return new ModelAndView("com/zzjee/wm/print/wavejianhuo-print");
    }

    /**
     * easyui AJAX请求数据
     *
     * @param response
     * @param request
     */
    @RequestMapping(params = "datagrid")
    public void datagrid(WaveToDownEntity waveToDown, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WaveToDownEntity.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, waveToDown, request.getParameterMap());
        cq.add();
        // 调用waveToDownService的getDataGridReturn方法，传入cq参数和true作为第二个参数
        this.waveToDownService.getDataGridReturn(cq, true);
        // 使用TagUtil工具类的datagrid方法，将response和dataGrid作为参数传递
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * 删除wave_to_down
     *
     * @return
     */
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WaveToDownEntity waveToDown, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        // 根据ID获取WaveToDownEntity实体
        waveToDown = systemService.getEntity(WaveToDownEntity.class, waveToDown.getId());
        message = "wave_to_down删除成功";
        try {
            // 调用waveToDownService的delete方法删除实体
            waveToDownService.delete(waveToDown);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
              throw new BusinessException(e.getMessage());
        }
        // 将message设置为j对象的msg属性
        j.setMsg(message);
        return j;
    }

    /**
     * 批量删除wave_to_down
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "wave_to_down删除成功";
        try {
            for (String id : ids.split(",")) {
                WaveToDownEntity waveToDown = systemService.getEntity(WaveToDownEntity.class,
                        id
                );
                waveToDownService.delete(waveToDown);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
             throw new BusinessException(e.getMessage());
        }
        // 将message设置为j对象的msg属性
        j.setMsg(message);
        return j;
    }

    /**
     * 添加wave_to_down
     *
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WaveToDownEntity waveToDown, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "wave_to_down添加成功";
        try {
            // 调用waveToFjService的save方法保存waveToFj对象
            waveToDownService.save(waveToDown);
            // 调用systemService的addLog方法记录日志，传入message、Globals.Log_Type_INSERT和Globals.Log_Leavel_INFO作为参数
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
             throw new BusinessException(e.getMessage());
        }
        // 将message设置为j对象的msg属性
        j.setMsg(message);
        return j;
    }

    /**
     * 更新wave_to_down
     *
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WaveToDownEntity waveToDown, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "wave_to_down更新成功";
        WaveToDownEntity t = waveToDownService.get(WaveToDownEntity.class, waveToDown.getId());
        try {
            // 将waveToFj对象的属性复制到t对象中，只复制非空属性
            MyBeanUtils.copyBeanNotNull2Bean(waveToDown, t);
            // 保存或更新t对象
            waveToDownService.saveOrUpdate(t);
            // 记录日志，类型为更新，级别为信息
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
            // 抛出一个BusinessException异常，传入异常信息
             throw new BusinessException(e.getMessage());
        }
        // 将message设置为j对象的msg属性
        j.setMsg(message);
        return j;
    }

    /**
     * wave_to_down新增页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(WaveToDownEntity waveToDown, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(waveToDown.getId())) {
            waveToDown = waveToDownService.getEntity(WaveToDownEntity.class, waveToDown.getId());
            req.setAttribute("waveToDownPage", waveToDown);
        }
        return new ModelAndView("com/zzjee/wave/waveToDown-add");
    }

    /**
     * wave_to_down编辑页面跳转
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WaveToDownEntity waveToDown, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(waveToDown.getId())) {
            waveToDown = waveToDownService.getEntity(WaveToDownEntity.class, waveToDown.getId());
            req.setAttribute("waveToDownPage", waveToDown);
        }
        return new ModelAndView("com/zzjee/wave/waveToDown-update");
    }

    /**
     * 导入功能跳转
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "waveToDownController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public String exportXls(WaveToDownEntity waveToDown, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(WaveToDownEntity.class, dataGrid);
        // 根据请求参数和实体类属性生成HQL查询语句
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, waveToDown, request.getParameterMap());
        // 根据查询条件获取WaveToFjEntity对象列表
        List<WaveToDownEntity> waveToDowns = this.waveToDownService.getListByCriteriaQuery(cq, false);
        modelMap.put(NormalExcelConstants.FILE_NAME, "wave_to_down");
        modelMap.put(NormalExcelConstants.CLASS, WaveToDownEntity.class);
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("wave_to_down列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                "导出信息"));
        // 将查询结果放入modelMap中
        modelMap.put(NormalExcelConstants.DATA_LIST, waveToDowns);
        // 返回视图名称，用于跳转到导出Excel的页面
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 导出excel 使模板
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(WaveToDownEntity waveToDown, HttpServletRequest request, HttpServletResponse response
            , DataGrid dataGrid, ModelMap modelMap) {
        // 设置 Excel 文件名
        modelMap.put(NormalExcelConstants.FILE_NAME, "wave_to_down");
        // 指定导出的数据实体类类型
        modelMap.put(NormalExcelConstants.CLASS, WaveToDownEntity.class);
        // 设置导出参数，包括标题、作者和描述
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("wave_to_down列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(),
                "导出信息"));
        // 设置数据列表为空列表，此处应根据实际需求填充数据
        modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
        // 返回视图名称，用于展示或下载 Excel 文件
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<WaveToDownEntity> listWaveToDownEntitys = ExcelImportUtil.importExcel(file.getInputStream(), WaveToDownEntity.class, params);
                for (WaveToDownEntity waveToDown : listWaveToDownEntitys) {
                    waveToDownService.save(waveToDown);
                }
                j.setMsg("文件导入成功！");
            } catch (Exception e) {
                j.setMsg("文件导入失败！");
                logger.error(ExceptionUtil.getExceptionMessage(e));
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return j;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<WaveToDownEntity> list() {
        List<WaveToDownEntity> listWaveToDowns = waveToDownService.getList(WaveToDownEntity.class);
        return listWaveToDowns;
    }

    @RequestMapping(value = "/list/todown", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list(@RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "searchstr", required = false) String searchstr,
                                  @RequestParam(value = "searchstr2", required = false) String searchstr2,
                                  @RequestParam(value = "searchstr3", required = false) String searchstr3) {
        ResultDO D0 = new ResultDO();
        D0.setOK(true);

        String hql = "from WaveToDownEntity where waveId = ?  ";

        List<WaveToDownEntity> listWaveToDowns = new ArrayList<>();
        List<WaveToDownEntity> listWaveToDownsnew = new ArrayList<>();
        if (StringUtil.isEmpty(searchstr) && StringUtil.isEmpty(searchstr2)) {
            hql = "from WaveToDownEntity    ";
            listWaveToDowns = waveToDownService.findHql(hql);
        }
        if (StringUtil.isNotEmpty(searchstr) && StringUtil.isEmpty(searchstr2)) {
            hql = "from WaveToDownEntity where waveId = ?  ";
            listWaveToDowns = waveToDownService.findHql(hql, searchstr);
        }
        if (StringUtil.isEmpty(searchstr) && StringUtil.isNotEmpty(searchstr2)) {
            hql = "from WaveToDownEntity where binId = ?  ";
            listWaveToDowns = waveToDownService.findHql(hql, searchstr2);
        }
        if (StringUtil.isNotEmpty(searchstr) && StringUtil.isNotEmpty(searchstr2)) {
            hql = "from WaveToDownEntity where waveId = ? and  binId = ?";
            listWaveToDowns = waveToDownService.findHql(hql, searchstr, searchstr2);
        }
        System.out.println("11111searchstr3=" + searchstr3);

        if (StringUtil.isNotEmpty(searchstr3)) {
            for (WaveToDownEntity t : listWaveToDowns) {
                System.out.println("t.getGoodsId()=" + t.getGoodsId());
                System.out.println("searchstr3=" + searchstr3);
                try {
                    if (StringUtil.strPos(t.getGoodsId(), searchstr3) || StringUtil.strPos(t.getShpTiaoMa(), searchstr3)) {
                        listWaveToDownsnew.add(t);

                    }
                } catch (Exception e) {

                }

            }
            D0.setObj(listWaveToDownsnew);

        } else {
            D0.setObj(listWaveToDowns);

        }

        return new ResponseEntity(D0, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create(@RequestParam String waveToDownstr, UriComponentsBuilder uriBuilder) {
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.

        ResultDO D0 = new ResultDO();

        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        WaveToDownEntity waveToDown = (WaveToDownEntity) JSONHelper.json2Object(waveToDownstr, WaveToDownEntity.class);

        //保存
        try {
            String hql = "from WmOmQmIEntity where waveId = ? and  goodsId = ? and proData = ? and tinId = ? and binId =  ? and binSta = ?";
            List<WmOmQmIEntity> listwavedown = systemService.findHql(hql, waveToDown.getWaveId(), waveToDown.getGoodsId(), waveToDown.getProData(), waveToDown.getTinId(), waveToDown.getBinId(), "N");

            for (WmOmQmIEntity wmOmQmI : listwavedown) {
                WmToDownGoodsEntity wmToDownGoods = new WmToDownGoodsEntity();
                wmToDownGoods.setBinIdFrom(wmOmQmI.getTinId());//下架托盘
                wmToDownGoods.setKuWeiBianMa(wmOmQmI.getBinId());//储位
                wmToDownGoods.setBinIdTo(wmOmQmI.getOmNoticeId());//到托盘
                wmToDownGoods.setCusCode(wmOmQmI.getCusCode());//货主
                wmToDownGoods.setGoodsId(wmOmQmI.getGoodsId());//
                wmToDownGoods.setGoodsProData(wmOmQmI.getProData());//生产日期
                wmToDownGoods.setOrderId(wmOmQmI.getOmNoticeId());//出货通知单
                wmToDownGoods.setOrderIdI(wmOmQmI.getId());//出货通知项目
                wmToDownGoods.setBaseUnit(wmOmQmI.getBaseUnit());//基本单位
                wmToDownGoods.setBaseGoodscount(wmOmQmI.getBaseGoodscount());//基本单位数量
                wmToDownGoods.setGoodsUnit(wmOmQmI.getGoodsUnit());//出货单位
                wmToDownGoods.setGoodsQua(wmOmQmI.getQmOkQuat());//出货数量
                wmToDownGoods.setGoodsQuaok(wmOmQmI.getQmOkQuat());//出货数量
                wmToDownGoods.setGoodsName(wmOmQmI.getGoodsName());//商品名称
                wmToDownGoods.setOmBeizhu(wmOmQmI.getOmBeizhu());//备注
                wmToDownGoods.setImCusCode(wmOmQmI.getImCusCode());//客户单号
                wmToDownGoods.setOrderType("99");//默认为01
                wmToDownGoods.setCreateBy(waveToDown.getCreateBy());
                wmToDownGoods.setCreateDate(now());
                systemService.save(wmToDownGoods);
                wmOmQmI.setBinSta("H");
                wmOmQmI.setUpdateBy(waveToDown.getCreateBy());
                wmOmQmI.setFirstRq(waveToDown.getFirstRq());
                systemService.saveOrUpdate(wmOmQmI);
            }

        } catch (Exception e) {
            e.printStackTrace();
            D0.setOK(false);
            return new ResponseEntity(D0, HttpStatus.OK);
        }
        D0.setOK(true);
        return new ResponseEntity(D0, HttpStatus.OK);
    }


    @RequestMapping(value = "/jsondown", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody WaveToDownEntity waveToDown) {
        ResultDO D0 = new ResultDO();
        //调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
        //保存
        try {
            String hql = "from WmOmQmIEntity where waveId = ? and  goodsId = ? and proData = ? and tinId = ? and binId =  ? and binSta = ?";
            List<WmOmQmIEntity> listwavedown = systemService.findHql(hql, waveToDown.getWaveId(), waveToDown.getGoodsId(), waveToDown.getProData(), waveToDown.getTinId(), waveToDown.getBinId(), "N");

            for (WmOmQmIEntity wmOmQmI : listwavedown) {
                WmToDownGoodsEntity wmToDownGoods = new WmToDownGoodsEntity();
                wmToDownGoods.setBinIdFrom(wmOmQmI.getTinId());//下架托盘
                wmToDownGoods.setKuWeiBianMa(wmOmQmI.getBinId());//储位
                wmToDownGoods.setBinIdTo(wmOmQmI.getOmNoticeId());//到托盘
                wmToDownGoods.setCusCode(wmOmQmI.getCusCode());//货主
                wmToDownGoods.setGoodsId(wmOmQmI.getGoodsId());//
                wmToDownGoods.setGoodsProData(wmOmQmI.getProData());//生产日期
                wmToDownGoods.setOrderId(wmOmQmI.getOmNoticeId());//出货通知单
                wmToDownGoods.setOrderIdI(wmOmQmI.getId());//出货通知项目
                wmToDownGoods.setBaseUnit(wmOmQmI.getBaseUnit());//基本单位
                wmToDownGoods.setBaseGoodscount(wmOmQmI.getBaseGoodscount());//基本单位数量
                wmToDownGoods.setGoodsUnit(wmOmQmI.getGoodsUnit());//出货单位
                wmToDownGoods.setGoodsQua(wmOmQmI.getQmOkQuat());//出货数量
                wmToDownGoods.setGoodsQuaok(wmOmQmI.getQmOkQuat());//出货数量
                wmToDownGoods.setGoodsName(wmOmQmI.getGoodsName());//商品名称
                wmToDownGoods.setOmBeizhu(wmOmQmI.getOmBeizhu());//备注
                wmToDownGoods.setImCusCode(wmOmQmI.getImCusCode());//客户单号
                wmToDownGoods.setOrderType("99");//默认为01
                wmToDownGoods.setCreateDate(now());
                systemService.save(wmToDownGoods);
                wmOmQmI.setBinSta("H");
                wmOmQmI.setFirstRq(waveToDown.getFirstRq());
                systemService.saveOrUpdate(wmOmQmI);
            }
            D0.setOK(true);

        } catch (Exception e) {
            e.printStackTrace();
            D0.setOK(false);
            return new ResponseEntity(D0, HttpStatus.OK);
        }
        return new ResponseEntity(D0, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        waveToDownService.deleteEntityById(WaveToDownEntity.class, id);
    }
}
