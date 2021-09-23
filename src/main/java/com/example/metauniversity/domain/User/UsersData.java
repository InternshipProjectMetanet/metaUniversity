package com.example.metauniversity.domain.User;

import com.example.metauniversity.domain.Base.BaseEntity;
import com.example.metauniversity.domain.User.dto.userDto.getMyInfoResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "usersData")
@Getter
@DynamicUpdate
public class UsersData {

    @Id
    @Column(name = "userCode")
    private String userCode;

    @JsonIgnore
    @OneToOne(mappedBy = "usersData")
    private User user;

    @Enumerated(EnumType.STRING)
    private UserTyped userType;

    private String userName;
    private String userBirth;
    private String userPhone;
    private String userEmail;
    private String Address;
    private String userDepartment;

    /**
     * Student
     */
    private String userMajor;
    private String userMinor;
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;
    private Integer userGrade;

    /**
     * Worker
     */
    private String workerSpot;
    
    // 개인 정보 수정
    public void updateData(getMyInfoResponse usersdto) {
		this.userName = usersdto.getUserName();
    	this.userBirth = usersdto.getUserBirth();
    	this.userPhone = usersdto.getUserPhone();
    	this.userEmail = usersdto.getUserEmail();
    	this.Address = usersdto.getAddress();
	}
    
    // 휴학, 복학 신청
    public void updateEnroll(EnrollmentStatus enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
}
