/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;

/**
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
