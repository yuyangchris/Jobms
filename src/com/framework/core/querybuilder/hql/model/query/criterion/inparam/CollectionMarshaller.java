package com.framework.core.querybuilder.hql.model.query.criterion.inparam;

import java.util.Collection;


/**
 * 功能:
 * @author yuyang
 * @version 1.0
 * @since Jun 6, 20093:19:21 PM
 */
public class CollectionMarshaller implements Marshaller {
    private Collection values;
    
    public CollectionMarshaller(Object value) {
        this.values = (Collection) value;
    }
    
    public Object getNamedQueryParamValue(Class paramClass) {
        // doesn't check param's type
        return values;
    }

    public boolean hasValues() {
        return !values.isEmpty();
    }

    public String stringValue() {
        Object[] array = values.toArray();
        
        StringBuffer buf = new StringBuffer(48);
        for (int i = 0; i < array.length; i++) {
            buf.append(array[i]);
            if (i < array.length - 1) {
                buf.append(",");
            }
        }
        return buf.toString();
    }

    public String toString() {
        return "CollectionMarshaller";
    }

}

