package com.service;

import com.model.dto.MenuDTO;

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
}
