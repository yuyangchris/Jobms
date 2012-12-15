package com.framework.core.querybuilder.hql.model.query.converter;



/**
 * 功能:
 * @author yuyang
 * @version 1.0
 * @since Jun 6, 20093:01:57 PM
 */
public class LongConverter implements Converter {

    public Object convert(Class type, String value) throws ConversionException {
        return Long.valueOf(value);
    }

}

