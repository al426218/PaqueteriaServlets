package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;
import org.json.simple.JSONObject;


@WebServlet(name = "RetiraPaqueteCli", value = "/RetiraPaqueteCli")
public class RetiraPaqueteCli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("codcli") == null) {
            response.sendRedirect("index.html");
            return;
        }
        // Obtén el gestor de paquetes desde el contexto de la aplicación
        ServletContext contexto = getServletContext();
        String codcli = (String) contexto.getAttribute("codcli");
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        long codpaquete = Long.parseLong(request.getParameter("codPaq"));
        JSONObject paquete = gestor.retiraPaquete(codcli, codpaquete);
        String mensaje = "";
        if (paquete.isEmpty()){
            mensaje = "Hola, "+ codcli + ", ha habido un error al retirar el paquete.";
        }else{
            mensaje = "Enhorabuena, "+ codcli + ", has retirado el paquete con código: " + codpaquete;
        }
        request.setAttribute("mensaje", mensaje);
        RequestDispatcher vista = request.getRequestDispatcher("PaqueteRetiradoCli.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}