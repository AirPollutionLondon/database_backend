package pollutionapi.model.users;

import pollutionapi.model.sensors.ISensor;
import pollutionapi.model.utils.AGetSet;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a user registering to own sensors.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa,
 *     Carla Delgado
 */
public class User extends AGetSet implements IUser {
    /**
     * Contains information about a user. If a user does not provide a phone number or postcode,
     * default values of -1 and "" (respectively) should be used.
     *
     * @param firstName    first name of user
     * @param lastName     last name of user
     * @param email        email of user
     * @param password     password for this account
     * @param phone        phone number of user
     * @param location     user's postcode
     * @param ownedSensors list of sensors the user owns
     **/
    public User(String email,
                String firstName,
                String lastName,
                String password,
                String location,
                String phone,
                List<ISensor> ownedSensors)
            throws IllegalArgumentException {
        this.fields = new HashMap<>();
        this.fields.put("email", validateEmail(email));
        this.fields.put("firstName", validateFirstName(firstName));
        this.fields.put("lastName", validateLastName(lastName));
        this.fields.put("password", validatePassword(password));
        this.fields.put("phone", phone);
        this.fields.put("location", location);
        this.fields.put("ownedSensors", ownedSensors);
    }

    /**
     * Ensure given first name is valid.
     *
     * @param firstName                     The first name to check
     * @return                              The given first name, if valid
     * @throws IllegalArgumentException     If the given name contains anything that is not a letter
     */
    private String validateFirstName(String firstName) throws IllegalArgumentException {
        if (!firstName.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("invalid first name: " + firstName);
        }
        return firstName.trim();
    }

    /**
     * Ensure given last name is valid.
     *
     * @param lastName                      The last name to check
     * @return                              The given last name, if valid
     * @throws IllegalArgumentException     If the given name contains anything that is not a letter
     */
    private String validateLastName(String lastName) throws IllegalArgumentException {
        if (!lastName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("invalid last name");
        }
        return lastName.trim();
    }

    /**
     * Ensure given email is valid.
     *
     * @param email                         The email to check.
     * @return                              The given email, if valid
     * @throws IllegalArgumentException     If the email contains invalid characters
     */
    private String validateEmail(String email) throws IllegalArgumentException {
        if (!(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"))) {
            throw new IllegalArgumentException("invalid email");
        }
        return email.trim();
    }

    /**
     * Ensure given password is valid.
     *
     * @param password                      The password to check
     * @return                              The given password, if valid
     * @throws IllegalArgumentException     If the password contains invalid characters
     */
    private String validatePassword(String password) throws IllegalArgumentException {
        if (!password.matches("[a-zA-Z0-9_+&*()./-?!]+")) {
            throw new IllegalArgumentException("invalid password: " + password);
        }
        return password.trim();
    }
}

