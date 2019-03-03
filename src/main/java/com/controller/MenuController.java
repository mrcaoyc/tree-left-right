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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "获取菜单和子菜单")
    @GetMapping("/menus/{id}")
    public HttpEntity<?> getMenuAndChildren(@PathVariable String id) {
        try {
            List<MenuDTO> menuAndChildren = menuService.getMenuAndChildren(id);
            return ResponseEntity.ok(menuAndChildren);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "获取菜单和子菜单的树形结构")
    @GetMapping("/menus/{id}/tree")
    public HttpEntity<?> getMenuAndChildrenTree(@PathVariable String id) {
        try {
            MenuDTO menuDTO = menuService.getMenuAndChildrenTree(id);
            return ResponseEntity.ok(menuDTO);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
