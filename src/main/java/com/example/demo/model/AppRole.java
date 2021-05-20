package com.example.demo.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
public class AppRole implements Serializable  {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long roleId;

    private String roleName;

    public AppRole() {
    }
    public AppRole(Long id, String name) {
        this.roleId = id;
        this.roleName = name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}