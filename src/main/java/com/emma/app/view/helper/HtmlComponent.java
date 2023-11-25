package com.emma.app.view.helper;

import com.emma.app.model.Employee;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

public class HtmlComponent {
    public static String table(List<?> dataList, Class<?> dataClass) {

        StringBuilder trBuilder = new StringBuilder();
        trBuilder.append("<div class= \"searchDiv\">");
        trBuilder.append("<input type=\"text\" id=\"searchInput\" placeholder=\"Search\" class =\"form-control\" style=\"text-align:center\" >");
        trBuilder.append("</div>");
        trBuilder.append("<table><tr>");

        Field[] fields = dataClass.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyTableColHeader.class)) continue;

            trBuilder.append("<th>" + field.getAnnotation(MyTableColHeader.class).header() + "</th>");
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
                            trBuilder.append("<td><img src=\"images/prof/" + field.get(model) + "\" alt='Employee Image' class = \"prof\"></td>");
                        } else {
                            trBuilder.append("<td>").append(field.get(model)).append("</td>");
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                trBuilder.append("</tr>");

            }
        }

        trBuilder.append("</table>");

        trBuilder.append(getSearchScript());
        return trBuilder.toString();

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
            String fieldType = String.valueOf(field.getType()); //recheck this later

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

    private static String getSearchScript() {
        StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("<script>");
        scriptBuilder.append("function searchTable() {");
        scriptBuilder.append("  var input, filter, table, tr, td, i, txtValue;");
        scriptBuilder.append("  input = document.getElementById('searchInput');");
        scriptBuilder.append("  filter = input.value.toUpperCase();");
        scriptBuilder.append("  table = document.querySelector('table');");
        scriptBuilder.append("  tr = table.querySelectorAll('tr');");
        scriptBuilder.append("  for (i = 1; i < tr.length; i++) {");  // Start from 1 to skip the header row
        scriptBuilder.append("    tds = tr[i].querySelectorAll('td');");
        scriptBuilder.append("    for (j = 0; j < tds.length; j++) {");
        scriptBuilder.append("      td = tds[j];");
        scriptBuilder.append("      if (td) {");
        scriptBuilder.append("        txtValue = td.textContent || td.innerText;");
        scriptBuilder.append("        if (txtValue.toUpperCase().indexOf(filter) > -1) {");
        scriptBuilder.append("          tr[i].style.display = '';");
        scriptBuilder.append("          break;");
        scriptBuilder.append("        } else {");
        scriptBuilder.append("          tr[i].style.display = 'none';");
        scriptBuilder.append("        }");
        scriptBuilder.append("      }");
        scriptBuilder.append("    }");
        scriptBuilder.append("  }");
        scriptBuilder.append("}");
        scriptBuilder.append("document.getElementById('searchInput').addEventListener('input', searchTable);");
        scriptBuilder.append("</script>");

        return scriptBuilder.toString();
    }

}
