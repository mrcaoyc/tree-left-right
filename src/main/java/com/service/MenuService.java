package com.service;

import com.model.dto.MenuDTO;

import java.util.List;

/**
 * @author CaoYongCheng
 */
public interface MenuService {

    /**
     * 在子菜单末尾添加子菜单
     *
     * @param menuDTO  菜单
     * @param parentId 父菜单ID
     * @return 菜单
     */
    MenuDTO appendMenu(MenuDTO menuDTO, String parentId);

    /**
     * 在子菜单头部添加子菜单
     *
     * @param menuDTO  菜单
     * @param parentId 父菜单ID
     * @return 菜单
     */
    MenuDTO prependMenu(MenuDTO menuDTO, String parentId);

    /**
     * 获取菜单及其子菜单
     *
     * @param id 菜单ID
     * @return 菜单列表
     */
    List<MenuDTO> getMenuAndChildren(String id);

    /**
     * 获取菜单及其子菜单树形结构
     *
     * @param id 菜单ID
     * @return 菜单树
     */
    MenuDTO getMenuAndChildrenTree(String id);
}
