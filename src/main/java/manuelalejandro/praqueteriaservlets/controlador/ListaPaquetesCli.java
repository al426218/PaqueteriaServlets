package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;
import org.json.simple.JSONArray;


@WebServlet(name = "ListaPaquetesCli", value = "/ListaPaquetesCli")
public class ListaPaquetesCli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el gestor de paquetes desde el contexto de la aplicación
        Logger logger = Logger.getLogger("ListarPaquetesCLi");
        logger.info("Entro a listar servlet");
        HttpSession session = request.getSession(false);
        ServletContext contexto = getServletContext();
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        String codcli = (String) session.getAttribute("codcli");
        logger.info((String) session.getAttribute("codcli"));

        if (codcli == null) {
            response.sendRedirect("index.html");
            return;
        }
        JSONArray paquetes = gestor.listaPaquetesCliente(codcli);
        request.setAttribute("paquetes", paquetes);
        RequestDispatcher vista = request.getRequestDispatcher("ListarPaquetesCli.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}