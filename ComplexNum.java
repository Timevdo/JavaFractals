public class ComplexNum {
    
    private double a;
    private double b;

    /**
     * Creates a new complex number in the form a + bi
     * @param a_value real component
     * @param b_value imaginary component
     */
    public ComplexNum(double a_value, double b_value){
        a = a_value;
        b = b_value;
    }

    /**
     * Copy constructor
     * @param other
     */
    public ComplexNum(ComplexNum other){
        a = other.a;
        b = other.b;
    }

    //getter methods
    public double getReal() {return a;}
    public double getImag() {return b;}

    /**
     * Adds a complex number to this complex number
     * @param other
     */
    public void add(ComplexNum other){
        a += other.a;
        b += other.b;
    }

    /**
     * Adds a real 
     * @param n
     */
    public void add(double n){
        a += n;
    }

    public void multiply(ComplexNum other){
        double atemp = a*other.a - b*other.b;
        b = a*other.b + b*other.a;
        a = atemp;
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