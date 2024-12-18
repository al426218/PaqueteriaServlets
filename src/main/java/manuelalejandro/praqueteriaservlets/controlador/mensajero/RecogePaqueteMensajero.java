package manuelalejandro.praqueteriaservlets.controlador.mensajero;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet(name = "RecogePaqueteMensajero", value = "/RecogePaqueteMensajero")
public class RecogePaqueteMensajero extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el gestor de paquetes desde el contexto de la aplicación
        ServletContext contexto = getServletContext();
        HttpSession session = request.getSession(false);
        String codcli = (String) session.getAttribute("codcli");
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        long codPaq = Long.parseLong(request.getParameter("codPaq"));
        JSONObject paquete = gestor.recogePaquete(codPaq,codcli);
        String mensaje = "";
        if (paquete.isEmpty()){
            mensaje = "Hola, "+ codcli + ", ha habido un error al recoger el paquete.";
        }else{
            mensaje = "Enhorabuena, "+ codcli + ", has recogido el paquete con código: " + codPaq;
        }
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("paquete", paquete);
        RequestDispatcher vista = request.getRequestDispatcher("RecogePaquete.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}