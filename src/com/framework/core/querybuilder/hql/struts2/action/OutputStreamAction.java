package com.framework.core.querybuilder.hql.struts2.action;

import java.io.OutputStream;


/**
 * 功能:
 * @author yuyang
 * @version 1.0
 * @since Jun 6, 20093:35:36 PM
 */
public interface OutputStreamAction {

    public abstract void render(OutputStream os) throws Exception;
    
}