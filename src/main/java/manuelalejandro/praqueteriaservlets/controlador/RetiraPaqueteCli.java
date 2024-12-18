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
        // Obtén el gestor de paquetes desde el contexto de la aplicación
        ServletContext contexto = getServletContext();
        HttpSession session = request.getSession(false);
        String codcli = (String) session.getAttribute("codcli");
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        if ( request.getParameter("codPaquete")!=null){
            long codpaq= Long.parseLong(request.getParameter("codPaquete"));
            JSONObject paquete = gestor.retiraPaquete(codcli, codpaq);
            request.setAttribute("paquete", paquete);
        }else{
            request.setAttribute("paquete", new JSONObject());
        }
        RequestDispatcher vista = request.getRequestDispatcher("PaqueteRetiradoCli.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}