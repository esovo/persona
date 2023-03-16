package com.ssafy.project.common.security.common;

import com.ssafy.project.common.db.entity.base.RoleEnum;
import com.ssafy.project.common.db.entity.common.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {

    private long id;
    private List<String> authorities;

    // CliENT 권한을 가진 principal 생성
    public static UserPrincipal create(User user) {

        List<String> authorities =
                Collections.singletonList("" + RoleEnum.CLIENT);
        user.getAuthorities().add("" + RoleEnum.CLIENT);
        return new UserPrincipal(
                user.getId(),
                authorities,
                null
        );
    }

    // 권한 정보가 담기지 않은 principal 생성
    public static UserPrincipal loadDefault(User user) {

        return new UserPrincipal(
                user.getId(),
                null,
                null
        );
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    public List<String> getAuthoritiesToList() {
        return this.authorities;
    }

    // 아래부터 미 사용
    private Map<String, Object> attributes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

/*    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }*/
}