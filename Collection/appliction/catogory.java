import java.util.*;
class Product{
    private String name;
    public Product(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return name;
    }
}

class Category{
    private String name;
    ArrayList<Product> li=new ArrayList<>();
    public Category(String name)
    {
        this.name=name;
    }
    public void add(Product c)
    {
        li.add(c);
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<Product> getList()
    {
        return li;
    }
}
class Test{
    public static void main(String args[])
    {
        Category c1=new Category("furniture");
        Category c2=new Category("cosmatic");
        Category c3=new Category("electronic");

        c1.add(new Product("sofa"));
        c1.add(new Product("bench"));
        c1.add(new Product("bathtub"));

        c2.add(new Product("lipstic"));
        c2.add(new Product("vasline"));
        c2.add(new Product("boroplus"));

        HashMap<String,ArrayList<Product>> hp= new HashMap<>();
        hp.put(c1.getName(),c1.getList());
        hp.put(c2.getName(),c2.getList());

        Set<Map.Entry<String,ArrayList<Product>>> set1=hp.entrySet();

        for(Map.Entry<String,ArrayList<Product>> e: set1)
        {
            System.out.println(e.getKey()+" "+e.getValue());
        }


        

    }
}