package org.jeecg.common.system.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 多数据源管理
 * @Author: base-boot
 * @Date: 2019-12-25
 * @Version: V1.0
 */
@Data
@TableName("sys_data_source")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "sys_data_source对象", description = "多数据源管理")
public class SysDataSource {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_UUID )
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 数据源编码
     */
    @Excel(name = "数据源编码", width = 15)
    @ApiModelProperty(value = "数据源编码")
    private String code;
    /**
     * 数据源名称
     */
    @Excel(name = "数据源名称", width = 15)
    @ApiModelProperty(value = "数据源名称")
    private String name;
    /**
     * 描述
     */
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 数据库类型
     */
    @Excel(name = "数据库类型", width = 15, dicCode = "database_type")
    @ApiModelProperty(value = "数据库类型")
    private String dbType;
    /**
     * 驱动类
     */
    @Excel(name = "驱动类", width = 15)
    @ApiModelProperty(value = "驱动类")
    private String dbDriver;
    /**
     * 数据源地址
     */
    @Excel(name = "数据源地址", width = 15)
    @ApiModelProperty(value = "数据源地址")
    private String dbUrl;
    /**
     * 数据库名称
     */
    @Excel(name = "数据库名称", width = 15)
    @ApiModelProperty(value = "数据库名称")
    private String dbName;
    /**
     * 用户名
     */
    @Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private String dbUsername;
    /**
     * 密码
     */
    @Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
    private String dbPassword;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**
     * 所属部门
     */
    @Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
}
