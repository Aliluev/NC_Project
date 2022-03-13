package com.store.model;
import com.store.dto.UserDTO;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name="roleid")
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "userid",cascade = CascadeType.ALL)
    private List<Order> order;

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, phone, email, roles);
    }

    public User(String username, String phone, String email) {
        this.username = username;
        this.phone = phone;
        this.email = email;
    }

    public User(UserDTO userDTO){
        this.username=userDTO.getUsername();
        this.phone=userDTO.getPhone();
        this.email=userDTO.getEmail();
    }

    public User(SignupRequest signupRequest){

        this.username=signupRequest.getUsername();
        //this.password Пароль установить с шифрованием с помощью сервиса!!!
        this.email=signupRequest.getEmail();
    }


    public User() {
    }
}
