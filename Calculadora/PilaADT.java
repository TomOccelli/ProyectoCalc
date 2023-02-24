/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;

/**
 *
 * <pre>
 * La interfase para una pila genérica.
 * Todas las pilas tienen que tener estos métods.
 * Push: agrega dato en el último lugar
 * Pop: elimina el último dato de la pila
 * Peek: regresa el último dato de la pila
 * isEmpty: regresa verdadero si la pila está vacío y falso si tiene al menos un dato
 * multiPop: si la pila tiene "n" o más datos, los elimina
 * <pre>
 * 
 * @author fernandobarbaperez
 */
public interface PilaADT <T> {
    public void push (T dato);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public void multiPop(int n);
}
