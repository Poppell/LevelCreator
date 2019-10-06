package ich.ich;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    GridLayout tilelayout;
    BorderLayout outerlayout;

    JPanel buttonspanel;
    JPanel controlpanel;

    JButton[][] buttons;

    char[][] level;

    int buttonsize;
    int rows;
    int columns;

    public UI(int rows, int columns){
        super();
        this.rows = rows;
        this.columns = columns;
        this.buttonsize = 32;
        this.level = new char[rows][columns];

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
        this.add(controlpanel, BorderLayout.SOUTH);


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void makeButtons(){
        for(int i = 0; i < buttons.length; i++){
            for(int j = 0; j < buttons[0].length;j++){
                buttons[i][j] = new JButton();
                buttons[i][j].setVisible(true);
               /* buttons[i][j].setBorder(BorderFactory.createEmptyBorder());
                buttons[i][j].setContentAreaFilled(false);*/
               this.buttonspanel.add( buttons[i][j]);
            }
        }
    }
}
