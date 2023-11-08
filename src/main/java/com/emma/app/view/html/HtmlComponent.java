package com.emma.app.view.html;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

public class HtmlComponent {
    public static String table(List<? extends Object> models) {

        if (models == null || models.isEmpty()) {
            return StringUtils.EMPTY;
        }
        Field[] fields = models.get(0).getClass().getDeclaredFields();

        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<table><tr>");

        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyTableColHeader.class))
                continue;

            trBuilder.append("<th>" + field.getAnnotation(MyTableColHeader.class).header() + "</th>");
        }
        trBuilder.append("</tr>");

        for (Object model : models) {
            trBuilder.append("<tr>");
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyTableColHeader.class)) continue;
                try {
                    field.setAccessible(true);
                    trBuilder.append("<td>").append(field.get(model)).append("</td>");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            trBuilder.append("</tr>");

        }
        trBuilder.append("</table>");
        return trBuilder.toString();

    }

    public static String form(Class<?> model) {
        return null;
    }

}
