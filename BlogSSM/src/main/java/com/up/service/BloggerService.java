package com.up.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.dao.BloggerMapper;
import com.up.entity.Blogger;
import com.up.entity.BloggerExample;
import com.up.entity.BloggerExample.Criteria;

@Service
public class BloggerService {

	@Autowired
	BloggerMapper bloggerMapper;

	/**
	 * ��¼
	 */
	public long bloggerSelectByLogin(Blogger blogger) {
		BloggerExample bloggerExample = new BloggerExample();
		Criteria criteria = bloggerExample.createCriteria();
		criteria.andNameEqualTo(blogger.getName());
		criteria.andPasswordEqualTo(blogger.getPassword());

		return bloggerMapper.countByExample(bloggerExample);
	}

	/**
	 * ���
	 */
	public int bloggerInsert(Blogger blogger) {
		return bloggerMapper.insert(blogger);
	}

	/**
	 * ����IDɾ������
	 */
	public int bloggerDeleteById(int id) {
		return bloggerMapper.deleteByPrimaryKey(id);
	}

	/**
	 * ����ɾ�� ���һ:checkboxֻѡ��һ�� �����:checkboxѡ�ж��
	 */
	public int bloggerDeleteByCheckbox(List<Integer> list) {
		BloggerExample bloggerExample = new BloggerExample();
		Criteria criteria = bloggerExample.createCriteria();
		criteria.andIdIn(list);

		return bloggerMapper.deleteByExample(bloggerExample);
	}

	/**
	 * ����ID��ѯ����
	 */
	public Blogger bloggerSelectById(int id) {
		return bloggerMapper.selectByPrimaryKey(id);
	}

	/**
	 * ��ѯ�û����Ƿ��Ѿ���ռ��
	 */
	public boolean bloggerSelectByName(String name) {
		BloggerExample bloggerExample = new BloggerExample();
		Criteria criteria = bloggerExample.createCriteria();
		criteria.andNameEqualTo(name);

		long count = bloggerMapper.countByExample(bloggerExample);
		return count == 0;
	}

	/**
	 * ��ҳ��ѯ
	 */
	public List<Blogger> bloggerSelect() {
		return bloggerMapper.selectByExample(new BloggerExample());
	}

	/**
	 * �޸Ĳ�����Ϣ
	 */
	public int bloggerUpdate(Blogger blogger) {
		return bloggerMapper.updateByPrimaryKeySelective(blogger);
	}
	
	
	
}
