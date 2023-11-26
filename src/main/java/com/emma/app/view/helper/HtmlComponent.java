package com.emma.app.view.helper;

import com.emma.app.model.Employee;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class HtmlComponent {

    public static String table(List<?> dataList, Class<?> dataClass) {
        return table(dataList, dataClass, false);
    }

    public static String table(List<?> dataList, Class<?> dataClass, boolean defaultIncludeActions) {
        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<div class= \"searchDiv\">");
        trBuilder.append("<input type=\"text\" id=\"searchInput\" placeholder=\"Search\" class =\"form-control\" style=\"text-align:center\" >");
        trBuilder.append("</div>");
        trBuilder.append("<table><tr>");

        Field[] fields = dataClass.getDeclaredFields();

        String idFieldName = findIdField(fields);
        if (idFieldName == null) {
            throw new RuntimeException("No field with header 'Employee ID' found in class " + dataClass.getSimpleName());
        }

        boolean includeActions = defaultIncludeActions;
        if (dataClass.isAnnotationPresent(MyTableSetting.class)) {
            MyTableSetting tableSettingsAnnotation = dataClass.getAnnotation(MyTableSetting.class);
            includeActions = tableSettingsAnnotation.includeActions();
        }

        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyTableColHeader.class)) continue;

            MyTableColHeader colHeaderAnnotation = field.getAnnotation(MyTableColHeader.class);
            trBuilder.append("<th>").append(colHeaderAnnotation.header()).append("</th>");
        }

        if (includeActions) {
            trBuilder.append("<th>Actions</th>");
        }

        trBuilder.append("</tr>");

        if (dataList != null && !dataList.isEmpty()) {
            for (Object model : dataList) {
                trBuilder.append("<tr>");

                for (Field field : fields) {
                    if (!field.isAnnotationPresent(MyTableColHeader.class)) continue;

                    try {
                        field.setAccessible(true);
                        if (field.getName().equals("employeeImage")) {
                            trBuilder.append("<td><img src=\"images/prof/").append(field.get(model)).append("\" alt='Employee Image' class = \"prof\"></td>");
                        } else {
                            trBuilder.append("<td>").append(field.get(model)).append("</td>");
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (includeActions) {
                    trBuilder.append("<td>");
                    trBuilder.append("<button type=\"button\" class=\"btn btn-sm btn-success\" onclick=\"editItem(")
                            .append(getFieldValue(model, idFieldName)).append(")\">Edit</button>");
                    trBuilder.append("<button type=\"button\" class=\"btn btn-sm btn-danger\" onclick=\"deleteItem(")
                            .append(getFieldValue(model, idFieldName)).append(")\">Delete</button>");
                    trBuilder.append("</td>");
                }

                trBuilder.append("</tr>");
            }
        }

        trBuilder.append("</table>");

        return trBuilder.toString();
    }

    private static String findIdField(Field[] fields) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyTableColHeader.class)) {
                MyTableColHeader headerAnnotation = field.getAnnotation(MyTableColHeader.class);
                if ("Employee ID".equals(headerAnnotation.header())) {
                    return field.getName();
                }
            }
        }
        return null;
    }

    private static Object getFieldValue(Object model, String fieldName) {
        try {
            Field field = model.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(model);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static String form(Class<?> model) {
        MyHtmlForm myHtmlForm = null;
        if (model.isAnnotationPresent(MyHtmlForm.class)) myHtmlForm = model.getAnnotation(MyHtmlForm.class);
        if (myHtmlForm == null) return StringUtils.EMPTY;

        String htmlForm = "<form action=\"" + myHtmlForm.url() + "\" method=\"" + myHtmlForm.httpMethod() + "\" class=\"modal-content\"> " + "<h2>" + myHtmlForm.label() + "</h2>";
        Field[] fields = model.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyHtmlFormField.class)) continue;

            MyHtmlFormField formField = field.getAnnotation(MyHtmlFormField.class);
            String fieldName = field.getName();
            String fieldType = String.valueOf(field.getType());

            boolean isEnum = field.getType().isEnum();

            if (isEnum) {
                htmlForm += "<br><label for=\"" + (StringUtils.isBlank(formField.labelFor()) ? fieldName : formField.labelFor()) + "\">" + (StringUtils.isBlank(formField.label()) ? fieldName : formField.label()) + ":</label><br>";

                htmlForm += "<select name= \"" + (StringUtils.isBlank((formField.name())) ? fieldName : formField.name()) + "\">";
                for (Object option : field.getType().getEnumConstants()) {
                    htmlForm += "<option value=\"" + option + "\">" + option + "</option>";
                }
                htmlForm += "</select><br></br>";
            } else {
                if (field.getType() == String.class && fieldName.equals("employeeImage")) {
                    htmlForm += "<input type=\"file\" id=\"" + (StringUtils.isBlank(formField.id()) ? fieldName : formField.id()) + "\" name=\"" + (StringUtils.isBlank((formField.name())) ? fieldName : formField.name()) + "\" " + "class=\"form-control\">";
                } else {
                    htmlForm += "<input type=\"text\" id=\"" + (StringUtils.isBlank(formField.id()) ? fieldName : formField.id()) + "\" name=\"" + (StringUtils.isBlank((formField.name())) ? fieldName : formField.name()) + "\" placeholder=\"" + (StringUtils.isBlank((formField.placeholder())) ? fieldName : formField.placeholder()) + "\" class=\"form-control\">";
                }
            }
        }

        htmlForm += "<input type=\"submit\" value=\"Submit\" class=\"submit-button\">";
        htmlForm += "</form><br/>";

        return htmlForm;
    }

}
