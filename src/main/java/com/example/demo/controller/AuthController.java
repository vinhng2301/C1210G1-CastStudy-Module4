package com.example.demo.controller;

import com.example.demo.dto.reponse.JwtResponse;
import com.example.demo.dto.reponse.ResponseMessage;
import com.example.demo.dto.request.ChangePassword;
import com.example.demo.dto.request.EditUserForm;
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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
//@RequestMapping("/api/auth")
//có quyền vào để đăng nhập hoặc đăng ký
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
    @GetMapping("/api/auth/login")
    public String loginForm(){
        return "login";
    }
    @PostMapping("/api/auth/login")
    public String login(@Valid LoginForm loginRequest, HttpSession session){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        AppUserPrinciple userDetails = (AppUserPrinciple) authentication.getPrincipal();
        session.setAttribute("user", userDetails); //dăng lý session user
        ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(),
                userDetails.getUsername(), userDetails.getEmail(), userDetails.getPhone(),
                userDetails.getAuthorities() //cái này là role nhé
        ));
        return "redirect:/home";
    }

    @GetMapping("/api/auth/signup")
    public String signupForm(){  //Model model
//        model.addAttribute("signupForm",new SignUpForm());
        return "signup";
    }
    @PostMapping("/api/auth/signup")
    public String signup(@Valid SignUpForm signUpForm, HttpSession session){
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm) {
        if (appUserService.existsByUsername(signUpForm.getUsername())) {
            new ResponseEntity<>(new ResponseMessage("Username is existed"), HttpStatus.BAD_REQUEST);
            return "redirect:/signup";
        }
        if (appUserService.existsByEmail(signUpForm.getEmail())) {
            new ResponseEntity<>(new ResponseMessage("email is existed"), HttpStatus.BAD_REQUEST);
            return "redirect:/signup";
        }
        AppUser appUser = new AppUser(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), signUpForm.getPhone(), passwordEncoder.encode(signUpForm.getPassword()));
//        Set<String> strRoles = signUpForm.getAppRole();
        Set<AppRole> roles = new HashSet<>();
        AppRole userRole = roleService.findByName(RoleName.USER)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        roles.add(userRole);
//            }
//        });
        // để đây sau a sửa lại mặc định là user, tk admin tự fix trong database
        appUser.setAppRole(roles);
        appUserService.save(appUser);
        AppUserPrinciple userDetails = AppUserPrinciple.build(appUser);  //session chứa AppUserPrinciple
        session.setAttribute("user",userDetails);
        new ResponseEntity<>(new ResponseMessage("registered success"), HttpStatus.OK); // a tra trên mạng là cái dưới , không biết nó có phải chỉ là thông báo không vì nó vẫn tạo đc bt
//        return ResponseEntity.ok(new ResponseMessage("registered success");
        return "redirect:/home";
    }
    @RequestMapping("/user-information")
    public String info(){
        return "user-information";
    }

    @GetMapping("/editUser")
    public String editUserForm(){
        return "editUser";
    }
    @PostMapping("/editUser")
    public String editUser(@Valid EditUserForm editUserForm, HttpSession session){
         AppUserPrinciple userDetails = (AppUserPrinciple) session.getAttribute("user");
         session.removeAttribute("user");
         AppUser appUser =appUserService.findById(userDetails.getUserId());
         appUser.setName(editUserForm.getName());  ///??? tại sao dữ liệu lại được đổi mà trên database chưa đổi nếu không gọi save???
         appUser.setPhone(editUserForm.getPhone());
         appUserService.save(appUser);
         userDetails.setName(editUserForm.getName());
         userDetails.setPhone(editUserForm.getPhone());
         session.setAttribute("user",userDetails);
        return "redirect:/home";
    }
    @GetMapping("/changePassword")
    public String changePasswordForm(){
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid ChangePassword changePassword,HttpSession session){
        AppUserPrinciple userPrinciple= (AppUserPrinciple) session.getAttribute("user");
        AppUser appUser = appUserService.findById(userPrinciple.getUserId());
        if(changePassword.getNumber().equals(session.getAttribute("number"))){
            if((changePassword.getPassword()).equals(changePassword.getRePassword())){
                session.removeAttribute("user");
                session.removeAttribute("number");
                appUser.setPassword(passwordEncoder.encode(changePassword.getPassword()));
                AppUserPrinciple userPrinciple1 = AppUserPrinciple.build(appUser);
                session.setAttribute("user", userPrinciple1);
                return "redirect:/home";
            }
        }
        return "changePassword";
    }
}
