package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;

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
        ServletContext contexto = getServletContext();
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        String codcli = (String) contexto.getAttribute("codcli");
        JSONArray paquetes = gestor.listaPaquetesCliente(codcli);
        request.setAttribute("paquetes", paquetes);

        RequestDispatcher vista = request.getRequestDispatcher("ListarPaquetesCli.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}