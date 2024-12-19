package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;

@WebServlet(name = "ServletAcceso", value = "/ServletAcceso")
public class ServletAcceso extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        // Recupero el contexto de la aplicacion
        ServletContext contexto = getServletContext();
        GestorPaquetes gestorcontexto= (GestorPaquetes) contexto.getAttribute("gestor");
        if (gestorcontexto==null){
            gestorcontexto = new GestorPaquetes();
            contexto.setAttribute("gestor", gestorcontexto);
        }
        HttpSession session = request.getSession();
        String tipo = request.getParameter("tipo");
        String codcli = request.getParameter("codcli");
        session.setAttribute("codcli", codcli);
        if ("cliente".equals(tipo)) {
            response.sendRedirect("menuClientes.html");

        } else if ("mensajero".equals(tipo)) {
            response.sendRedirect("menuMensajeros.html");

        }

    }
}