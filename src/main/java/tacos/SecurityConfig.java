package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();    //使用bcrypt强哈希加密
    }

    /*该方法用来配置在web级别该如何处理安全性*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
                .antMatchers("/","/**").permitAll()
                .and().formLogin()
                .and().logout().logoutSuccessUrl("/")
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(encoder());
    }
}
