package pela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.Timer;






//import pela.Apple;

public class Taust extends JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DELAY = 50;

    private String t;
    private int nr=1;
    private boolean inGame = true;
    
    private Apple apple;
    
  
    private Timer timer;

    public Taust() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        initGame();
    }



    private void initGame() {
    	
    //	Apple.initApple();
    //    Apple.locateApple();
    	apple = new Apple(1,1,"a", 0);

        timer = new Timer(DELAY, this);
        timer.start();
        leiaviimaneOunad();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame) {
        doDrawing(g);
        } else {
        	drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
        }
    
    private void doDrawing(Graphics g) {
        

            ArrayList<Ounad> os = apple.getOunad();
           
            for (Ounad o : os) {
            	if (o.isVisible()) {
                   g.drawImage(o.getImage(), o.getX(), o.getY(), this);                   
                   g.drawString(o.getStr().toUpperCase(), o.getX()+20, o.getY()+30);
                   g.drawString(o.getNr(), o.getX()+2, o.getY()+12);
            	}
            }
          g.setColor(Color.red);
          Font small = new Font("Helvetica", Font.BOLD, 18);
          g.setFont(small);
          g.drawString(Integer.toString(nr), 10, 15);
        } 
    
    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
        msg= "Final score: " + nr;
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2+20);
    }
    
    private void updateOunad() {

        ArrayList<Ounad> os = apple.getOunad();
        if (os.size() > 35) {
        inGame = false;
        }
        for (int i = 0; i < os.size(); i++) {

            Ounad o = os.get(i);

            if (o.isVisible()) {
              //  o.move();
            } else {
                os.remove(i);
            }
        }
    }
    
 private void leiaviimaneOunad() {
    	
    	ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    	exec.scheduleAtFixedRate(new Runnable() {
    		public void run() {ArrayList<Ounad> os = apple.getOunad();
        	for(int i = 0; i< os.size(); i++) {
        		Ounad o = os.get(i);
            if(o.getNr().equals(Integer.toString(nr))){
            	o.muudaOun();
            }
        	}
    		}
    	},0,100,TimeUnit.MILLISECONDS);
    	
        
    	}    
    public void checkChar() {

        ArrayList<Ounad> os = apple.getOunad();

        for (Ounad o : os) {
            String r1 = o.getStr();
            String r2 = o.getNr();
                if (r1.equals(t) && r2.equals(Integer.toString(nr))) {
                    o.setVisible(false);
                    nr++;
                }
            }
        }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
        	
        }

        updateOunad();
        checkChar();
        repaint();
    }
    
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            apple.keyPressed(e);
            t=String.valueOf(e.getKeyChar());
            }

  
    }
    
}