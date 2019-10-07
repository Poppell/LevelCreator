package ich.ich;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class UI extends JFrame {

    FileWriter writer;

    GridLayout tilelayout;
    BorderLayout outerlayout;

    JPanel buttonspanel;
    JPanel controlpanel;

    JButton[][] buttons;

    JButton btntree;
    JButton btnwall;
    JButton btnclear;
    JButton btnsave;

    JTextField area;




    char[][] level;

    char active;

    int buttonsize;
    int rows;
    int columns;

    public UI(int columns, int rows){
        super();
        this.rows = rows;
        this.columns = columns;
        this.buttonsize = 32;
        this.level = new char[rows][columns];
        this.active = ' ';
       // this.writer = new FileWriter();

        this.buttonspanel = new JPanel();

        this.setSize(this.columns * this.buttonsize,this.rows * this.buttonsize + 100);
        this.outerlayout = new BorderLayout();
        this.tilelayout = new GridLayout();
        tilelayout.setColumns(columns);
        tilelayout.setRows(rows);

        this.buttonspanel.setLayout(tilelayout);

        this.setLayout(outerlayout);
        this.add(buttonspanel);
        this.buttonspanel.setSize(this.buttonspanel.getParent().getWidth(),this.buttonspanel.getParent().getHeight() -100 );
        this.buttonspanel.setVisible(true);



        buttons = new JButton[this.rows][this.columns];
        init();

        controlpanel = new JPanel();
    //    controlpanel.setVisible(true);

        addControl();


        this.add(controlpanel, BorderLayout.SOUTH);


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private String activeToPath(char active){
        String path = "images/";
        switch (active){
            case 'b':
                path = path + "tree.jpg";
                break;
            case 'm':
                path = path + "wall.jpg";
                break;
            case ' ':
                path = path + "empty.jpg";
                break;
            default:
                path = path+ "empty.jpg";

        }
        return path;
    }

    private void init(){
        for(int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[0].length;j++){
                final int x = i;
                final int y = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setVisible(true);
                buttons[i][j].addActionListener(e -> {
                    buttons[x][y].setIcon(new ImageIcon(activeToPath(this.active)));
                    level[x][y] = this.active;
                });
                buttons[i][j].setBackground(Color.WHITE);
               this.buttonspanel.add( buttons[i][j]);

               level[i][j]= ' ';
            }
        }
    }

    private void addControl(){
        btntree = new JButton();
        btntree.setIcon(new ImageIcon("images/tree.jpg"));
        btntree.addActionListener(e -> this.active = 'b');
        controlpanel.add(btntree);

        btnwall = new JButton();
        btnwall.setIcon(new ImageIcon("images/wall.jpg"));
        btnwall.addActionListener(e -> this.active = 'm');
        controlpanel.add(btnwall);

        btnclear = new JButton("Remove");
        btnclear.addActionListener(e -> this.active = ' ');
        controlpanel.add(btnclear);

        area = new JTextField(20);

        controlpanel.add(area);

        btnsave = new JButton("Save");
        btnsave.addActionListener(e -> writeFile());
        controlpanel.add(btnsave);

    }

    private void writeFile(){
        if(  area.getText().length() > 0 ) {
            File file = new File(area.getText()+ ".csv");
            int writefile = 0;
            if(file.exists()){
              writefile =  JOptionPane.showConfirmDialog(null, "Datei überschreiben?");

            }
            if(writefile == 0){
                final ArrayList<String> temp = this.getLevel();
                writer = new FileWriter(area.getText() + ".csv", temp);
                writer.write();
            }

        }else{
            System.out.println("Dateiname!");
            JOptionPane.showMessageDialog(null,"Gib einen Dateinamen an!");
        }
    }

    public ArrayList<String> getLevel(){
        ArrayList<String> temp = new ArrayList<>();
        for(char[] item: level){
            temp.add(new String(item));
        }

        return temp;
    }
}
