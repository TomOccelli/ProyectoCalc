/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;

/**
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