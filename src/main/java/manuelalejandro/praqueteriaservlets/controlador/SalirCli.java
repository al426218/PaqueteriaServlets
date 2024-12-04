package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;


@WebServlet(name = "SalirCli", value = "/SalirCli")
public class SalirCli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext contexto = getServletContext();
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        String codcli = (String) contexto.getAttribute("codcli");
        gestor.guardaDatos();
        contexto.removeAttribute("codcli");

        HttpSession session = request.getSession(false);
        session.invalidate();
        request.setAttribute("codcli", codcli);
        RequestDispatcher vista = request.getRequestDispatcher("SalirCli.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}