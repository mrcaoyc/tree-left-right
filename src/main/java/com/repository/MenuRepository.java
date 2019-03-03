package com.repository;

import com.model.po.MenuPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author CaoYongCheng
 */
public interface MenuRepository extends JpaRepository<MenuPO, String> {

    /**
     * right大于或等于当前值得菜单的right加二
     *
     * @param value 值
     */
    @Modifying
    @Transactional(rollbackOn = RuntimeException.class)
    @Query(value = "update menu set menu.right=menu.right+2 where menu.right>=?1 ; ", nativeQuery = true)
    void rightAdd(int value);

    /**
     * left大于或等于当前值得菜单的left加二
     *
     * @param value 值
     */
    @Modifying
    @Transactional(rollbackOn = RuntimeException.class)
    @Query(value = "update menu set menu.left=menu.left+2 where menu.left>=?1 ; ", nativeQuery = true)
    void leftAdd(int value);

}
