package manuelalejandro.praqueteriaservlets.controlador.mensajero;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;
import org.json.simple.JSONArray;

import java.io.IOException;


@WebServlet(name = "ListaPaquetesMensajeros", value = "/ListaPaquetesMensajeros")
public class ListaPaquetesMensajeros extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtén el gestor de paquetes desde el contexto de la aplicación
        ServletContext contexto = getServletContext();
        String codcli = (String) contexto.getAttribute("codcli");
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        String codpostal = request.getParameter("CPDestino");
        JSONArray paquetes = gestor.listaPaquetesCP(codpostal);
        request.setAttribute("paquetes", paquetes);
        RequestDispatcher vista = request.getRequestDispatcher("ListarMensajero.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}