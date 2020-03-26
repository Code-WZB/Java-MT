package com.up.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.up.entity.Blogger;
import com.up.entity.Msg;
import com.up.service.BloggerService;
import com.up.shiro.MD5;

@Controller
@RequestMapping("/admin")
public class BloggerController {

	@Autowired
	BloggerService bloggerService;

	/**
	 * ����IDɾ������
	 */
	@ResponseBody
	@RequestMapping("bloggerDeleteById")
	public Msg bloggerDeleteById(int id) {
		int num = bloggerService.bloggerDeleteById(id);
		return Msg.success();
	}

	/**
	 * ����ɾ�� ���һ:checkboxֻѡ��һ�� �����:checkboxѡ�ж��
	 */
	@ResponseBody
	@RequestMapping("bloggerDeleteByCheckbox")
	public Msg bloggerDeleteByCheckbox(String ids) {
		if (ids.contains("-")) {
			// ��ѡ
			List<Integer> list = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			for (String id : str_ids) {
				list.add(Integer.parseInt(id));
			}

			bloggerService.bloggerDeleteByCheckbox(list);
		} else {
			// ��ѡ
			bloggerService.bloggerDeleteById(Integer.parseInt(ids));
		}

		return Msg.success();
	}

	/**
	 * ���
	 */
	@ResponseBody
	@RequestMapping("bloggerInsert")
	public Msg bloggerInsert(@Valid Blogger blogger, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();

			// У��ʧ��,Ӧ�÷���ʧ��,��ģ̬����ʾУ��ʧ�ܵĴ�����Ϣ
			List<FieldError> list = result.getFieldErrors();
			for (FieldError fieldError : list) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.error().add("map", map);
		} else {

			String password_md5 = MD5.getMD5(blogger);
			blogger.setPassword(password_md5);

			int num = bloggerService.bloggerInsert(blogger);
			return Msg.success();
		}

	}

	/**
	 * ��¼
	 */
	@ResponseBody
	@RequestMapping("bloggerSelectByLogin")
	public Msg bloggerSelectByLogin(Blogger blogger) {
		String password = blogger.getPassword();

		String password_md5 = MD5.getMD5(blogger);
		blogger.setPassword(password_md5);

		long num = bloggerService.bloggerSelectByLogin(blogger);
		if (num == 0) {
			return Msg.error().add("msg", "�˺��������");
		} else {
			// Shiro��֤
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(blogger.getName(), password);
				token.setRememberMe(true);
				try {
					currentUser.login(token);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return Msg.success().add("blogger", blogger);
		}
	}

	/**
	 * �޸Ĳ�����Ϣ
	 */
	@ResponseBody
	@RequestMapping("bloggerUpdate")
	public Msg bloggerUpdate(@Valid Blogger blogger, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();

			// У��ʧ��,Ӧ�÷���ʧ��,��ģ̬����ʾУ��ʧ�ܵĴ�����Ϣ
			List<FieldError> list = result.getFieldErrors();
			for (FieldError fieldError : list) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.error().add("map", map);
		} else {
			bloggerService.bloggerUpdate(blogger);
			return Msg.success();
		}

	}

	/**
	 * ����ID��ѯ����
	 */
	@ResponseBody
	@RequestMapping("bloggerSelectById")
	public Msg bloggerSelectById(int id) {
		Blogger blogger = bloggerService.bloggerSelectById(id);
		return Msg.success().add("blogger", blogger);
	}

	/**
	 * ��ѯ�û����Ƿ��Ѿ���ռ��
	 */
	@ResponseBody
	@RequestMapping("bloggerSelectByName")
	public Msg bloggerSelectByName(String name) {
		// Java������ʽ
//		String reg = "(^[a-zA-Z0-9_-]{6,19}$)|(^[\u2E80-\u9FFF]{2,5}$)";
//		if(name.matches(reg)){
//		}else{
//			return Msg.error().add("msg", "�û���������2-5λ���Ļ�6-19λӢ�ĺ����ֵ����");
//		}

		// ��ѯ���ݿ����У��
		boolean b = bloggerService.bloggerSelectByName(name);
		if (b) {
			return Msg.success().add("msg", "�û�������");
		} else {
			return Msg.error().add("msg", "�û����ѱ�ռ��");
		}
	}

	/**
	 * ��ҳ��ѯ
	 */
	@ResponseBody
	@RequestMapping("bloggerSelect")
	public Msg bloggerSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// ָ��PageHelper��ǰҳ��ÿҳ��ʾ����
		PageHelper.startPage(pn, 6);
		// ��ѯ���ݿ�blogger����������
		List<Blogger> list = bloggerService.bloggerSelect();
		// ��PageInfo
		PageInfo<Blogger> pageInfo = new PageInfo<Blogger>(list, 6);

		return Msg.success().add("pageInfo", pageInfo);
	}
	
	
//	@RequestMapping(value="/admin/article",method=RequestMethod.GET)
//	public String article(HttpServletRequest request) {
//		return "admin/article";
//	}
	
	
}
