//   1.  The perimeter of a rectangle is 230 cm. If the length of the rectangle is 70 cm, find its breadth and area.

class FindValues{
    public int perimeter ;
    public int length;
    public int area;
    public int breadth;
    
    public setPerimeter(int perimeter){
        perimeter = this.perimeter;
    }

    public setlength(int length){
        length= this.length;
    }

public int getBreadth(int perimeter, int length ){
    breadth =(perimeter/2)-length;
    return breadth ;

    System.out.println("breadth is :"breadth);
}

public double getArea(int length, int breadth){
    area = length*breadth;
    return area;

    System.out.println(" area is : "area );
}



}
class Demo{
    public static void main(String args[]){

        FindValues f1 = new FindValues;
        

    }
}