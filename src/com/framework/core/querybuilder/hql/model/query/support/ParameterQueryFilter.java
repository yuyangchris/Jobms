package com.framework.core.querybuilder.hql.model.query.support;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.WebUtils;

import com.framework.core.querybuilder.hql.model.query.QueryContext;
import com.framework.core.querybuilder.hql.model.query.QueryFilter;


/**
 * 功能:
 * @author yuyang
 * @version 1.0
 * @since Jun 6, 20093:20:20 PM
 */
public class ParameterQueryFilter implements QueryFilter {
	private static final Log log = LogFactory.getLog(ParameterQueryFilter.class);
	
	/**
	 * queryRequired == "true" && hasQueried != "true"
	 */
	public boolean isQueryRequired(QueryContext queryContext) {
		Map requestParams = queryContext.getRequestParams();
		String queryRequired = "";//WebUtils.getRequestParameter(requestParams, "queryRequired");
		String hasQueried = "";//WebUtils.getRequestParameter(requestParams, "hasQueried");
		
		if (log.isDebugEnabled()) {
			log.debug("queryRequired=" + queryRequired);
			log.debug("hasQueried=" + hasQueried);
		}
		
		return "true".equalsIgnoreCase(queryRequired)
				&& !("true".equalsIgnoreCase(hasQueried));
	}

}
