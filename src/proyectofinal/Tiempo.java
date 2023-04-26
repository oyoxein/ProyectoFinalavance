/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author Sebastian
 */
public class Tiempo extends Thread {
    private int tiempoTranscurrido = 0;
    private boolean running = true;

    public void stopTimer() {
        running = false;
    }

    public void run() {
        try {
            while (running) {
                // Incrementar el tiempo transcurrido cada segundo
                tiempoTranscurrido++;
                // Imprimir el tiempo transcurrido
                System.out.println("Tiempo transcurrido: " + tiempoTranscurrido + " segundos");
                // Dormir durante un segundo
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
