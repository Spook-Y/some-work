import java.io.* ;   // for File
import java.util.* ; // for Scanner
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*****************************************************************************\
|*                                                                           *|
\*****************************************************************************/

public class AdventureGame implements ActionListener
{
   /**************************************************************************\
   |* Game Constants                                                         *|
   \**************************************************************************/

   public static final String QUIT_GAME = "Q" ;
   public static final String GET_LAMP = "L" ;
   public static final String GET_KEY = "K" ;
   public static final String OPEN_CHEST = "C" ;

   public static final String GO_NORTH = "N" ;
   public static final String GO_SOUTH = "S" ;
   public static final String GO_EAST = "E" ;
   public static final String GO_WEST = "W" ;


   /**************************************************************************\
   |* Game State                                                             *|
   \**************************************************************************/

   public Player player ;

   public Room room ;
   public Room lastRoom ;

   public boolean isGameOver ;
   public boolean isGrueLurking ;

   /**************************************************************************\
   |* Other Game Data Objects and Components                                 *|
   \**************************************************************************/

   private Scanner keyboard ;
   JTextArea ta;
   JScrollPane pn;
   JTextField tf;
   JPanel cp;
   JFrame f1;
   String textFieldInput = "";
   String input;


   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public static void main(String[] arg)
   {
       AdventureGame a1 = new AdventureGame();
   }

   public void actionPerformed(ActionEvent ae)
   {
   	   if(!isGameOver())
   	   {

   	     textFieldInput = tf.getText();

   	     System.out.println("You wrote: " + textFieldInput);

   	     input = getUserInput() ;

       	 processUserInput(input) ;

       	 tf.setText("");

       	 if(isGameOver())
       	 {
       	 	showGameOverScreen();

       	 	tf.setEditable(false);
       	 }
       	 else
       	 {
       	 	showBoard();

       	 	showUserInputOptions() ;
       	 }
   	   }
   }
   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/
   public AdventureGame()
   {
      init() ;

      showSplashScreen() ;

      showBoard();

      showUserInputOptions() ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void init()
   {
   	  ta = new JTextArea();
	  	ta.setEditable(false);

	  pn = new JScrollPane(ta);
	    pn.setWheelScrollingEnabled(true);

	  tf = new JTextField();
		tf.setEditable(true);
		tf.addActionListener(this);

	  cp = new JPanel(new BorderLayout());
		cp.add(pn, BorderLayout.CENTER);
		cp.add(tf, BorderLayout.SOUTH);

	  f1 = new JFrame();
		f1.setTitle("ADVENTURE GAME");
		f1.setVisible(true);
		f1.setSize(800,600);
		f1.setLocationRelativeTo(null);
		f1.setContentPane(cp);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	  PrintStream out = new PrintStream(new TextAreaOutputStream(ta));
		System.setOut(out);
		System.setErr(out);

      player = new Player() ;

      room = createDungeon() ;

      lastRoom = null ;

      isGameOver = false ;

      isGrueLurking = false ;

      keyboard = new Scanner(System.in) ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void showSplashScreen()
   {
      System.out.println("Welcome to Adventure Game!") ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void showBoard()
   {
      isGrueLurking = false ;

      if(player.hasLamp)
      {
         room.isLit = true ;
      }

      if(!room.isLit)
      {
         isGrueLurking = true ;
      }

      System.out.println(room) ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void showUserInputOptions()
   {
      String options = "[" + QUIT_GAME + "] Quit Game | " +
                       "[" + GET_LAMP + "] Get Lamp | " +
                       "[" + GET_KEY + "] Get Key | " +
                       "[" + OPEN_CHEST + "] Open Chest | " +
                       "[" + GO_NORTH + "] Go North | " +
                       "[" + GO_SOUTH + "] Go South | " +
                       "[" + GO_EAST + "] Go East | " +
                       "[" + GO_WEST + "] Go West" ;

      System.out.println(options) ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public String getUserInput()
   {
    String input = textFieldInput;

   	return input;

   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public boolean checkInput(String input)
   {
      return input.equalsIgnoreCase(QUIT_GAME) ||
             input.equalsIgnoreCase(GET_LAMP)  ||
             input.equalsIgnoreCase(GET_KEY)   ||
             input.equalsIgnoreCase(OPEN_CHEST)||
             input.equalsIgnoreCase(GO_NORTH)  ||
             input.equalsIgnoreCase(GO_SOUTH)  ||
             input.equalsIgnoreCase(GO_EAST)   ||
             input.equalsIgnoreCase(GO_WEST) ;

   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void processUserInput(String input)
   {
           if(input.equalsIgnoreCase(QUIT_GAME)) { quitGame() ; }
      else if(input.equalsIgnoreCase(GET_LAMP)) { getLamp() ; }
      else if(input.equalsIgnoreCase(GET_KEY)) { getKey() ; }
      else if(input.equalsIgnoreCase(OPEN_CHEST)) { openChest() ; }
      else if(input.equalsIgnoreCase(GO_NORTH)) { goNorth() ; }
      else if(input.equalsIgnoreCase(GO_SOUTH)) { goSouth() ; }
      else if(input.equalsIgnoreCase(GO_EAST)) { goEast() ; }
      else if(input.equalsIgnoreCase(GO_WEST)) { goWest() ; }
      else { System.out.println("Invalid input, please enter one of the shown characters."); }

   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void updateGameState()
   {
      //System.out.println("Updating Game State") ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public boolean isGameOver()
   {
      return isGameOver ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void showGameOverScreen()
   {
      System.out.println("Game Over!") ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void quitGame()
   {
      isGameOver = true ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void getLamp()
   {
      if(isGrueLurking)
      {
         System.out.println("You have just gotten eaten alive!") ;
         isGameOver = true ;
      }
      else
      {
         if(room.hasLamp)
         {
            player.hasLamp = true ;
            room.hasLamp = false ;

            System.out.println("You got the lamp!\n") ;
         }
         else
         {
            System.out.println("There is no lamp in this room.\n") ;
         }
      }
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void getKey()
   {
      if(isGrueLurking)
      {
         System.out.println("You have just gotten eaten alive!") ;
         isGameOver = true ;
      }
      else
      {
         if(room.hasKey)
         {
            player.hasKey = true ;
            room.hasKey = false ;

            System.out.println("You got the key!\n") ;
         }
         else
         {
            System.out.println("There is no key in this room.\n") ;
         }
      }
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void openChest()
   {
      if(isGrueLurking)
      {
         System.out.println("You have just gotten eaten alive!") ;
         isGameOver = true ;
      }
      else
      {
         if(room.hasChest)
         {
            if(player.hasKey)
            {
               System.out.println("You open the chest, and it grows thousands of fangs. You got eaten!\n") ;
               isGameOver = true ;
            }
            else
            {
               System.out.println("Dumbass! You don't have the key!\n") ;
            }
         }
         else
         {
            System.out.println("There is no chest in this room.\n") ;
         }
      }
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public Room createDungeon()
   {
      try
      {
         Scanner file = new Scanner(new File("dungeon.txt")) ;

         int roomCount = file.nextInt() ;

         Room[] rooms = new Room[roomCount] ;

         for(int i=0 ; i<roomCount ; i++)
         {
            Room r = new Room() ;

            file.nextInt() ;
            file.nextLine() ;

            r.description = file.nextLine() ;

            r.isLit = file.nextBoolean() ;
            r.hasLamp = file.nextBoolean() ;
            r.hasKey = file.nextBoolean() ;
            r.hasChest = file.nextBoolean() ;

            rooms[i] = r ;
         }

         for(int i=0 ; i<roomCount ; i++)
         {
            int index = file.nextInt() ;

            int n = file.nextInt() ;
            int s = file.nextInt() ;
            int e = file.nextInt() ;
            int w = file.nextInt() ;

            if(n != -1) { rooms[index].north = rooms[n] ; }
            if(s != -1) { rooms[index].south = rooms[s] ; }
            if(e != -1) { rooms[index].east  = rooms[e] ; }
            if(w != -1) { rooms[index].west  = rooms[w] ; }
         }

         return rooms[0] ;
      }
      catch(Exception e)
      {
         e.printStackTrace() ;
      }

      return null ;
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void goNorth()
   {
      if(isGrueLurking && lastRoom != room.north)
      {
         System.out.println("You have just gotten eaten alive!") ;
         isGameOver = true ;
      }
      else
      {
         if(room.north != null)
         {
            lastRoom = room ;
            room = room.north ;
         }
         else
         {
            System.out.println("You cannot go north!") ;
         }
      }
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void goSouth()
   {
      if(isGrueLurking && lastRoom != room.south)
      {
         System.out.println("You have just gotten eaten alive!") ;
         isGameOver = true ;
      }
      else
      {
         if(room.south != null)
         {
            lastRoom = room ;
            room = room.south ;
         }
         else
         {
            System.out.println("You cannot go south!") ;
         }
      }
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void goEast()
   {
      if(isGrueLurking && lastRoom != room.east)
      {
         System.out.println("You have just gotten eaten alive!") ;
         isGameOver = true ;
      }
      else
      {
         if(room.east != null)
         {
            lastRoom = room ;
            room = room.east ;
         }
         else
         {
            System.out.println("You cannot go east!") ;
         }
      }
   }

   /**************************************************************************\
   |*                                                                        *|
   \**************************************************************************/

   public void goWest()
   {
      if(isGrueLurking && lastRoom != room.west)
      {
         System.out.println("You have just gotten eaten alive!") ;
         isGameOver = true ;
      }
      else
      {
         if(room.west != null)
         {
            lastRoom = room ;
            room = room.west ;
         }
         else
         {
            System.out.println("You cannot go west!") ;
         }
      }
   }
}
