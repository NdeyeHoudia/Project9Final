package com.openclassrooms.geteway_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private  final  UserDetailsService userDetailsService;
    @Autowired
    private AccountService accountService;
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * PasswordEncoder is Spring Security's interface for encoding and comparing passwords
     * @return an object BCryptPasswordEncoder
     */
    /*@Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * this method is the inetrface of the authentication entry point.
     * its role is to authenticate the user issuing the request
     * @param httpSecurity
     * @param encoder
     * @return  an object Authentication
     * @throws Exception
     */
   /* @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder encoder) throws Exception {
        AuthenticationManagerBuilder managerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
        return  managerBuilder.build();
    }

    /**
     * This method defines the security filter chain for the application.
     * The filter chain specifies various security configurations.
     * It configures authorization rules using authorizeHttpRequests()to allow public access to specific endpoints
     * @param http
     * @return
     * @throws Exception
     */
   /* @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .anyRequest()
                                .authenticated())
                .formLogin(Customizer.withDefaults()).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll())

                .build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
 /*   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                        AppUser user = accountService.loadUserByUsername(username);
                        Collection<GrantedAuthority> authorities = new ArrayList<>();
                        user.getAppRoles().forEach(role -> {
                            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                        });
                        return new User(user.getUsername(),user.getPassword(),authorities);
                    }
                });
    }*/
//}
