package zuhriddinscode;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zuhriddinscode.domain.model.UserEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String password;
    private final String email;
    private final boolean active;
    private final Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(UserEntity user) {
        String userRole = user.getRoles().iterator().next().toString();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userRole));
        return new UserPrincipal(user.getUserid(), user.getPassword(), user.getEmail(), user.isActive(), authorities);
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
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }
}