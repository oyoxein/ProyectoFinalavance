/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;
import javax.swing.JOptionPane;
import proyectofinal.Tiempo;
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // Crear juego y comenzar partida
    Juego game = new Juego();
    Tiempo tiempo = new Tiempo();
    String[] playerTroopTypes = {"archer", "knight", "wizard"};
    //int[] playerTroopQuantities = {10, 5, 3};
    tiempo.start();
    game.start(playerTroopTypes);
    // Imprimir información de los castillos y tropas después de la partida
    System.out.println("Información del castillo del jugador:");
    System.out.println("Salud: " + game.getPlayerCastleHealth());
    System.out.println("------------------------");
    System.out.println("Información del castillo del enemigo:");
    System.out.println("Salud: " + game.getEnemyCastleHealth());
    System.out.println("------------------------");
    System.out.println("Información de las tropas del jugador:");
    System.out.println(game.getPlayerTroopInfo());
    System.out.println("------------------------");
    System.out.println("Información de las tropas del enemigo:");
    System.out.println(game.getEnemyTroopInfo());
    tiempo.stopTimer();
}}
