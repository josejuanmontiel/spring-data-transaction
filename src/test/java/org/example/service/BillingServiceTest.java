package org.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.example.domain.Invoice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for {@link BillingService}.
 */
@ContextConfiguration(locations = { "classpath:springDataContext.xml", "classpath:springServiceContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class BillingServiceTest
{
  @Autowired
  private BillingService service;

  
  @Test
  public void testConcurrentGenerateAndUndoInvoice() {
      // Definir los valores de entrada
      int[] inputs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
      int[] returned = {3, 5, 8, 10};
      Invoice[] r = new Invoice[10];
      int[] inputs_second = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

      // Crear un array de CompletableFuture para almacenar los resultados
      CompletableFuture<Invoice>[] futures = new CompletableFuture[inputs.length];
      CompletableFuture<Invoice>[] futures_second = new CompletableFuture[inputs_second.length];
      
      // Invocar la función de manera concurrente para cada valor de entrada
      for (int i = 0; i < inputs.length; i++) {
          futures[i] = CompletableFuture.supplyAsync(() -> service.generateInvoice("AB","23"));
      }

      System.out.println("TEST - Lanzamos las operaciones concurrentes");
      // Esperar a que todas las tareas se completen
      CompletableFuture.allOf(futures).join();
      System.out.println("TEST - Terminan las operaciones concurrentes");
      
      // Validar los resultados
      for (int i = 0; i < inputs.length; i++) {
          int input = inputs[i];
          try {
        	  Invoice result = futures[i].get();
          	  r[i]=result;
              System.out.println("Resultado para el valor " + input + ": " + result);
              // Realizar validaciones adicionales según el resultado obtenido
              // ...
          } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
          }
      }        
      

      // Invocar la función de manera concurrente para cada valor de entrada
      for (int i = 0; i < returned.length; i++) {
          int input = returned[i];
          String refound = new String("AB"+input);
          int iter = returned[i];
          CompletableFuture.runAsync(() -> service.returnInvoice("AB","23",r[iter].getCode()));
      }
      for (int i = 0; i < inputs_second.length; i++) {
    	  String refound = new String(); 
          futures_second[i] = CompletableFuture.supplyAsync(() -> service.generateInvoice("AB","23"));
      }

      System.out.println("TEST - Lanzamos las operaciones concurrentes");
      // Esperar a que todas las tareas se completen
      CompletableFuture.allOf(futures_second).join();
      System.out.println("TEST - Terminan las operaciones concurrentes");
              
      
      // Validar los resultados
      for (int i = 0; i < inputs_second.length; i++) {
          int input = inputs_second[i];
          try {
        	  Invoice result = futures_second[i].get();
              System.out.println("Resultado para el valor " + input + ": " + result.getCode());
              // Realizar validaciones adicionales según el resultado obtenido
              // ...
          } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
          }
      }
  }      
  
  @Test
  public void testConcurrentGenerateInvoice() {
      // Definir los valores de entrada
      int[] inputs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

      // Crear un array de CompletableFuture para almacenar los resultados
      CompletableFuture<Invoice>[] futures = new CompletableFuture[inputs.length];

      // Invocar la función de manera concurrente para cada valor de entrada
      for (int i = 0; i < inputs.length; i++) {
          int input = inputs[i];
          futures[i] = CompletableFuture.supplyAsync(() -> service.generateInvoice("AB","23"));
      }

      // Esperar a que todas las tareas se completen
      CompletableFuture.allOf(futures).join();

      // Validar los resultados
      for (int i = 0; i < inputs.length; i++) {
          int input = inputs[i];
          try {
        	  Invoice result = futures[i].get();
              System.out.println("Resultado para el valor " + input + ": " + result.getCode());
              // Realizar validaciones adicionales según el resultado obtenido
              // ...
          } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
          }
      }
  }  
  
  
  /**
   * Tests that an invoice can be generated successfully.
   */
  @Test
  public void testGenerateInvoice()
  {
    final Invoice first = service.generateInvoice("AB","23");

    Assert.assertNotNull(first);
    Assert.assertNotNull(first.getCode());
    Assert.assertNotNull(first.getID());

    final Invoice second = service.generateInvoice("RQ","23");

    Assert.assertNotNull(second);
    Assert.assertNotNull(second.getCode());
    Assert.assertNotNull(second.getID());

    Assert.assertNotEquals(first.getCode(), second.getCode());
    Assert.assertNotEquals(first.getID(), second.getID());
  }
}
