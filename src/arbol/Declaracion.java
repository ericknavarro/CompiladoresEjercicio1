/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */
package arbol;

import java.util.LinkedList;

/**
 * Clase que ejecuta las acciones de una instrucción de declaración y que
 * implementa la interfaz de instrucción
 *
 * @author Erick
 */
public class Declaracion implements Instruccion {

    /**
     * Lista de Identificadores de las variables que serán declarada.
     */
    private final LinkedList<String> identificadores;
    /**
     * Tipo de la variable que será declarada.
     */
    Simbolo.Tipo tipo;
    /**
     * valor de la variable que será declarada
     */
    private final Operacion valor;

    /**
     * Constructor de la clase
     *
     * @param a Lista de Identificadores de las variable que serán declaradas
     * @param b Tipo de la clase que será declarada
     */
    public Declaracion(LinkedList<String> a, String b) {
        identificadores = a;
        this.valor = null;
        switch (b.toLowerCase()) {
            case "numeric":
                tipo = Simbolo.Tipo.NUMERO;
                break;
            case "string":
                tipo = Simbolo.Tipo.CADENA;
                break;
            case "boolean":
                tipo = Simbolo.Tipo.BOOLEANO;
                break;
        }
    }

    /**
     * Constructor de la clase Declaración cuando incluye asignacion de valor
     *
     * @param identificadores
     * @param tip
     * @param valor
     */
    public Declaracion(LinkedList<String> identificadores, String tip, Operacion valor) {
        this.identificadores = identificadores;
        this.valor = valor;
        switch (tip.toLowerCase()) {
            case "numeric":
                tipo = Simbolo.Tipo.NUMERO;
                break;
            case "string":
                tipo = Simbolo.Tipo.CADENA;
                break;
            case "boolean":
                tipo = Simbolo.Tipo.BOOLEANO;
                break;
        }
    }

    /**
     * Método que ejecuta la accion de declarar una variable, es una
     * sobreescritura del método ejecutar que se debe programar por la
     * implementación de la interfaz instrucción
     *
     * @param ts Tabla de símbolos del ámbito padre.
     * @return No retorna nada porque no es una sentencia que genere un valor.
     */
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        if (valor == null) {
            for (String id : identificadores) {
                ts.add(new Simbolo(id, tipo));
                System.out.println("Se declaro var: " + id);
            }
        }else{
            for (String id : identificadores) {
                ts.add(new Simbolo(id, tipo));
                System.out.println("Se declaro var: " + id);
            }
            ts.setValor(identificadores.getLast(), valor.ejecutar(ts));
        }
        return null;
    }

}
