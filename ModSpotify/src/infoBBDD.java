/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Clase con la que se obtiene la información de la base de datos
 * @author usuario
 * @version 2024
 */
public class infoBBDD {
    // Variables de la clase
    private String titulo;
    private String descripcion;
    private String cantantes;
/**
 * Constructor de la clase para obtener la información
 * @param titulo Define el titulo de la categoría
 * @param descripcion Nos proporciona la desripció de la categoría
 * @param cantantes Nos proporciona los cantantes de dicha categoría
 */
    public infoBBDD(String titulo, String descripcion, String cantantes) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cantantes = cantantes;
    }
/**
 * Método con el que obtenemos el título
 * @return titulo Devuelve el titulo de la categoría
 */
    public String getTitulo() {
        return titulo;
    }
/**
 * Método con el que obtenemos la decripción
 * @return descripcion Devuelve la descripcion de la categoría
 */
    public String getDescripcion() {
        return descripcion;
    }
/**
 * Método con el que obtenemos los cantantes
 * @return cantantes Devuelve los cantantes de la categoría
 */
    public String getCantantes() {
        return cantantes;
    }
}
