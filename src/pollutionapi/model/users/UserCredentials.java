/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollutionapi.model.users;


/**
 *
 * @author igortn
 */
public class UserCredentials {
    private String user;
    private String pwd; 

   
    public UserCredentials (String usr, String pwd) {
        this.user =usr;
        this.pwd = pwd;
        
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
}
