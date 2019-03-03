package com.model.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author CaoYongCheng
 */
@Data
@Entity
@Table(name = "menu")
@GenericGenerator(name="jpa-uuid",strategy = "guid")
public class MenuPO {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String name;
    @Column(name = "`left`")
    private Integer left;
    @Column(name = "`right`")
    private Integer right;
}
