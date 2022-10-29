package egecoskun121.com.crm.config;


import egecoskun121.com.crm.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/login").hasAnyAuthority()
                .antMatchers("/api/v1/product/showList").hasAnyAuthority("ROLE_USER")
                .antMatchers("/api/v1/product/showAllProducts").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .antMatchers("/api/v1/product/showAllProductsById").hasAuthority("ROLE_ADMIN")
                .antMatchers("/h2/**").permitAll()
              //  .antMatchers("api/v1/product/**").hasAuthority("ROLE_USER")
                        .and()
                                .formLogin(form -> form.defaultSuccessUrl("/api/v1/product/showAllProducts")
                                        .loginPage("/login")
                                       );


        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

}
