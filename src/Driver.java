import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;

public class Driver {
	
	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";
	

	private static FileOutputStream resultFile;
	private static PrintStream fileData;
	private static File file;
	
	private  int sim_step;
	private Manager m;
	private Random r;
		
	public Driver(int mm_size, int sim_step){
		this.sim_step = sim_step;
		int strat = 0;
		m = new Manager(strat, mm_size);
		r = new Random();
	}
	
	public void runSimulation(){
		boolean success= false;
		for(int i = 0; i < sim_step; i++){
			do{
				int size = 0;
				success = m.request(size);
			}while(success == true);
		}
		
		// repeat simulation with a different strategy
		
		for(int i = 0; i < sim_step; i++){
			do{
				int size = 0;
				success = m.request(size);
			}while(success == true);
		}
	}
	

	
	
	
	public void record(){
		
	}
}