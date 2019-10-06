package ich.ich;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> testdata = new ArrayList<>();
         testdata.add(       "mmmmmbbbb     ");
          testdata.add(      "m           mm");
           testdata.add(     "m mmm bbbb    ");


      /*   FileWriter writer = new FileWriter("test.csv",testdata );
        writer.write();
       testdata.forEach(item ->{
            item = String.join(";", item.split(""));
            System.out.println(item);
        });*/
     //  testdata.forEach(item -> System.out.println(item));

        UI ui = new UI(20,20);
    }
}
