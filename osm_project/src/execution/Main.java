package execution;
import java.awt.EventQueue;

import GUI.Dessin;

public class Main {
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Dessin ex = new Dessin();
                ex.setVisible(true);
            }
        });
	}
}