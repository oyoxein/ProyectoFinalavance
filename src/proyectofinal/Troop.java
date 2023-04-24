/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author Sebastian
 */
public class Troop {
    private String name;
    private int health;
    private int damage;
    private int attackSpeed;
    private int quantity;

    public Troop(String name, int health, int damage, int attackSpeed) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.quantity = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void attack(Castle target, int numTroops) {
        int totalDamage = damage * numTroops;
        target.takeDamage(totalDamage);
        System.out.println(name + " hizo " + totalDamage + " puntos de daño a " + target.getName() + " con " + numTroops + " tropas.");
    }

    public String getDescription() {
        return name + " - Salud: " + health + ", Daño: " + damage + ", Velocidad de ataque: " + attackSpeed + ", Cantidad: " + quantity;
    }
}
