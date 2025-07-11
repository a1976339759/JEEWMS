package com.zzjee.wm.controller;
import com.zzjee.wm.entity.BaTrayEntity;
import com.zzjee.wm.service.BaTrayServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jredis.JredisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Title: Controller
 * @Description: ba_tray
 * @author onlineGenerator
 * @date 2021-07-13 14:01:06
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/baTrayController")
public class BaTrayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaTrayController.class);

	@Autowired
	private BaTrayServiceI baTrayService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * ba_tray列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/wm/baTrayList");
	}

	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(BaTrayEntity baTray,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaTrayEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baTray, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_createDate_begin = request.getParameter("createDate_begin");
		String query_createDate_end = request.getParameter("createDate_end");
		if(StringUtil.isNotEmpty(query_createDate_begin)){
			cq.ge("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_createDate_begin));
		}
		if(StringUtil.isNotEmpty(query_createDate_end)){
			cq.le("createDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_createDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		// 调用wmOmQmIService的getDataGridReturn方法执行查询并返回结果
		this.baTrayService.getDataGridReturn(cq, true);
		// 将查询结果封装成DataGrid对象并返回给前端
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除ba_tray
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaTrayEntity baTray, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		// 根据传入的baTray对象的id获取对应的实体
		baTray = systemService.getEntity(BaTrayEntity.class, baTray.getId());
		message = "ba_tray删除成功";
		try{
			// 调用服务层方法删除实体
			baTrayService.delete(baTray);
			// 添加日志记录，记录类型为删除，级别为信息
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			// 如果发生异常，打印堆栈跟踪信息
			e.printStackTrace();
 			throw new BusinessException(e.getMessage());
		}
		// 将message设置为j对象的msg属性
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除ba_tray
	 *
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "ba_tray删除成功";
		try{
			for(String id:ids.split(",")){
				BaTrayEntity baTray = systemService.getEntity(BaTrayEntity.class,
				id
				);
				baTrayService.delete(baTray);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
 			throw new BusinessException(e.getMessage());
		}
		 // 将message设置为j对象的msg属性
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加ba_tray
	 *
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaTrayEntity baTray, HttpServletRequest request) {
		// 定义一个String类型的变量message用于存储消息内容
		String message = null;
		// 创建AjaxJson对象j，用于封装返回给前端的数据
		AjaxJson j = new AjaxJson();
		// 设置默认的消息内容为"ba_store_area添加成功"
		message = "ba_tray添加成功";
		try{
			// 调用baStoreAreaService的save方法保存传入的BaStoreAreaEntity对象到数据库
			baTrayService.save(baTray);
			// 记录操作日志，类型为插入操作，级别为INFO
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			// 打印异常堆栈信息
			e.printStackTrace();
			// 抛出业务异常，并附带异常信息
 			throw new BusinessException(e.getMessage());
		}
		// 将message设置为j对象的msg属性
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新ba_tray
	 *
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaTrayEntity baTray, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "ba_tray更新成功";
		BaTrayEntity t = baTrayService.get(BaTrayEntity.class, baTray.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baTray, t);
			baTrayService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
 			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ba_tray新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaTrayEntity baTray, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baTray.getId())) {
			baTray = baTrayService.getEntity(BaTrayEntity.class, baTray.getId());
			req.setAttribute("baTrayPage", baTray);
		}
		return new ModelAndView("com/zzjee/wm/baTray-add");
	}
	/**
	 * ba_tray编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaTrayEntity baTray, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baTray.getId())) {
			baTray = baTrayService.getEntity(BaTrayEntity.class, baTray.getId());
			req.setAttribute("baTrayPage", baTray);
		}
		return new ModelAndView("com/zzjee/wm/baTray-update");
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baTrayController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaTrayEntity baTray,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaTrayEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baTray, request.getParameterMap());
		List<BaTrayEntity> baTrays = this.baTrayService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"ba_tray");
		modelMap.put(NormalExcelConstants.CLASS,BaTrayEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("ba_tray列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baTrays);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaTrayEntity baTray,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"ba_tray");
    	modelMap.put(NormalExcelConstants.CLASS,BaTrayEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("ba_tray列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
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
				List<BaTrayEntity> listBaTrayEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaTrayEntity.class,params);
				for (BaTrayEntity baTray : listBaTrayEntitys) {
					baTrayService.save(baTray);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
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
	public List<BaTrayEntity> list() {
		List<BaTrayEntity> listBaTrays=baTrayService.getList(BaTrayEntity.class);
		return listBaTrays;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BaTrayEntity task = baTrayService.get(BaTrayEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BaTrayEntity baTray, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaTrayEntity>> failures = validator.validate(baTray);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}
		//保存
		try{
			baTrayService.save(baTray);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = baTray.getId();
		URI uri = uriBuilder.path("/rest/baTrayController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BaTrayEntity baTray) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaTrayEntity>> failures = validator.validate(baTray);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}
		//保存
		try{
			baTrayService.saveOrUpdate(baTray);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		baTrayService.deleteEntityById(BaTrayEntity.class, id);
	}
}
