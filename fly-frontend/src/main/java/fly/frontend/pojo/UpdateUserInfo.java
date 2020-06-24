package fly.frontend.pojo;

public class UpdateUserInfo {
    private String email;
    private String username;
    private String city;
    private String signature;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "UpdateUserInfo{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", city='" + city + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
