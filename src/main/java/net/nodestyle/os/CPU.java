package net.nodestyle.os;

/**
 * Created by maciej on 23.09.16.
 */
public class CPU {
    public String model;
    public Integer speed;
    public CPUTimes times;
    public CPU(String model,int speed,CPUTimes times) {
        this.model=model;
        this.speed=speed;
        this.times=times;
    }
}
