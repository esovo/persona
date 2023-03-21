package com.ssafy.project.common.db.dto.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
// @MappedSuperclass vs @Embeddable => https://blog.naver.com/PostView.nhn?blogId=qjawnswkd&logNo=222074957987
@MappedSuperclass
// @LastModifiedDate 위해 Auditing 기능 포함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime {

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "register_date", updatable = false)
    private Date registerDate;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "update_date")
    private Date updateDate;
}