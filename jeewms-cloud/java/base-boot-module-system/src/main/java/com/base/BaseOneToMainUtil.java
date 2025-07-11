package com.base;

import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.codegenerate.generate.impl.CodeGenerateOneToMany;
import org.jeecgframework.codegenerate.generate.pojo.onetomany.MainTableVo;
import org.jeecgframework.codegenerate.generate.pojo.onetomany.SubTableVo;

/**
 * 代码生成器入口【一对多】
 * 【 GUI模式已弃用，请转移Online模式进行代码生成 】
 * @Author 张代浩
 * @site www.base.org
 * 
 */
public class BaseOneToMainUtil {

	/**
	 * 一对多(父子表)数据模型，生成方法
	 * @param args
	 */
	public static void main(String[] args) {
		//第一步：设置主表配置
		MainTableVo mainTable = new MainTableVo();
		mainTable.setTableName("base_order_main");//表名
		mainTable.setEntityName("GuiTestOrderMain");	 //实体名
		mainTable.setEntityPackage("gui");	 //包名
		mainTable.setFtlDescription("GUI订单管理");	 //描述
		
		//第二步：设置子表集合配置
		List<SubTableVo> subTables = new ArrayList<SubTableVo>();
		//[1].子表一
		SubTableVo po = new SubTableVo();
		po.setTableName("base_order_customer");//表名
		po.setEntityName("GuiTestOrderCustom");	    //实体名
		po.setEntityPackage("gui");	        //包名
		po.setFtlDescription("客户明细");       //描述
		//子表外键参数配置
		/*说明: 
		 * a) 子表引用主表主键ID作为外键，外键字段必须以_ID结尾;
		 * b) 主表和子表的外键字段名字，必须相同（除主键ID外）;
		 * c) 多个外键字段，采用逗号分隔;
		*/
		po.setForeignKeys(new String[]{"order_id"});
		subTables.add(po);
		//[2].子表二
		SubTableVo po2 = new SubTableVo();
		po2.setTableName("base_order_ticket");		//表名
		po2.setEntityName("GuiTestOrderTicket");			//实体名
		po2.setEntityPackage("gui"); 				//包名
		po2.setFtlDescription("产品明细");			//描述
		//子表外键参数配置
		/*说明: 
		 * a) 子表引用主表主键ID作为外键，外键字段必须以_ID结尾;
		 * b) 主表和子表的外键字段名字，必须相同（除主键ID外）;
		 * c) 多个外键字段，采用逗号分隔;
		*/
		po2.setForeignKeys(new String[]{"order_id"});
		subTables.add(po2);
		mainTable.setSubTables(subTables);
		
		//第三步：一对多(父子表)数据模型,代码生成
		try {
			new CodeGenerateOneToMany(mainTable,subTables).generateCodeFile(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
