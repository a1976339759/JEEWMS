package org.jeecg.common.system.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 用户通告阅读标记表
 * @Author: base-boot
 * @Date:  2019-02-21
 * @Version: V1.0
 */
@Data
public class AnnouncementSendModel implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_UUID )
	private String id;
	/**通告id*/
	private String anntId;
	/**用户id*/
	private String userId;
	/**标题*/
	private String titile;
	/**内容*/
	private String msgContent;
	/**发布人*/
	private String sender;
	/**优先级（L低，M中，H高）*/
	private String priority;
	/**阅读状态*/
	private String readFlag;
	/**发布时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date sendTime;
	/**页数*/
	private Integer pageNo;
	/**大小*/
	private Integer pageSize;
    /**
     * 消息类型1:通知公告2:系统消息
     */
    private String msgCategory;
	/**
	 * 业务id
	 */
	private String busId;
	/**
	 * 业务类型
	 */
	private String busType;
	/**
	 * 打开方式 组件：component 路由：url
	 */
	private String openType;
	/**
	 * 组件/路由 地址
	 */
	private String openPage;

	/**
	 * 业务类型查询（0.非bpm业务）
	 */
	private String bizSource;

	/**
	 * 摘要
	 */
	private String msgAbstract;
}
