package braynstorm.navmesheditor.core;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFormattedTextField;

import java.awt.Color;

import javax.swing.border.MatteBorder;
import javax.swing.JButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JToggleButton;

import braynstorm.navigator.Sector;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUINavMeshEditor extends JFrame {
	static final long serialVersionUID = 1L;

	public Sector currentSector = Sector.sectors[135][92];
	public PanelQuickDraw[][] mapPanels = new PanelQuickDraw[3][3];
	public PanelQuickDraw[][] navmeshPanels = new PanelQuickDraw[3][3];
	private JFormattedTextField boxCurX;
	private JFormattedTextField boxCurY;
	private JFormattedTextField boxXSector;
	private JFormattedTextField boxYSector;
	
	private KeyAdapter movement = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent paramKeyEvent) {
			if(paramKeyEvent.getKeyCode() == KeyEvent.VK_W || paramKeyEvent.getKeyCode() == KeyEvent.VK_UP){ // MoveUp
				loadSector(currentSector.offset(0, +1));
			}
			if(paramKeyEvent.getKeyCode() == KeyEvent.VK_S || paramKeyEvent.getKeyCode() == KeyEvent.VK_DOWN){ // MoveUp
				loadSector(currentSector.offset(0, -1));
			}
			
			if(paramKeyEvent.getKeyCode() == KeyEvent.VK_A || paramKeyEvent.getKeyCode() == KeyEvent.VK_LEFT){ // MoveUp
				loadSector(currentSector.offset(-1, 0));
			}
			
			if(paramKeyEvent.getKeyCode() == KeyEvent.VK_D || paramKeyEvent.getKeyCode() == KeyEvent.VK_RIGHT){ // MoveUp
				loadSector(currentSector.offset(+1, 0));
			}
		}
	};
	
	
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextPane boxConstructStartX;
	private JTextPane boxConstructStartY;
	private JTextPane boxConstructEndX;
	private JTextPane boxConstructEndY;
	
	public GUINavMeshEditor(){
		getContentPane().addKeyListener(movement);
		setTitle("KekBot NavMesh Editor");
		getContentPane().setLayout(null);
		setSize(968, 968);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(0, 0, 0)));
		panel.setBounds(768, 0, 184, 768);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		JLabel lblCurrentCenterPosition = new JLabel("Current Center Position");
		lblCurrentCenterPosition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurrentCenterPosition.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentCenterPosition.setBounds(0, 0, 184, 25);
		panel.add(lblCurrentCenterPosition);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(10, 36, 62, 20);
		panel.add(lblX);
		
		boxCurX = new JFormattedTextField();
		boxCurX.setEditable(false);
		boxCurX.setHorizontalAlignment(SwingConstants.CENTER);
		boxCurX.setBounds(82, 36, 92, 20);
		panel.add(boxCurX);
		
		JLabel lblY = new JLabel("Y");
		lblY.setHorizontalAlignment(SwingConstants.CENTER);
		lblY.setBounds(10, 58, 62, 20);
		panel.add(lblY);
		
		boxCurY = new JFormattedTextField();
		boxCurY.setHorizontalAlignment(SwingConstants.CENTER);
		boxCurY.setEditable(false);
		boxCurY.setBounds(82, 58, 92, 20);
		panel.add(boxCurY);
		
		boxXSector = new JFormattedTextField();
		boxXSector.setHorizontalAlignment(SwingConstants.CENTER);
		boxXSector.setEditable(false);
		boxXSector.setBounds(82, 80, 92, 20);
		panel.add(boxXSector);
		
		JLabel lblXSector = new JLabel("X Sector");
		lblXSector.setHorizontalAlignment(SwingConstants.CENTER);
		lblXSector.setBounds(10, 80, 62, 20);
		panel.add(lblXSector);
		
		boxYSector = new JFormattedTextField();
		boxYSector.setHorizontalAlignment(SwingConstants.CENTER);
		boxYSector.setEditable(false);
		boxYSector.setBounds(82, 102, 92, 20);
		panel.add(boxYSector);
		
		JLabel lblYSector = new JLabel("Y Sector");
		lblYSector.setHorizontalAlignment(SwingConstants.CENTER);
		lblYSector.setBounds(10, 102, 62, 20);
		panel.add(lblYSector);
		
		JButton btnLoadMap = new JButton("MOVE");
		btnLoadMap.addKeyListener(movement);
		btnLoadMap.setBounds(10, 133, 77, 23);
		panel.add(btnLoadMap);
		
		JButton btnAStar = new JButton("A*");
		btnAStar.setBounds(97, 133, 77, 23);
		panel.add(btnAStar);
		
		JToggleButton btnShowTraversableAreas = new JToggleButton("Show Traversable Areas");
		btnShowTraversableAreas.setBounds(10, 201, 164, 23);
		panel.add(btnShowTraversableAreas);
		
		JToggleButton btnShowUntraversableAreas = new JToggleButton("Show Untraversable Areas");
		btnShowUntraversableAreas.setBounds(10, 167, 164, 23);
		panel.add(btnShowUntraversableAreas);
		
		JLabel lblDrawNavmesh = new JLabel("Draw NavMesh");
		lblDrawNavmesh.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrawNavmesh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDrawNavmesh.setBounds(0, 235, 184, 25);
		panel.add(lblDrawNavmesh);
		
		JRadioButton radioAddMode = new JRadioButton("Add Mode");
		buttonGroup.add(radioAddMode);
		radioAddMode.setBounds(10, 267, 109, 23);
		panel.add(radioAddMode);
		
		JRadioButton radioSelectMode = new JRadioButton("Select Mode");
		buttonGroup.add(radioSelectMode);
		radioSelectMode.setSelected(true);
		radioSelectMode.setBounds(10, 293, 109, 23);
		panel.add(radioSelectMode);
		
		JLabel lblMaualAddA = new JLabel("Maual Add a Triangle");
		lblMaualAddA.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaualAddA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaualAddA.setBounds(0, 323, 184, 25);
		panel.add(lblMaualAddA);
		
		JLabel label = new JLabel("X");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 359, 62, 20);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Y");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 381, 62, 20);
		panel.add(label_1);
		
		JLabel lblZ = new JLabel("Z");
		lblZ.setHorizontalAlignment(SwingConstants.CENTER);
		lblZ.setBounds(10, 403, 62, 20);
		panel.add(lblZ);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setText("0");
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(82, 403, 92, 20);
		panel.add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setText("0");
		formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_1.setBounds(82, 381, 92, 20);
		panel.add(formattedTextField_1);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setText("0");
		formattedTextField_2.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_2.setBounds(82, 359, 92, 20);
		panel.add(formattedTextField_2);
		
		JButton butAdd = new JButton("Add");
		butAdd.setBounds(82, 423, 92, 23);
		panel.add(butAdd);
		
		boxConstructStartX = new JTextPane();
		boxConstructStartX.setBounds(10, 837, 106, 20);
		getContentPane().add(boxConstructStartX);
		
		boxConstructStartY = new JTextPane();
		boxConstructStartY.setBounds(10, 864, 106, 20);
		getContentPane().add(boxConstructStartY);
		
		boxConstructEndY = new JTextPane();
		boxConstructEndY.setBounds(140, 864, 106, 20);
		getContentPane().add(boxConstructEndY);
		
		boxConstructEndX = new JTextPane();
		boxConstructEndX.setBounds(140, 837, 106, 20);
		getContentPane().add(boxConstructEndX);
		
		JButton butFromCurrent = new JButton("From Current");
		butFromCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getBoxConstructStartX().setText(getBoxXSector().getText());
				getBoxConstructStartY().setText(getBoxYSector().getText());
			}
		});
		butFromCurrent.setBounds(10, 810, 106, 23);
		getContentPane().add(butFromCurrent);
		
		JButton butConstruct = new JButton("Construct");
		butConstruct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				Sector start = Sector.sectors[Integer.parseInt(getBoxConstructStartX().getText())][Integer.parseInt(getBoxConstructStartY().getText())];
				Sector end = Sector.sectors[Integer.parseInt(getBoxConstructEndX().getText())][Integer.parseInt(getBoxConstructEndY().getText())];
				BufferedImage finalImage = new BufferedImage(Math.abs(start.x - end.x) * 256, Math.abs(start.y - end.y) * 256, BufferedImage.TYPE_INT_RGB);
				
				int startX,startY,endX,endY;
				startX = Math.max(start.x, end.x);
				endX = Math.min(start.x, end.x);
				startY = Math.max(start.y, end.y);
				endY = Math.min(start.y, end.y);
				
				
				for(int i = 0; i < Math.abs(startX- endX); i++){
					for(int j = 0; j < Math.abs(startY - endY); j++){
						Graphics endImageG = finalImage.getGraphics();
						endImageG.drawImage(NavMeshEditor.images.getWithDefault(start.offset(i, -j)).getImage(), i * 256, j * 256, null);
					}
				}
				File outputfile = new File("E:\\image.png");
				try {
					ImageIO.write(finalImage, "png", outputfile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		butConstruct.setBounds(10, 895, 236, 23);
		getContentPane().add(butConstruct);
		
		JButton btnToCurrent = new JButton("To Current");
		btnToCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				getBoxConstructEndX().setText(getBoxXSector().getText());
				getBoxConstructEndY().setText(getBoxYSector().getText());
			}
		});
		btnToCurrent.setBounds(140, 810, 106, 23);
		getContentPane().add(btnToCurrent);
		
		
		
		
		for (int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				mapPanels[i][j] = new PanelQuickDraw();
				mapPanels[i][j].setBounds(i * 256, j * 256, 256, 256);
				mapPanels[i][j].setLayout(null);
				mapPanels[i][j].setFocusable(true);
				mapPanels[i][j].requestFocusInWindow();
				mapPanels[i][j].addKeyListener(movement);
				getContentPane().add(mapPanels[i][j]);
			}
		}
		
		for (int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				mapPanels[i][j] = new PanelQuickDraw();
				mapPanels[i][j].setBounds(i * 256, j * 256, 256, 256);
				mapPanels[i][j].setLayout(null);
				mapPanels[i][j].setFocusable(true);
				mapPanels[i][j].requestFocusInWindow();
				mapPanels[i][j].addKeyListener(movement);
				getContentPane().add(mapPanels[i][j]);
			}
		}
		
		loadSector(currentSector);
		
	}
	
	public void loadSector(Sector s){
		
		mapPanels[0][0].loadImage(NavMeshEditor.images.getWithDefault(s.offset(-1, +1)));
		mapPanels[0][1].loadImage(NavMeshEditor.images.getWithDefault(s.offset(-1, 0)));
		mapPanels[0][2].loadImage(NavMeshEditor.images.getWithDefault(s.offset(-1,  - 1)));
		
		mapPanels[1][0].loadImage(NavMeshEditor.images.getWithDefault(s.offset(0,  + 1)));
		mapPanels[1][1].loadImage(NavMeshEditor.images.getWithDefault(s.offset(0, 0))); // Middle Widdle
		mapPanels[1][2].loadImage(NavMeshEditor.images.getWithDefault(s.offset(0,  - 1)));
		
		mapPanels[2][0].loadImage(NavMeshEditor.images.getWithDefault(s.offset(+ 1, + 1)));
		mapPanels[2][1].loadImage(NavMeshEditor.images.getWithDefault(s.offset(+ 1, 0)));
		mapPanels[2][2].loadImage(NavMeshEditor.images.getWithDefault(s.offset(+ 1, - 1)));
		
		for(PanelQuickDraw[] m : mapPanels){
			for(PanelQuickDraw n : m){
				n.repaint();
			}
		}
		
		if(s != null)
			System.out.println("=Loading sector" + s.toString());
		getBoxCurX().setText(s.getCenterX() + "");
		getBoxCurY().setText(s.getCenterY() + "");
		
		getBoxXSector().setText(s.x + "");
		getBoxYSector().setText(s.y + "");
		
		currentSector = s;
		
	}
	
	public JFormattedTextField getBoxCurX() {
		return boxCurX;
	}
	public JFormattedTextField getBoxCurY() {
		return boxCurY;
	}
	public JFormattedTextField getBoxXSector() {
		return boxXSector;
	}
	public JFormattedTextField getBoxYSector() {
		return boxYSector;
	}
	public JTextPane getBoxConstructStartX() {
		return boxConstructStartX;
	}
	public JTextPane getBoxConstructStartY() {
		return boxConstructStartY;
	}
	public JTextPane getBoxConstructEndX() {
		return boxConstructEndX;
	}
	public JTextPane getBoxConstructEndY() {
		return boxConstructEndY;
	}
}
