package ua.com.coach.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.com.coach.entity.User;

/**
 * Web security configuration.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //Lombok генерує конструктор.. +++++
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The {@link AppConfig}.
     */
    private final AppConfig appConfig;

    /**
     * The role of an administration.
     */
    private static final String ADMIN_ROLE = User.Role.ADMIN.name();

    /**
     * The role of a customer.
     */
    private static final String USER_ROLE = User.Role.USER.name();

    /**
     * Configures an {@link AuthenticationManagerBuilder}.
     *
     * @param  auth      the {@link AuthenticationManagerBuilder}
     * @throws Exception if an error occurs when adding the JDBC authentication
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
            .dataSource(appConfig.dataSource())
//            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("select email, password, active"
                    + "from User "
                    + "where email = ?")
            .authoritiesByUsernameQuery("select email, role "
                    + "from User "
                    + "where email = ?");
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors();

        httpSecurity

//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();


//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/log").fullyAuthenticated()
//                .and().httpBasic();


                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .anyRequest().authenticated()
                                                                                  //+
                .and()
                .httpBasic()

                .and()
                .logout().permitAll();



//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/registration").permitAll()
//                .antMatchers("/user/balance").hasAnyAuthority(CUSTOMER_ROLE, CUSTOMER_ADMIN_ROLE)
//                .antMatchers("/menu/*").permitAll()
//                .antMatchers("/menu/**").hasAuthority(PROVIDER_ROLE)
//                .antMatchers("/dish/**").hasAuthority(PROVIDER_ROLE)
//                .antMatchers("/order/**").authenticated()
//                .antMatchers("/order/provider/**").hasAuthority(PROVIDER_ROLE)
//                .anyRequest().hasAuthority(ADMIN_ROLE)
//                .and()
//                .logout()
//                .permitAll()
//
//                .and()
//                .httpBasic()
//
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Returns a {@link BCryptPasswordEncoder}.
     *
     * @return the {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
