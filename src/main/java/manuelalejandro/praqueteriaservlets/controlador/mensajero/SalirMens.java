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
        HttpSession session = request.getSession(false);

        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        String codcli = (String) session.getAttribute("codcli");
        gestor.guardaDatos();
        session.invalidate();
        // Guardar datos antes de salir
        // Limpiar cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null); // Borra el valor de la cookie
                cookie.setMaxAge(0);  // Expira la cookie inmediatamente
                cookie.setPath("/");  // Asegura que se elimine en toda la aplicación
                response.addCookie(cookie); // Agrega la cookie actualizada al response
            }
        }

        // Redirigir al JSP
        request.setAttribute("codMens", codcli);
        RequestDispatcher vista = request.getRequestDispatcher("SalirMens.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Si no estás usando POST, puedes dejarlo vacío.
    }
}
