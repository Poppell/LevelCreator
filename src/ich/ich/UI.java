package ich.ich;

import javax.swing.*;
import java.awt.*;
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

    public UI(int rows, int columns){
        super();
        this.rows = rows;
        this.columns = columns;
        this.buttonsize = 32;
        this.level = new char[rows][columns];
        this.active = ' ';
       // this.writer = new FileWriter();

        this.buttonspanel = new JPanel();

        this.setSize(this.rows * this.buttonsize,this.columns * this.buttonsize + 100);
        this.outerlayout = new BorderLayout();
        this.tilelayout = new GridLayout();
        tilelayout.setColumns(rows);
        tilelayout.setRows(columns);

        this.buttonspanel.setLayout(tilelayout);

        this.setLayout(outerlayout);
        this.add(buttonspanel);
        this.buttonspanel.setSize(this.buttonspanel.getParent().getWidth(),this.buttonspanel.getParent().getHeight() -100 );
        this.buttonspanel.setVisible(true);



        buttons = new JButton[this.rows][this.columns];
        makeButtons();

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

    private void makeButtons(){
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
               /* buttons[i][j].setBorder(BorderFactory.createEmptyBorder());
                buttons[i][j].setContentAreaFilled(false);*/
               this.buttonspanel.add( buttons[i][j]);
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
        btnsave.addActionListener(e -> {for(char[] item: level){
            for(char charitem: item){
                if(charitem == '\u0000'){
                    charitem = ' ';
                }
                System.out.print(charitem);
            }
            System.out.println("");

            }
        });
        controlpanel.add(btnsave);

    }

    public ArrayList<String> getLevel(){
        ArrayList<String> temp = new ArrayList<>();
        for(char[] item: level){
            temp.add(new String(item));
        }

        return temp;
    }
}
