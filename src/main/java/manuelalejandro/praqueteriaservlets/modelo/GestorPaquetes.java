package manuelalejandro.praqueteriaservlets.modelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;

public class GestorPaquetes {

    private FileWriter os;			// stream para escribir los datos en el fichero

    /**
     * 	Diccionario para manejar los datos en memoria.
     * 	La clave es el codigo único de los clientes.
     * 	El valor es un vector con todos los paquetes enviados por el cliente
     */
    final private HashMap<String, Vector<Paquete>> mapa;


    /**
     * Constructor del gestor de paquetes
     * Crea o Lee un fichero con datos de prueba
     */
    public GestorPaquetes() {
        this.mapa =  new HashMap<String, Vector<Paquete>>();
        File file = new File("paquetes.json");
        try {
            if (!file.exists() ) {
                // Si no existe el fichero de datos, los genera, rellena el diccionario y los escribe en el fichero
                os = new FileWriter(file);
                generaDatos();
                escribeFichero(os);
                os.close();
            }
            else {
                // Si existe el fichero o lo acaba de crear, lo lee y rellena el diccionario con los datos
                // stream para leer los datos del fichero
                FileReader is = new FileReader(file);
                leeFichero(is);
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Cuando cada cliente cierra su sesión volcamos los datos en el fichero para mantenerlos actualizados
     */
    public void guardaDatos(){
        File file = new File("paquetes.json");
        try {
            os = new FileWriter(file);
            escribeFichero(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Copia en el fichero un array JSON con los datos de los paquetes guardadas en el diccionario
     *
     * @param os	stream de escritura asociado al fichero de datos
     */
    @SuppressWarnings("unchecked")
    private void escribeFichero(FileWriter os) throws IOException {
        JSONArray array = new JSONArray();
        for (Map.Entry<String, Vector<Paquete>> entry : mapa.entrySet())
        {
            for (int i=0;i<entry.getValue().size();i++){
                array.add(entry.getValue().get(i));
            }
        }
        try (os) {
            os.write(String.valueOf(array));
        } catch (IOException e) {
            throw new IOException("NO VA: 1");
        }


    }

    /**
     * Almacena un paquete en el diccionario
     * @param paquete	paquete a almacenar
     */
    private void almacenaPaquete(Paquete paquete) {
        String codcli= paquete.getCodCliente();
        if (mapa.containsKey(codcli)){ // Si el cliente ya tiene paquetes almacenados actualizas su vector de paquetes añadiendo el nuevo
            Vector<Paquete> paquetes = mapa.get(codcli);
            paquetes.add(paquete);
            mapa.put(codcli,paquetes);
        }else{
            Vector<Paquete> paquetes = new Vector<>(); // Si no tiene paquetes pues creas el vector y lo añades al mapa
            paquetes.add(paquete);
            mapa.put(codcli,paquetes);
        }
    }


    /**
     * Genera los datos iniciales y los guarda en el diccionario
     */
    private void generaDatos() {
        almacenaPaquete(new Paquete("cli01", "12001", "12006", 0.7));
        almacenaPaquete(new Paquete("cli02", "12005", "12002", 1.2));
        almacenaPaquete(new Paquete("cli02", "12002", "12006", 15.2));
        almacenaPaquete(new Paquete("cli03", "12003", "12001", 3));
        almacenaPaquete(new Paquete("cli04", "12004", "12002", 2.6));
    }


    /**
     * Lee los datos del fichero en formato JSON y los añade al diccionario en memoria
     *
     * @param is	stream de lectura de los datos del fichero
     */
    private void leeFichero(FileReader is) {
        JSONParser parser = new JSONParser();
        try {
            // Leemos toda la información del fichero en un array de objetos JSON
            JSONArray array = (JSONArray) parser.parse(is);
            // Rellena los datos del diccionario en memoria a partir del JSONArray
            rellenaDiccionario(array);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    /**
     * Rellena el diccionario a partir de los datos en un JSONArray
     *
     * @param array	JSONArray con los datos de los paquetes
     */
    private void rellenaDiccionario(JSONArray array) {
        for(Object paquete: array){
            Paquete p = new Paquete((JSONObject) paquete);
            almacenaPaquete(p);
        }
    }


    /**
     * Obtiene una lista de todos los paquetes asociados con un cliente específico.
     * Devuelve un `JSONArray` con la representación JSON de cada paquete.
     *
     * @param codCliente El código del cliente cuyos paquetes se desean listar.
     * @return Un `JSONArray` que contiene la representación JSON de cada paquete del cliente.
     */
    public JSONArray listaPaquetesCliente(String codCliente) {
        JSONArray array = new JSONArray();
        if(!mapa.containsKey(codCliente)){
            return array;
        }
        Vector<Paquete> paquetes= mapa.get(codCliente);
        for (int i=0;i< paquetes.size();i++){
            Paquete paq = paquetes.get(i);
            array.add(paq.toJSON());
        }
        return array;
    }

    /**
     * Envía un paquete creando un objeto `Paquete` y almacenándolo.
     * Luego, retorna un objeto `JSONObject` con el código del paquete.
     *
     * @param codCliente El código del cliente que envía el paquete.
     * @param CPOrigen El código postal de origen del paquete.
     * @param CPDestino El código postal de destino del paquete.
     * @param peso El peso del paquete en kilogramos.
     * @return Un objeto `JSONObject` que contiene el código del paquete bajo la clave "codPaquete".
     */
    public JSONObject enviaPaquete(String codCliente, String CPOrigen, String CPDestino, double peso) {
        Paquete paquete=new Paquete(codCliente,CPOrigen,CPDestino,peso);
        long codpaquete= paquete.getCodPaquete();
        almacenaPaquete(paquete);
        JSONObject objeto= new JSONObject();
        objeto.put("codPaquete",codpaquete);
        return objeto;
    }


    /**
     * Busca un paquete con un código dado
     *
     * @param vector		vector de paquetes enviados por un cliente
     * @param codPaquete	código del paquete buscado
     * @return Referencia al paquete. Si no la encuentra, null
     *
     * Devolvemos una referencia al paquete para poder borrarlo o modificarlo
     */
    private Paquete buscaPaquete(Vector<Paquete> vector, long codPaquete) {
        for (int i=0;i<vector.size();i++){
            if (vector.get(i).getCodPaquete()==codPaquete){
                return vector.get(i);
            }
        }
        return null;
    }


    /**
     * Modifica un paquete específico de un cliente dado, actualizando sus datos si no ha sido recogido.
     * Retorna un `JSONObject` con la representación JSON del paquete modificado.
     *
     * @param codCliente El código del cliente que posee el paquete.
     * @param codPaquete El código del paquete a modificar.
     * @param CPOrigen El nuevo código postal de origen del paquete. Si está vacío, no se modifica.
     * @param CPDestino El nuevo código postal de destino del paquete. Si está vacío, no se modifica.
     * @param peso El nuevo peso del paquete. Si es 0, no se modifica.
     * @return Un objeto `JSONObject` con la representación del paquete modificado, o un objeto vacío si no se encontró el paquete o ya fue recogido.
     */
    public JSONObject modificaPaquete(String codCliente, long codPaquete, String CPOrigen, String CPDestino, double peso) {
        Vector<Paquete> PaquetesCliente = mapa.get(codCliente);
        JSONObject devolver = new JSONObject();
        Paquete p = buscaPaquete(PaquetesCliente, codPaquete);
        if(p != null && Objects.equals(p.getFechaRecogida(), "")){
            if (p.getCPOrigen()!=null){
                p.setCPOrigen(CPOrigen);
            }
            if(p.getCPDestino() != null){
                p.setCPDestino(CPDestino);
            }
            p.setFechaEnvio(Paquete.fechaHoy());
            if(p.getPeso() != 0){
                p.setPeso(peso);
            }
            mapa.put(codCliente, PaquetesCliente);
            devolver.put("codPaquete", p.getCodPaquete());
        }
        return devolver;
    }


    /**
     * Retira un paquete específico de un cliente dado, si no ha sido recogido.
     * El paquete retirado se elimina del sistema y se retorna su representación JSON.
     *
     * @param codCliente El código del cliente que posee el paquete.
     * @param codPaquete El código del paquete a retirar.
     * @return Un objeto `JSONObject` con la representación del paquete retirado, o un objeto vacío si no se encontró el paquete o ya fue recogido.
     */
    public JSONObject retiraPaquete(String codCliente, long codPaquete) {
        Vector<Paquete> PaquetesCliente = mapa.get(codCliente);
        if (PaquetesCliente==null){
            System.out.println("No tienes paquetes a tu nombre");
            return new JSONObject();
        }else{
            JSONObject devolver = new JSONObject();
            for(int i=0; i< PaquetesCliente.size();i++) {
                Paquete PaqueteCliente = PaquetesCliente.get(i);
                if (PaqueteCliente.getCodPaquete() == codPaquete) {
                    if (Objects.equals(PaqueteCliente.getFechaRecogida(), "")) {
                        devolver.put("paquete", PaquetesCliente.get(i));
                        PaquetesCliente.remove(i);
                        mapa.put(codCliente, PaquetesCliente);
                        return devolver;

                    }
                }
            }
            if (devolver.isEmpty()){
                System.out.println("No podemos retirarlo ya que o el paquete no existe o no te pertence o ha sido recogido");
            }
            return devolver; // MODIFICAR
        }
    }

    /* METODOS USADOS POR LOS MENSAJEROS */

    /**
     * Devuelve la lista de paquetes destinados a un código postal dado que no han sido recogidos todavía.
     *
     * @return JSONArray con la lista de paquetes. Vacío si no hay paquetes destinados a ese código postal
     */
    public JSONArray listaPaquetesCP(String CPDestino) {
        JSONArray devolver = new JSONArray();
        for (Map.Entry<String, Vector<Paquete>> entry : mapa.entrySet()) {
            Vector<Paquete> paquetes = entry.getValue();
            for (Paquete PaqueteCliente : paquetes) {
                if (PaqueteCliente.getCPDestino().equals(CPDestino) && PaqueteCliente.getFechaRecogida().equals("")) {
                    devolver.add(PaqueteCliente.toJSON());
                }
            }
        }
        return devolver;
    }


    /**
     * Método para que un mensajero busque y recoja un paquete con el código dado.
     *
     * @param codPaquete  el código único del paquete a recoger
     * @param codMensajero el código único del mensajero que recoge el paquete
     * @return un objeto JSON con la información del paquete recogido
     */
    public JSONObject recogePaquete(long codPaquete, String codMensajero) {
        JSONObject devolver = new JSONObject();
        for (Map.Entry<String, Vector<Paquete>> entry : mapa.entrySet()) {
            Vector<Paquete> paquetes = entry.getValue();
            for (Paquete PaqueteCliente : paquetes) {
                if (PaqueteCliente.getCodPaquete()==codPaquete && PaqueteCliente.getFechaRecogida().isEmpty()){
                    PaqueteCliente.setCodMensajero(codMensajero);
                    PaqueteCliente.setFechaRecogida(Paquete.fechaHoy());
                    devolver.put("codCliente", PaqueteCliente.getCodCliente());
                    devolver.put("CPOrigen", PaqueteCliente.getCPOrigen());
                    devolver.put("CPDestino", PaqueteCliente.getCPDestino());
                    devolver.put("peso", PaqueteCliente.getPeso());
                    return devolver;
                }
            }
        }

        return devolver;
    }
}