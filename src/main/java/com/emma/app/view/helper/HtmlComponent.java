package com.emma.app.view.helper;

import com.emma.app.model.Attendance;
import com.emma.app.model.BaseEntity;
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
        trBuilder.append("<div style=\"max-height: 60vh; overflow: auto;\">");
        trBuilder.append("<table id=\"dataTable\" class=\" display table table-bordered border-4 table-striped table-responsive-sm \"><thead><tr>");

        Field[] fields = dataClass.getDeclaredFields();

        String idFieldName;
        boolean isEmployeeTable = Employee.class.isAssignableFrom(dataClass);

        idFieldName = findIdField(fields);


        if (idFieldName == null) {
            throw new RuntimeException("No field with header 'Employee FK' found in class " + dataClass.getSimpleName());
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
        trBuilder.append("<th>View Attendance</th>"); // New column for the "View Attendance" link
        if (includeActions) {
            trBuilder.append("<th>Actions</th>");
        }
        trBuilder.append("</tr></thead><tbody>");

        if (dataList != null && !dataList.isEmpty()) {
            for (Object model : dataList) {
                trBuilder.append("<tr>");

                for (Field field : fields) {
                    if (!field.isAnnotationPresent(MyTableColHeader.class)) continue;

                    try {
                        field.setAccessible(true);
                        // Get the background color for the current field
                        String cellBgColor = getCellBackgroundColor(field, model);
                        String joinBgColor = getjoinBackgroundColor(field, model);


                        if (field.getName().equals("employeeImage")) {
                            trBuilder.append("<td><img src=\"images/prof/").append(field.get(model)).append("\" alt='Employee Image' class = \"prof\"></td>");
                        } else {
                            if (field.getName().equals("attendanceStatus")) {
                                trBuilder.append("<td style=\"background-color: ").append(cellBgColor).append(";\">").append(field.get(model)).append("</td>");
                            } else if (field.getName().equals("joiningStatus")) {
                                trBuilder.append("<td style=\"background-color: ").append(joinBgColor).append(";\">").append(field.get(model)).append("</td>");

                            } else {
                                trBuilder.append("<td>").append(field.get(model)).append("</td>");
                            }


                        }

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (isEmployeeTable) {
                    trBuilder.append("<td>\n" +
                            " <button  class=\"submit-button\" onclick=\"viewAttendance('" + ((Employee) model).getId() + "')\">View Attendance</button>" +
                            "</td>");
                } else {
                    trBuilder.append("<td>\n" +
                            " <button  class=\"submit-button\" onclick=\"viewAttendance('" + getFieldValue(model, idFieldName) + "')\">View Attendance</button>" +
                            "</td>");
                }


                if (includeActions) {
                    trBuilder.append("<td>");
                    trBuilder.append("<div class=\"action-buttons\">");

                    // Edit Form
                    trBuilder.append("<button  class=\"btn btn-sm btn-success\" onclick=\"editEmployee('" + getFieldValue(model, idFieldName) + "')\">Update</button>");

                    // Delete Form
                    trBuilder.append("<form method=\"post\" action=\"" + dataClass.getAnnotation(MyHtmlForm.class).deleteURL() + "\">");
                    trBuilder.append("<input type=\"hidden\" name=\"action\" value=\"delete\"/>"); // Added hidden field
                    trBuilder.append("<input type=\"hidden\" name=\"itemId\" value=\"")
                            .append(getFieldValue(model, idFieldName)).append("\"/>");
                    trBuilder.append("<button type=\"submit\" class=\"btn btn-sm btn-danger\">Delete</button>");
                    trBuilder.append("</form>");
                    trBuilder.append("</div>");
                    trBuilder.append("</td>");
                }

                trBuilder.append("</tr>");
            }
        }

        trBuilder.append("</tbody></table>");
        trBuilder.append("</div>");

        return trBuilder.toString();
    }

    private static String findIdField(Field[] fields) {
        for (Field field : fields) {
            if (field.isAnnotationPresent(TableColumnIdentifier.class)) {
                TableColumnIdentifier identityAnnotation = field.getAnnotation(TableColumnIdentifier.class);
                if ("Employee FK".equals(identityAnnotation.columnIdentifier())) {
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

    private static String getCellBackgroundColor(Field field, Object model) {
        if (field.isAnnotationPresent(BackgroundColor.class)) {
            field.setAccessible(true);
            Attendance attendance = (Attendance) model;
            BackgroundColor bgColorAnnotation = field.getAnnotation(BackgroundColor.class);
            String status = attendance.getAttendanceStatus();

            switch (status) {
                case "Present":
                    return bgColorAnnotation.presentColor();
                case "Late":
                    return bgColorAnnotation.lateColor();
                case "Absent":
                    return bgColorAnnotation.absentColor();
                case "HalfDay":
                    return bgColorAnnotation.halfdayColor();
                case "FullDay":
                    return bgColorAnnotation.fulldayColor();
                case "In-Time":
                    return bgColorAnnotation.inTimeColor();



                default:
                    return bgColorAnnotation.value();
            }

        }
        return "";
    }

    private static String getjoinBackgroundColor(Field field, Object model) {
        if (field.isAnnotationPresent(BackgroundColor.class)) {
            field.setAccessible(true);
            Attendance attendance = (Attendance) model;
            BackgroundColor bgColorAnnotation = field.getAnnotation(BackgroundColor.class);
            String status = attendance.getJoiningStatus();
            if (status != null) {
                switch (status) {
                    case "Late":
                        return bgColorAnnotation.joiningLateColor();
                    case "In-Time":
                        return bgColorAnnotation.inTimeColor();
                    case "Not Attended":
                        return bgColorAnnotation.nullColor();
                    default:
                        return bgColorAnnotation.value();
                }
            }
            return bgColorAnnotation.nullColor();

        }
        return "";

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

    public static String editForm(Class<?> model, Object entity) {
        MyHtmlForm myHtmlForm = null;
        if (model.isAnnotationPresent(MyHtmlForm.class)) myHtmlForm = model.getAnnotation(MyHtmlForm.class);
        if (myHtmlForm == null) return StringUtils.EMPTY;

        String htmlForm = "<form  id =\"editForm\" action=\"" + myHtmlForm.editURL() + "\" method=\"" + myHtmlForm.httpMethod() + "\" class=\"modal-content\"> " + "<h2>" + myHtmlForm.editLabel() + "</h2>";
        Field[] fields = model.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyHtmlFormField.class)) continue;

            MyHtmlFormField formField = field.getAnnotation(MyHtmlFormField.class);
            String fieldName = field.getName();
            String fieldType = String.valueOf(field.getType());

            boolean isEnum = field.getType().isEnum();

            htmlForm += "<div class=\"row\">";

            if (isEnum) {
                htmlForm += "<div class=\"col-md-4\">";
                htmlForm += "<label for=\"" + fieldName + "\" class=\"form-label edit-form-label\">" + formField.editLabel() + "</label>";
                htmlForm += "<select class=\"form-select form-select-sm\" id=\"" + fieldName + "\" name=\"" + fieldName + "\">";

                for (Object option : field.getType().getEnumConstants()) {
                    htmlForm += "<option value=\"" + option + "\" " + (getFieldValue(entity, field).equals(option) ? "selected" : "") + ">" + option + "</option>";
                }

                htmlForm += "</select>";
                htmlForm += "</div>";
            } else {
                htmlForm += "<div class=\"col-md-4\">";
                htmlForm += "<label for=\"" + fieldName + "\" class=\"form-label edit-form-label\">" + formField.editLabel() + "</label>";

                if (field.getType() == String.class && fieldName.equals("employeeImage")) {
                    htmlForm += "<input type=\"file\" class=\"edit-form-control edit-form-label\" id=\"" + fieldName + "\" name=\"" + fieldName + "\" value=\"" + getFieldValue(entity, field) + "\">";
                } else {
                    htmlForm += "<input type=\"text\" class=\"edit-form-control \" id=\"" + fieldName + "\" name=\"" + fieldName + "\" value=\"" + getFieldValue(entity, field) + "\">";
                }

                htmlForm += "</div>";
            }

            htmlForm += "</div>";
        }

        htmlForm += "<div class=\"gap-2 p-2 d-flex justify-content-center\">";
        htmlForm += ("<input type=\"hidden\" name=\"action\" value=\"update\"/>"); // Added hidden field
        htmlForm += "<input type=\"submit\" value=\"" + myHtmlForm.editSubmit() + "\" class=\"submit-button\">";
        htmlForm += "</div>";
        htmlForm += "</form>";

        return htmlForm;
    }

    private static Object getFieldValue(Object entity, Field field) {
        try {
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
