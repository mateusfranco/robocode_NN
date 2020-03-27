import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.html.HTMLEditorKit.Parser;

public class RNA {
	ArrayList< ArrayList< ArrayList< Double > > > pesos;
	ArrayList< ArrayList< ArrayList< Double > > > pesosSigmoided;
	
	public RNA(ArrayList< Integer > camadasNeuronios, int inpShape) {
		
		Random rand = new Random();
		
		this.pesos = new ArrayList<ArrayList<ArrayList<Double>>>();
		this.pesosSigmoided = new ArrayList<ArrayList<ArrayList<Double>>>();
		
		int anterior = 0;
		
		for (int i = 0; i < camadasNeuronios.size(); i++) {
			
			if(i == 0)
				anterior = inpShape;
			
			ArrayList<ArrayList<Double >> aux = new ArrayList< ArrayList< Double > >(); 
				
			for (int j = 0; j < anterior; j++) {
				ArrayList<Double > aux2 = new ArrayList<Double >(); 
				for (int j2 = 0; j2 < camadasNeuronios.get(i); j2++) {
					aux2.add(rand.nextDouble());
				}
				aux.add(aux2);
			}
			anterior = camadasNeuronios.get(i);
			
			this.pesos.add(aux);
		}
		
	}
	
	public void printPesos() {
		for (int i = 0; i < this.pesos.size(); i++) {
			for (int j = 0; j < this.pesos.get(i).size(); j++) {
				for (int j2 = 0; j2 < this.pesos.get(i).get(j).size(); j2++) {
					System.out.printf(" %f",this.pesos.get(i).get(j).get(j2));
				}
				System.out.println("");
			}
			System.out.println("\n\n");
		}
	}

	public Double sigmoid(Double soma) {
		return 1 / (1 + Math.exp(-soma));
	}
	
	public ArrayList<ArrayList<Double>> sigmoidArray(ArrayList<ArrayList<Double>> m){
		for (int i = 0; i < m.size(); i++) {
			for (int j = 0; j < m.get(0).size(); j++) {
				m.get(i).set(j,this.sigmoid(m.get(i).get(j)));
			}
		}
		return m;
	}
	
	public Double devatedSigmoid(Double sig) {
		return sig * (1 - sig);
	}
	
	public ArrayList<ArrayList<Double>> dotProduct( ArrayList<ArrayList<Double>> matriz1, ArrayList<ArrayList<Double>> matriz2) {
		
		ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();

		for (int i = 0; i < matriz1.size(); i++) {
			ArrayList<Double> part = new ArrayList<>();
			for (int j = 0; j < matriz2.get(0).size(); j++) {
				Double sumPartial = 0.00;
				for (int k = 0; k < matriz1.get(0).size(); k++) {
					sumPartial += matriz1.get(i).get(k) * matriz2.get(k).get(j);
				}
				part.add(sumPartial);
			}
			result.add(part);
		}
		return result;
	}
	
	public double mean( ArrayList<Double> list) {
		double avg = 0;
		for (int i = 0; i < list.size(); i++) {
			avg += Math.abs(list.get(i));
		}
		avg /= list.size();
		return avg;
	}
	
	public ArrayList<ArrayList<Double>> tranpose(ArrayList<ArrayList<Double>> matriz){
		ArrayList<ArrayList<Double>> novaMatriz =
				new ArrayList<ArrayList<Double>>(); 
		

		for (int j = 0; j < matriz.get(0).size(); j++) {
			ArrayList<Double> aux = new ArrayList<Double>();
			for (int i = 0; i < matriz.size(); i++) {
				aux.add(matriz.get(i).get(j));
			}
			novaMatriz.add(aux);
		}
		
		return novaMatriz;
	} 
	
	public double calculateError(ArrayList<ArrayList<Double>> valueExpected, ArrayList<ArrayList<Double>> valueRNA){		
		ArrayList<Double> error = new ArrayList<Double>();
		for (int i = 0; i < valueExpected.size(); i++) {
			error.add(valueExpected.get(i).get(0) - valueRNA.get(i).get(0));
		}
		return this.mean(error);
	}
	
	public double feedForward(ArrayList<ArrayList<Double>> entradas, ArrayList<ArrayList<Double>> saidas) {
		ArrayList<ArrayList<Double>> sinapse
			= new ArrayList<ArrayList<Double>>();

		for (int i = 0; i < this.pesos.size(); i++) {
			if(i==0)
				sinapse = this.dotProduct(entradas, this.pesos.get(i));			
			else
				sinapse = this.dotProduct(this.pesosSigmoided.get(i-1), this.pesos.get(i));
			this.pesosSigmoided.add(this.sigmoidArray(sinapse));
			
		}
		return this.pesosSigmoided.get(this.pesosSigmoided.size()-1).get(0).get(0);
		//return this.calculateError(saidas, this.pesosSigmoided.get(this.pesosSigmoided.size()-1));
 	}
	
	public void backPropagation() {

	}
	
	public ArrayList<String> saveW() {
		ArrayList<String> aux = new ArrayList<String>();
		for (int i = 0; i < this.pesos.size(); i++) {
			for (int j = 0; j < this.pesos.get(i).size(); j++) {
				for (int j2 = 0; j2 < this.pesos.get(i).get(j).size(); j2++) {
					aux.add((Double.toString(this.pesos.get(i).get(j).get(j2))));
					aux.add("\n");
				}
			}
		}
		return aux;
	}
	
}
