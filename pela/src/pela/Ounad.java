package pela;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Ounad{


    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
    public char c;
    public String str;
    public int nr;
    public Ounad(int x, int y, String str, int nr) {
    	this.x = x;
        this.y = y;
        this.str = str;
        this.nr = nr;
        vis = true;
        initOun();
        
    }
    
    private void initOun() {
        
        loadImage("src/pildid/kast2.png");  
    }
    public void muudaOun() {
    	loadImage("src/pildid/kast2.png"); 
    }
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    public String getStr() {
    	return str;
    }
    public String getNr() {
    	return Integer.toString(nr);
    }
    public Image getImage() {
        return image;
    }
    
    public boolean isVisible() {
        return vis;
    }
    public void setVisible(Boolean visible) {
        vis = visible;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
