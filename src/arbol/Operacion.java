/*
 * Ejemplo desarrollado por Erick Navarro
 * Blog: e-navarro.blogspot.com
 * Julio - 2018
 */

package arbol;

/**
 * Clase que ejecuta las acciones de una operación, ya sea aritmética o realacional
 * y que implementa la interfaz de instrucción, ya que estas operaciones pueden 
 * ejecutarse y al ejecutarse retornan un valor.
 * @author Erick
 */
public class Operacion implements Instruccion{
    /**
     * Enumeración del tipo de operación que puede ser ejecutada por esta clase.
     */
    public static enum Tipo_operacion{
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        NEGATIVO,
        NUMERO,
        IDENTIFICADOR,
        CADENA,
        MAYOR_QUE,
        MENOR_QUE,
        MAYOR_IGUAL_QUE,
        MENOR_IGUAL_QUE,
        DIFERENTE_QUE,
        IGUAL_QUE,
        NOT,
        AND,
        OR,
        TRUE,
        FALSE,
        CONCATENACION
    }
    /**
     * Tipo de operación a ejecutar.
     */
    private final Tipo_operacion tipo;
    /**
     * Operador izquierdo de la operación.
     */
    private Operacion operadorIzq;
    /**
     * Operador derecho de la operación.
     */
    private Operacion operadorDer;
    /**
     * Valor específico si se tratara de una literal, es decir un número o una 
     * cadena.
     */
    private Object valor;
    /**
     * Constructor de la clase para operaciones binarias (con dos operadores), estas
     * operaciones son:
     * SUMA, RESTA, MULTIPLICACION, DIVISION, CONCATENACION, MAYOR_QUE, MENOR_QUE
     * @param operadorIzq Operador izquierdo de la operación
     * @param operadorDer Opeardor derecho de la operación
     * @param tipo Tipo de la operación
     */
    public Operacion(Operacion operadorIzq, Operacion operadorDer, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
        this.operadorDer = operadorDer;
    }
    /**
     * Constructor para operaciones unarias (un operador), estas operaciones son:
     * NEGATIVO
     * NOT
     * @param operadorIzq Único operador de la operación
     * @param tipo Tipo de operación
     */
    public Operacion(Operacion operadorIzq, Tipo_operacion tipo) {
        this.tipo = tipo;
        this.operadorIzq = operadorIzq;
    }
    /**
     * Constructor para operaciones unarias (un operador), cuyo operador es 
     * específicamente una cadena, estas operaciones son:
     * IDENTIFICADOR, CADENA
     * @param a Cadena que representa la operación a realizar
     * @param tipo Tipo de operación
     */
    public Operacion(String a, Tipo_operacion tipo) {
        this.valor=a;
        this.tipo = tipo;
    }
    /**
     * Constructor para operaciones unarias (un operador), cuyo operador es 
     * específicamente una NUMERO, estas operaciones son:
     * NUMERO_ENTERO, NUMERO_DECIMAL
     * @param a Valor de tipo Double que representa la operación a realizar.
     */
    public Operacion(Double a) {
        this.valor=a;
        this.tipo = Tipo_operacion.NUMERO;
    }
        
    /**
     * Método que ejecuta la instrucción operación, es una sobreescritura del 
     * método ejecutar que se debe programar por la implementación de la interfaz
     * instrucción
     * @param ts tabla de símbolos del ámbito padre de la sentencia
     * @return Esta instrucción retorna el valor producido por la operación que se ejecutó
     */    
    @Override
    public Object ejecutar(TablaDeSimbolos ts) {
        Object a = (operadorIzq==null)?null:operadorIzq.ejecutar(ts);
        Object b = (operadorDer==null)?null:operadorDer.ejecutar(ts);
        
        if(tipo== Tipo_operacion.DIVISION){
            if(a instanceof Double && b instanceof Double){
                return (Double)a / (Double)b;
            }else{
                System.err.println("Error de tipos, la división debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.MULTIPLICACION){
            if(a instanceof Double && b instanceof Double){
                return (Double)a * (Double)b;
            }else{
                System.err.println("Error de tipos, la multiplicación debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.RESTA){
            if(a instanceof Double && b instanceof Double){
                return (Double)a - (Double)b;
            }else{
                System.err.println("Error de tipos, la resta debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.SUMA){
            if(a instanceof Double && b instanceof Double){
                return (Double)a + (Double)b;
            }else{
                System.err.println("Error de tipos, la suma debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.NEGATIVO){
            if(a instanceof Double){
                return (Double)a * -1;
            }else{
                System.err.println("Error de tipos, el operador negativo debe aplicarse a un número.");
                return null;
            }
        }else if(tipo== Tipo_operacion.NUMERO){
            return new Double(valor.toString());
        }else if(tipo== Tipo_operacion.IDENTIFICADOR){
            return ts.getValor(valor.toString());
        }else if(tipo== Tipo_operacion.CADENA){
            return valor.toString();
        }else if(tipo== Tipo_operacion.MAYOR_QUE){
            if(a instanceof Double && b instanceof Double){
                return (Boolean)(((Double)a).doubleValue()>((Double)b).doubleValue());
            }else{
                System.err.println("Error de tipos, la comparación mayor que debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.MENOR_QUE){
            if(a instanceof Double && b instanceof Double){
                return (Boolean)(((Double)a).doubleValue()<((Double)b).doubleValue());
            }else{
                System.err.println("Error de tipos, la comparación menor que debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.MENOR_IGUAL_QUE){
            if(a instanceof Double && b instanceof Double){
                return (Boolean)(((Double)a).doubleValue()<=((Double)b).doubleValue());
            }else{
                System.err.println("Error de tipos, la comparación menor o igual que debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.MAYOR_IGUAL_QUE){
            if(a instanceof Double && b instanceof Double){
                return (Boolean)(((Double)a).doubleValue()>=((Double)b).doubleValue());
            }else{
                System.err.println("Error de tipos, la comparación mayor o igual que debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.DIFERENTE_QUE){
            if(a instanceof Double && b instanceof Double){
                return (Boolean)(((Double)a).doubleValue()!=((Double)b).doubleValue());
            }else{
                System.err.println("Error de tipos, la comparación diferente que debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.IGUAL_QUE){
            if(a instanceof Double && b instanceof Double){
                return (Boolean)(((Double)a).doubleValue()==((Double)b).doubleValue());
            }else{
                System.err.println("Error de tipos, la comparación igual que debe hacerse entre números.");
                return null;
            }
        }else if(tipo== Tipo_operacion.NOT){
            if(a instanceof Boolean){
                return !((Boolean)a);
            }else{
                System.err.println("Error de tipos, la negación debe hacerse a una expresión booleana.");
                return null;
            }
        }else if(tipo== Tipo_operacion.AND){
            if(a instanceof Boolean && b instanceof Boolean){
                return ((Boolean)a) && ((Boolean)b);
            }else{
                System.err.println("Error de tipos, la operación and debe hacerse entre expresiones booleanas.");
                return null;
            }
        }else if(tipo== Tipo_operacion.OR){
            if(a instanceof Boolean && b instanceof Boolean){
                return ((Boolean)a) || ((Boolean)b);
            }else{
                System.err.println("Error de tipos, la operación or debe hacerse entre expresiones booleanas.");
                return null;
            }
        }else if(tipo== Tipo_operacion.TRUE){
            return new Boolean(true);
        }else if(tipo== Tipo_operacion.FALSE){
            return new Boolean(false);
        }else if(tipo== Tipo_operacion.CONCATENACION){
            return ((a==null)?null:a.toString())+((b==null)?null:b.toString());
        }else{
            return null;
        }
    }    
}
