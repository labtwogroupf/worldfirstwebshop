package se.iths.worldfirstwebshop.webshop.security;

public class UserDto {
    String name;
    String password;
    Role role;

    public Role getRole() {
        return role;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
