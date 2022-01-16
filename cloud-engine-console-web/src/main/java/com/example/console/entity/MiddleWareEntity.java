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
@Table(name = "middle_ware")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class MiddleWareEntity implements Serializable {

    private static final long serialVersionUID = 6532409730548698645L;
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "middle_type",length = 3,nullable = false)
    private String middle_type;

    @Column(name = "instance_id",length = 50,nullable = false)
    private String instance_id;

    @Column(name = "info",length = 255,nullable = false)
    private String info;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="createTime",nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="lastTime",updatable = true,nullable = false)
    private Date lastTime;

}
