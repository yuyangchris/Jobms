package com.framework.core.querybuilder.hql.model.query.converter;



/**
 * 功能:
 * @author yuyang
 * @version 1.0
 * @since Jun 6, 20093:01:43 PM
 */
public class DefaultConverter implements Converter {
    
    /**
     *
     * @param type Data type to which this value should be converted
     * @param value The input value to be converted
     *
     * @exception ConversionException if conversion cannot be performed
     *  successfully
     */
    public Object convert(Class type, String value) throws ConversionException {
    	return value;
    }
    
}