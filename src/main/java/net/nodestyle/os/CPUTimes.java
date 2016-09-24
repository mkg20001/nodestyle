package net.nodestyle.os;

/**
 * Created by maciej on 23.09.16.
 */
public class CPUTimes {
    public Integer user;
    public Integer nice;
    public Integer sys;
    public Integer idle;
    public Integer irq;
    public CPUTimes(int user,int nice,int sys,int idle,int irq) {
        this.user=user;
        this.nice=nice;
        this.sys=sys;
        this.idle=idle;
        this.irq=irq;
    }
}
