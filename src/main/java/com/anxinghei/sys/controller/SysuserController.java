package com.anxinghei.sys.controller;

//import com.github.wxiaoqi.security.common.rest.BaseController;
//import com.anxinghei.sys.biz.SysuserBiz;
import com.anxinghei.sys.entity.Sysuser;
import com.anxinghei.sys.vo.LoginVo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// extends BaseController<SysuserBiz,Sysuser>

@RestController
@RequestMapping("sysuser")
public class SysuserController  {

	@PostMapping("/logouting")
	public String logouting() {
		LoginVo.setSysuser(null);
		System.out.println(LoginVo.getSysuser());
		return "success";
	}
}