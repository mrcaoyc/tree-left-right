package com.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author CaoYongCheng
 */
@Data
public class MenuDTO {
    private String id;
    private String name;
    private Integer left;
    private Integer right;

    private List<MenuDTO> children;
}
