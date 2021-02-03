package io.github.douglas2363.cliente.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    //Gerar a chave de assinatura atraves do jwt.signing-key
    @Value("${security.jwt.signig-key}")
    private String signingKey;

    //Conversor de Token
   @Bean
   public TokenStore tokenStore(){
       return new JwtTokenStore(accessTokenConverter());
   }

   @Bean
   public JwtAccessTokenConverter accessTokenConverter(){
       JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
       tokenConverter.setSigningKey(signingKey);
       return tokenConverter;
   }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
       endpoints
               .tokenStore(tokenStore())
               .accessTokenConverter(accessTokenConverter())
               .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       clients
               .inMemory()
               .withClient("cliente-app")
               .secret("@321")
               .scopes("read", "write")
               .authorizedGrantTypes("password")
               .accessTokenValiditySeconds(1800);
    }
}
