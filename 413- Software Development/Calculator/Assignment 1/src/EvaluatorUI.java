import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

  Evaluator evaluator = new Evaluator();
  private TextField txField = new TextField();
  private Panel buttonPanel = new Panel();
  

  // total of 20 buttons on the calculator,
  // numbered from left to right, top to bottom
  // bText[] array contains the text for corresponding buttons
  private static final String[] bText = 
  {
    "CE", "C", "(", ")",
    "7", "8", "9", "+", 
    "4", "5", "6", "-", 
    "1", "2", "3", "*", 
    "0", "^", "/", "=" 
  };
  private Button[] buttons = new Button[ bText.length ];

  public static void main(String argv[]) 
  {
    EvaluatorUI calc = new EvaluatorUI();
    
  }

  public EvaluatorUI() 
  {
    setLayout( new BorderLayout() );

    add( txField, BorderLayout.NORTH );
    txField.setEditable( false );
    txField.setFont(new Font("superAwesomeText" ,Font.BOLD, 12));

    add( buttonPanel, BorderLayout.CENTER );
    buttonPanel.setLayout( new GridLayout( 5, 4 ));

    //create 20 buttons with corresponding text in bText[] array
    for ( int i = 0; i < 20; i++ ) 
    {
      buttons[ i ] = new Button( bText[ i ]);
    }
    
    /* Changing Colors for Buttons */
    buttons[0].setBackground(Color.RED);
    buttons[1].setBackground(Color.RED);
    for (int i = 2; i < 20; i++)
    {
        if (i%4 != 3 && i > 3  && i < 17) 
        {
            buttons[i].setBackground(Color.ORANGE);
        }
        else 
        {
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
        }
    }

    //add buttons to button panel
    for (int i=0; i<20; i++) 
    {
      buttonPanel.add( buttons[ i ]);
    }

    //set up buttons to listen for mouse input
    for ( int i = 0; i < 20; i++ )
    {
      buttons[ i ].addActionListener( this );
    }

    setTitle( "Calculator" );
    setSize( 270, 350 );
    setLocationByPlatform( true  );
    setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    setVisible( true );
  }

  public void actionPerformed( ActionEvent event ) 
  {
      if( event.getSource() == buttons[0] ) //BACKSPACE
      {
          String tempString = txField.getText();
          tempString = tempString.substring(0, tempString.length() - 1 );
          txField.setText( tempString );
      }
      else if (event.getSource() == buttons[1] ) //CLEAR ALL
      {
          txField.setText( "" );
      }
      else if (event.getSource() == buttons[19] ) //EVALUATE
      {
          txField.setText( "" + evaluator.eval( txField.getText() ) );
      }
      else
      {
        txField.setText( txField.getText() + ((Button)(event.getSource())).getLabel()  );
      }
  }
} 
