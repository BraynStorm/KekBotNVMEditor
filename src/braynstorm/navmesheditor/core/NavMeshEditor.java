package braynstorm.navmesheditor.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import braynstorm.navigator.MapImage;
import braynstorm.navigator.NavMesh;
import braynstorm.navigator.Sector;

public class NavMeshEditor {
	public static String mainFolder = ((new File(System.getProperty("java.class.path"))).getAbsoluteFile().getParentFile().toString()).split(";")[0];
	
	public static FailProofHashMap<Sector, MapImage> images = new  FailProofHashMap<Sector, MapImage>();
	public static FailProofHashMap<Sector, NavMesh> meshes = new  FailProofHashMap<Sector, NavMesh>();
	
	public static void main(String[] args) {
		BufferedImage defImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = defImage.createGraphics();
		g.setColor(Color.red);
		g.fillRect(0, 0, 256, 256);
		g.dispose();
		images.setDefaultValue(new MapImage(defImage));
		
		//BufferedImage defNavmesh = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
		////g = defNavmesh.createGraphics();
		//g.dispose();
		///meshes.setDefaultValue(new MapImage(defNavmesh));
		
		try {
			System.out.println("Loading...");
			Files.walk(Paths.get(mainFolder + "\\map")).filter(Files::isRegularFile).collect(Collectors.toList()).forEach(path -> {
				String filename = path.getFileName().toString();
				if(filename.contains("x") && !(filename.lastIndexOf("x") > 3)){
					String[] xy = path.getFileName().toString().replace(".jpg", "").split("x");
					images.put(new Sector(Integer.parseInt(xy[0]),Integer.parseInt(xy[1])), new MapImage(path));
					//System.out.println("Loading " + xy[0] + ", " + xy[1] +", " + filename);
				}
			});
			System.out.println(images.size() + " images loaded ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GUINavMeshEditor gui = new GUINavMeshEditor();
		gui.setVisible(true);
		
		
	}

	
	
	
}
