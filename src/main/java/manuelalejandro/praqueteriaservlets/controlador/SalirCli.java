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
            session.removeAttribute("codcli");
            gestor.guardaDatos();
            session.invalidate();
        }

        RequestDispatcher vista = request.getRequestDispatcher("SalirCli.jsp");
        vista.forward(request, response);
    }
}