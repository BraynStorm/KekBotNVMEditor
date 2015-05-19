package braynstorm.navigator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class MapImage {
	private BufferedImage image;
	
	public MapImage(Path p){
		load(p.toFile());
	}
	
	public MapImage(BufferedImage img){
		image = img;
	}
	
	public void load(String path){
		load(new File(path));
	}
	
	public void load(File file){
		try{
			image = ImageIO.read(file);
		} catch (IOException e){
			System.out.println("Couldnt load image: " + file.getAbsolutePath());
		}
	}
	
	public BufferedImage getImage(){
		return image;
	}
}
