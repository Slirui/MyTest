package com.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.dao.GenericRepository;
import com.dao.QueryKword;
import com.pages.QueryResult;

public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements GenericRepository<T, ID> {

	private final EntityManager entityManager;
	private Class<T> entityClass;
	private String entityName;

	public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		entityManager = em;
	}

	public GenericRepositoryImpl(final JpaEntityInformation<T, ?> entityInformation,
			final EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityClass = entityInformation.getJavaType();
		this.entityName = entityInformation.getEntityName();
	}

	public QueryResult<T> list(HttpServletRequest request, List<QueryKword> queryKwordList, Pageable page) {
		String sql = "select t from " + entityName + " t where 1=1  ";
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (queryKwordList != null) {
			for (QueryKword key : queryKwordList) {
				sql += " and " + key.parse(map);
			}
		}
		String totalSql = sql.replaceAll("^select.*?from", "select count(t) from ");
		String orders = "";
		for (Sort.Order order : page.getSort()) {
			orders += "t." + order.getProperty() + " " + order.getDirection() + " ";
		}

		sql = sql + (orders.equals("") ? "" : " order by  " + orders);
		TypedQuery<T> q = this.entityManager.createQuery(sql, entityClass);
		if (queryKwordList != null) {
			for (String key : map.keySet()) {
				q.setParameter(key, map.get(key));
			}
		}
		if (page != null) {
			q.setFirstResult(page.getOffset());
			q.setMaxResults(page.getPageSize());
		}

		String tsql = totalSql;
		Query query = entityManager.createQuery(tsql);
		if (map != null) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		Long total = (Long) query.getSingleResult();
		return new QueryResult<T>(request, q.getResultList(), total);

	}

}
