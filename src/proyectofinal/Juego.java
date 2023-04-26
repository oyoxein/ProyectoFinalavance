/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;
import javax.swing.JOptionPane;
public class Juego {
    private Castle playerCastle;
    private Castle enemyCastle;
    private int waveNumber;
    private boolean isPlayerTurn;
    private Troop[] playerTroops;
    private Troop[] enemyTroops;
    

    
 public Juego() {
        playerCastle = new Castle();
        enemyCastle = new Castle();
        waveNumber = 1;
        isPlayerTurn = true;
    }
public int getPlayerCastleHealth() {
    return playerCastle.getHealth();
}

public int getEnemyCastleHealth() {
    return enemyCastle.getHealth();
}

public String getEnemyTroopInfo() {
        String info = "";
        for (Troop troop : enemyTroops) { // Modificar esta línea
            info += troop.getDescription() + "\n";
        }
        return info;
    }
   public void start(String[] playerTroopTypes) {
    while (playerCastle.getHealth() > 0 && enemyCastle.getHealth() > 0) {
        int maxTroopQuantity = waveNumber + 4;
        int[] playerTroopQuantities = getPlayerTroopQuantities(playerTroopTypes, maxTroopQuantity);
        playerTroops = createTroops(playerTroopTypes, playerTroopQuantities);
        enemyTroops = createEnemyTroops(waveNumber);

        while (playerCastle.getHealth() > 0 && enemyCastle.getHealth() > 0) {
            if (isPlayerTurn) {
                playerTurn(playerTroops, enemyCastle);
            } else {
                enemyTurn(enemyTroops, playerCastle);
            }
            isPlayerTurn = !isPlayerTurn;
        }

        waveNumber++; // Incrementar el número de oleada al final de cada ronda

        if (playerCastle.getHealth() == 0) {
            System.out.println("¡Has perdido! El castillo del enemigo ha sobrevivido.");
        } else {
            System.out.println("¡Has ganado! El castillo del enemigo ha sido destruido.");
        }
    }
}

private int[] getPlayerTroopQuantities(String[] playerTroopTypes, int maxTroopQuantity) {
    int[] playerTroopQuantities = new int[playerTroopTypes.length];
    int totalTroops = 0;

    for (int i = 0; i < playerTroopTypes.length; i++) {
        String message = "Por favor, ingresa la cantidad de " + playerTroopTypes[i] + " (0 a " + (maxTroopQuantity - totalTroops) + "):";
        String input = JOptionPane.showInputDialog(message);
        int quantity = Integer.parseInt(input);

        while (quantity < 0 || quantity > maxTroopQuantity - totalTroops) {
            message = "Cantidad inválida. Por favor, ingresa la cantidad de " + playerTroopTypes[i] + " (0 a " + (maxTroopQuantity - totalTroops) + "):";
            input = JOptionPane.showInputDialog(message);
            quantity = Integer.parseInt(input);
        }

        playerTroopQuantities[i] = quantity;
        totalTroops += quantity;
    }

    return playerTroopQuantities;
}

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

   private Troop[] createEnemyTroops(int waveNumber) {
    // El número de tropas enemigas aumenta con cada oleada
    int numEnemyTroops = waveNumber * 3;

    // Se crea un arreglo para almacenar las tropas enemigas
    Troop[] enemyTroops = new Troop[numEnemyTroops];

    // Se elige un tipo de tropa al azar para cada tropa enemiga
    for (int i = 0; i < numEnemyTroops; i++) {
        int troopTypeIndex = (int) (Math.random() * 3);
        if (troopTypeIndex == 0) {
            enemyTroops[i] = new Archer(1);
        } else if (troopTypeIndex == 1) {
            enemyTroops[i] = new Knight(1);
        } else {
            enemyTroops[i] = new Wizard(1);
        }
    }

    return enemyTroops;
}

  private void playerTurn(Troop[] playerTroops, Castle enemyCastle) {
    // Se muestra la información del castillo del jugador y del enemigo
    System.out.println("Castillo del jugador: " + playerCastle.getHealth() + " puntos de vida");
    System.out.println("Castillo del enemigo: " + enemyCastle.getHealth() + " puntos de vida");

    // Se muestra la información de las tropas del jugador
    System.out.println("Tropas del jugador:");
    for (int i = 0; i < playerTroops.length; i++) {
        System.out.println((i+1) + ". " + playerTroops[i].getDescription());
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
    
    public String getPlayerTroopInfo() {
    String info = "";
    for (Troop troop : playerTroops) {
        info += troop.getDescription() + "\n";
    }

    return info;
}
    
}
