package prop.gomoku.auxiliars;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LecturaBuffers implements Lectura {
    public char llegirChar() {
        char c = ' ';
        try {
            BufferedInputStream in = new BufferedInputStream(System.in);
            c = (char) in.read();
        } catch (IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace(); // Ajuda a debugar errors del programa, imprimint
                                 // una traça de la pila on es van empilant els
                                 // mètodes cridat (la pila la gestiona la JVM)
        }
        return c;
    }
    public byte llegirByte() {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace();
        }
        return Byte.parseByte(s);
    }
    public short llegirShort() {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace();
        }
        return Short.parseShort(s);
    }
    public int llegirInt() {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace();
        }
        return Integer.parseInt(s);
    }
    public long llegirLong() {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace();
        }
        return Long.parseLong(s);
    }
    public float llegirFloat() {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace();
        }
        return Float.parseFloat(s);
    }
    public double llegirDouble() {
        String s = null;
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace();
        }
        return Double.parseDouble(s);
    }
    public String llegirString() {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch(IOException e) {
            System.out.println("Error durant la lectura per teclat");
            e.printStackTrace();
        }
        return s;
    }
}