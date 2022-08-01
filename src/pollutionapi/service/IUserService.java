package pollutionapi.service;

import pollutionapi.model.users.IUser;
import pollutionapi.model.sensors.SensorInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Represents services that can be done with users.
 *
 * @author Antoine Assaf, Jenny Do, Jessica Fedor, Savvas Panagiotis Nikopoulos, Isabelle Papa
 */
public interface IUserService extends IService {
    /**
     * Add a new user to the database when a new user register.
     *
     * @param newUser a new user
     */
    void addNewUser(IUser newUser);

    /**
     * Validate the given password. Check whether it is associated with the given email or not.
     *
     * @param givenEmail    the given email of the user
     * @param givenPassword the password user enter
     * @return true if the password is correct
     * @throws IllegalArgumentException if the given email doesn't exist
     *                                  or the password is not associated with the given email.
     */
    boolean validateCredentials(String givenEmail, String givenPassword)
            throws IllegalArgumentException;

    /**
     * Update user information, allowing the user to edit information.
     *
     * @param email   user's email
     * @param updates a HashMap with the fields user want to update and their new values
     */
    void updateUser(String email, HashMap<String, Object> updates);

    /**
     * Delete user in user database.
     *
     * @param email user's email
     */
    void deleteUser(String email);

    /**
     * Gets all sensors of given user.
     *
     * @param email given user's email
     * @return all sensors user owns
     */
    List<SensorInfo> getAllSensors(String email);
}