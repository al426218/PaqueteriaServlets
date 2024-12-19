package manuelalejandro.praqueteriaservlets.controlador.mensajero;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;

@WebServlet(name = "SalirMens", value = "/SalirMens")
public class SalirMens extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext contexto = getServletContext();
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        // Invalidar la sesión si existe
        HttpSession session = request.getSession(false);
        if (session != null) {
            String codcli= (String) session.getAttribute("codcli");
            request.setAttribute("codMens", codcli);
            session.removeAttribute("codcli");
            gestor.guardaDatos();
            session.invalidate();
        }
        response.sendRedirect("SalirMens.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Si no estás usando POST, puedes dejarlo vacío.
    }
}
