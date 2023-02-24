/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;

import java.util.ArrayList;

/**
 *
 * <pre>
 * Representa una calculadora.
 * Permite hacer operaciones aritméticas simples con operandos (+,-,*,/,^).
 * Los números pueden ser decimales y acepta el uso de paréntesis.
 * Al ser todos los métodos, métodos estáticos, no hay constructor ni atrubutos.
 * <pre>
 * 
 * @author fernandobarbaperez
 */
public class Calculadora {
    
    /**
     * Evalúa si están bien balanceados los paréntesis.
     * Es decir, cada "(" tiene su ")", en ese orden.
     * @return </ul>
     * <li>true: si todos los parentesis están correctamente balanceados.</li>
     * <li>false: si hay al menos uno que no esté balanceado.</li>
     * <li>es decir, hay "(" o ")" de más o están mal posicionados </li>
     */
    private static boolean estanBalanceadosParentesis(String operacion){
        boolean resp = false, bandera=true;
        int i=0;
        PilaA pila = new PilaA();
        
        while(i<operacion.length() && bandera){
            if(operacion.charAt(i)=='('){
                pila.push(operacion.charAt(i));
            }
            else 
                if(operacion.charAt(i)==')'){
                    try{
                    pila.pop();
                    }catch(Exception e){
                        bandera=false;
                    }
                }
            i++;
        }
        if(pila.isEmpty() && bandera){
            resp=true;
        }
        return resp;
    }
    
    /**
     * Evalúa si un char es un dígito.
     * @return </ul>
     * <li>true: si es 1,2,3,4,5,6,7,8,9,0</li>
     * <li>false: si es cualquier otra cosa</li>
     */
    private static boolean esNumero(char c){
        boolean resp;
        try{
            Integer.parseInt(c+"");
            resp=true;
        }catch(NumberFormatException e){
            resp=false;
        }
        return resp;
    }
    
    /**
     * Evalúa si un String es un número.
     * @return </ul>
     * <li>true: si es o un entero o un decimal</li>
     * <li>false: si es cualquier otra cosa</li>
     */
    private static boolean esNumero(String s){
        boolean resp;
        try{
            Double.parseDouble(s);
            resp=true;
        }catch(NumberFormatException e){
            resp=false;
        }
        return resp;
    }
    
    /**
     * Evalúa si un char es un símbolo matemático.
     * Sirve para asegurarnos que la calculadora funcione debidamente
     * @return </ul>
     * <li>true: si es: (,),+,-,*,/,^,. . </li>
     * <li>false: si es cualquier otra cosa</li>
     */
    private static boolean esSimbolo(char c){
        boolean resp=false;
        if(c=='(' || c==')' || c=='+' || c=='-' || c=='*' || c=='/' || c=='^' || c=='.'){
            resp=true;
        }
        return resp;
    }
    
    /**
     * Evalúa si la operacion está conformada únicamente por elementos matemáticos.
     * @return </ul>
     * <li>true: si toda la cadena está formada por números o símbolos matemático </li>
     * <li>false: si encuentra al menos un caracter que no sea ni número ni símbolo</li>
     */
    private static boolean tieneSoloElementosMatematicos(String operacion){
        boolean resp=true;
        int i=0;
        char ele;
        while(i<operacion.length() && resp){
            ele = operacion.charAt(i);
            if(esNumero(ele) || esSimbolo(ele)){
                i++;
            }
            else{
                resp=false;
            }
        }
        return resp;
    }
    
    /**
     * Evalúa que la operación sea válida y pueda ser evaluada debidamente.
     * Evalúa que la operación solo tenga elementos matemáticos y que los parentesis estén balanceados.
     * Adicionalmente, checa que no haya operaciones seguidas y que comience con un número (ya sea positivo o negativo) y acabe con otro.
     * También permite empezar y terminar con parentesis.
     * @return </ul>
     * <li>String: regresa la misma operación si no hay errores </li>
     * <li>null: si encuentra al menos un error dentro de los requisitos mencionados</li>
     */
    public static String noTieneErrores(String operacion){
        boolean resp=true;
        String corregido="";

        if(operacion!=null && !operacion.isEmpty()){   
            if(tieneSoloElementosMatematicos(operacion) && estanBalanceadosParentesis(operacion)){ //hay que checar que no haya operaciones seguidas
                int i=0;
                char c = operacion.charAt(i),ant, ult=operacion.charAt(operacion.length()-1);
                boolean bandera=true;

                if((esNumero(c) || c=='-' || c=='(') && (esNumero(ult) || ult==')')){//que inicie bien y que su ultimo termino sea correcto
                    corregido+=c;
                    i++;
                    ant=c;
                    if (i<operacion.length()){
                        c=operacion.charAt(i);
                    }
                    else{
                        bandera=false;
                    }
                    while(i<operacion.length() && bandera){
                        if(esSimbolo(c)){ //si es símbolo, comprueba que no haya otro símbolo antes, a menos que sea un parentesis cerrado
                            if(c=='('||esNumero(ant) ||ant==')'|| (c=='-' && esSimbolo(ant))){
                                if((c=='(') && (!esSimbolo(ant) || ant=='.')){
                                    bandera=false;
                                    resp=false;
                                }
                                corregido+=c;
                                i++;
                                ant=c;
                                try{
                                    c=operacion.charAt(i);
                                }catch(Exception e){
                                    bandera=false;
                                }
                                if(ant==')'&& esNumero(c)){
                                    bandera=false;
                                    resp=false;
                                }
                            }
                            else{
                                resp=false;
                                bandera=false;
                            }
                        }
                        else{ //es un numero
                            corregido+=c;
                            i++;
                            ant=c;
                            try{
                                c=operacion.charAt(i);
                            }catch(Exception e){
                                bandera=false;
                            }
                        }
                    }

                }
                else{
                    resp=false;
                }
            }
            else{
                resp=false;
            }
            if(resp==false){
                corregido=null;
            }
        }
        else{
            corregido=null;
        }
        return corregido;
    }
    
    /**
     * Crea un ArrayList de String, dividiendo la operación en valores para trabajar con ellos más fácilmente.
     * Es decir, en cada celda pone o un número (entero o decimal, ya sea positivo o negativo) o un símbolo
     * El método diferencía si un "-" es para restar o para hacer un número negativo dependiendo su contexto en la operación
     * El método retoma el método de noTieneErrores para asegurarse que la operación sea una operación válida
     * @return </ul>
     * <li>ArrayList<String>: regresa la misma operación pero dividida. Una celda por número o símbolo no "." o "-" si es para hacer a un número negativo  </li>
     * <li>null: si la operación tenía algún error </li>
     */
    public static ArrayList<String> divideOperacion(String operacion){
        
        ArrayList<String> arreglo = new ArrayList<>();
        
        if(noTieneErrores(operacion)!=null){ 
            int i=0,indice=0;
            String c=String.valueOf(operacion.charAt(i)), ant="";
            String simbolosIndex="+^()*/";
            
            arreglo.add(indice,c);
            i++;
            if(c.equals("(")){
                indice++;
            }
            while(i<operacion.length()){
                if(i<operacion.length()){
                    ant=c;
                    c=operacion.charAt(i)+"";
                }
                if(simbolosIndex.contains(c) || (c.equals("-")&& (esNumero(ant.charAt(0)) || ant.equals(")")))){ //avanza de celda si es simbolo
                    indice++;
                }
                if(arreglo.size()>indice){
                    arreglo.add(indice,(arreglo.get(indice)+c));
                    arreglo.remove(indice+1);//indispensable para que no haya repetidos
                }
                else{
                    try{
                        arreglo.add(indice,c); //agrega símbolo o número
                    }catch(Exception e){
                        indice--;
                        arreglo.add(indice,c);
                    }
                }
                if(simbolosIndex.contains(c) || (c.equals("-")&& (esNumero(ant.charAt(0)) || ant.equals(")")))){ //avanza de celda si es simbolo
                    indice++;
                }
                i++;
            }
        }
        else{
            arreglo=null;
        }
        return arreglo;
    }
    
    /**
     * Evalúa dos operadores basado en su prioridad matemática (PEMDAS).
     * @return </ul>
     * <li>true: si el operador 1 es menor o igual que el operador 2 en prioridad matemática </li>
     * <li>false: si el operador 1 es mayor que el operador 2 en prioridad matemática </li>
     */
    private static boolean comparaOperadores(String op1, String op2){//c1<=c2 en prioridad
        boolean resp=false;
        String operadores="^*/-+";
        
        switch(op1){
            case "+": 
                resp=(operadores.contains(op2)); 
                break;
            case "-": 
                resp=(operadores.contains(op2)); 
                break;
            case "*":
                resp=(op2.equals("^")||op2.equals("*")||op2.equals("/")); 
                break;
            case "/":
                resp=(op2.equals("^")||op2.equals("*")||op2.equals("/")); 
                break;
            case "^":
                resp=(op2.equals("^"));
                break;
        }
        return resp;
    }
    
    /**
     * Acomoda los elementos de la operación previamente dividida de notación infija a notación postfija.
     * El método retoma el método de divideOperacion en el cual ya viene dividio todo en celdas y se asegura que la operación sea una operación válida
     * Se apoya en una pila para almacenar los operadores dependiendo su orden y prioridad matemática. 
     * @return </ul>
     * <li>ArrayList<String>: regresa la misma operación pero en notación postfija. </li>
     * <li>null: si la operación tenía algún error </li>
     */
    public static ArrayList<String> convierteInfijaAPostfija(String operacion){
        
        ArrayList<String> postfija = new ArrayList<>();
        ArrayList<String> op = divideOperacion(operacion);
        
        if(op!=null){
            int indice=0;
            String dato;
            PilaA <String> pila = new PilaA();
            while(indice<op.size()){
                dato=op.get(indice);
                if(esNumero(dato)){
                    postfija.add(dato);
                }
                else{
                    switch(dato){
                        case "(":
                            pila.push(dato);
                            break;
                        case ")":
                            while(!pila.peek().equals("(")){
                                postfija.add(pila.pop());
                            }
                            pila.pop(); //saca el "(" de la pila
                            break;
                        default:
                            while(!pila.isEmpty() && comparaOperadores(dato,pila.peek())){
                                postfija.add(pila.pop());
                            }
                            pila.push(dato);
                            break;
                    }
                    
                }
                indice++;
            }
            while(!pila.isEmpty()){
                postfija.add(pila.pop());
            }
        }
        else{
            postfija=null;
        }
        return postfija;
    }
    
    /**
     * Regresa el resultado matemático de la operacion previamente validada.
     * El método retoma el método de convierteInfijaAPostfija para asegurarse que la operación sea una operación válida y que sea más fácil de evaluar
     * @return </ul>
     * <li>String: regresa el resultado matemático de la operación  </li>
     * <li>null: si la operación tenía algún error </li>
     */
    public static String evaluaOperacion(String operacion) {
        double result=0;
        String respuesta="";
        ArrayList<String> op = convierteInfijaAPostfija(operacion);
        if(op!=null){
            int indice=0;
            String dato;
            PilaA <String> pila = new PilaA();
            double a, b;
            while(indice<op.size()){
                dato=op.get(indice);
                if(esNumero(dato)){
                    pila.push(dato);
                }
                else{
                    b = Double.parseDouble(pila.pop());
                    a = Double.parseDouble(pila.pop());     
                    switch(dato){
                        case "+":   // a b +
                            result=a+b;
                            break;
                        case "-":   // a b -
                            result=a-b;
                            break;
                        case "*":   // a b *
                            result=a*b;
                            break;
                        case "/":   // a b /
                            try{
                                result=a/b;
                            }catch(Exception e){
                                result=0/0;
                            }    
                            break;  // a b ^
                        case "^":
                            result=Math.pow(a, b);
                            break;
                    }
                    pila.push(result+"");
                }
                indice++;
            }
            respuesta=result+"";
        }
        else{
            respuesta = null;   //en la calculadora poner SYNTAX ERROR
        }
        return respuesta;
    }
    
    public static void main(String[] args) {
        
        String s1="-2+(3.2^(8/-3)*(567.9-4)+-2)";
        System.out.println("\nPrueba 1");
        System.out.println(noTieneErrores(s1));
        System.out.println(divideOperacion(s1));
        System.out.println(convierteInfijaAPostfija(s1));
        System.out.println(evaluaOperacion(s1)); // ≈21.3592 
        
        String s2="-2+(3.(2^(8/-3)*(5)67.9)+-2)";        
        System.out.println("\nPrueba 2");
        System.out.println(noTieneErrores(s2));
        System.out.println(divideOperacion(s2));
        System.out.println(convierteInfijaAPostfija(s2));  
        System.out.println(evaluaOperacion(s2)); // = null
        
        String s3="-2+(3.2^(8/-3)*(5)67.9+-2)";
        System.out.println("\nPrueba 3");
        System.out.println(noTieneErrores(s3));
        System.out.println(divideOperacion(s3)); 
        System.out.println(convierteInfijaAPostfija(s3));  
        System.out.println(evaluaOperacion(s3)); // = null
        
        String s4="1.2+34*(-5+6.7)^-8/-9"; //a+b*(c+d)^e/k
        System.out.println("\nPrueba 4");
        System.out.println(noTieneErrores(s4));
        System.out.println(divideOperacion(s4));
        System.out.println(convierteInfijaAPostfija(s4));  //ab cd+e^ *k/+
        System.out.println(evaluaOperacion(s4)); // ≈1.14584 

        String s5="";
        System.out.println("\nPrueba 5");
        System.out.println(noTieneErrores(s5));
        System.out.println(divideOperacion(s5));
        System.out.println(convierteInfijaAPostfija(s5));  
        System.out.println(evaluaOperacion(s5)); // = null
        
        String s6=null;
        System.out.println("\nPrueba 6");
        System.out.println(noTieneErrores(s6));
        System.out.println(divideOperacion(s6));
        System.out.println(convierteInfijaAPostfija(s6));  
        System.out.println(evaluaOperacion(s6)); // =0
    }
}

