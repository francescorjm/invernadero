package zoo;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;
public class Verplantas extends JFrame  {
	public JPanel panel;
	public JLabel Adelfa;
	public JLabel Azalea;
	public JLabel Baladre;
	public JLabel Cactus;
	public JLabel Camelia;
	public JLabel Cotalillo;
	public JLabel FlorDelBeso;
	public JLabel Gallitos;
	public JLabel Gardenia;
	public JLabel GarraDeLeon;
	public JLabel Helecho;
	public JLabel Kiwi;
	public JLabel Liston;
	public JLabel Chaguaramo;
	public JLabel Orquidea;
	public JTextField IngresarPlanta;
	public JTextField ClavePlanta;
	public JLabel PlantaIntroducido;
	public JLabel HambCalido;
	public JLabel HambFrio;
	public String dbName = "Register";
	public String URL = "jdbc:postgresql://localhost:5432/INVERNADERO";
	public String USER = "postgres";
	public String PASSWORD = "zoroyokou";
	PreparedStatement ps;
	ResultSet res;
	//
	public JTextField ingresoMensaje;
    public JTextArea pantallaChat;
    public JMenuItem adjuntar;
    private static ServerSocket servidor;
    private static Socket cliente;
    private static String ipServidor;// = "127.0.0.1";
    public static Cliente ventanaCliente;
    public static String usuario;
    public boolean recibir;
	private Component add;
	



public Veranimal() {
		this.setTitle("Animales");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		IniciarAnimales();
		this.setLocationRelativeTo(null);

	}
public Connection getConection() {
	Connection con = null;
	try {
		
		Class.forName("org.postgresql.Driver");
		con = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
		System.out.println("conexion exitosa");
		JOptionPane.showMessageDialog(null,"conexion exitosa");
		
	}catch(Exception e){
		System.out.println(e.getMessage());
	}return con;
 }
	private void LimpiarCajas() {
		ClaveAnimal.setText(null);
		IngresarAnimal.setText(null);
		
	}
	public void IniciarPlantas(){
		ColocarPlantas();
		ColocarCosas();
		TodasLasPlantas();
		PlantaNueva();
		ColocarPlanta();
		Boton2();
		Boton3();
		BotonEliminar();
		BotonModificar();
		BotonBuscar();
		ClavePlanta();
		ListaPlantas();
	}
	public void ColocarAnimales()  {
	panel = new JPanel();
	panel.setLayout(null);
	this.getContentPane().add(panel);
	}
	public void ColocarCosas() {
	JLabel animales = new JLabel();
	animales.setText("ZOOLOGICO");
	animales.setBounds(440,20,1000,50);
	animales.setForeground(Color.RED);
	animales.setFont(new Font("arial",1,20));
	panel.add(plantas);
	}
	public void TodasLasPlantas() {
		JButton boton1 = new JButton();
		boton1.setText("Lista de Plantas");
		boton1.setEnabled(false);
		boton1.setBounds(100, 100, 200, 40);
		
		panel.add(boton1);
		JLabel AnimalNuevoRepetido = new JLabel();
		AnimalNuevoRepetido.setBounds(100, 215, 100, 200);
		panel.add(PlantaNuevaRepetida);
		
		ActionListener Boton01 = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
					AnimalNuevoRepetido.setText(""+IngresarPlanta.getText());
					try {
					Connection con =null;
					con =  getConection();
					
					ps= con.prepareStatement("Select * FROM Plantas");
					res= ps.executeQuery();
					if(res.next()) {
						JOptionPane.showMessageDialog(null, res.getString("Planta"));
					}else {
						JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS");
						}
					con.close();
					}catch(Exception a) {
						System.out.println(a);
					}
					}
			
		};
		boton1.addActionListener(Boton01);
	}
	public void PlantaNueva() {
		JLabel	PlantaN = new JLabel();
		AnimalN.setBounds(450, 60, 100, 30);
		AnimalN.setText("Ingrese la Planta");
		panel.add(PlantaN);
	}		
	public void Boton2() {
		JButton Boton02 = new JButton();
		Boton02.setBounds(400, 240, 200, 40);
		Boton02.setText("Guardar");
		
		AnimalIntroducido = new JLabel();
		AnimalIntroducido.setBounds(400, 200, 250, 30);
		panel.add(PlantaIntroducida);
		panel.add(Boton02);
		JLabel PlantaRepetida2 = new JLabel();
		PlantaRepetida2.setBounds(100, 215, 100, 200);
		panel.add(PlantaRepetida2);
		
		
		
		ActionListener AgregarPlanta = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = getConection();
					ps= con.prepareStatement("INSERT INTO Plantas (Planta,Id) VALUES(?,?)");
					ps.setString(1,IngresarPlanta.getText());
					ps.setString(2, ClavePlanta.getText());
					int res= ps.executeUpdate();
					if(res>0) {
						JOptionPane.showMessageDialog(null, "Planta Guardada");
						LimpiarCajas();
					}else {
						JOptionPane.showMessageDialog(null, "Error al Guardar");
						LimpiarCajas();
					}
					con.close();
				}catch(Exception c) {
					System.err.print(c);
					
				}
				
			}
			
		};
		Boton02.addActionListener(AgregarPlanta);
	}
	public void ColocarPlantas() {
		IngresarPlanta = new JTextField();
		IngresarPlanta.setBounds(400, 100, 200, 40);
		panel.add(IngresarPlanta);
		
	}
	public void Boton3() {

		JButton Boton03 = new JButton();
		Boton03.setBounds(700, 100, 280, 40);
		Boton03.setText("Caracteristicas Especiales de Plantas");
		panel.add(Boton03);
		HambCalido= new JLabel();
		HambCalido.setBounds(790, 250, 100, 40);
		panel.add(HambCalido);
		HambFrio = new JLabel();
		HambFrio.setBounds(790,230, 100, 40);
		panel.add(HambFrio);
		JLabel Hambientales = new JLabel();
		Voladores.setBounds(790,200, 100, 40);
		panel.add(Hambientales);
		
		ActionListener CompPlantas = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HambCalido.setText("Cactus");
				LechuVuela.setText("Orquidea");
				Voladores.setText("Hambientales:");
				
				
			}
			
		};
		Boton03.addActionListener(CompPlantas);
		
	
	}

	public void BotonEliminar() {
		JButton BotonEliminar = new JButton();
		BotonEliminar.setBounds(400, 340, 200, 40);
		BotonEliminar.setText("Eliminar");
		panel.add(BotonEliminar);
		
		ActionListener BotonEliminarAcc = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = getConection();
					ps= con.prepareStatement("DELETE FROM PLANTAS WHERE Id=?");
					ps.setString(1,ClavePlanta.getText());
					int res= ps.executeUpdate();
					if(res>0) {
						JOptionPane.showMessageDialog(null, "Planta Borrada");
						LimpiarCajas();
					}else {
						JOptionPane.showMessageDialog(null, "Error Al Borrar");
						LimpiarCajas();
					}
					con.close();
				}catch(Exception c) {
					System.err.print(c);
					
				}
				
			}
			
		};
		BotonEliminar.addActionListener(BotonEliminarAcc);
		
	}
	public void BotonModificar() {
		JButton BotonModificar = new JButton();
		BotonModificar.setBounds(400, 440, 200, 40);
		BotonModificar.setText("Modificar");
		panel.add(BotonModificar);
		ActionListener BotonModificarAcc = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con = getConection();
					ps= con.prepareStatement("UPDATE Plantas SET Planta=? WHERE Id=?");
					ps.setString(1,IngresarPlanta.getText());
					ps.setString(2,ClavePlanta.getText());
					int res= ps.executeUpdate();
					if(res>0) {
						JOptionPane.showMessageDialog(null, "Planta Modificado");
						LimpiarCajas();
					}else {
						JOptionPane.showMessageDialog(null, "Error al Modificar");
						LimpiarCajas();
					}
					con.close();
				}catch(Exception c) {
					System.err.print(c);
					
				}
				
			}
			
		};
		BotonModificar.addActionListener(BotonModificarAcc);
	}
	public void BotonBuscar() {
		JButton BotonBuscar = new JButton();
		BotonBuscar.setBounds(400, 540, 200, 40);
		BotonBuscar.setText("Buscar");
		panel.add(BotonBuscar);
		
		ActionListener BotonBuscarAcc = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				try {
					con=getConection();
					ps= con.prepareStatement("Select * From Plantas WHERE Id = ?");
					ps.setString(1,ClavePlanta.getText() );
					res = ps.executeQuery();
					if(res.next()) {
						ClavePlanta.setText(res.getString("id"));
						IngresarPlanta.setText(res.getString("Planta"));
					}else {
						JOptionPane.showMessageDialog(null, "No Tenemos esa planta en este Invernadero");
					}
				}catch(Exception d){
					System.err.print(d);
				}
				
				
			}
			
		};
		BotonBuscar.addActionListener(BotonBuscarAcc);
		
	}
	public void ClavePlanta() {
		ClavePlanta = new JTextField();
		JLabel ID = new JLabel();
		ClavePlanta.setBounds(400,175, 200, 40);
		panel.add(ClavePlanta);
		ID.setBounds(500, 110, 100, 100);
		ID.setText("ID");
		panel.add(ID);
	}

	public void ListaPlantas() {
		JLabel Lista = new JLabel();
		Lista.setBounds(175, 105, 100, 100);
		Lista.setText("ID  /  Plantas ");
		panel.add(Lista);
		Adelfa = new JLabel();
		Adelfa.setBounds(100, 110, 100, 200);
		Adelfa.setText("1/Adelfa");
		Azalea = new JLabel();
		Azalea.setBounds(100, 125, 100, 200);
		Azalea.setText("2/Azalea");
		Baladre = new JLabel();
		Baladre.setBounds(100, 140, 100, 200);
		Baladre.setText("3/Baladre");
		Cactus = new JLabel();
		Cactus.setBounds(100, 155, 100, 200);
		Cactus.setText("4/Cactus");
		Camelia = new JLabel();
		Camelia.setBounds(100, 170, 100, 200);
		Camelia.setText("5/Camelia");
		Cotalillo = new JLabel();
		Cotalillo.setText("6/Cotalillo");
		Cotalillo.setBounds(100, 185, 100, 200);
		FlorDelBeso = new JLabel();
		FlorDelBeso.setText("7/FlorDelBeso");
		FlorDelBeso.setBounds(100, 200, 100, 200);
		Gallitos= new JLabel();
		Gallitos.setText("8/Gallitos");
		Gallitos.setBounds(250, 110, 100, 200);
		Gardenia = new JLabel();
		Gardenia.setText("9/Gardenia");
		Gardenia.setBounds(250, 125, 100, 200);
		GarraDeLeon= new JLabel();
		GarraDeLeon.setText("10/GarraDeLeon");
		GarraDeLeon.setBounds(250, 140, 100, 200);
		Helecho = new JLabel();
		Helecho.setText("11/Helecho");
		Helecho.setBounds(250, 155, 100, 200);
		Kiwi = new JLabel();
		Kiwi.setText("12/Kiwi");
		Kiwi.setBounds(250, 170, 100, 200);
		Liston = new JLabel();
		Liston.setText("13/Liston");
		Listom.setBounds(250, 185, 100, 200);
		Chaguaramo = new JLabel();
		Chaguaramo.setText("14/Chaguaramo");
		Chaguaramo.setBounds(250, 200, 100, 200);
		Orquidea = new JLabel();
		Orquidea.setText("15/Orquidea");
		Orquidea.setBounds(250, 215, 100, 200);
		panel.add(Orquidea);
		panel.add(Azalea);
		panel.add(Chaguaramo);
		panel.add(Liston);
		panel.add(Kiwi);
		panel.add(Helecho);
		panel.add(GarraDeLeon);
		panel.add(Gardenia);
		panel.add(Gallitos);
		panel.add(FlorDelBeso);
		panel.add(Baladre);
		panel.add(Camelia);
		panel.add(Azalea);
		panel.add(Adelfa);
		panel.add(Cactus);
		panel.add(Cotalillo);
	
	}	
	}
	