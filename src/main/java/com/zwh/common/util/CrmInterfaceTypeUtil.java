/**
 * Project Name:phapp
 * File Name:CrmInterfaceTypeUtil.java
 * Package Name:com.thinkgem.jeesite.common.utils
 * Date:2016年8月3日上午9:28:48
 * Copyright (c) 2016, hukailee@163.com All Rights Reserved.
 *
*/

package com.zwh.common.util;
/**
 * ClassName:CrmInterfaceTypeUtil (定义crm 进件数据的 字典数据类型)
 * Date:     2016年8月3日 上午9:28:48
 * @Author   airufei
 * @Version  1.0
 * @see 	 
 */
public class CrmInterfaceTypeUtil {
	public static String getTypename(String field) {
		String typename="";
		if(field!=null)
		{
			if(field.equals("loan_type")||field.equals("zs_loan_typeid"))//借款服务类型
			{
				typename="crm_type";
			}
			if(field.equals("state"))//进件状态
			{
				typename="crm_state";
			}
			if(field.equals("loan_time")||field.equals("zs_loanTimeid"))//借款期限
			{
				typename="crm_deadline";
			}
			if(field.equals("account_static"))//合同状态
			{
				typename="crm_contract";
			}
			if(field.equals("status"))//还款状态
			{
				typename="crm_overdue";
			}
			if(field.equals("use"))//借款用途
			{
				typename="crm_use";
			}
			
		}
		return typename;
	}

	
	
}

