package org.jeecg.common.system.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @Author zhangweijian
 * @since 2018-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_UUID )
    private String id;

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 字典项文本
     */
    @Excel(name = "字典项文本", width = 20)
    private String itemText;

    /**
     * 字典项值
     */
    @Excel(name = "字典项值", width = 30)
    private String itemValue;

    /**
     * 描述
     */
    @Excel(name = "描述", width = 40)
    private String description;

    /**
     * 排序
     */
    @Excel(name = "排序", width = 15,type=4)
    private Integer sortOrder;


    /**
     * 状态（1启用 0不启用）
     */
    private Integer status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;


}
