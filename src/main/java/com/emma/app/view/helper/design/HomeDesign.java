package com.emma.app.view.helper.design;

import com.emma.app.bean.EmployeeBeanI;
import com.emma.app.model.Employee;
import com.emma.app.model.EmployeeRole;

import javax.inject.Named;

@Named("home")
public class HomeDesign implements DesignI {
    @Override
    public String designer() {
        return null;
    }

    public String designer(EmployeeBeanI employeeBean) {

        int noOfEmployees = employeeBean.countRecords(Employee.class, "");
        int noOfQA = employeeBean.countRecords(Employee.class, "role = ?", EmployeeRole.QA.name());
        int noOfDevops = employeeBean.countRecords(Employee.class, "role = ?", EmployeeRole.DEVOPS.name());
        int noOfDeveloper = employeeBean.countRecords(Employee.class, "role = ?", EmployeeRole.DEVELOPER.name());

        String homeHTML = " <div class=\"image-container\">" + " <div> " +
                "<img src=\"images/Slide1crop.jpg\" alt=\"Image 1\">" +
                "</div>" +
                "</div>" +
                "          <div class=\"row mb-3\">\n" +
                "<div class=\"col-xl-3 col-md-6 mb-4\">\n" +
                "              <div class=\"card h-100\">\n" +
                "                <div class=\"card-body\">\n" +
                "                  <div class=\"row no-gutters align-items-center\">\n" +
                "                    <div class=\"col mr-2\">\n" +
                "                      <div class=\"text-xs font-weight-bold text-uppercase mb-1\">Employees</div>\n" +
                "                      <div class=\"h5 mb-0 mr-3 font-weight-bold text-gray-800\">" + noOfEmployees + "</div>\n" +
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
                "            </div>" +
                " <div class=\"col-xl-3 col-md-6 mb-4\">\n" +
                "              <div class=\"card h-100\">\n" +
                "                <div class=\"card-body\">\n" +
                "                  <div class=\"row align-items-center\">\n" +
                "                    <div class=\"col mr-2\">\n" +
                "                      <div class=\"text-xs font-weight-bold text-uppercase mb-1\">Developer</div>\n" +
                "                      <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + noOfDeveloper + "</div>\n" +
                "                      <div class=\"mt-2 mb-0 text-muted text-xs\">\n" +
                "                        <!-- <span class=\"text-success mr-2\"><i class=\"fa fa-arrow-up\"></i> 3.48%</span>\n" +
                "                        <span>Since last month</span> -->\n" +
                "                      </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-auto\">\n" +
                "                      <i class=\"fas fa-chalkboard fa-2x text-primary\"></i>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>" +
                "           <div class=\"col-xl-3 col-md-6 mb-4\">\n" +
                "              <div class=\"card h-100\">\n" +
                "                <div class=\"card-body\">\n" +
                "                  <div class=\"row no-gutters align-items-center\">\n" +
                "                    <div class=\"col mr-2\">\n" +
                "                      <div class=\"text-xs font-weight-bold text-uppercase mb-1\">Devops</div>\n" +
                "                      <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + noOfDevops + "</div>\n" +
                "                      <div class=\"mt-2 mb-0 text-muted text-xs\">\n" +
                "                        <!-- <span class=\"text-success mr-2\"><i class=\"fas fa-arrow-up\"></i> 12%</span>\n" +
                "                        <span>Since last years</span> -->\n" +
                "                      </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-auto\">\n" +
                "                      <i class=\"fas fa-code-branch fa-2x text-success\"></i>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "           <div class=\"col-xl-3 col-md-6 mb-4\">\n" +
                "              <div class=\"card h-100\">\n" +
                "                <div class=\"card-body\">\n" +
                "                  <div class=\"row no-gutters align-items-center\">\n" +
                "                    <div class=\"col mr-2\">\n" +
                "                      <div class=\"text-xs font-weight-bold text-uppercase mb-1\">QA</div>\n" +
                "                      <div class=\"h5 mb-0 font-weight-bold text-gray-800\">" + noOfQA + "</div>\n" +
                "                      <div class=\"mt-2 mb-0 text-muted text-xs\">\n" +
                "                        <!-- <span class=\"text-danger mr-2\"><i class=\"fas fa-arrow-down\"></i> 1.10%</span>\n" +
                "                        <span>Since yesterday</span> -->\n" +
                "                      </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-auto\">\n" +
                "                      <i class=\"fas fa-calendar fa-2x text-warning\"></i>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>" +
                "</div>" +


                "<h1 style=\" color: #3D0C11;\n" +
                "    text-align: center; \"> RECENT ATTENDANCE RECORDS</h1>";
        return homeHTML;

    }


}
