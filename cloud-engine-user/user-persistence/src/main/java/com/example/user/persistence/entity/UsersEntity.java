package com.example.user.persistence.entity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="userName")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name = "mobilePhone")
    private String mobilePhone;

    @Column(name = "idNo")
    private String idNo;

    @Column(name = "idType")
    private String idtype;

    @Column(name="email")
    private String email;

    @Column(name ="createTime")
    private Date createTime;

    @Column(name ="lastTime")
    private Date lastTime;

}
