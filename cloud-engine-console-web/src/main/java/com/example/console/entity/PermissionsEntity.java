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
@Table(name = "permissions")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class PermissionsEntity implements Serializable {

    private static final long serialVersionUID = -5029162321671623630L;

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "permission",length = 20,nullable = false)
    private String permission;

    @Column(name = "permission_description",length = 20)
    private String permissionDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="createTime",nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="lastTime",updatable = true,nullable = false)
    private Date lastTime;

}
