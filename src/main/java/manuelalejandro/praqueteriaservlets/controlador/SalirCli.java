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

        // Obtener el gestor de paquetes
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");

        // Invalidar la sesión si existe
        HttpSession session = request.getSession(false);
        if (session != null) {
            String codcli = (String) session.getAttribute("codcli");
            request.setAttribute("codcli", codcli);
            session.removeAttribute("codcli");
            gestor.guardaDatos();
            session.invalidate();
        }

        // Activar la bandera global
        // Redirigir a la vista de salida
        RequestDispatcher vista = request.getRequestDispatcher("SalirCli.jsp");
        vista.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}