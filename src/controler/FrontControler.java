/*
 * FrontControler.java
 *
 * Created on February 21, 2007, 4:40 PM
 */

package controler;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class FrontControler extends HttpServlet
{
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String id = request.getRequestURI();
        id = id.substring(request.getContextPath().length());
        getServletContext().log("CORE FrontController received a request for " + id);
        getServletContext().log("CORE FrontController received a " + request.getMethod());
        
        String dest; //destination
        
        // If no request is received or if the request received is
        // for the root, serve /WEB-INF/docs/index.jsp
        if (id == null || id.trim().equals("") || id.equals("/"))
            dest = "/WEB-INF/docs/index.jsp";
        else if(id.equalsIgnoreCase("financial"))
            dest = "/financial";//financial servlet
        else
        {//not accept any request that is not served by the any servlet
            dest = "/WEB-INF/docs/nonesuch.jsp";
            request.setAttribute("nonesuch", id);
        }
        
        //request.setAttribute("page", dest);
        try
        {
            request.getRequestDispatcher(dest).forward(request, response);
        }
        catch(Throwable t)
        {
            getServletContext().log(t.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo()
    {
        return "Short description";
    }
    // </editor-fold>
}
