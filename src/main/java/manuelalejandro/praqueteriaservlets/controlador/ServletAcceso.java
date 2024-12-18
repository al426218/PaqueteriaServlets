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
        // Recupero el contexto de la aplicacion
        ServletContext contexto = getServletContext();
        GestorPaquetes gestorcontexto= (GestorPaquetes) contexto.getAttribute("gestor");
        if (gestorcontexto==null){
            gestorcontexto = new GestorPaquetes();
            contexto.setAttribute("gestor", gestorcontexto);
        }
        Logger logger = Logger.getLogger("ServletAcceso");
        logger.info("Entro en servlet Acceso");
        HttpSession session = request.getSession();
        String tipo = request.getParameter("tipo");
        String codcli = request.getParameter("codcli");
        session.setAttribute("codcli", codcli);
        logger.info("codcli"+session.getAttribute("codcli"));

        // Redirige al menú correspondiente según el tipo
        if ("cliente".equals(tipo)) {
            RequestDispatcher vista = request.getRequestDispatcher("menuClientes.html");
            vista.forward(request, response);
        } else if ("mensajero".equals(tipo)) {
            RequestDispatcher vista = request.getRequestDispatcher("menuMensajeros.html");
            vista.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}