import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
 
public class  starGod extends JPanel
{
 
   /*
   Creating general fields that I thought I would need
   */
   public static  int height = 1000;
   public static int width = 1000;
   private static ArrayList<double[]> demStars = new ArrayList<double[]>();


   /*
   Creating the name for the JFrame where stars and constellations are going to be
   */
   static JFrame window = new JFrame("Constellations");

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      width=window.getWidth();
      height= window.getHeight();
      repaint();
      paintMag(g);
   }

   /*
   This method is what readss in the text file and sorts it all and adds it to the arraylist above.
   This methd is extremly useful becasue iot places x,y coords in the correct arrays
   */
   public static void readCoords(String file)
   {
      try
      {
         FileReader fileReader = new FileReader(file);
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         StringBuffer holdingCell = new StringBuffer();
         String line;
         while ((line = bufferedReader.readLine()) != null)
         {
            double[] xyzMagArray = new double[5];
            String[] split = line.split(" ");
            int pointer =0;
            while(pointer < 5){
               xyzMagArray[pointer] = Double.parseDouble(split[pointer]);
               pointer++;
            }
            demStars.add(xyzMagArray);
         }
         fileReader.close();
      }
      catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void coordinatesToPixels(double x, double y, double size)
   {
      double newx = x*500+500;
      double newy = y*-500 + 500;
      newx += size /2;
      newy += size/2;
      x = newx;
      y = newy;
   
   }

   public static void paintXY(Graphics g)
   {
      double newx;
      double newy;
   
      for(double[] each: demStars)
      {
         newx = each[0]*width/2 + width/2;
         newy = each[1]*-height/2 + height/2;
         g.setColor(Color.WHITE);
         g.fillRect((int)newx,(int)newy,2,2);
      }
   }
   
   public static void paintMag(Graphics g)
   {
      double newx;
      double newy;
      double third_man;
      double star_size;
      for(double [] memes: demStars)
      {
         newx = memes[0]*width/2 + width/2;
         newy = memes[1]*-height/2 + height/2;
         star_size = (int)Math.round(10.0/(memes[4] + 2));
         g.setColor(Color.WHITE);
         g.fillRect((int) newx, (int) newy,(int)star_size,(int)star_size);
        
      }
   }



   public static void main(String[] args)
   {
      window.setBounds(0, 0, width, height);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel canvas = new starGod();
      canvas.setBackground(Color.BLACK);
      window.getContentPane().add(canvas);
      window.setVisible(true);
      readCoords("stars.txt");
   
   
   
   }
}