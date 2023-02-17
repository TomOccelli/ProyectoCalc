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
    private String operacion;
    
    public Calculadora(String ope){
        this.operacion=ope;
    }
    
    private boolean estanBalanceadosParentesis(){
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
    
    private boolean tieneSoloElementosMatematicos(){
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
    
    public String noTieneErrores(){
        boolean resp=true;
        String corregido="";

        if(!operacion.isEmpty()){   
            if(tieneSoloElementosMatematicos() && estanBalanceadosParentesis()){ //hay que checar que no haya operaciones seguidas
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
    
    public ArrayList<String> divideOperacion(){
        
        ArrayList<String> arreglo = new ArrayList<>();
        
        if(noTieneErrores()!=null && !operacion.equals("0")){ 
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
    
    public ArrayList<String> convierteInfijaAPostfija(){
        ArrayList<String> postfija = new ArrayList<>();
        ArrayList<String> op = divideOperacion();
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
    
    public String evaluaOperacion() {
        double result=0;
        String respuesta="";
        ArrayList<String> op = convierteInfijaAPostfija();
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
        Calculadora c0 = new Calculadora("-2+(3.2^(8/-3)*(567.9-4)+-2)");
        System.out.println("\nPrueba 1");
        System.out.println(c0.noTieneErrores());
        System.out.println(c0.divideOperacion());
        System.out.println(c0.convierteInfijaAPostfija());
        System.out.println(c0.evaluaOperacion()); // segun photomath ≈21.3592 
        
        Calculadora c1 = new Calculadora("-2+(3.(2^(8/-3)*(5)67.9)+-2)");
        System.out.println("\nPrueba 2");
        System.out.println(c1.noTieneErrores());
        System.out.println(c1.divideOperacion());
        System.out.println(c1.convierteInfijaAPostfija());  
        System.out.println(c1.evaluaOperacion()); // = null
        
        Calculadora c2 = new Calculadora("-2+(3.2^(8/-3)*(5)67.9+-2)");
        System.out.println("\nPrueba 3");
        System.out.println(c2.noTieneErrores());
        System.out.println(c2.divideOperacion()); 
        System.out.println(c2.convierteInfijaAPostfija());  
        System.out.println(c2.evaluaOperacion()); // = null
        
        Calculadora c3 = new Calculadora("1.2+34*(-5+6.7)^-8/-9"); //a+b*(c+d)^e/k 
        System.out.println("\nPrueba 4");
        System.out.println(c3.noTieneErrores());
        System.out.println(c3.divideOperacion());
        System.out.println(c3.convierteInfijaAPostfija());  //ab cd+e^ *k/+
        System.out.println(c3.evaluaOperacion()); // ≈1.14584 todo bien

        Calculadora c4 = new Calculadora ("");
        System.out.println("\nPrueba 5");
        System.out.println(c4.noTieneErrores());
        System.out.println(c4.divideOperacion());
        System.out.println(c4.convierteInfijaAPostfija());  
        System.out.println(c4.evaluaOperacion()); // = null
        
        Calculadora c5 = new Calculadora ("5.7*10^-9");
        System.out.println("\nPrueba 6");
        System.out.println(c5.noTieneErrores());
        System.out.println(c5.divideOperacion());
        System.out.println(c5.convierteInfijaAPostfija());  
        System.out.println(c5.evaluaOperacion()); // =0.0000000059
    }
}

