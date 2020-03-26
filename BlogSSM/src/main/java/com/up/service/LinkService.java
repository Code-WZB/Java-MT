package com.up.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up.dao.LinkMapper;
import com.up.entity.LinkExample;
import com.up.entity.Link;

@Service
public class LinkService {

	@Autowired
	LinkMapper linkMapper;
	
	/**
	 * ���
	 */
	public int linkInsert(Link link) {
		return linkMapper.insert(link);
	}
	
	
	/**
	 * ����IDɾ�����
	 */
	public int linkDeleteById(int id) {
		return linkMapper.deleteByPrimaryKey(id);
	}

	/**
	 * �޸������Ϣ
	 */
	public int linkUpdate(Link link) {
		return linkMapper.updateByPrimaryKeySelective(link);
	}

	/**
	 * ����ID��ѯ���
	 */
	public Link linkSelectById(int id) {
		return linkMapper.selectByPrimaryKey(id);
	}

	/**
	 * ��ҳ��ѯ
	 */
	public List<Link> linkSelect() {
		return linkMapper.selectByExample(new LinkExample());
	}

	
}
