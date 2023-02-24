/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;


/**
 *
 * <pre>
 * Excepción para una pila (también funciona para cualquier tipo de colección de datos).
 * Para cuando la pila (o colección) no tiene elementos en esta.
 * <pre>
 * 
 * @author fernandobarbaperez
 */
public class EmptyCollectionException extends RuntimeException {
    
    public EmptyCollectionException(){
        
    }
    public EmptyCollectionException(String message){
        super(message);
    }
}