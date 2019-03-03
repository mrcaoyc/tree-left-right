package com.controller;

import com.model.dto.MenuDTO;
import com.model.vo.MenuVO;
import com.service.MenuService;
import com.util.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caoyongcheng
 */
@RestController
@Api(description = "菜单")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ApiOperation(value = "在尾部添加子菜单")
    @PostMapping("/menus/append")
    public HttpEntity<?> appendMenu(String parentId, @RequestBody MenuVO menuVO) {
        MenuDTO menuDTO = BeanCopyUtils.copy(menuVO, MenuDTO.class);
        try {
            MenuDTO saveMenuDTO = menuService.appendMenu(menuDTO, parentId);
            return ResponseEntity.ok(saveMenuDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "在头部添加子菜单")
    @PostMapping("/menus/prepend")
    public HttpEntity<?> prependMenu(String parentId, @RequestBody MenuVO menuVO) {
        MenuDTO menuDTO = BeanCopyUtils.copy(menuVO, MenuDTO.class);
        try {
            MenuDTO saveMenuDTO = menuService.prependMenu(menuDTO, parentId);
            return ResponseEntity.ok(saveMenuDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
