/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Calculadora;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fernandobarbaperez
 */
public class CalculadoraTest {
    
    public CalculadoraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of noTieneErrores method, of class Calculadora.
     */
    @Test
    public void testNoTieneErrores() {
        System.out.println("noTieneErrores");
        String operacion = "-2+(3.2^(8/-3)*(567.9-4)+-2)";
        String expResult = "-2+(3.2^(8/-3)*(567.9-4)+-2)";
        String result = Calculadora.noTieneErrores(operacion);
        assertEquals(expResult, result);

    }

    /**
     * Test of divideOperacion method, of class Calculadora.
     */
    @Test
    public void testDivideOperacion() {
        System.out.println("divideOperacion");
        String operacion = "-2+(3.2^(8/-3)*(567.9-4)+-)2)";
        String expResult=null;
        ArrayList<String> result = Calculadora.divideOperacion(operacion);
        assertEquals(expResult, result); 
    }

    /**
     * Test of convierteInfijaAPostfija method, of class Calculadora.
     */
    @Test
    public void testConvierteInfijaAPostfija() {
        System.out.println("convierteInfijaAPostfija");
        String operacion = "-2+(3.(2^(8/-3)*(5)67.9)+-2)";
        ArrayList<String> expResult = null;
        ArrayList<String> result = Calculadora.convierteInfijaAPostfija(operacion);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of evaluaOperacion method, of class Calculadora.
     */
    @Test
    public void testEvaluaOperacion() {
        System.out.println("evaluaOperacion");
        String operacion = "-2+(3.2^(8/-3)*(567.9-4)+-2)";
        String expResult = "21.359196314493577";
        String result = Calculadora.evaluaOperacion(operacion);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of main method, of class Calculadora.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Calculadora.main(args);
       
    }
    
}
