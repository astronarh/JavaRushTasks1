package com.javarush.task.task37.task3711;

/**
 * Created by ShkerdinVA on 25.05.2017.
 */
public class Computer {
    private CPU cpu = new CPU();
    private Memory memory = new Memory();
    private HardDrive hardDrive = new HardDrive();

    public void run() {
        cpu.calculate();
        memory.allocate();
        hardDrive.storeData();
    }
}
