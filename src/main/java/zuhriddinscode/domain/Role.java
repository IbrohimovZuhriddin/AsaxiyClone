package zuhriddinscode.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MODERATOR,
    ROLE_MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}