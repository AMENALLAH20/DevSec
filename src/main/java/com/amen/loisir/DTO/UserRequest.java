package com.amen.loisir.DTO;

import com.amen.loisir.Entities.Role;
import com.amen.loisir.Entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @JsonIgnore(value = false)
    private Long iduser;
    private String fname;
    private String lname;
    private String password;
    private String email;
    private String adresse;

    private String username;

    private Integer age;
    private List<Role> roles;




    public UserRequest(User user) {
            this.iduser= user.getId();
            this.fname = user.getFname();
            this.lname = user.getLname();
            this.email = user.getEmail();
            this.adresse = user.getAdresse();
           this.username = user.getUsername();

        this.age = user.getAge();
            this.roles=user.getRoles();


    }


    public List<Role> getRoles() {
        return (List<Role>) roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}


