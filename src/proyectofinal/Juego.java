/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author Sebastian
 */
public class Juego {
    private Castle playerCastle;
    private Castle enemyCastle;
    private int waveNumber;
    private boolean isPlayerTurn;
    

    
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
        Iterable<Troop> EnemyTroops = null;
    for (Troop troop : EnemyTroops) {
        info += troop.getDescription() + "\n";
        
       
    }

    return info;

}
    public void start(String[] playerTroopTypes, int[] playerTroopQuantities) {
        Troop[] playerTroops = createTroops(playerTroopTypes, playerTroopQuantities);
        Troop[] enemyTroops = createEnemyTroops(waveNumber);

        while (playerCastle.getHealth() > 0 && enemyCastle.getHealth() > 0) {
            if (isPlayerTurn) {
                playerTurn(playerTroops, enemyCastle, playerTroopQuantities);
            } else {
                enemyTurn(enemyTroops, playerCastle);
            }
            isPlayerTurn = !isPlayerTurn;
        }

        if (playerCastle.getHealth() == 0) {
            System.out.println("¡Has perdido! El castillo del enemigo ha sobrevivido.");
        } else {
            System.out.println("¡Has ganado! El castillo del enemigo ha sido destruido.");
        }
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

    private void playerTurn(Troop[] playerTroops, Castle enemyCastle, int[] troopQuantities) {
    // Se muestra la información del castillo del jugador y del enemigo
    System.out.println("Castillo del jugador: " + playerCastle.getHealth() + " puntos de vida");
    System.out.println("Castillo del enemigo: " + enemyCastle.getHealth() + " puntos de vida");

    // Se muestra la información de las tropas del jugador
    System.out.println("Tropas del jugador:");
    for (Troop troop : playerTroops) {
        System.out.println(troop.getDescription());
    }

    // Se ataca al castillo enemigo con las tropas seleccionadas
    for (int i = 0; i < playerTroops.length; i++) {
        playerTroops[i].attack(enemyCastle, troopQuantities[i]);
    }
}
    private void enemyTurn(Troop[] enemyTroops, Castle playerCastle) {
        // Código para que el enemigo envíe sus tropas a la batalla y ataque el castillo del jugador
    }
}
