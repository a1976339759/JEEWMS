package com.base.modules.system.service;


import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.base.modules.system.model.DepartIdModel;
import com.base.modules.system.entity.SysUser;
import com.base.modules.system.entity.SysUserDepart;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * SysUserDpeart用户组织机构service
 * </p>
 * @Author ZhiLin
 *
 */
public interface ISysUserDepartService extends IService<SysUserDepart> {
	

	/**
	 * 根据指定用户id查询部门信息
	 * @param userId
	 * @return
	 */
	List<DepartIdModel> queryDepartIdsOfUser(String userId);
	

	/**
	 * 根据部门id查询用户信息
	 * @param depId
	 * @return
	 */
	List<SysUser> queryUserByDepId(String depId);
  	/**
	 * 根据部门code，查询当前部门和下级部门的用户信息
	 */
	List<SysUser> queryUserByDepCode(String depCode,String realname);

	/**
	 * 用户组件数据查询
	 * @param departId
	 * @param username
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	IPage<SysUser> queryDepartUserPageList(String departId, String username, String realname, int pageSize, int pageNo);

}
