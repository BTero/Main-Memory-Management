import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
	private static String sourceFile = "TestFile3";
	
	private static PrintStream fileData;
	private static File resultFile;
	private static String outFile = "ResultFile" + sourceFile;
	
	private int mm_size;
	private int sim_step;
	private Manager m;
	private Random r;
	private int data[];
	
		
	public Driver(int mm_size, int sim_step){
		this.mm_size = mm_size;
		this.sim_step = sim_step;
		m = new Manager(1, mm_size);
		r = new Random();
		
		try {
			resultFile = new File(outFile + ".csv");
			fileData = new PrintStream(resultFile);
			fileData.println("Release point #, Memory Utilization, # of blocks searched before releasing, Counts made before release, Memory request size of release");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void runSimulation(){
		boolean success= false;
		int counter = 0;
		int size = 0;
		for(int i = 0; i < sim_step; i++){
			do{
				if(counter == 1000){
					return;
				}else{
					size = data[counter];
					success = m.request(size);
				}
				counter++;
			}while(success);
			fileData.println("Release #" + i + ", " + m.getMemoryUtilization() + ", " + m.getAverageSearchTime() + ", " + m.getCounts() + ", " + size);
			m.release();
		}
	}
	

	public void parseFile(){
		try{
			data = new int[mm_size];
			br = new BufferedReader(new FileReader(sourceFile + ".txt"));
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