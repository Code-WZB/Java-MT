package com.up.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.dao.BlogTypeMapper;
import com.up.entity.BlogType;
import com.up.entity.BlogTypeExample;

@Service
public class BlogTypeService {

	@Autowired
	BlogTypeMapper blogTypeMapper;
	
	/**
	 * ���
	 */
	public int blogtypeInsert(BlogType blogType) {
		return blogTypeMapper.insert(blogType);
	}
	
	
	/**
	 * ����IDɾ�����
	 */
	public int blogtypeDeleteById(int id) {
		return blogTypeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * �޸������Ϣ
	 */
	public int blogTypeUpdate(BlogType blogType) {
		return blogTypeMapper.updateByPrimaryKeySelective(blogType);
	}

	/**
	 * ����ID��ѯ���
	 */
	public BlogType blogtypeSelectById(int id) {
		return blogTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * ��ҳ��ѯ
	 */
	public List<BlogType> blogtypeSelect() {
		return blogTypeMapper.selectByExample(new BlogTypeExample());
	}

	

}
