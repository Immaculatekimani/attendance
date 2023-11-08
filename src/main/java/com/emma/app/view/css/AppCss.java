package com.emma.app.view.css;

import java.io.Serializable;

public class AppCss implements Serializable {
    private String style = "<style>" +
            ".topnav {" +
            "   overflow: hidden;" +
            "   background-color: #3D0C11;" +
            "}" +
            ".topnav a {" +
            "   float: left;" +
            "   display: block;" +
            "   color: #f2f2f2;" +
            "   text-align: center;" +
            "   padding: 14px 16px;" +
            "   text-decoration: none;" +
            "}" +
            ".topnav a:hover {" +
            "   background-color: #ddd;" +
            "   color: black;" +
            "   transition: background-color 0.3s;" +
            "}" +
            ".topnav a.active {" +
            "   background-color:  #F9DEC9;" +
            "   color: #068DA9;" +
            "}" +
            ".attendance { " +
            "    margin: 20px; " +
            "    padding: 20px; " +
            "    background-color: #fff; " +
            "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); " +
            "    border-radius: 5px; " +
            "} " +
            "table { " +
            "    width: 100%; " +
            "    border-collapse: collapse; " +
            "    margin-top: 20px; " +
            "    background-color: #C08261; " +
            "    border: 1px solid #ddd; " +
            "} " +
            "table th, table td { " +
            "    border: 1px solid #ddd; " +
            "    padding: 10px; " +
            "    text-align: center; " +
            "} " +
            "table th { " +
            "    background-color: #186F65; " +
            "    color: #fff; " +
            "} " +
            "table tbody tr:nth-child(odd) { " +
            "    background-color: #C08261; " +
            "} " +
            "table tbody tr:hover { " +
            "    background-color: #d1d1d1; " +
            "} " +
            "</style> ";
    private String homeImageCss = "    <style>" +
            "        .image-container {" +
            "            position: relative;" +
            "            max-width: 100%;" +
            "            margin: auto;" +
            "        }" +
            "        img {" +
            "            width: 100%;" +
            "        }" +
            "    </style>";


    private String AddEmployeeFormCss = "<style> .modal {" +
            "  display: none;" +
            "  position: fixed;" +
            "  z-index: 1;" +
            "  left: 0;" +
            "  top: 0;" +
            "  width: 100%;" +
            "  height: 100%;" +
            "  background-color: rgba(0,0,0,0.4);" +
            "}" +
            ".modal-content {" +
            "  background-color: #F9DEC9;" +
            "  border: 1px solid #ccc;" +
            "  border-radius: 5px;" +
            "  padding: 20px;" +
            "  width: 400px;" +
            "  margin: 15% auto;" +
            "  text-align: left;" +
            "  position: relative;" +
            "  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);" +
            "}" +
            ".close {" +
            "  position: absolute;" +
            "  top: 0;" +
            "  right: 0;" +
            "  padding: 10px;" +
            "  cursor: pointer;" +
            "}" +
            ".form-control {" +
            " width: 80%;\n" +
            "    padding: 10px;\n" +
            "    margin: 10px 0;\n" +
            "    border: 1px solid #ccc;\n" +
            "    border-radius: 5px;" +
            "}" +
            ".submit-button {" +
            "  background-color: #068DA9;" +
            "  color: #fff;" +
            "  border: none;" +
            "  padding: 10px 20px;" +
            "  border-radius: 5px;" +
            "  cursor: pointer;" +
            "}" +
            ".submit-button:hover {" +
            "  background-color: #0056b3;" +
            "}</style>";

    public String getAddEmployeeFormCss() {
        return AddEmployeeFormCss;
    }

    public String getStyle() {
        return style;
    }

    public String getHomeImageCss() {
        return homeImageCss;
    }
}
