package pela;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;


public class Apple {

	private int x;
	private int y;
	private String str;
	private int nr = 0;
	private int l=0;
	private int oun_x;
	private int oun_y;
	
    protected int width;
    protected int height;
    protected Image image;
    
    private ArrayList<Ounad> ounad;

    
    public Apple(int x, int y, String str, int nr){
    		this.x = x;
    		this.y= y;
      initApple();
    }
    
    private void initApple() {
    	ounad = new ArrayList<>();
    	loadImage("head.png");
    	ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    	
    	exec.scheduleAtFixedRate(new Runnable() {
    	  @Override
    	  public void run() {
    	    if(l<10) {uusOun();l++;}
    	  }
    	}, 0, 1000, TimeUnit.MILLISECONDS);
    	
    	   	exec.scheduleAtFixedRate(new Runnable() {
          	  @Override
          	  public void run() {
          		    if(9<l && l<20) {uusOun();l++;}
          		  
          	  }
          	}, 0, 800, TimeUnit.MILLISECONDS);
    		
    		exec.scheduleAtFixedRate(new Runnable() {
            	  @Override
            	  public void run() {
            		    if(l>19) {uusOun();}
            		  
            	  }
            	}, 17400, 600, TimeUnit.MILLISECONDS);
    	
    }
   
    
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        
    }

    public Image getImage() {    	
        return image;
    }


    
    private void uusOun() {
    	int j=0;
    	while(j==0) {
        int x1 = (int) (Math.random()*6);
        int y1 = (int) (Math.random()*6);
        
        ArrayList<Ounad> os = getOunad();
        x1=x1*50;
        y1=y1*50;
        int k=0;
        for (Ounad o : os) {
           int x2 = o.getX();
           int y2 = o.getY();
                if (x1 == x2 && y1 == y2) {
                	k=1;
                }
            }
        if(k==0) {
        oun_x = x1;
        oun_y = y1;
        nr++;
        j=1;
        
    	Random rand = new Random();
    	char c = (char)(rand.nextInt(26) + 'a');
    	str = String.valueOf(c);
    	    	
        ounad.add(new Ounad(oun_x, oun_y, str, nr));
        }
        }
    }
    
    
    public int getX() {
        return x;
    }

    public  int getY() {
        return y;
    }
    
    public ArrayList getOunad() {
        return ounad;
    }
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            uusOun();
        }
        if (key == KeyEvent.VK_K) {
        	
        }

    }
    

}
