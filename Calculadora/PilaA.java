/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;

/**
 *
 * <pre>
 * Representa una pila genérica.
 * Una pila es un a que ...
 * ciemta con la funcionalidad ...
 * <pre>
 * 
 * @author fernandobarbaperez
 */
public class PilaA <T> implements PilaADT <T>{
    private T[] pila;
    private int tope;
    private final int MAX=100;
    
    public PilaA(){
        pila = (T[])new Object[MAX];
        tope=-1;
    }
    
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
    
    public void push (T dato){
        if(tope == pila.length - 1){ //No hay espacio, hay que expandir
            expands();
        }         //si no entra al if, es que si hay espacio
        tope++;
        pila[tope]=dato;  
    }
    
    private void expands(){
        T[] masGrande = (T[]) new Object[pila.length*2];
        
        for(int i=0; i<=tope; i++){
            masGrande[i]=pila[i];
        }
        pila = masGrande;
    }
    
    public T pop(){
        if(isEmpty()){
            throw new EmptyCollectionException("La pila no tiene elementos"); //Me saca del método, ya no continua
        }
        
        T eliminado = pila[tope];
        pila[tope]=null;
        tope--;
        
        return eliminado;
    }
    
    public T peek(){
        if(isEmpty()){
            throw new EmptyCollectionException("La pila no tiene elementos"); //Me saca del método, ya no continua
        }
        return pila[tope];
    }
    
    public void multiPop(int n){
        if(tope+1>=n){
           for(int i=0; i<n;i++){
               pop();
           } 
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<=tope; i++){
            sb.append("\n").append(pila[i]);
        }
        return sb.toString();
    }
}
    