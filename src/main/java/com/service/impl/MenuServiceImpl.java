package com.service.impl;

import com.model.dto.MenuDTO;
import com.model.po.MenuPO;
import com.repository.MenuRepository;
import com.service.MenuService;
import com.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author CaoYongCheng
 */
@Service
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public MenuDTO appendMenu(MenuDTO menuDTO, String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            return addRoot(menuDTO);
        }

        MenuPO parent = getParentMenu(parentId);
        menuDTO.setLeft(parent.getRight());
        menuDTO.setRight(parent.getRight() + 1);

        MenuPO menuPO = BeanCopyUtils.copy(menuDTO, MenuPO.class);
        menuRepository.rightAdd(menuDTO.getLeft());
        menuRepository.leftAdd(menuDTO.getLeft());
        MenuPO saveMenuPO = menuRepository.save(menuPO);
        return BeanCopyUtils.copy(saveMenuPO, MenuDTO.class);
    }

    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    public MenuDTO prependMenu(MenuDTO menuDTO, String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            return addRoot(menuDTO);
        }
        MenuPO parent = getParentMenu(parentId);
        menuDTO.setLeft(parent.getLeft() + 1);
        menuDTO.setRight(parent.getLeft() + 2);
        menuRepository.rightAdd(parent.getLeft() + 1);
        menuRepository.leftAdd(parent.getLeft() + 1);
        MenuPO menuPO = BeanCopyUtils.copy(menuDTO, MenuPO.class);
        MenuPO saveMenuPO = menuRepository.save(menuPO);
        return BeanCopyUtils.copy(saveMenuPO, MenuDTO.class);
    }

    /**
     * 获取父菜单
     *
     * @param parentId 父菜单ID
     * @return 父菜单
     */
    private MenuPO getParentMenu(String parentId) {
        Optional<MenuPO> optionalMenuPO = menuRepository.findById(parentId);
        return optionalMenuPO.orElseThrow(() -> new RuntimeException("父菜单不存在"));
    }

    /**
     * 添加根菜单
     *
     * @param menuDTO 根菜单
     * @return 根菜单
     */
    private MenuDTO addRoot(MenuDTO menuDTO) {
        menuDTO.setLeft(1);
        menuDTO.setRight(2);
        MenuPO menuPO = BeanCopyUtils.copy(menuDTO, MenuPO.class);
        MenuPO saveMenuPO = menuRepository.save(menuPO);
        return BeanCopyUtils.copy(saveMenuPO, MenuDTO.class);
    }
}
