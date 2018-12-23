package com.husky.account.login.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.druid.support.json.JSONUtils;
import com.husky.account.login.secure.RSAUtils;
import com.husky.account.login.service.IloginService;
import com.husky.account.login.service.imp.LoginService;
import com.husky.account.login.vo.UserInfoVO;
import com.husky.utils.JsonUtil;
import com.husky.vo.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@RestController("accountLoginController")
@RequestMapping("/api/login")
@Api(value = "/api/login", description = "登录操作类")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	@Resource
	private IloginService service;

	private static String codeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@RequestMapping(value = "/getValidationCode",consumes= {"application/octet-stream"})
	@ApiOperation(value = "生成校验码", httpMethod = "POST")
	public void getValidationCode() throws IOException {
		HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 获得验证码集合的长度
		int charsLength = codeChars.length();
		// 下面3条记录是关闭客户端浏览器的缓冲区
		// 这3条语句都可以关闭浏览器的缓冲区，但是由于浏览器的版本不同，对这3条语句的支持也不同
		// 因此，为了保险起见，同时使用这3条语句来关闭浏览器的缓冲区
		resp.setHeader("ragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		// 设置图形验证码的长和宽
		int width = 90, height = 30;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics(); // 获得用于输出文字的Graphics对象
		Random random = new Random();
		g.setColor(getRandomColor(180, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, height));
		g.setColor(getRandomColor(120, 180));
		// 用户保存最后随机生成的验证码
		StringBuilder validationCode = new StringBuilder();
		// 验证码的随机字体
		String[] fontNames = { "Times New Roman", "Book antiqua", "Arial" };
		// 随机生成4个验证码
		for (int i = 0; i < 4; i++) {
			// 随机设置当前验证码的字符的字体
			g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));
			// 随机获得当前验证码的字符
			char codeChar = codeChars.charAt(random.nextInt(charsLength));

			validationCode.append(codeChar);
			// 随机设置当前验证码字符的颜色
			g.setColor(getRandomColor(10, 100));
			// 在图形上输出验证码字符，x和y都是随机生成的
			g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7), height - random.nextInt(6));
		}
		// 获得HttpSession对象
		HttpSession session = req.getSession();
		// 设置session对象5分钟失效
		session.setMaxInactiveInterval(5 * 60);
		// 将验证码保存在session对象中,key为validation_code
		session.setAttribute("validation_code", validationCode.toString());
		// 关闭Graphics对象
		g.dispose();
		OutputStream outS = resp.getOutputStream();
		ImageIO.write(image, "JPEG", outS);
	}

	@RequestMapping(value = "/logIn")
	@ApiOperation(value = "登录", httpMethod = "POST")
	public Result<String> logIn() {
//		logger.info("LoginController logIn request:" + JsonUtil.BeanToJsonStr(param));
//		return service.login(param);
		return Result.success("aa");
	}

	@PostMapping(value = "/rdspwd",consumes="application/json", produces="application/json")
	@ApiOperation(value = "获取公匙", httpMethod = "POST")
	public Result<String> rdspwd() throws Exception {
		String publicKey = RSAUtils.generateBase64PublicKey();
		logger.info("public key:" + publicKey);
		return Result.success(publicKey);
	}
	
	private static Color getRandomColor(int minColor, int maxColor) {
		Random random = new Random();
		if (minColor > 255) {
			minColor = 255;
		}
		if (maxColor > 255) {
			maxColor = 255;
		}
		// 获得r的随机颜色值
		int red = minColor + random.nextInt(maxColor - minColor);
		// g
		int green = minColor + random.nextInt(maxColor - minColor);
		// b
		int blue = minColor + random.nextInt(maxColor - minColor);
		return new Color(red, green, blue);
	}

}
