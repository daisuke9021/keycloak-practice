package keyclock.practice.keycloakpracticeback.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtConverter implements Converter<Jwt, CustomToken> {
    private static final String CLAIM_KEY_ID = "sub";
    private static final String CLAIM_KEY_USERNAME = "preferred_username";
    private static final String CLAIM_KEY_ROLES = "roles";

    @Override
    public CustomToken convert(Jwt token) {

        // リクエスト→(JWT/String)→JwtDecode(String to Jwt)→JwtConvert(Jwt to T implements
        // Authentication)
        // Decodeの時点で、署名が改ざんされていないかチェック＝JwtDecodeを通過すれば認証OK
        // ...Authenticationは「署名検証完了後、Jwt内の情報を@AuthenticationPrincipalで持ち回るためのもの」

        // 1. principal(id,username)の作成 AS CustomPrincipalクラス
        String id = token.getClaimAsString(CLAIM_KEY_ID);
        String name = token.getClaimAsString(CLAIM_KEY_USERNAME);
        CustomPrincipal principal = CustomPrincipal.builder().id(id).name(name).build();

        // 2. authoritiesの作成 AS CustomRoleクラス
        Collection<CustomRole> authorities = Collections.emptyList();
        if (token.getClaims().containsKey(CLAIM_KEY_ROLES)) {
            authorities = token.getClaimAsStringList(CLAIM_KEY_ROLES).stream().map(CustomRole::new)
                    .toList();
        }

        // CustomToken生成
        CustomToken customToken = new CustomToken(authorities, principal);
        // AuthenticationIFで用意されているsetAuthenticated()で更新
        // これをtrueにしないと401エラーで終わる
        customToken.setAuthenticated(true);
        return customToken;
    }

}
