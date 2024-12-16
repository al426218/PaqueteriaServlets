package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;
import org.json.simple.JSONObject;


@WebServlet(name = "EnvioPaquetesCli", value = "/EnvioPaquetesCli")
public class
EnvioPaquetesCli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Validar la sesión y el código del cliente
        ServletContext contexto = getServletContext();
        String codcli = (String) contexto.getAttribute("codcli");

        // Intentar obtener el gestor de paquetes
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        // Validar los parámetros obligatorios
        String CPOrigen = request.getParameter("CPOrigen");
        String CPDestino = request.getParameter("CPDestino");
        String pesoParam = request.getParameter("peso");
        double peso = Double.parseDouble(request.getParameter("peso"));

        JSONObject paquete = gestor.enviaPaquete(codcli, CPOrigen, CPDestino, peso);
        request.setAttribute("paquete", paquete);
        request.setAttribute("codcli", codcli);


        RequestDispatcher vista = request.getRequestDispatcher("PaqueteEnviadoCli.jsp");
        vista.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}