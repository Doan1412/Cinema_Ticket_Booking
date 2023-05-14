//package com.example.cinema_booking_system.Configuration;
//
//import com.example.cinema_booking_system.Model.Account;
//import com.example.cinema_booking_system.Repositories.AccountRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class ApplicationConfig {
//    private final AccountRepository accountRepository;
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> accountRepository.findById(Long.parseLong(username))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(PasswordUtil.passwordEncoder());
//        return authProvider;
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//}
