package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
    private double pocetna, krajnja;
    private boolean pocetna_pripada, krajnja_pripada;
    static final double eps = 0.000001;
    public Interval(double pocetna, double krajnja, boolean pocetna_pripada, boolean krajnja_pripada) throws IllegalArgumentException{
        if((krajnja - pocetna) < eps) throw new IllegalArgumentException("Neispravne granice intervala");
        this.krajnja=krajnja;
        this.pocetna=pocetna;
        this.krajnja_pripada=krajnja_pripada;
        this.pocetna_pripada=pocetna_pripada;
    }
    public Interval(){
        pocetna=krajnja=0;
        pocetna_pripada=krajnja_pripada=false;
    }
    public static Interval intersect(Interval i, Interval i2) {
        return i.intersect(i2);
    }
    public Interval intersect(Interval i){
        Interval novi=new Interval();
        novi.pocetna=Math.max(i.pocetna, pocetna);
        novi.krajnja=Math.min(i.krajnja, krajnja);
        if(Math.abs(novi.pocetna-pocetna)<=eps)  novi.pocetna_pripada=pocetna_pripada;
        if(Math.abs(novi.pocetna-i.pocetna)<=eps) novi.pocetna_pripada=i.pocetna_pripada;
        if(Math.abs(novi.krajnja-krajnja)<=eps) novi.krajnja_pripada=krajnja_pripada;
        if(Math.abs(novi.krajnja-i.krajnja)<=eps) novi.krajnja_pripada=i.krajnja_pripada;
        return novi;
    }
    public boolean isIn(double tacka) {
        if(Math.abs(tacka-pocetna)<eps && !pocetna_pripada) return false;
        if(Math.abs(tacka-krajnja)<eps && !krajnja_pripada) return false;
        return pocetna - tacka < eps && tacka - krajnja < eps ;
    }

    public boolean isNull() {
        return pocetna == 0 && krajnja == 0 && !pocetna_pripada && !krajnja_pripada;
    }

    @Override
    public String toString() {
        String z2="]";
        String z1="[";
        if(!pocetna_pripada) z1="(";
        if(!krajnja_pripada) z2=")";
        if(isNull()) return "()";
        return z1 + pocetna +","+ krajnja + z2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Double.compare(interval.pocetna, pocetna) == 0 &&
                Double.compare(interval.krajnja, krajnja) == 0 &&
                pocetna_pripada == interval.pocetna_pripada &&
                krajnja_pripada == interval.krajnja_pripada;
    }
}
