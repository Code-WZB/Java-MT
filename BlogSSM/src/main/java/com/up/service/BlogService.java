package com.up.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.dao.BlogMapper;
import com.up.entity.Blog;
import com.up.entity.BlogExample;

@Service
public class BlogService {

	@Autowired
	BlogMapper blogMapper;

	/**
	 * ���
	 */
	public int blogInsert(Blog blog) {
		return blogMapper.insert(blog);
	}
	
	/**
	 * ����IDɾ������
	 */
	public int blogDeleteById(int id) {
		return blogMapper.deleteByPrimaryKey(id);
	}
	
	
	/**
	 * ����ID��ѯ����
	 */
	public Blog blogSelectById(int id) {
		return blogMapper.selectByPrimaryKey(id);
	}

	/**
	 * ��ѯ��������
	 */
	public Object articleSelect(Blog blog) {
		return blogMapper.selectByPrimaryKey(blog);
	}

	/**
	 * ��ҳ��ѯ
	 */
	public List<Blog> blogSelect() {
		return blogMapper.selectByExample(new BlogExample());
	}

}
