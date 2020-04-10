package com.anxinghei.sys.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anxinghei.sys.entity.Permission;
import com.anxinghei.sys.entity.Role;
import com.anxinghei.sys.entity.Sysuser;
import com.anxinghei.sys.mapper.PermissionMapper;
import com.anxinghei.sys.mapper.RoleMapper;
import com.anxinghei.sys.util.baiscData;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@RestController
public class loginController {

	private String SHIRO_VERIFY_SESSION = "verifySessionCode";
	@Autowired
    private DefaultKaptcha defaultKaptcha;
	@Autowired
	private RoleMapper authGroupService;
	@Autowired
	private PermissionMapper ruleService;
	
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String toLogin() {
		Subject subject = SecurityUtils.getSubject();	
		if(subject.isRemembered()){
			System.out.println("-------------------------------------------------");
            System.out.println("认证成功");
//            Sysuser member = (Sysuser)subject.getPrincipal();
//            model.addAttribute("member",member);
            return "success";
        }
		return "failed";
	}

	@RequestMapping(value="login",method = RequestMethod.POST)
	public Map<String, String> login(String userName,String password,String verifyCode,boolean rememberMe)  {
		/**
		 * 使用Shiro编写认证操作
		 */
//1.获取Subject
		Subject subject = SecurityUtils.getSubject();	
//2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(userName,password,rememberMe);
//3.执行登录方法
		// 获取session中的验证码
        String verCode = (String) subject.getSession().getAttribute(SHIRO_VERIFY_SESSION);
        Map<String, String> map=new HashMap<>();
        if("".equals(verifyCode)||(!verCode.equals(verifyCode))){
            map.put("failed", "验证码错误");
            return map;
        }
		try {
			token.setRememberMe(rememberMe);
			subject.login(token);
			//登录成功，跳转到主页面
			map.put("success", "登录成功");
		} catch (UnknownAccountException e) {
			//登录失败:用户名不存在
			map.put("failed", "用户名不存在");
		}catch (IncorrectCredentialsException e) {
			//登录失败:密码错误
			map.put("failed", "密码错误");
		}
		return map;
	}
	
	@RequestMapping("/toHome")
	public List<Object> toHome() {
		List<Object> toReturn=new ArrayList<>();
		// 得到当前用户
		Subject subject = SecurityUtils.getSubject();
		Sysuser member = (Sysuser)subject.getPrincipal();
		
		toReturn.add(member);
		
        int groupId=member.getRole();
        Role authGroup=authGroupService.selectByPrimaryKey(groupId);
        // 得到角色的权限集：String-->int[]
     	String authString=authGroup.getPerm();
     	int[] rulesInt=baiscData.splitString(authString);
     	// 划分一二级菜单
     	List<Permission> firstRules=new ArrayList<Permission>();
     	List<Permission> secondRules=new ArrayList<Permission>();
     	Permission authRule=new Permission();
     	for (int i = 0; i < rulesInt.length; i++) {
     		authRule=ruleService.selectByPrimaryKey(rulesInt[i]);
     		if (0==authRule.getPid()) {
				firstRules.add(authRule);
			}else {
				secondRules.add(authRule);
			}
		}
     	
     	toReturn.add(firstRules);
     	toReturn.add(secondRules);
     	
		return toReturn;
	}
	
	/**
     * 获取验证码
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute(SHIRO_VERIFY_SESSION,createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge,"jpg",jpegOutputStream);
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    
}