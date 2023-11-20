package com.emma.app.model;

import com.emma.database.helper.DbTableColumn;
import com.emma.database.helper.DbTableId;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    @DbTableId
    @DbTableColumn(name = "id", definition = "int")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
