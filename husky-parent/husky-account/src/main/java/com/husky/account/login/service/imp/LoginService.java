package com.husky.account.login.service.imp;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.husky.account.login.service.IloginService;
import com.husky.account.login.vo.LoginInfoVO;
import com.husky.account.login.vo.UserInfoExample;
import com.husky.account.login.vo.UserInfoVO;
import com.husky.account.login.vo.UserInfoExample.Criteria;
import com.husky.constant.RedisConstant;
import com.husky.dao.GenericityDao;
import com.husky.enums.ResultEnum;
import com.husky.exception.QueryException;
import com.husky.redis.RedisUtil;
import com.husky.utils.JsonUtil;
import com.husky.vo.Result;

@Service
public class LoginService implements IloginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Resource(name = "mysqlDao")
	private GenericityDao mysqlDao;
	@Resource(name = "redisUtil")
	private RedisUtil redisUtil;

	private static String nameSpace = UserInfoVO.class.getCanonicalName();

	@Override
	public Result<UserInfoVO> login(UserInfoVO vo) {
		try {
			UserInfoExample example = new UserInfoExample();
			Criteria criteria1 = example.createCriteria();
			criteria1.andIsCancelledEqualTo("1");
			criteria1.andIsForbiddenEqualTo("1");
			criteria1.andAccountEqualTo(vo.getAccount());
			UserInfoVO userInfoVO = mysqlDao.queryOne(nameSpace + ".selectByExample", example);
			String password = userInfoVO.getPassword();
			// 解密 TODO
			if (StringUtils.equals(password, vo.getPassword())) {
				// 登录成功
				//1.记录登录信息
				LoginInfoVO loginInfo = new LoginInfoVO();
				loginInfo.setAccount(userInfoVO.getAccount());
				loginInfo.setCreatedDate(new Date());
				loginInfo.setCreatedBy(userInfoVO.getAccount());
				mysqlDao.insertOne(nameSpace + ".insertSelective", loginInfo);
				//2.将登录信息写入redis
				String loginInfoStr = JsonUtil.BeanToJsonStr(loginInfo);
				logger.info("login success loginInfo:" + loginInfoStr);
				boolean set = redisUtil.set(RedisConstant.LOGIN_CATCH_KEY, loginInfoStr);
				if (set) {
					logger.info("login info save ro catch success!");
					return Result.build(ResultEnum.SUCCESS.getCode(), null, userInfoVO);
				}
				return Result.build(ResultEnum.SUCCESS.getCode(), "登录信息缓存失败", userInfoVO);
			}
		} catch (QueryException e) {
			logger.error("", e);
		}
		return Result.build(ResultEnum.LOGIN_ERROR.getCode(), ResultEnum.LOGIN_ERROR.getMsg());
	}

	@Override
	public Result<Void> loginOut(UserInfoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Void> register(UserInfoVO vo) {
		Long insertOne = mysqlDao.insertOne(nameSpace + ".insertSelective", vo);
		if (insertOne > 0) {
			return Result.success(null);
		}
		return Result.build(ResultEnum.SYS_ERROR.getCode(), ResultEnum.SYS_ERROR.getMsg());
	}

}
