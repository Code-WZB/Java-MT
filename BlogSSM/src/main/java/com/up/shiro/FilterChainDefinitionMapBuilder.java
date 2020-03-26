package com.up.shiro;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	// ������Щҳ����Ҫ�ܱ���,�Լ�������Щҳ����Ҫ��Ȩ��
	// anon:���Ա���������
	// authc:������֤(����¼)֮��ſ��Է��ʵ�ҳ��
	// logout:�ǳ�
	public LinkedHashMap<String, String> builderFilterChainDefinitionMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("/bloggerSelectByLogin", "anon");
		map.put("/logout", "logout");

		// static��̬��Դ
		map.put("/static/**", "anon");
		
		map.put("/admin/main.jsp","anon"); 
		
		return map;
	}
}
