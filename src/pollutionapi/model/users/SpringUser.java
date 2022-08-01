package pollutionapi.model.users;

import pollutionapi.model.sensors.ISensor;

import java.util.ArrayList;

public class SpringUser {
  private String firstName;
  private String lastName;
  private String username;
  private String email;
  private String password;
  private Integer phone;
  private String postcode;

    /**
     *
     * 
     */
    public SpringUser(String fn, String ln, String un, String email, String pwd, Integer ph, String pcode) {
        this.firstName = fn;
        this. lastName = ln;
        this.username= un;
        this.email = email;
        this.password = pwd;
        this.phone = ph;
        this. postcode = pcode;
    }

    public IUser createNewUser() {
        ArrayList<ISensor> s = new ArrayList<ISensor>();
        return new User(this.email, this.firstName, this.lastName, this.password, this.postcode, this.phone, s);
    }

//    String email,
//    String firstName,
//    String lastName,
//    String password,
//    String location,
//    int phone,
//    List<ISensor> ownedSensors

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}