package com.unicauca.gestion.Infrastucture.Output.Persistence.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
public class UserEntity implements UserDetails{
    
    @Id
    private long idUser;
    private String names;
    private String lastNames;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;
    private long numberPhone;

    private String state;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_user",
        joinColumns = @JoinColumn(name = "idUser"),
        inverseJoinColumns = @JoinColumn(name = "idRole"))
    private List<RoleEntity> roles;

    public UserEntity(){
        this.roles = new ArrayList<>();
    }

    public UserEntity(long idUser,String names,String lastNames,String email){
        this.idUser = idUser;
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
        
}

