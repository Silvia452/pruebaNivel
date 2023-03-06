import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Diccionario {

    private HashMap<Character, LinkedList<String>> tablaHash;

    public Diccionario() {
        tablaHash = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            tablaHash.put(c, new LinkedList<>());
        }
    }

    public void agregarPalabra(String palabra) {
        char primeraLetra = palabra.charAt(0);
        LinkedList<String> lista = tablaHash.get(primeraLetra);
        lista.add(palabra);
    }

    public boolean buscarPalabra(String palabra) {
        char primeraLetra = palabra.charAt(0);
        LinkedList<String> palabrasConLetra = tablaHash.get(primeraLetra);
        return palabrasConLetra.contains(palabra);
    }

    public void leerArchivo(String nombreArchivo) {
        try {
            FileReader archivo = new FileReader(nombreArchivo);
            BufferedReader lector = new BufferedReader(archivo);
            String linea = lector.readLine();
            while (linea != null) {
                String[] palabras = linea.split(","); // o cualquier otro delimitador
                for (String palabra : palabras) {
                    agregarPalabra(palabra.trim().toLowerCase()); // para evitar problemas con mayúsculas y espacios en blanco
                }
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
