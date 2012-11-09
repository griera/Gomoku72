package prop.gomoku.auxiliars;

import java.util.Scanner;

public class LecturaScanners implements Lectura {
    private Scanner scanner;
    
    public LecturaScanners() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Mètode que ens permet llegir un caràcter 
     * @return caràcter llegit
     */
    public char llegirChar() {
        return this.scanner.findInLine(".").charAt(0);
    }
    
    public byte llegirByte() {
        return this.scanner.nextByte();
    }
    public short llegirShort() {
        return this.scanner.nextShort();
    }
    public int llegirInt() {
        return this.scanner.nextInt();
    }
    public long llegirLong() {
        return this.scanner.nextLong();
    }
    public float llegirFloat() {
        return this.scanner.nextFloat();
    }
    public double llegirDouble() {
        return this.scanner.nextFloat();
    }
    public String llegirString() {
        return this.scanner.next();
    }
}