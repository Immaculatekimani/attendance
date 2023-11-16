package com.emma.app.view.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TopBar implements Menu, Serializable {
    private final List<MenuLink> links = new ArrayList<>();

    {
        links.add(new MenuLink("./home", "Home", MenuLinkStatus.ACTIVE));
        links.add(new MenuLink("./attendance-sheet", "Attendance Sheet", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./employee", "Employees", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./reports", "Reports", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./logout", "Logout", MenuLinkStatus.NOT_ACTIVE));
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
        String menuBar = "<nav class=\"topnav\">";

        for (MenuLink link : links)
            menuBar += "<a " + (link.getStatus() == MenuLinkStatus.ACTIVE ? "class=\"active\"" : "")
                    + " href=\"" + link.getLink() + "\">" + link.getLabel() + "</a>";

        menuBar += "</nav>";
        return menuBar;
    }
}
