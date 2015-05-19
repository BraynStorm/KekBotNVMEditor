package braynstorm.navigator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class NavMesh {
	
	// ARrayList of the building component, defaulting to UNTRAVERSABLE
	private ArrayList<Point> vertices;
	private ArrayList<Point> indices; // Triangle indices MOFO
	
	
	private NavMesh() {
		indices = new ArrayList<Point>();
		vertices = new ArrayList<Point>();
	}
	
	/**
	 * File structure:
	 * 	BEGIN
	 * 		BEGIN SECTION VERTICES
	 * 			Vertex(short x, short y, short z),
	 * 			Vertex(short x, short y, short z),
	 * 			...
	 * 			Until:
	 * 				Vertex(0, 0, 0)
	 * 				Vertex(0, 0, 0)
	 * 		END SECTION
	 * 		BEGIN SECTION INDICES
	 * 			Index(int x, int y, int z),
	 * 			Index(int x, int y, int z)
	 * 			...
	 * 			Until: 
	 * 				EOF
	 * 		END SECTION
	 *  END
	 * @param path
	 * @return
	 */
	
	public static NavMesh loadNavMesh(String path){
		NavMesh mesh = new NavMesh();
		
		try{
			DataInputStream filestream = new DataInputStream(new FileInputStream(path));
			
			//Load vertices
			short[] points = new short[6];
			
			do{
				points = new short[6];
				for(int i = 0; i < points.length; i++)
					points[i] = filestream.readShort();
				
				if(!twoEmptyPoints(points)){
					
					for(int i = 0; i < 2; i++){
						Point p = new Point(points[i + 0],points[i + 1],points[i + 2]);
						if(!mesh.vertices.contains(p))
							mesh.vertices.add(p);
					}
				}else
					break;
			}while(true);
			
			int[] index = new int[3];
			while(filestream.available() >= Integer.BYTES * 3){
				index = new int[3];
				for(int i = 0; i < index.length; i++)
					index[i] = filestream.readInt();
				mesh.indices.add(new Point(index[0],index[1],index[2]));
			}
			
			filestream.close();
		} catch (IOException e){
			System.err.println("Can't load navmesh file.");
			e.printStackTrace();
		}
		
		return mesh;
	}
	
	public void saveNavMesh(String path){
		DataOutputStream fileStream;
		try {
			fileStream = new DataOutputStream(new FileOutputStream(path));
			
			for(Point p : vertices){
				fileStream.writeShort(p.getX());
				fileStream.writeShort(p.getY());
				fileStream.writeShort(p.getZ());
			}
			
			fileStream.write(new byte [12]);
			
			for(Point p : indices){
				fileStream.writeInt(p.getX());
				fileStream.writeInt(p.getY());
				fileStream.writeInt(p.getZ());
			}
			
			fileStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean twoEmptyPoints(short[] points){
		return points[0] == 0 && points[1] == 0 && points[2] == 0 && 
				points[3] == 0 && points[4] == 0 && points[5] == 0; 
	}
	
}
