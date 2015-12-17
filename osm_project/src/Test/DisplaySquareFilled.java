package Test;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
 
import javax.swing.JFrame;
 
public class DisplaySquareFilled{
 public static void main(String[] args) {
  JFrame frame = new JFrame();
  frame.setSize(new Dimension(640,480));
   
  DisplaySquareFilled display = new DisplaySquareFilled();
  Component compo = display.new MonCompo();
  frame.add(compo);
  frame.setVisible(true);
 }
 
 
 
 public class MonCompo extends Component{
   @Override
   public void paint(Graphics arg0) {
    Graphics2D g=(Graphics2D)arg0;
    g.setColor(new Color(100,100,100));
    g.fill3DRect(10, 10, 100, 100, true);
   }
 }
}