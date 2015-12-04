package servlets;

import datastore.DAOSQLite;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VideoGame;

/**
 * All of this application's web pages send their requests to this controller
 * which then updates the model / database as needed and transfers control with
 * data to one the the HTML/JSP view-oriented programs for display.
 *
 * @author John Phillips
 */
public class Controller extends HttpServlet {

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

        // get real path to the sqlite db
        ServletContext sc = this.getServletContext();
        String dbPath = sc.getRealPath("/WEB-INF/games.db");

        // set default url
        String url = "/home.html";

        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }

        // perform action and set url
        if (action.equalsIgnoreCase("home")) {
            url = "/home.html";

        } else if (action.equalsIgnoreCase("createRecord")) {
            // get parameters passed in from the request
            String name = request.getParameter("name");
            String rating = request.getParameter("rating");
            String date = request.getParameter("date");
            String completion = request.getParameter("completion");
            String comments = request.getParameter("comments");

            // store data in an Employee object
            VideoGame game = new VideoGame(0, name, rating, date, completion, comments);

            // validate the parameters
            if (name == null || rating == null || date == null || completion == null || comments == null ||
                    name.isEmpty() || rating.isEmpty() || date.isEmpty() || completion.isEmpty()) {
                url = "/createRecord.jsp";
            } else {
                // insert this data record into the database
                DAOSQLite.createRecord(game, dbPath);
                url = "/home.html";
            }

            // add the user object to the request object so that the data is available on the next page
            request.setAttribute("game", game);
            
        } else if (action.equalsIgnoreCase("report")) {
            List<VideoGame> mydata = null;
            mydata = DAOSQLite.retrieveAllRecordsByName(dbPath);
            request.setAttribute("mydata", mydata);
            url = "/displayRecords.jsp";

        } else if (action.equalsIgnoreCase("updateRecord1")) {
            List<VideoGame> mydata = null;
            mydata = DAOSQLite.retrieveAllRecordsByName(dbPath);
            request.setAttribute("mydata", mydata);
            String idString = request.getParameter("Id");
            if (idString == null || idString.isEmpty()) {
                url = "/updateRecord1.jsp";
            } else {
                // get employee
                VideoGame game = DAOSQLite.retrieveRecordById(Integer.parseInt(idString), dbPath);
                request.setAttribute("game", game);
                url = "/updateRecord2.jsp";
            }

        } else if (action.equalsIgnoreCase("updateRecord2")) {
            int id;

            // get parameters passed in from the request
            String idString = request.getParameter("ID");
            String name = request.getParameter("Name");
            String rating = request.getParameter("Rating");
            String date = request.getParameter("Date");
            String completion = request.getParameter("Completion");
            String comments = request.getParameter("Comments");

            // validate and convert empId string into an int
            if (idString == null || idString.isEmpty()) {
                id = 0;
            } else {
                id = Integer.parseInt(idString);
            }

            // store data in an Employee object
            VideoGame game = new VideoGame(id, name, rating, date, completion, comments);

            // validate the parameters
            if (name == null || rating == null || date == null || completion == null || comments == null ||
                    name.isEmpty() || rating.isEmpty() || date.isEmpty() || completion.isEmpty()) {
                request.setAttribute("game", game);
                url = "/updateRecord2.jsp";
            } else {
                // update this record in the database
                DAOSQLite.updateRecord(game, dbPath);
                url = "/home.html";
            }

        } else if (action.equalsIgnoreCase("deleteRecord")) {
            List<VideoGame> mydata = null;
            mydata = DAOSQLite.retrieveAllRecordsByName(dbPath);
            request.setAttribute("mydata", mydata);
            String idString = request.getParameter("ID");
            if (idString == null || idString.isEmpty()) {
                url = "/deleteRecord.jsp";
            } else {
                // delete this data record from the database
                DAOSQLite.deleteRecord(Integer.parseInt(idString), dbPath);
                url = "/home.html";
            }
            
        } else if (action.equalsIgnoreCase("makeDB")) {
            DAOSQLite.dropTable(dbPath);
            DAOSQLite.createTable(dbPath);
            //DAOSQLite.populateTable(dbPath);
            url = "/home.html";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controller for Moment App";
    }// </editor-fold>

}
