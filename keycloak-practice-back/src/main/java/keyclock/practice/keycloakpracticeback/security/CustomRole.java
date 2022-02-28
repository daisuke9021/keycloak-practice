package keyclock.practice.keycloakpracticeback.security;

import org.springframework.security.core.GrantedAuthority;

public class CustomRole implements GrantedAuthority {

    private String role;

    public CustomRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        // hasRole()でチェックするときに「ROLE_」をprefixとして付与されるように。
        return "ROLE_" + role;
    }

}
