package com.up.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.up.entity.Link;
import com.up.entity.Msg;
import com.up.service.LinkService;

@Controller
@RequestMapping("/admin")
public class LinkController {

	@Autowired
	LinkService linkService;

	/**
	 * ���
	 */
	@ResponseBody
	@RequestMapping("linkInsert")
	public Msg linkInsert(Link link) {
		int num = linkService.linkInsert(link);
		return Msg.success();
	}

	/**
	 * ����IDɾ�����
	 */
	@ResponseBody
	@RequestMapping("linkDeleteById")
	public Msg linkDeleteById(int id) {
		int num = linkService.linkDeleteById(id);
		return Msg.success();
	}

	/**
	 * �޸������Ϣ
	 */
	@ResponseBody
	@RequestMapping("linkUpdate")
	public Msg linkUpdate(Link link) {
		linkService.linkUpdate(link);
		return Msg.success();
	}

	/**
	 * ����ID��ѯ���
	 */
	@ResponseBody
	@RequestMapping("linkSelectById")
	public Msg linkSelectById(int id) {
		Link link = linkService.linkSelectById(id);
		return Msg.success().add("link", link);
	}

	/**
	 * ��ҳ��ѯ
	 */
	@ResponseBody
	@RequestMapping("linkSelect")
	public Msg linkSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 6);
		List<Link> list = linkService.linkSelect();
		PageInfo<Link> pageInfo = new PageInfo<Link>(list, 1);
		return Msg.success().add("pageInfo", pageInfo);
	}

}
