/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import javax.swing.JOptionPane;

public class Juego {

    // Variables de instancia
    private Castle playerCastle;  // Castillo del jugador
    private Castle enemyCastle;   // Castillo del enemigo
    private int waveNumber;       // Número de oleada actual
    private boolean isPlayerTurn; // Indica si es el turno del jugador
    private Troop[] playerTroops; // Tropas del jugador
    private Troop[] enemyTroops;  // Tropas del enemigo

    //// Constructor de la clase
    public Juego() {
        playerCastle = new Castle();
        enemyCastle = new Castle();
        waveNumber = 1;
        isPlayerTurn = true;
    }

    // Getter para la vida del castillo del jugador
    public int getPlayerCastleHealth() {
        return playerCastle.getHealth();
    }
    // Getter para la vida del castillo del enemigo

    public int getEnemyCastleHealth() {
        return enemyCastle.getHealth();
    }

    // Método para obtener la información de las tropas enemigas
    public String getEnemyTroopInfo() {
        String info = "";
        for (Troop troop : enemyTroops) { // Modificar esta línea
            info += troop.getDescription() + "\n";
        }
        return info;
    }

    // Método para iniciar el juego
    public void start(String[] playerTroopTypes) {
        // Bucle principal del juego, se ejecuta mientras ambos castillos tengan vida
        while (playerCastle.getHealth() > 0 && enemyCastle.getHealth() > 0) {
            
            int maxTroopQuantity = waveNumber + 4;
            
            // Se obtienen las cantidades de tropas del jugador
            int[] playerTroopQuantities = getPlayerTroopQuantities(playerTroopTypes, maxTroopQuantity);
            
            // Se crean las tropas del jugador y del enemigo
            playerTroops = createTroops(playerTroopTypes, playerTroopQuantities);
            enemyTroops = new Troop[waveNumber * 3];
            createEnemyTroops(waveNumber * 3, enemyTroops, 0);

            // Bucle de turnos
            while (playerCastle.getHealth() > 0 && enemyCastle.getHealth() > 0) {
                if (isPlayerTurn) {
                    playerTurn(playerTroops, enemyCastle);  // Turno del jugador
                } else {
                    enemyTurn(enemyTroops, playerCastle); // Turno del enemigo
                }
                isPlayerTurn = !isPlayerTurn; // Se cambia el turno
            }

            waveNumber++; // Incrementar el número de oleada al final de cada ronda
            
            // Mensajes finales según el resultado
            if (playerCastle.getHealth() == 0) {
                System.out.println("¡Has perdido! El castillo del enemigo ha sobrevivido.");
            } else {
                System.out.println("¡Has ganado! El castillo del enemigo ha sido destruido.");
            }
        }
    }

         // Método para obtener las cantidades de tropas del jugador
    private int[] getPlayerTroopQuantities(String[] playerTroopTypes, int maxTroopQuantity) {
        
        // para almacenar la cantidad de cada tipo de tropa que el usuario ingresará.
        int[] playerTroopQuantities = new int[playerTroopTypes.length];
        // Variable "totalTroops" para llevar un seguimiento
        int totalTroops = 0;

        for (int i = 0; i < playerTroopTypes.length; i++) {
            String message = "Por favor, ingresa la cantidad de " + playerTroopTypes[i] + " (0 a " + (maxTroopQuantity - totalTroops) + "):";
            String input = JOptionPane.showInputDialog(message);
            
            int quantity = Integer.parseInt(input);
            int maxQuantity = (i == playerTroopTypes.length - 1) ? maxTroopQuantity - totalTroops - 1 : maxTroopQuantity - totalTroops;
            
             // Ciclo "while" para asegurarse de que el usuario ingrese una cantidad válida de tropas.
            while (quantity < 0 || quantity > maxTroopQuantity - totalTroops) {
                message = "Cantidad inválida. Por favor, ingresa la cantidad de " + playerTroopTypes[i] + " (0 a " + (maxTroopQuantity - totalTroops) + "):";
                input = JOptionPane.showInputDialog(message);
                quantity = Integer.parseInt(input);
            }
            // Se asigna la cantidad ingresada a la posición correspondiente en el arreglo "playerTroopQuantities".    
            playerTroopQuantities[i] = quantity;
            // Se actualiza la variable "totalTroops" con la cantidad de tropas ingresada en esta iteración.
            totalTroops += quantity;
        }

        return playerTroopQuantities;
    }
     // Método para crear tropas
    private Troop[] createTroops(String[] troopTypes, int[] troopQuantities) {
        Troop[] troops = new Troop[troopTypes.length];
        for (int i = 0; i < troopTypes.length; i++) {
            if (troopTypes[i].equals("wizard")) {
                troops[i] = new Wizard(troopQuantities[i]);
            } else if (troopTypes[i].equals("knight")) {
                troops[i] = new Knight(troopQuantities[i]);
            } else if (troopTypes[i].equals("archer")) {
                troops[i] = new Archer(troopQuantities[i]);
            }
        }
        return troops;
    }

    //Recursivdad - Crea una tropa enemiga y se llama asi misma para crear la siguiente
    //Hasta que se hayan creado todas las tropas requeridad
    private void createEnemyTroops(int numTroops, Troop[] enemyTroops, int index) {
        //  si se han creado todas las tropas requeridas
        if (index == numTroops) {
            return;
        }
        // Crear una tropa enemiga de forma aleatoria
        int troopTypeIndex = (int) (Math.random() * 3);
        Troop newTroop = null;
        if (troopTypeIndex == 0) {
            newTroop = new Archer(1);
        } else if (troopTypeIndex == 1) {
            newTroop = new Knight(1);
        } else {
            newTroop = new Wizard(1);
        }
        // Asignar un camino aleatorio a la tropa
        int path = Math.random() < 0.5 ? 0 : 1;
        if (index % 2 == 0) {
            path = 1 - path;
        }
        // Agregar la tropa al array
        newTroop.setPath(path);
        enemyTroops[index] = newTroop;
        
        // Llamar recursivamente para crear la siguiente tropa
        createEnemyTroops(numTroops, enemyTroops, index + 1);
    }
    
    // Método para ejecutar el turno del jugador
    private void playerTurn(Troop[] playerTroops, Castle enemyCastle) {
        // Se muestra la información del castillo del jugador y del enemigo
        System.out.println("Castillo del jugador: " + playerCastle.getHealth() + " puntos de vida");
        System.out.println("Castillo del enemigo: " + enemyCastle.getHealth() + " puntos de vida");

        // Se muestra la información de las tropas del jugador
        System.out.println("Tropas del jugador:");
        for (int i = 0; i < playerTroops.length; i++) {
            System.out.println((i + 1) + ". " + playerTroops[i].getDescription());
        }

        // Seleccionar tropas para el ataque
        int selectedTroopIndex = -1;
        while (selectedTroopIndex < 0 || selectedTroopIndex >= playerTroops.length) {
            String userInput = JOptionPane.showInputDialog("Elija el número de la tropa que desea enviar al ataque (1 - " + playerTroops.length + "):");
            selectedTroopIndex = Integer.parseInt(userInput) - 1;
            if (selectedTroopIndex < 0 || selectedTroopIndex >= playerTroops.length) {
                JOptionPane.showMessageDialog(null, "Entrada inválida, por favor ingrese un número válido.");
            }
        }

        // Atacar al castillo enemigo con las tropas seleccionadas
        playerTroops[selectedTroopIndex].attack(enemyCastle, playerTroops[selectedTroopIndex].getQuantity());
    }
    // Método para ejecutar el turno del enemigo
    private void enemyTurn(Troop[] enemyTroops, Castle playerCastle) {
        
        // Se muestra la información del castillo del jugador y del enemigo
        System.out.println("Castillo del jugador: " + playerCastle.getHealth() + " puntos de vida");
        System.out.println("Castillo del enemigo: " + enemyCastle.getHealth() + " puntos de vida");

        // Se muestra la información de las tropas del enemigo
        System.out.println("Tropas del enemigo:");
        for (Troop troop : enemyTroops) {
            System.out.println(troop.getDescription());
        }

        // Se ataca al castillo del jugador con las tropas enemigas
        for (int i = 0; i < enemyTroops.length; i++) {
            enemyTroops[i].attack(playerCastle, 1);
        }
    }
    // Método para obtener la información de las tropas del jugador
    public String getPlayerTroopInfo() {
        return getPlayerTroopInfoRecursidivdad(playerTroops, 0);
    }
    // Método recursivo para obtener la información de las tropas del jugador   
    private String getPlayerTroopInfoRecursidivdad(Troop[] troops, int index) {
        //si se ha llegado al final del array de tropas
        if (index >= troops.length) {
            return "";
        }
         // Retornar la descripción de la tropa actual y llamar recursivamente para las siguientes tropas
        return troops[index].getDescription() + "\n" + getPlayerTroopInfoRecursidivdad(troops, index + 1);
    }

}
