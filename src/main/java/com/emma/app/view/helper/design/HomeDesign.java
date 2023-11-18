package com.emma.app.view.helper.design;

public class HomeDesign {

    public static String design(){

        String homeHTML = " <div class=\"image-container\">" + " <div> " +
                "<img src=\"images/Slide1crop.jpg\" alt=\"Image 1\">" +
                "</div>" +
                "</div>" +
                "          <div class=\"row mb-3\">\n"+
                "<div class=\"col-xl-3 col-md-6 mb-4\">\n" +
                "              <div class=\"card h-100\">\n" +
                "                <div class=\"card-body\">\n" +
                "                  <div class=\"row no-gutters align-items-center\">\n" +
                "                    <div class=\"col mr-2\">\n" +
                "                      <div class=\"text-xs font-weight-bold text-uppercase mb-1\">Employee</div>\n" +
                "                      <div class=\"h5 mb-0 mr-3 font-weight-bold text-gray-800\">Studentsme</div>\n" +
                "                      <div class=\"mt-2 mb-0 text-muted text-xs\">\n" +
                "                        <!-- <span class=\"text-success mr-2\"><i class=\"fas fa-arrow-up\"></i> 20.4%</span>\n" +
                "                        <span>Since last month</span> -->\n" +
                "                      </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-auto\">\n" +
                "                      <i class=\"fas fa-users fa-2x text-info\"></i>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>"+
                "</div>"+"<h1 style=\" color: #3D0C11;\n" +
                "    text-align: center; \"> RECENT ATTENDANCE RECORDS</h1>";
        return homeHTML;

    }


}
