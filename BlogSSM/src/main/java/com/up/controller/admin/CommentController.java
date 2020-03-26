package com.up.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.up.entity.Blog;
import com.up.entity.Comment;
import com.up.entity.Msg;
import com.up.service.CommentService;

@Controller
@RequestMapping("/admin")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	
	/**
	 * ���
	 */
	@ResponseBody
	@RequestMapping("commentInsert")
	public Msg commentInsert(Comment comment) {
		int num = commentService.commentInsert(comment);
		return Msg.success();
	}
	
	
	
	/**
	 * ����IDɾ������
	 */
	@ResponseBody
	@RequestMapping("commentDeleteById")
	public Msg commentDeleteById(int id) {
		int num = commentService.commentDeleteById(id);
		return Msg.success();
	}
	
	/**
	 * ����ɾ�� ���һ:checkboxֻѡ��һ�� �����:checkboxѡ�ж��
	 */
	@ResponseBody
	@RequestMapping("commentDeleteByCheckbox")
	public Msg commentDeleteByCheckbox(String ids) {
		if (ids.contains("-")) {
			// ��ѡ
			List<Integer> list = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			for (String id : str_ids) {
				list.add(Integer.parseInt(id));
			}

			commentService.commentDeleteByCheckbox(list);
		} else {
			// ��ѡ
			commentService.commentDeleteById(Integer.parseInt(ids));
		}

		return Msg.success();
	}
	
	
	/**
	 * ����ID��ѯ
	 */
	@ResponseBody
	@RequestMapping("commentSelectById")
	public Msg commentSelectById(int id) {
		Comment comment = commentService.commentSelectById(id);
		return Msg.success().add("comment", comment);
	}
	
	/**
	 * �鿴��������
	 */
	@ResponseBody
	@RequestMapping("acommentSelect")
	public Msg articleSelect(Comment comment){
		commentService.articleSelect(comment);
		return Msg.success();
	}
	
	
	
	/**
	 * ��ҳ��ѯ
	 */
	@ResponseBody
	@RequestMapping("commentSelect")
	public Msg commentSelect(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		PageHelper.startPage(pn, 4);
		List<Comment> list = commentService.commentSelect();
		PageInfo<Comment> pageInfo = new PageInfo<Comment>(list, 1);
		return Msg.success().add("pageInfo", pageInfo);
	}

}
