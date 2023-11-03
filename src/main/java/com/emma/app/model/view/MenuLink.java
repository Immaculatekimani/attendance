package com.emma.app.model.view;

import java.io.Serializable;

public class MenuLink implements Serializable {
    private String link;
    private String label;
    private MenuLinkStatus status;

    public MenuLink(String link, String label, MenuLinkStatus status) {
        this.link = link;
        this.label = label;
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MenuLinkStatus getStatus() {
        return status;
    }

    public void setStatus(MenuLinkStatus status) {
        this.status = status;
    }
}
