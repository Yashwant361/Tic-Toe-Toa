package TicToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tictoe extends JFrame implements ActionListener {

    JLabel heading , clockLabel /*,players_Turn */;
    Font font = new Font("",Font.BOLD,40);
    JPanel mainPanel;

    JButton [] btns = new JButton[9]; // Array for Button --> use in game

    // Game instance variable..

    int gameChance[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer = 0;

    int wps[][]={
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6},
    };
    int winner = 2;

    boolean gameOver = false; // tracking the game



    Tictoe(){
        setTitle("Tic Tac Toe");
        setSize(800,800);
        ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGui();
        setVisible(true);
    }

    private void createGui()
    {
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());

        // north heading/..
        heading = new JLabel("Tic Tac Toe");
      //  heading.setIcon(new ImageIcon(getClass().getResource("logo.png")));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(heading,BorderLayout.NORTH);

        // Creating object of JLabel for clock
        clockLabel = new JLabel("Clock");
        clockLabel.setFont(font);

//        players_Turn = new JLabel("Player 1's Turn");
//        players_Turn.setFont(font);
//        players_Turn.setHorizontalAlignment(SwingConstants.CENTER);
//        players_Turn.setForeground(Color.white);
//        this.add(players_Turn,BorderLayout.SOUTH);

        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.white);

        this.add(clockLabel,BorderLayout.SOUTH);

        // Adding clock to the SOUTH in BorderLayout
        Timer timer = new Timer(1000, new ActionListener()

        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   String dateTime = new Date().toString();
                String dateTime = new Date().toLocaleString(); // To add AM / PM

                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:a");
                clockLabel.setText(dateTime);
            }
        });

        timer.start(); // Calling the start

        ///// Panel Section...
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));

        for (int i =1 ;i<=9;i++){
            JButton btn = new JButton();
         //   btn.setIcon(new ImageIcon(getClass().getResource("0.jpeg")));
            btn.setBackground(Color.decode("#90caf9"));

            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1] = btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1)); // Name of each button

            this.add(mainPanel,BorderLayout.CENTER); /** Here this. represents the frame */
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButon =(JButton) e.getSource();

        String nameStr = currentButon.getName();
        System.out.println(nameStr);

        int name = Integer.parseInt(nameStr.trim());


        if (gameOver == true)
        {
            JOptionPane.showMessageDialog(this,"Game Already over..");
            return;
        }

       if( gameChance[name] ==2){
           if (activePlayer == 1){
               currentButon.setIcon(new ImageIcon(getClass().getResource("X.png")));

               gameChance[name] = activePlayer;
               activePlayer=0;

//               //Update the player turn label
//               players_Turn.setText("Player 1's Turn");

           }else {
               currentButon.setIcon(new ImageIcon(getClass().getResource("0.png")));

               gameChance[name] = activePlayer;
               activePlayer=1;

//               //Update the player turn label
//               players_Turn.setText("Player 2's Turn");
           }

           //find the winner.......
           for (int []temp:wps)
           {
               if ((gameChance[temp[0]] == gameChance[temp[1]]) && (gameChance[temp[1]] == gameChance[temp[2]]) && gameChance[temp[2]]!=2){

                     winner = gameChance[temp[0]];
                     gameOver =true;
                     JOptionPane.showMessageDialog(null,"Player "+ winner + " has won the game..");

                     int i = JOptionPane.showConfirmDialog(this,"Do you play more ??");
                      if (i == 0){ // if user wants to play more
                          this.setVisible(false);
                          new Tictoe();
                      }
                      else if (i==1) { // if user don't wants to play
                          System.exit(0);
                      }
                      else {
                      //    System.exit(0);
                      }
                   System.out.println(i);
                      break;
               }
           }

           // Draw logic  ..............
          int y = 0;  // y is counter variable
           for (int x:gameChance)
           {
                  if (x ==2){
                      y++;
                      break;
                  }
           }
           if (y==0 && gameOver ==false ){
               JOptionPane.showMessageDialog(null,"Its draw....");

               int i = JOptionPane.showConfirmDialog(this,"Play more??");
               if ( i == 0)
               {
                   this.setVisible(false);
                   new Tictoe();
               }
               else if (i==1) {
                   System.exit(0);
               }
               else {
                      // here user not choose any Option..
               }
               gameOver = true;
           }
       }
       else {
           JOptionPane.showMessageDialog(this,"Position already occupied");
       }
    }
}
