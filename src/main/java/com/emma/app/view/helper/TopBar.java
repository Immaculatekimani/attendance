package com.emma.app.view.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TopBar implements Menu, Serializable {
    private final List<MenuLink> links = new ArrayList<>();
    private String sessionUsername;

    public void setSessionUsername(String sessionUsername) {
        this.sessionUsername = sessionUsername;
    }

    {
        links.add(new MenuLink("./home", "Home", MenuLinkStatus.ACTIVE));
        links.add(new MenuLink("./attendance-sheet", "Attendance Sheet", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./employee", "Employees", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./reports", "Reports", MenuLinkStatus.NOT_ACTIVE));
    }

    private void activateLink(int link) {
        for (int i = 0; i < links.size(); i++) {
            if (i == link)
                links.get(i).setStatus(MenuLinkStatus.ACTIVE);
            else
                links.get(i).setStatus(MenuLinkStatus.NOT_ACTIVE);
        }
    }

    @Override
    public String menu(int activeMenu) {
        this.activateLink(activeMenu);


        StringBuilder menuBar = new StringBuilder();
        menuBar.append("<nav class=\"navbar  navbar-expand bg-gradient-primary topbar mb-4 static-top\">");
        menuBar.append("<div class=\"sidebar-brand-icon\" >\n");
        menuBar.append("<img class=\"logo-img\" src=\"images/logo.jpg\" alt=\"Logo\">");
        menuBar.append("</div>");
        menuBar.append("<ul class=\"navbar-nav mx-auto\">");

        for (MenuLink link : links) {
            menuBar.append("<li class=\"nav-item ");
            menuBar.append(link.getStatus() == MenuLinkStatus.ACTIVE ? "active" : "");
            menuBar.append("\">");
            menuBar.append("<a class=\"nav-link\" href=\"").append(link.getLink()).append("\">").append(link.getLabel()).append("</a>");
            menuBar.append("</li>");
        }

        menuBar.append("</ul>");
        // Welcome section
        menuBar.append("<li class=\"nav-item dropdown no-arrow\">");
        menuBar.append("<a class=\"nav-link\" href=\"./logout\" id=\"userDropdown\" role=\"button\" ");
        menuBar.append("aria-haspopup=\"true\" aria-expanded=\"false\">");
        menuBar.append("<img class=\"img-profile rounded-circle\" src=\"images/user-icn.png\" style=\"max-width: 60px\">");
        menuBar.append("<span class=\"ml-2 d-none d-lg-inline text-white small\"><b>Logout ").append(sessionUsername).append("</b></span>");
        menuBar.append("</a>");

        menuBar.append("</li>");

        menuBar.append("</nav>");
        return menuBar.toString();
    }
}

