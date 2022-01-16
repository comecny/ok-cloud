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
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 7781888213546766600L;

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="userName",length = 20,nullable = false)
    private String userName;

    @Column(name="password",length = 255,nullable = false)
    private String password;

    @Column(name = "mobilePhone",length = 11,nullable = false)
    private String mobilePhone;

    @Column(name="email",length = 30)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="createTime",nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="lastTime",updatable = true,nullable = false)
    private Date lastTime;

    @Column(name = "remark",length = 100)
    private String remark;

}
