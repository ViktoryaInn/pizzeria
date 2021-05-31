package pizzeria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pizzeria.services.UserService;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //Отключает CSRF Protection, поскольку она не нужна для API
            .authorizeRequests()
                .antMatchers("/", "/auth**", "/js/**", "/error**").permitAll()
                .anyRequest().authenticated() //Декларирует, что все запросы к любой конечной точке должны быть авторизованы, иначе они должны быть отклонены.
            .and().httpBasic() // сообщает Spring, чтобы он ожидал базовую HTTP аутентификацию (обсуждалось выше).
            .and().sessionManagement().disable(); // сообщает Spring, что не следует хранить информацию о сеансе для пользователей, поскольку это не нужно для API
//        http
//            .csrf().disable()
//            .httpBasic()
//            .and()
//            .authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/ingredients/**").hasRole("USER")
//            .antMatchers(HttpMethod.GET, "/ingredients/**").hasRole("ADMIN")
//            .antMatchers(HttpMethod.POST, "/ingredients").hasRole("ADMIN")
//            .antMatchers(HttpMethod.PUT, "/ingredients/**").hasRole("ADMIN")
//            .antMatchers(HttpMethod.DELETE, "/ingredients/**").hasRole("ADMIN")
//            .antMatchers(HttpMethod.GET, "/orders/**").hasRole("ADMIN")
//            .antMatchers(HttpMethod.POST, "/orders").hasRole("USER")
//            .antMatchers(HttpMethod.POST, "/orders").hasRole("ADMIN")
//            .antMatchers(HttpMethod.PUT, "/orders/**").hasRole("ADMIN")
//            .antMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")
//            .and()
//            .sessionManagement().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
