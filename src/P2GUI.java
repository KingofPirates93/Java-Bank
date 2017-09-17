import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;<<<<<<< Updated upstream
public class P2GUI {
=======
/**
 * Program: Java Bank
 * Package: Default
 * Class: P2GUI
 * Author: Swapnil Patel
 * Date: 9/14/2017
 */


//imports
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class P2GUI extends JFrame {
//variable declaration//

    //Building the ATM JFRAME//

    private JFrame JFATMFRAME;
    private JButton JFATMWITHDRAW;
    private JButton JFATMDEPOSIT;
    private JButton JFTRANSFER;
    private JButton JFBALANCE;
    private JRadioButton JFCHECKING;
    private JRadioButton JFSAVIGNS;
    private JTextArea JFDISPLAY;
    private JPanel JFPANEL;

    //creating account for savings and checking//
    private Account CHECKING;
    private Account SAVINGS;
    private Account CURRENT;

    //--------------------------------------------------------------------------------------------------------------------//
    //method to initialize GUI components//
    private void createView() {

        //ini accounts
        CHECKING = new Account( 1250 );
        SAVINGS = new Account( 3500 );
        CURRENT = new Account( 0 );

        //ini buttons and action listeners
        //DEPOSIT BUTTON
        JFATMDEPOSIT = new JButton( "Deposit" );
        JFATMDEPOSIT.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DEPOSITBUTTONVAL = JOptionPane.showInputDialog( JFATMDEPOSIT, "Please Enter Amount You Want to Deposit: " );
                try {
                    CURRENT.DEPOSIT( Double.parseDouble( DEPOSITBUTTONVAL ) );
                    JFDISPLAY.setText( "$" + DEPOSITBUTTONVAL + " Deposited into your " + CURRENT + " Account." );
                }catch (NumberFormatException e1){}

            }
        } );

        //WITHDRAW BUTTON
        JFATMWITHDRAW = new JButton( "WITHDRAW" );
        JFATMWITHDRAW.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String WITHDRAWBUTTONVAL = JOptionPane.showInputDialog( JFATMWITHDRAW, "Please Enter Amount You would Like to Withdraw: " );
                try {
                    CURRENT.WITHDRAW( Double.parseDouble( WITHDRAWBUTTONVAL ) );

                } catch (NumberFormatException e1) {
                } catch (InsufficentFundsExcpetion el) {}
            }

        } );

        //TRANSFER BUTTON
        JFTRANSFER = new JButton( "TRANSFER TO" );
        JFTRANSFER.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] OPTIONS = new String[]{"Checking", "Savings", "CANCEL"};
                int reponse = JOptionPane.showOptionDialog( null,"Please Select an Option to TRANSFER MONEY TO",null, JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,OPTIONS,OPTIONS[0] );
                //bulletproofing selections
                if (reponse == 0 && (!(JFCHECKING.isSelected()))) {
                    try {
                        String TRANSFERBUTTONVAL = JOptionPane.showInputDialog( JFTRANSFER, "Please Enter Amount You Would Like to Transfer " );
                        SAVINGS.WITHDRAW( Double.parseDouble( TRANSFERBUTTONVAL ) );
                        CHECKING.DEPOSIT( Double.parseDouble( TRANSFERBUTTONVAL ) );
                        JFDISPLAY.setText( "$" + TRANSFERBUTTONVAL + " Was Transferred to " + CURRENT );

                    } catch (NumberFormatException e1) {
                    } catch (InsufficentFundsExcpetion insufficentFundsExcpetion) {
                        insufficentFundsExcpetion.printStackTrace();
                    }
                } else if (reponse == 1 && (!(JFSAVIGNS.isSelected()))) {
                    try {
                        String TRANSFERBUTTONVAL = JOptionPane.showInputDialog( JFTRANSFER, "Please Enter Amount You Would Like to Transfer " );
                        CHECKING.WITHDRAW( Double.parseDouble( TRANSFERBUTTONVAL ) );
                        SAVINGS.DEPOSIT( Double.parseDouble( TRANSFERBUTTONVAL ) );
                        JFDISPLAY.setText( "$" + TRANSFERBUTTONVAL + " Was Transferred to " + CURRENT );
                    } catch (NumberFormatException el) {
                    } catch (InsufficentFundsExcpetion insufficentFundsExcpetion) {
                    }
                } else if (reponse == 2) {
                    JOptionPane.showMessageDialog( null,"You Have Cancelled your Transfer." );
                } else {
                    JOptionPane.showMessageDialog( null, "You Cant Transfer from the same TWO accounts!! Please select a different account and continue" );
                }
            }
        } );

        //SHOW BALANCE BUTTON
        JFBALANCE = new JButton( "SHOW BALANCE" );
        JFBALANCE.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFDISPLAY.setText( String.valueOf("Your Current Balance is: $" + CURRENT.getBalance() ) );
            }
        } );

        // SAVINGS RADIO BUTTON
        JFSAVIGNS = new JRadioButton( "SAVINGS" );
        JFSAVIGNS.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JFSAVIGNS.isSelected()) {
                    CURRENT = SAVINGS;
                    JFCHECKING.setSelected( false );

                }
            }
        } );

        //CHECKING RADIO BUTTON
        JFCHECKING = new JRadioButton( "CHECKINGS" );
        JFCHECKING.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JFCHECKING.isSelected()) {
                    CURRENT = CHECKING;
                    JFSAVIGNS.setSelected( false );
                }
            }
        } );
//--------------------------------------------------------------------------------------------------------------------//

        //Placing everything together

        //Initial frame and panel//
        JFATMFRAME = new JFrame();
        //To see or to not to see
        JFATMFRAME.setVisible( true );
        //Setting default dimension
        JFATMFRAME.setSize(600,600);
        //Disable resizing
        JFATMFRAME.setResizable( true );
        //Setting program start position
        JFATMFRAME.setLocationRelativeTo( null );
        //Setting a default title
        JFATMFRAME.setTitle( "JAVA BANK MACHINE" );
        //Setting Default close operation
        JFATMFRAME.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );


        // JPANEL Config **BUTTONS**
        JFPANEL = new JPanel();
        JFATMFRAME.getContentPane().add( JFPANEL );
        JFPANEL.setLayout( new GridLayout( 4, 2 ) );
        JFPANEL.add( JFATMDEPOSIT );
        JFPANEL.add(JFATMWITHDRAW);
        JFPANEL.add(JFTRANSFER);
        JFPANEL.add(JFBALANCE);
        JFPANEL.add(JFSAVIGNS, CENTER_ALIGNMENT);
        JFPANEL.add(JFCHECKING, CENTER_ALIGNMENT);
        JFDISPLAY = new JTextArea();
        JFDISPLAY.setSize( 300,100 );
        JFDISPLAY.setEditable( false );
        JFDISPLAY.setLineWrap( true );
        JFDISPLAY.setWrapStyleWord( true );
        JFPANEL.add( JFDISPLAY , BorderLayout.SOUTH);
        pack();







    }



    //constructor
    public P2GUI() {
        createView();
    }


    //main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                P2GUI ATMGUI = new P2GUI();

            }
        } );


    }
}



