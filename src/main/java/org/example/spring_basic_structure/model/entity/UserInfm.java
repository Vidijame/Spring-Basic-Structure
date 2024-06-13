package org.example.spring_basic_structure.model.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;
import org.example.spring_basic_structure.common.GenericEntity;
import org.example.spring_basic_structure.helper.GenerateNextId;
import org.example.spring_basic_structure.model.request.UserInfmReq;
import org.example.spring_basic_structure.model.response.UserInfmRes;
import org.example.spring_basic_structure.utils.DateTimeUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "UserInfm")
@Name("UIF")

public class UserInfm extends GenericEntity<UserInfmReq, UserInfmRes> implements UserDetails {

    @Id
    @GeneratedValue(generator = "usr-id")
    @GenericGenerator(name = "usr-id", type = GenerateNextId.class)
    private String usrId;
    @Column(unique = true)
    private String eml;
    private String usrPwd;
    private String tel;
    private String regId;
    private String regDtm;
    private String modId;
    private String modDtm;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "usrId"))
    private Set<String> roles;

    @PrePersist
    public void setRegDtm() {
        this.regDtm = DateTimeUtils.formatLocalDateTime(LocalDateTime.now(), DateTimeUtils.PATTERN_DTM_14);
        this.regId = this.eml;
    }

    @PreUpdate
    public void setModDtm() {
        this.modDtm = DateTimeUtils.formatLocalDateTime(LocalDateTime.now(), DateTimeUtils.PATTERN_DTM_14);
        this.modId = this.eml;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return usrPwd;
    }

    @Override
    public String getUsername() {
        return eml;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void toEntity(UserInfmReq userInfmReq) {
        setUsrId(usrId);
        setEml(userInfmReq.getEml());
        setTel(userInfmReq.getTel());
    }

    @Override
    public UserInfmRes toResponse() {
        return UserInfmRes.builder().eml(eml).tel(tel).regId(regId).regDtm(DateTimeUtils.formatCustomDateTime(regDtm)).modId(modId).modDtm(modDtm != null ? DateTimeUtils.formatCustomDateTime(modDtm) : null).build();
    }
}
