package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;

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
        GestorPaquetes gestor = new GestorPaquetes();

        String tipo = request.getParameter("tipo").toString();
        String codcli = request.getParameter("codcli").toString();
        contexto.setAttribute("codcli", codcli);
        contexto.setAttribute("gestor", gestor);

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