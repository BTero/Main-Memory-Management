import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;

public class Driver {
	
	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";
	
	private static BufferedReader br;
	
	private static FileOutputStream resultFile;
	private static PrintStream fileData;
	private static File file;
	
	private int mm_size;
	private int sim_step;
	private Manager m;
	private Random r;
	private int data[];
	
		
	public Driver(int mm_size, int sim_step){
		this.mm_size = mm_size;
		this.sim_step = sim_step;
		m = new Manager(0, mm_size);
		r = new Random();
	}
	
	public void runSimulation(){
		boolean success= false;
		for(int i = 0; i < sim_step; i++){
			do{
				int size = data[i];
				success = m.request(size);
			}while(success == true);
			System.out.println("failure " + i);
		}
		
		// repeat simulation with a different strategy
//		m.reset(1);
//		for(int i = 0; i < sim_step; i++){
//			do{
//				int size = 0;
//				success = m.request(size);
//			}while(success == true);
//		}
	}
	

	public void parseFile(){
		String sourceFile = "";
		try{
			data = new int[mm_size];
			sourceFile = "TestFile0.txt";
			br = new BufferedReader(new FileReader(sourceFile));
			String line = br.readLine();
			int counter = 0;
			while(line != null){
				data[counter] = Integer.parseInt(line);
				line = br.readLine();
				counter++;
				
			}
		}catch(IOException e){
			e.printStackTrace();
			System.err.println("init: Errors accessing source file "
					+ sourceFile);
			System.exit(-2);
		}
	}
	
	
	public void record(){
		
	}
}