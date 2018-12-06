package husky.account.login.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.husky.vo.Result;

import husky.account.login.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/login")
@Api(value = "/login", description = "登录操作类")
public class LoginController {

	@RequestMapping(name = "/logIn", method = RequestMethod.POST)
	@ApiOperation(value = "登录", httpMethod = "POST")
	public Result<UserInfoVO> logIn(@RequestBody UserInfoVO param) {
		// 1.账号登录 2.手机号登录 3.邮箱登录
		if (!StringUtils.isEmpty(param.getAccount())) {

		} else if (!StringUtils.isEmpty(param.getTelephone())) {

		}

		else if (!StringUtils.isEmpty(param.getEmail())) {

		} else {
			return Result.error("账号为空");
		}
		return null;
	}
}
