package keyclock.practice.keycloakpracticeback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeycloakPracticeBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakPracticeBackApplication.class, args);
	}

	// JwtDecoderインタフェースに紐づくBeanがDIコンテナに登録されていない場合に、こいつをDIコンテナに登録する
	// @ConditionalOnMissingBean
	// @Bean
	// public JwtDecoder manageJwtDecoder() {
	// // ${host}/auth/realms/${realm}
	// return
	// JwtDecoders.fromIssuerLocation("http://localhost:8180/auth/realms/user");
	// }

}
