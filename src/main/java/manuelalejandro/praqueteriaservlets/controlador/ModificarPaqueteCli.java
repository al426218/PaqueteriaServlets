package manuelalejandro.praqueteriaservlets.controlador;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import manuelalejandro.praqueteriaservlets.modelo.GestorPaquetes;
import org.json.simple.JSONObject;


@WebServlet(name = "ModificarPaqueteCli", value = "/ModificarPaqueteCli")
public class ModificarPaqueteCli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtén el gestor de paquetes desde el contexto de la aplicación
        HttpSession session = request.getSession(false);
        ServletContext contexto = getServletContext();
        String codcli = (String) session.getAttribute("codcli");
        GestorPaquetes gestor = (GestorPaquetes) contexto.getAttribute("gestor");
        long codPaq = Long.parseLong(request.getParameter("codPaq"));
        String CPOrigen = request.getParameter("CPOrigen").toString();
        String CPDestino = request.getParameter("CPDestino").toString();
        double peso = Double.parseDouble(request.getParameter("peso"));

        JSONObject paquete = gestor.modificaPaquete(codcli,codPaq, CPOrigen, CPDestino, peso);
        String mensaje = "";
        if (paquete.isEmpty()){
            mensaje = "Hola, "+ codcli + ", ha habido un error al modificar el paquete.";
        }else{
            mensaje = "Enhorabuena, "+ codcli + ", has modificado el paquete con código: " + codPaq;
        }
        request.setAttribute("mensaje", mensaje);
        RequestDispatcher vista = request.getRequestDispatcher("PaqueteModificadoCli.jsp");
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}