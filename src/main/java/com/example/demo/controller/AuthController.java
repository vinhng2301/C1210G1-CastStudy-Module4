package com.example.demo.controller;

import com.example.demo.dto.reponse.JwtResponse;
import com.example.demo.dto.reponse.ResponseMessage;
import com.example.demo.dto.request.LoginForm;
import com.example.demo.dto.request.SignUpForm;
import com.example.demo.model.AppUser;
import com.example.demo.model.AppRole;
import com.example.demo.model.RoleName;
import com.example.demo.security.appuserprincipal.AppUserPrinciple;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.service.UserService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService appUserService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest, HttpSession session) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getAccount(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        AppUserPrinciple userDetails = (AppUserPrinciple) authentication.getPrincipal();
        session.setAttribute("user", userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getAccount(),
                userDetails.getUsername(), userDetails.getEmail(), userDetails.getPhone(),
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
        AppUser appUser = new AppUser(signUpForm.getUsername(), signUpForm.getAccount(), signUpForm.getEmail(), signUpForm.getPhone(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getAppRole();
        Set<AppRole> roles = new HashSet<>();
        strRoles.forEach(role -> {

            AppRole userRole = roleService.findByName(RoleName.USER)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
            roles.add(userRole);

        });
        appUser.setAppRole(roles);
        appUserService.save(appUser);
        return new ResponseEntity<>(new ResponseMessage("registered success"), HttpStatus.OK);
    }

//    @DeleteMapping("/logout")
//    public class AllCookieClearingLogoutConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.logout(logout -> logout
//                    .logoutUrl("/cookies/cookielogout")
//                    .addLogoutHandler((request, response, auth) -> {
//                        for (Cookie cookie : request.getCookies()) {
//                            String cookieName = cookie.getName();
//                            Cookie cookieToDelete = new Cookie(cookieName, null);
//                            cookieToDelete.setMaxAge(0);
//                            response.addCookie(cookieToDelete);
//                        }
//                    }));
//        }
//    }

}
