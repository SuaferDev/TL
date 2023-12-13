package org.suafer;


/*
Класс: Структура
Содержит поля: название, может ли быть разрушена, разрешена ли и прочность и состояние
Если строение стоит перед выстрелом, то из урона будет вычтено значение прочности строения, если оно разрушено, то ничего не будет вычитаться
При каждом выстреле значение состояния уменьшается на значение урона от попадания, если состояние==0, то строение разрушено
 */

public class  Structure{

    private String name;
    private final boolean isDestroyed;
    private final double strength;
    private double state;
    private StructureStatus structureStatus;

    public Structure(String name, boolean isDestroyed, double strength, double state, StructureStatus structureStatus) {
        this.name = name;
        this.isDestroyed = isDestroyed;
        this.strength = strength;
        this.state = state;
        this.structureStatus = structureStatus;
    }

    public Structure(String name, boolean isDestroyed, double strength, double state) {
        this.name = name;
        this.isDestroyed = isDestroyed;
        this.strength = strength;
        this.state = state;
        this.structureStatus = StructureStatus.OnlyStructure;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public double getStrength() {
        return strength;
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public StructureStatus getStructureStatus() {
        return structureStatus;
    }

    public void setStructureStatus(StructureStatus structureStatus) {
        this.structureStatus = structureStatus;
    }
}
