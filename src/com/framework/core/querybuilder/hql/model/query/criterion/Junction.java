package com.framework.core.querybuilder.hql.model.query.criterion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.framework.core.querybuilder.hql.model.query.QueryBuilder;


/**
 * 功能:
 * @author yuyang
 * @version 1.0
 * @since Jun 6, 20093:18:18 PM
 */
public class Junction implements Criterion {
	private final List criteria = new ArrayList(4);
	private final String operation;
	private transient boolean first = true;
	
	protected Junction(String operation) {
		this.operation = operation;
	}
	
	public Junction add(Criterion criterion) {
		criteria.add(criterion);
		return this;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public String toHqlString(QueryBuilder builder) {
		if (criteria.size() == 0) return "1=1";

		StringBuffer buffer = new StringBuffer().append('(');
		toHqlString(criteria.iterator(), buffer, builder);
		return buffer.append(')').toString();
	}
	
	private void toHqlString(Iterator it, StringBuffer buffer, QueryBuilder builder) {
		Criterion criterion = (Criterion) it.next();
		String hqlStr = criterion.toHqlString(builder);
		if (hqlStr != null) {
			if (first) {
				first = false;
			} else {
				buffer.append(' ').append(operation).append(' ');
			}
			buffer.append(hqlStr);
		}
		if (it.hasNext()) {
			toHqlString(it, buffer, builder);
		}
	}
	
}
