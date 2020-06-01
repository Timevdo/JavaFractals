public class ComplexNum {
    
    private double a;
    private double b;

    public ComplexNum(double a_value, double b_value){
        a = a_value;
        b = b_value;
    }

    public ComplexNum(ComplexNum other){
        a = other.a;
        b = other.b;
    }

    public double getReal() {return a;}
    public double getImag() {return b;}

    public void add(ComplexNum other){
        a += other.a;
        b += other.b;
    }

    public void add(int n){
        a += n;
    }

    public void multiply(ComplexNum other){
        a = a*other.a - b*other.b;
        b = a*other.b + b*other.a;
    }

    public void multiply(int n){
        a *= n;
        b *= n;
    }

    public void square(){
        double atemp = a*a - b*b;
        b = 2*a*b;
        a = atemp;
    }

    public double modulus(){
        return Math.sqrt(a*a + b*b);
    }

    public String toString(){
        return a + " + " + b + "i";
    }

}