package mertbsk.springbootecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// "/api/orders" Bağlantısını korumaya alır
		http.authorizeRequests()
				.antMatchers("/api/orders/**")
				.authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
		
		// CORS Filtrelerini ekler
		http.cors();
		
		// Browser dışındaki kaynakları kullanıma aç
		http.csrf().disable();
		
		// 401 TODO
		Okta.configureResourceServer401ResponseBody(http);
	
	}
}
