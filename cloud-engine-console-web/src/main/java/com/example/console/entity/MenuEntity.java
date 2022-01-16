package com.example.console.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "menu")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MenuEntity implements Serializable {

    private static final long serialVersionUID = -6170670398640454535L;
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "menuName",nullable = false,length = 10)
    private String menuName;

    @Column(name = "icon",length = 20)
    private String icon;

    @Column(name = "index",length = 2)
    private String index;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="createTime",nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="lastTime",updatable = true,nullable = false)
    private Date lastTime;

    @Column(name = "roleJosnlist")
    private String roleJosnlist;
}
