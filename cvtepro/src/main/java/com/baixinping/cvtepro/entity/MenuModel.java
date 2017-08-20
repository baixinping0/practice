package com.baixinping.cvtepro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel {
    private String id;
    private String text;
    private String classes = "folder";
    private boolean hasChildren = true;
}
