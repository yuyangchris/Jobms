package com.framework.core.querybuilder.hql.struts2.view;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateModel;


/**
 * 功能:
 * @author yuyang
 * @version 1.0
 * @since Jun 6, 20093:38:10 PM
 */
public class FreemarkerResult extends org.apache.struts2.views.freemarker.FreemarkerResult {
//    public static final String KEY_WEBWORK_TAG_LIB = "ww";
    public static final String KEY_BASE = "base";

    protected Writer getWriter() throws IOException {
        return new OutputStreamWriter(ServletActionContext.getResponse().getOutputStream(),
                ServletActionContext.getResponse().getCharacterEncoding());
    }
    
    protected boolean preTemplateProcess(Template template, TemplateModel model) throws IOException {
        super.preTemplateProcess(template, model);
        inject(model);
        return true;
    }

    private void inject(TemplateModel model) {
        SimpleHash hash = (SimpleHash) model;
        HttpServletRequest request = ServletActionContext.getRequest();
        hash.put(KEY_BASE, request.getContextPath());

//            TaglibFactory factory = (TaglibFactory) hash.get(FreemarkerManager.KEY_JSP_TAGLIBS);
//            hash.put(KEY_WEBWORK_TAG_LIB, factory.get("/WEB-INF/webwork.tld"));
    }
    
}
