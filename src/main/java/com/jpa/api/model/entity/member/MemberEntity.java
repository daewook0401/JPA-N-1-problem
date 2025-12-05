package com.jpa.api.model.entity.member;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa.api.model.entity.base.BaseEntity;
import com.jpa.api.model.enums.MemberStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "tb_member")
public class MemberEntity extends BaseEntity {
    

    @Column(nullable = false, unique = true, length = 30)
    private String username;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    
    @Column(nullable = false, length = 100)
    private String password;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(name = "phone_number", unique = true, nullable = false, length = 20)    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberStatus status;

}
