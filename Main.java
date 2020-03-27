import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> camadas = new ArrayList<Integer>(); 
		camadas.add(2);
		camadas.add(3);
		camadas.add(3);
		camadas.add(1);
		
		
		ArrayList<RNA> aux = new ArrayList<RNA>();
		
		
		int populacao = 100;
		for (int i = 0; i < populacao; i++) {
			aux.add(new RNA(camadas,1));
		}
		
		
		
		
		
		
		GeneticTrain train = new GeneticTrain(aux);
		
		train.rna.get(0).printPesos();
		try {
			train.saveWeigths();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		train.loadAllWigths();
		
		try {
			GeneticTrain.saveOneWeigths(train.rna.get(0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		train.rna.get(0).printPesos();
		
		
	}
}
