/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculadora;

import java.util.ArrayList;

/**
 *
 * @author fernandobarbaperez
 */
public class Calculadora {
    
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
    
    private static boolean esSimbolo(char c){
        boolean resp=false;
        if(c=='(' || c==')' || c=='+' || c=='-' || c=='*' || c=='/' || c=='^' || c=='.'){
            resp=true;
        }
        return resp;
    }
    
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
    
    public static String noTieneErrores(String operacion){
        boolean resp=true;
        String corregido="";

        if(!operacion.isEmpty()){   
            if(tieneSoloElementosMatematicos(operacion) && estanBalanceadosParentesis(operacion)){ //hay que checar que no haya operaciones seguidas
                int i=0;
                char c = operacion.charAt(i),ant, ult=operacion.charAt(operacion.length()-1);
                boolean bandera=true;

                if((esNumero(c) || c=='-' || c=='(') && (esNumero(ult) || ult==')')){//que inicie bien y que su ultimo termino sea correcto
                    corregido+=c;
                    i++;
                    ant=c;
                    try{
                        c=operacion.charAt(i);
                    }catch(Exception e){
                        bandera=false;
                    }
                    while(i<operacion.length() && bandera){
                        if(esSimbolo(c)){
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
    
    public static ArrayList<String> divideOperacion(String operacion){
        
        ArrayList<String> arreglo = new ArrayList<>();
        
        if(noTieneErrores(operacion)!=null && !operacion.equals("0")){ 
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
                if(simbolosIndex.contains(c) || (c.equals("-")&& (esNumero(ant.charAt(0)) || ant.equals(")")))){
                    indice++;
                }
                if(arreglo.size()>indice){
                    arreglo.add(indice,(arreglo.get(indice)+c));
                    arreglo.remove(indice+1);//indispensable
                }
                else{
                    try{
                        arreglo.add(indice,c);
                    }catch(Exception e){
                        indice--;
                        arreglo.add(indice,c);
                    }
                }
                if(simbolosIndex.contains(c) || (c.equals("-")&& (esNumero(ant.charAt(0)) || ant.equals(")")))){
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
        System.out.println(evaluaOperacion(s1)); // segun photomath ≈21.3592 
        
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
        System.out.println(evaluaOperacion(s4)); // ≈1.14584 todo bien

        String s5="";
        System.out.println("\nPrueba 5");
        System.out.println(noTieneErrores(s5));
        System.out.println(divideOperacion(s5));
        System.out.println(convierteInfijaAPostfija(s5));  
        System.out.println(evaluaOperacion(s5)); // = null
        
        String s6="5.7*10^-9";
        System.out.println("\nPrueba 6");
        System.out.println(noTieneErrores(s6));
        System.out.println(divideOperacion(s6));
        System.out.println(convierteInfijaAPostfija(s6));  
        System.out.println(evaluaOperacion(s6)); // =0.0000000059
    }
}

