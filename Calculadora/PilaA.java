/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;

/**
 *
 * <pre>
 * Representa una pila genérica.
 * Una pila es un arreglo que tiene un único extremo del cual se agregan o eliminan datos.
 * <pre>
 * 
 * @author fernandobarbaperez
 */
public class PilaA <T> implements PilaADT <T>{
    private T[] pila;
    private int tope;
    private final int MAX=100;
    
    /**
     * Constructor de una pila, sin parámetros.
     */
    public PilaA(){
        pila = (T[])new Object[MAX];
        tope=-1;
    }
    
    /**
     * Constructor de una pila, con maximo definido como parámetro.
     */
    public PilaA(int max){
        pila = (T[]) new Object[max];
        tope=-1;
    }
    
    /**
     * Evalúa si la pila almacena algún dato
     * @return </ul>
     * <li>true: si la pila no tiene ningún elemento.</li>
     * <li>false: si la pila tiene, al menos, un dato.</li>
     */
    public boolean isEmpty(){
        return tope==-1;
    }
    
    /**
     * Agrega algún dato al tope de la pila.
     * <li>Si la pila no es suficientemente grande, el código duplica el tamaño de la pila y copia los elementos.</li>
     * <li>Aumenta el tope.</li>
     */
    public void push (T dato){
        if(tope == pila.length - 1){ //No hay espacio, hay que expandir
            expands();
        }         //si no entra al if, es que si hay espacio
        tope++;
        pila[tope]=dato;  
    }
    
    /**
     * Expande la pila.
     * <li>El código duplica el tamaño de la pila y copia los elementos de la pila en la más grande.</li>
     */
    private void expands(){
        T[] masGrande = (T[]) new Object[pila.length*2];
        
        for(int i=0; i<=tope; i++){
            masGrande[i]=pila[i];
        }
        pila = masGrande;
    }
    
    /**
     * Elimina el último elemento de una pila no vacía.
     * @return </ul>
     * <li>el elemento eliminado.</li>
     * @throw </ul>
     * <li>EmptyCollectionException: si la pila está vacía.</li>
     */
    public T pop(){
        if(isEmpty()){
            throw new EmptyCollectionException("La pila no tiene elementos"); //Me saca del método, ya no continua
        }
        
        T eliminado = pila[tope];
        pila[tope]=null;
        tope--;
        
        return eliminado;
    }
    
    /**
     * Obtiene el último elemento de la pila.
     * @return </ul>
     * <li>el último elemento de la pila.</li>
     * @throw </ul>
     * <li>EmptyCollectionException: si la pila está vacía.</li>
     */
    public T peek(){
        if(isEmpty()){
            throw new EmptyCollectionException("La pila no tiene elementos"); //Me saca del método, ya no continua
        }
        return pila[tope];
    }
    
    /**
     * Elimina los últimos n elemento de una pila.
     * <li>Solo elimina los datos si se puede eliminar n datos.</li>
     * <li>Es decir, si la pila tiene n o mas elementos, elimina n de estos</li>
     */
    public void multiPop(int n){
        if(tope+1>=n){
           for(int i=0; i<n;i++){
               pop();
           } 
        }
    }
    
    /**
     * Elimina los últimos n elemento de una pila.
     * <li>Solo elimina los datos si se puede eliminar n datos.</li>
     * <li>Es decir, si la pila tiene n o mas elementos, elimina n de estos</li>
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<=tope; i++){
            sb.append("\n").append(pila[i]);
        }
        return sb.toString();
    }
}
    