package com.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.bean.DBTjBean;

public class NativeQueryUtil<T> {
	@SuppressWarnings("unchecked")
	public List<T> toList(List<Object[]> os, Class<T> obj) throws Exception {
		List<T> list = new ArrayList<T>();
		for (Object[] t : os) {

			try {
				T t1 = (T) obj.getConstructor(String.class, Integer.class).newInstance((String) t[0], (Integer) t[1]);
				list.add(t1);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
		return list;

	}

	public T getT() throws InstantiationException, IllegalAccessException {
		Type sType = getClass().getGenericSuperclass();
		Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
		Class<T> mTClass = (Class<T>) (generics[0]);
		return mTClass.newInstance();
	}

	public static void main(String[] args) {
		Object[] d = new Object[] { "1", 3 };
		List<Object[]> list2 = new ArrayList<>();
		list2.add(d);
		try {
			List<DBTjBean> list = new NativeQueryUtil<DBTjBean>().toList(list2, DBTjBean.class);
			System.out.println(list.get(0).key);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
