/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollutionapi.dbserver;

import java.util.HashMap;


/**
 *
 * @author igortn
 */
public class QueryResults {
    
    // HTTP response Code
    private String responseCode;
    // Json response body
    private String results;
    
    
    
    public QueryResults (String responseCode, String results) {
        this.responseCode = responseCode;
        this.results = results;
    
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @return the results
     */
    public String getResults() {
        return results;
    }
    
}
