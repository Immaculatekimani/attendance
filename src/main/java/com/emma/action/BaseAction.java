package com.emma.action;

import com.emma.app.model.entity.EmployeeRole;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.http.HttpServlet;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BaseAction extends HttpServlet {
    static {
        ConvertUtils.register(new EmployeeTypeConverter(), EmployeeRole.class);
    }

    public void serializeForm(Object bean, Map<String, ? extends Object> requestMap) {
        try {
            BeanUtils.populate(bean, requestMap);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
