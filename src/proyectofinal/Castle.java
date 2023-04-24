/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author Sebastian
 */
public class Castle {
    private int health;

    public Castle() {
        health = 10;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
