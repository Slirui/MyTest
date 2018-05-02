package com.spring.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * ��ֹ�ظ��ύע�⣬���ڷ�����<br/>
 * ���½�ҳ�淽���ϣ�����needSaveToken()Ϊtrue����ʱ����������Session�б���һ��token�� ͬʱ��Ҫ���½���ҳ�������
 * <input type="hidden" name="token" value="${token}"> <br/>
 * ���淽����Ҫ��֤�ظ��ύ�ģ�����needRemoveTokenΪtrue ��ʱ��������������֤�Ƿ��ظ��ύ
 * </p>
 * 
 * @author: chuanli
 * @date: 2013-6-27����11:14:02
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidDuplicateSubmission {
	boolean needSaveToken() default false;

	boolean needRemoveToken() default false;
}