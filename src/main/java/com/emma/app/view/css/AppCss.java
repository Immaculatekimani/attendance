package com.emma.app.view.css;

import java.io.Serializable;

public class AppCss implements Serializable {
    private String style = "<style>" +
            ".topnav {" +
            "   overflow: hidden;" +
            "   background-color: #333;" +
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
            "}" +
            ".topnav a.active {" +
            "   background-color: #4CAF50;" +
            "   color: white;" +
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
            "table tbody tr:nth-child(even) { " +
            "    background-color: #f2f2f2; " +
            "} " +
            "table tbody tr:hover { " +
            "    background-color: #d1d1d1; " +
            "} " +
            "</style> ";

    public String getStyle() {
        return style;
    }
}
