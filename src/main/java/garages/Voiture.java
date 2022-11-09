package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void entreAuGarage(Garage g) throws Exception {
		if (estDansUnGarage()) throw new Exception("Une voiture ne peut pas être dans 2 garages à la fois");
		if (g==null) throw new IllegalArgumentException("Le garage ne doit pas être null");
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	public void sortDuGarage() throws Exception {
		if (!estDansUnGarage()) throw new Exception("Une voiture doit être dans un garage pour pouvoir en sortir");
		for(Stationnement s:myStationnements){
			if (s.estEnCours()){
				s.terminer();
				break;
			}
		}
	}

	public Set<Garage> garagesVisites() {
		HashSet <Garage> gs=new HashSet<>();
		for(Stationnement s:myStationnements){
			gs.add(s.getGarage());
		}
		return gs;
	}

	public boolean estDansUnGarage() {
		for(Stationnement s:myStationnements){
			if (s.estEnCours()) return true;
		}
		return false;
	}

	public void imprimeStationnements(PrintStream out) {
		for(Garage i:garagesVisites()){
			out.println(i+":");
			for(Stationnement s:myStationnements){
				if(s.getGarage()==i){
					out.println("	"+s);
				}
			}
		}
	}
}
