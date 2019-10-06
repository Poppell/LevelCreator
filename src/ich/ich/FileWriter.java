package ich.ich;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriter {

    BufferedWriter writer;
    ArrayList<String> lines;
    String filename;

    public FileWriter(String filename, ArrayList<String> data){
            this.filename = filename;
            this.lines = data;
    }

    public void write(){
        try {
            writer = new BufferedWriter(new java.io.FileWriter(this.filename));
            lines.forEach(line -> {
                try {
                    writer.append(String.join(";",line.split("")));
                    writer.append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.flush();
            writer.close();
        }catch (IOException iox){
            iox.printStackTrace();
        }
    }
}
