/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecciondtojpa.javaafondojpa;

import com.proyecciondtojpa.javaafondojpa.dao.EmpleadoJpaController;
import com.proyecciondtojpa.javaafondojpa.dao.ProyectoJpaController;
import com.proyecciondtojpa.javaafondojpa.modelo.Empleado;
import com.proyecciondtojpa.javaafondojpa.modelo.Proyecto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pryet
 */
@WebServlet(name = "PreIndex", urlPatterns = {"/preIndex"})
public class PreIndex extends HttpServlet {
    @Inject
    EmpleadoJpaController empDAO;
    @Inject
    ProyectoJpaController proyectoDAO;

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
       Empleado e=new Empleado("paco");
       Empleado e2=new Empleado("maria");
        empDAO.create(e);
        empDAO.create(e2);
        Proyecto p=new Proyecto("rapar pies");
        Proyecto p2=new Proyecto("sacar papas");
        proyectoDAO.create(p);
        proyectoDAO.create(p2);
        e.addProyecto(p);
        e2.addProyecto(p);
        e.addProyecto(p2);
        request.setAttribute("empleado", e);
        request.setAttribute("proyecto", p);
        request.getRequestDispatcher("./index.jsp").forward(request, response);
        
        
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
        return "Short description";
    }// </editor-fold>

}
