package org.suafer;

public class Field {

    private Tank tank;
    private Structure structure;

    public Field(Tank tank, Structure structure) {this.tank = tank;this.structure = structure;}
    public Field(Tank tank) {this.tank = tank;this.structure = null;}
    public Field(Structure structure) {this.tank = null; this.structure = structure;}
    public Field() {this.tank = null; this.structure = null;}

    public Tank moveTank() {
        Tank t = tank;
        this.tank = null;
        return t;
    }

    public Tank getTank() {
        return tank;
    }
    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Structure getStructure() {
        return structure;
    }
    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}

