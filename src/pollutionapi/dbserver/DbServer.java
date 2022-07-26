/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pollutionapi.dbserver;

import pollutionapi.model.sensors.*;
import pollutionapi.model.users.*;
import pollutionapi.service.UserService;
import pollutionapi.service.SensorService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igortn
 */
@WebServlet(name = "DbServer", urlPatterns = {"/db/*"})
public class DbServer extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request to /user
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         // get the command to execute and the request payload from the request
        String dbCommand = request.getPathInfo();
        String jsonPayLoad = getPayLoad (request);

        Gson gson = new Gson();
        UserService userService = new UserService();
        SensorService sensorService = new SensorService();

        switch (dbCommand) {
            case "/login":
                try {
                    UserCredentials creds = gson.fromJson(jsonPayLoad, UserCredentials.class);
                    userService.validateCredentials(creds.getUser(), creds.getPwd());
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("User is valid");
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println(ex.getMessage());
                    response.getWriter().println("User/pwd is not valid");
                }
                break;
            case  "/newsensorreading":
                try {
                    ISensorReading sensorReading =
                            gson.fromJson(jsonPayLoad, SpringSensorReading.class).createSensorReading();
                    sensorService.addSensorReading(sensorReading);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("Sensor Reading added");
                 } catch (Exception ex) {
                           response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println(ex.getMessage());
                    response.getWriter().println("Sensor reading not added");
                }
      
                break;
            case  "/newsensor":
                try {
                    ISensor sensor = gson.fromJson(jsonPayLoad, SpringSensor.class).createSensorInfo();
                    sensorService.addNewSensor(sensor);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("Sensor added");
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println(ex.getMessage());
                    response.getWriter().println("Adding sensor failed");
                }
                break;
            case "/newuser":
                try {
                    IUser user = gson.fromJson(jsonPayLoad, SpringUser.class).createNewUser();
                    userService.addNewUser(user);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("User added");
                } catch (Exception ex) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().println(ex.getMessage());
                    response.getWriter().println("adding new user failed");
                }
                break;
            case  "/":
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("Error: not a valid endpoint.");
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Database operation is not REALLY recognized");
                
        }


 /*       response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DbServer</title>");            
            out.println("</head>");
            out.println("<body>");
             out.println("<h1>Servlet DbServer  path info at " + dbCommand + " </h1>");
            out.println("<h1>Servlet DbServer context path at " + request.getContextPath() + " </h1>");
          //  out.println("<h1>Servlet DbServer at " + creds.getUser() + "</h1>");

            out.println("</body>");
            out.println("</html>");
        }
        */
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
   private String getPayLoad (HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }    
        } catch (IOException ex) {
            Logger.getLogger(DbServer.class.getName()).log(Level.SEVERE, null, ex);
        }  finally {
            reader.close();
        }
        // System.out.println(sb.toString());
        return sb.toString();
       
   }

}