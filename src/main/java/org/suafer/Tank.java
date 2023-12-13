package org.suafer;

public class Tank {
    private final String name;
    private double health;
    private final double damage;
    private final int speed;
    private final double armor;
    private Ammo ammo;
    private int ammo_count;
    private TankStatus status;//Сделать через Enum. Сделать расширяемость, солид

    public Tank(String name, double health, double damage, int speed, double armor, Ammo ammo, int ammo_count) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.armor = armor;
        this.ammo = ammo;
        this.ammo_count = ammo_count;
        this.status = TankStatus.ShootMove;
    }

    public boolean checkShoot(){
        return ammo_count>0 && status==TankStatus.ShootMove;
    }

    public double shoot() {
        if(ammo_count>0){
            ammo_count--;
            return damage*ammo.getBonus();
        }
        return 0;
    }
    /*
    //Переименовать в doDamage + добавить проверку status
    public void setDamage(double damage){
        this.health=Math.max(health-damage, 0);
    }*/
    public void doDamage(double damage){
        this.health=Math.max(health-damage, 0);
        if(health==0){this.status=TankStatus.Dead;}
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public void addAmmo(int count){ammo_count+=count;}
    public void setAmmo(Ammo ammo){this.ammo=ammo;}
    public void setAmmo(Ammo ammo, int count){this.ammo=ammo; this.ammo_count = count;}
    public TankStatus getStatus() {return status;}
    public double getHealth() {return health;}
    public void setStatus(TankStatus status) {this.status = status;}
    public double getArmor() {
        return armor;
    }
    public int getAmmoCount(){return ammo_count;}
}