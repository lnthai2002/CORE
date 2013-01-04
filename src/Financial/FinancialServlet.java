/*
 * FinancialServlet.java
 *
 * Created on February 11, 2007, 10:59 PM
 */

package Financial;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author nhut_le
 * @version
 */
public class FinancialServlet extends HttpServlet
{
    private static final String driver = "com.mysql.jdbc.Driver";
    //private static final String url = "jdbc:mysql://lightportal.no-ip.info/test";//************
    private static final String url = "jdbc:mysql://192.168.2.105/test";//************
    private static final String user = "guest";
    private static final String password = "iamguest";
    private static final String sql = "select * from test.financial where id = ?";
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ServletContext context;
    
    public void init (ServletConfig config) throws ServletException
    {
        super.init(config);
        context = config.getServletContext();
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.prepareStatement(sql);
        }
        catch(ClassNotFoundException e)//no database driver
        {
            System.err.println("Unable to load database driver");
            throw new ServletException("Unable to load database driver");
        }
        catch(SQLException e)
        {
            System.err.println("Unable to connect to database");
            throw new ServletException("Unable to connect to database");
        }
    }
    private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {//puts "record" or "recordList" to request
        System.out.println("<html>");
        String jsp;
        String cmd = req.getParameter("cmd");
        String idString = req.getParameter("id");
        int id;
        try {id = Integer.parseInt(idString);}
        catch(NumberFormatException e) {id = 0;};
        
        if("get".equals(cmd))
        {
            TransactionBean bean = fetchTransaction(id);
            req.setAttribute("record", bean);
            jsp = "/WEB-INF/docs/displayRecord.jsp";
        }
        else
        {
            Vector list = fetchAllTransactions();
            req.setAttribute("recordList", list);
            jsp = "/WEB-INF/docs/displayRecordList.jsp";
        }
        RequestDispatcher dispatcher;
        dispatcher = context.getRequestDispatcher(jsp);
        dispatcher.forward(req, res);
    }
    
    public TransactionBean makeBean(ResultSet result) throws SQLException
    {   //transaction.id is set by constructor, not by mutator
        TransactionBean bean = new TransactionBean(result.getInt("id"));
        bean.setDate(result.getString("date"));
        bean.setAmount(result.getInt("amount"));
        bean.setCurrency(result.getString("currency"));
        bean.setNote(result.getString("note"));
        return bean;
    }
    
    public TransactionBean fetchTransaction(int id)
    {
        try
        {
            ResultSet results;
            synchronized(statement)
            {
                statement.clearParameters();
                statement.setInt(1, id);
                results = statement.executeQuery();
            }
            TransactionBean bean = null;
            if (results.next())
                bean = makeBean(results);
            if (results != null)
                results.close();
            return bean;
        }
        catch(SQLException se){return null;}
    }
    
    public Vector fetchAllTransactions()
    {
        try
        {
            Vector list = new Vector();
            ResultSet results;
            Statement st = connection.createStatement();
            results = st.executeQuery("select * from financial");
            while (results.next())
                list.add(makeBean(results));
            return list;
        }
        catch(SQLException se) {return null;}
    }
    
    public void destroy()
    {//close connection
        try
        {
            if (connection != null)
                connection.close();
        }
        catch(SQLException se) {/*do nothing*/}
    }
            
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        String id = request.getRequestURI();
        id = id.substring(request.getContextPath().length());
        getServletContext().log("FinancialServlet received a request for " + id);
        
        String dest; //destination
        
        // If no request is received or if the request received is
        // for the root, serve /WEB-INF/docs/index.jsp
        if (id == null || id.trim().equals("") || id.equals("/"))
        {    dest = "/WEB-INF/docs/index.jsp";
            try
            {
                request.getRequestDispatcher(dest).forward(request, response);
            }
            catch(Throwable t)
            {
                getServletContext().log(t.getMessage());
            }
        }
        else if(id.equalsIgnoreCase("financial"))
            //dest = "/financial";//financial servlet
            process(request, response);
        else
        {//not accept any request that is not served by the any servlet
            dest = "/WEB-INF/docs/nonesuch.jsp";
            request.setAttribute("nonesuch", id);
            try
            {
                request.getRequestDispatcher(dest).forward(request, response);
            }
            catch(Throwable t)
            {
                getServletContext().log(t.getMessage());
            }  
        }
        //request.setAttribute("page", dest);      
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