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
@Table(name = "roles")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class RolesEntity implements Serializable {

    private static final long serialVersionUID = 3515994990455163721L;

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "roles",length = 20,nullable = false)
    private String role;

    @Column(name = "role_description",length = 20)
    private String roleDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="createTime",nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="lastTime",updatable = true,nullable = false)
    private Date lastTime;

    @Column(name = "permissionJosnlist")
    private String permissionJosnlist;
}
