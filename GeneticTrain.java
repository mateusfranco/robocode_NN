import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class GeneticTrain {
	public ArrayList<RNA> rna;

	public GeneticTrain(ArrayList<RNA> rna) {
		super();
		this.rna = rna;
		
	}
	
	public void saveWeigths() throws IOException {
		FileWriter file = new FileWriter("filename.txt");
		
		for (RNA rna2 : this.rna) {
			try {
				file.write("rna\n");
				ArrayList<String> aux = rna2.saveW();
				for (int i = 0; i < aux.size(); i++) {					
					file.write(aux.get(i));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		file.close();
	}
	
	public static void saveOneWeigths(RNA rna) throws IOException {
		FileWriter file = new FileWriter("filename.txt");
		
		try {
			file.write("rna\n");
			ArrayList<String> aux = rna.saveW();
			for (int i = 0; i < aux.size(); i++) {					
				file.write(aux.get(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		file.close();
	}
	
	
	
	public void loadAllWigths() {
		try {
				File file = new File("filename.txt");
				Scanner myReader = new Scanner(file);
				for (int i = 0; i < this.rna.size(); i++) {
					myReader.nextLine();
					for (int j = 0; j < this.rna.get(i).pesos.size(); j++) {
						for (int j2 = 0; j2 < this.rna.get(i).pesos.get(j).size(); j2++) {
							for (int k = 0; k < this.rna.get(i).pesos.get(j).get(j2).size(); k++) {
								String auxS = myReader.nextLine();
//								System.out.println(auxS);
								double auxd = Double.parseDouble(auxS);
								this.rna.get(i).pesos.get(j).get(j2).set(k,auxd);
							}
						}
					}
				}
				myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void loadOneWigths(RNA rna) {
		try {
			File file = new File("filename.txt");
			Scanner myReader = new Scanner(file);
			myReader.nextLine();
			for (int j = 0; j < rna.pesos.size(); j++) {
				for (int j2 = 0; j2 < rna.pesos.get(j).size(); j2++) {
					for (int k = 0; k < rna.pesos.get(j).get(j2).size(); k++) {
						String auxS = myReader.nextLine();
//								System.out.println(auxS);
						double auxd = Double.parseDouble(auxS);
						rna.pesos.get(j).get(j2).set(k,auxd);
					}
				}
			}
			myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}

	
	
}
