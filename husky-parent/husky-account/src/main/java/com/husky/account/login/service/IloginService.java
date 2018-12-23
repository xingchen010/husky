package com.husky.account.login.service;

import com.husky.account.login.vo.UserInfoVO;
import com.husky.vo.Result;

public interface IloginService {
	
	/**
	 *   登录
	 * @param vo
	 * @return
	 */
	Result<UserInfoVO> login(UserInfoVO vo);
	
	/**
	 *注销
	 * @param vo
	 * @return
	 */
	Result<Void> loginOut(UserInfoVO vo);
	
	/**
	 * 注册
	 * @param vo
	 * @return
	 */
	Result<Void> register(UserInfoVO vo);
	
	
}
