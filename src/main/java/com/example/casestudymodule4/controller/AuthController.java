package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.dto.reponse.JwtResponse;
import com.example.casestudymodule4.dto.reponse.ResponseMessage;
import com.example.casestudymodule4.dto.request.LoginForm;
import com.example.casestudymodule4.dto.request.SignUpForm;
import com.example.casestudymodule4.model.AppUser;
import com.example.casestudymodule4.model.Role;
import com.example.casestudymodule4.model.RoleName;
import com.example.casestudymodule4.security.appuserprincal.AppUserPrinciple;
import com.example.casestudymodule4.security.jwt.JwtProvider;
import com.example.casestudymodule4.service.impl.AppUserServiceImpl;
import com.example.casestudymodule4.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AppUserServiceImpl appUserService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest, HttpSession session) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        AppUserPrinciple userDetails = (AppUserPrinciple) authentication.getPrincipal();
        session.setAttribute("user", userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),
                userDetails.getName(), userDetails.getEmail(), userDetails.getPhoneNumber(),
                userDetails.getAuthorities()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm) {
        if (appUserService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Username is existed"), HttpStatus.BAD_REQUEST);
        }
        if (appUserService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("email is existed"), HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = new AppUser(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), signUpForm.getPhoneNumber(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {

                    Role userRole = roleService.findByName(RoleName.USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);

        });
        appUser.setRoles(roles);
        appUserService.save(appUser);
        return new ResponseEntity<>(new ResponseMessage("registered success"), HttpStatus.OK);
    }

    //    @DeleteMapping("/logout")
//public class AllCookieClearingLogoutConfiguration extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .logout(logout -> logout
//                        .logoutUrl("/cookies/cookielogout")
//                        .addLogoutHandler((request, response, auth) -> {
//                            for (Cookie cookie : request.getCookies()) {
//                                String cookieName = cookie.getName();
//                                Cookie cookieToDelete = new Cookie(cookieName, null);
//                                cookieToDelete.setMaxAge(0);
//                                response.addCookie(cookieToDelete);
//                            }
//                        })
//                );
//    }
//}

}
