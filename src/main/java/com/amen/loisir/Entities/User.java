package com.amen.loisir.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class User implements  UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fname;
    private String lname;
    private String password;
    private String email;
    private String adresse;
    private String username;
    private int age;



    @Enumerated(EnumType.STRING)
    @ManyToMany(cascade = CascadeType.PERSIST)  // Cascade persist
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))


    private List<Role> roles;

   /* @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Activite> activities;*/

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore

    private List<Activite> activities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Reservation> reservations;






    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public User(Long iduser, String fname, String lname, String password, String confirmPassword, String email,
                String adresse, String username, List<Role> roles) {
        super();
        this.id = iduser;
        this.fname = fname;
        this.lname = lname;
        this.password = password;

        this.email = email;
        this.adresse = adresse;
        this.username = username;
        this.roles = roles;

    }


    public User() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Long getIduser() {
        return id;
    }


    public void setIduser(Long iduser) {
        this.id = iduser;
    }


    public String getFname() {
        return fname;
    }


    public void setFname(String fname) {
        this.fname = fname;
    }


    public String getLname() {
        return lname;
    }


    public void setLname(String lname) {
        this.lname = lname;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getAdresse() {
        return adresse;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    public void setUsername(String username) {
        this.username = username;
    }







    @Override
    public String toString() {
        return "User [iduser=" + id + ", fname=" + fname + ", lname=" + lname + ", password=" + password
                + ", email=" + email + ", adresse=" + adresse + ", username="
                + username + ", listroles=" + roles + "]";
    }

    public void addRole(Role role)
    {
        this.roles.add(role);
    }
    public List<Role> getRoles() {
        return (List<Role>) roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (this.roles != null) {
            for (Role role : this.roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleType().name())); // Assurez-vous que RoleType est une enum
            }
        }
        return authorities;
    }



}










