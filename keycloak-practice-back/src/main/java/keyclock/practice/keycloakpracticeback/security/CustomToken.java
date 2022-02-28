package keyclock.practice.keycloakpracticeback.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

public class CustomToken extends AbstractAuthenticationToken {

    public CustomToken(Collection<? extends GrantedAuthority> authorities, CustomPrincipal principal) {
        // AbstractAuthenticationTokenコンストラクタ に List<GrantedAuthority> を追加する
        super(authorities);
        this.principal = principal;
    }

    // Overrideしないといけないやつ1：Authentication.getPrincipal()
    // Overrideしないといけないやつ2：Authentication.getCredentials()
    private CustomPrincipal principal;

    @Override
    public CustomPrincipal getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

}
