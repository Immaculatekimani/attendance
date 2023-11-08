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
        MyHtmlForm myHtmlForm = null;
        if (model.isAnnotationPresent(MyHtmlForm.class))
            myHtmlForm = model.getAnnotation(MyHtmlForm.class);
        if (myHtmlForm == null)
            return StringUtils.EMPTY;

        String htmlForm = "<form action=\"" + myHtmlForm.url() + "\" method=\"" + myHtmlForm.httpMethod() + "\" class=\"modal-content\"> " +
                "<h2>" + myHtmlForm.label() + "</h2>";
        Field[] fields = model.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyHtmlFormField.class))
                continue;

            MyHtmlFormField formField = field.getAnnotation(MyHtmlFormField.class);
            String fieldName = field.getName();
            String fieldType = String.valueOf(field.getType()); //recheck this later

            boolean isEnum = field.getType().isEnum();

            if (isEnum) {
                htmlForm += "<br><label for=\""
                        + (StringUtils.isBlank(formField.labelFor()) ? fieldName : formField.labelFor())
                        + "\">"
                        + (StringUtils.isBlank(formField.label()) ? fieldName : formField.label()) + ":</label><br>";

                htmlForm += "<select name= \"" + (StringUtils.isBlank((formField.name())) ? fieldName : formField.name())
                        + "\">";
                for (Object option : field.getType().getEnumConstants()) {
                    htmlForm += "<option value=\"" + option + "\">" + option + "</option>";
                }
                htmlForm += "</select><br></br>";
            } else {
                htmlForm += "<input type=\"text\" id=\""
                        + (StringUtils.isBlank(formField.id()) ? fieldName : formField.id()) + "\" name= \""
                        + (StringUtils.isBlank((formField.name())) ? fieldName : formField.name()) +
                        "\" placeholder=\"" + (StringUtils.isBlank((formField.placeholder())) ? fieldName : formField.placeholder()) + "\" class=\"form-control\">";

            }


        }

        htmlForm += "<input type=\"submit\" value=\"Submit\" class=\"submit-button\">";
        htmlForm += "</form><br/>";

        return htmlForm;
    }

}
