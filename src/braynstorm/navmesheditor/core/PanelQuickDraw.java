package braynstorm.navmesheditor.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import braynstorm.navigator.MapImage;


class PanelQuickDraw extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage image;
    Dimension size = new Dimension();
  
    public PanelQuickDraw() { }
  
    public PanelQuickDraw(BufferedImage image) {
        setImage(image);
    }
  
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
  
    public Dimension getPreferredSize() {
        return size;
    }
  
    public void setImage(BufferedImage bi) {
        image = bi;
        setComponentSize();
        repaint();
    }
    
    public void loadImage(File file) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch(IOException e) {
            System.out.println("read error: " + e.getMessage());
        }
        this.setImage(image);
    }
    
    public void loadImage(MapImage mapImage){
    	this.setImage(mapImage.getImage());
    }
    
  
    private void setComponentSize() {
        if(image != null) {
            size.width  = image.getWidth();
            size.height = image.getHeight();
            revalidate();  // signal parent/scrollpane
        }
    }
    
    
    
}
