package com.up.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.dao.CommentMapper;
import com.up.entity.Blog;
import com.up.entity.Comment;
import com.up.entity.CommentExample;
import com.up.entity.CommentExample.Criteria;

@Service
public class CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	/**
	 * ���
	 */
	public int commentInsert(Comment comment) {
		return commentMapper.insert(comment);
	}
	
	
	/**
	 * ����IDɾ������
	 */
	public int commentDeleteById(int id) {
		return commentMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * ����ɾ�� ���һ:checkboxֻѡ��һ�� �����:checkboxѡ�ж��
	 */
	public int commentDeleteByCheckbox(List<Integer> list) {
		CommentExample commentExample = new CommentExample();
		Criteria criteria = commentExample.createCriteria();
		criteria.andIdIn(list);

		return commentMapper.deleteByExample(commentExample);
	}
	
	/**
	 * ����ID��ѯ����
	 */
	public Comment commentSelectById(int id) {
		return commentMapper.selectByPrimaryKey(id);
	}

	/**
	 * ��ѯ��������
	 */
	public Object articleSelect(Comment comment) {
		return commentMapper.selectByPrimaryKey(comment);
	}
	
	
	/**
	 * ��ҳ��ѯ
	 */
	public List<Comment> commentSelect() {
		return commentMapper.selectByExample(new CommentExample());
	}



}
