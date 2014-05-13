import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;


public class TestFileCreator {

	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";

//	private static FileOutputStream outFile;
	private static PrintStream fileData;
	private static File file;

	private  double mm_size;
	private  int sim_step;
	private Random r;

	public TestFileCreator(int mm_size, int sim_step){
		this.mm_size = (double) mm_size;
		this.sim_step = sim_step;

	}

	public void createTestFiles(){
		int count = 0;
		double  a, d;
		r = new Random();
		for(int i = 0; i < 4; i++){
			try{
				file = new File("TestFile" + i + ".txt");
				if(!file.exists()){
					fileData = new PrintStream(file);
					if(count <= 1){
						a = mm_size / 2;
						d = (3.0 / 10.0) * mm_size;
						createSizes(a, d);
					}else if(count == 2){
						a = mm_size / 4;
						d = (3.0 / 10.0) * mm_size;
						createSizes(a, d);
					}else if(count == 3){
						a = (mm_size * 3) / 4;
						d = (3.0 / 10.0) * mm_size;
						createSizes(a, d);
					}
					count++;
				}

			}catch(IOException e){
				e.printStackTrace();
				System.exit(-2);
			}
		}
	}

	public void createSizes(double a, double d){
		for(int i = 0; i < 1000; i++){
			double n = r.nextGaussian();
			int value = (int) (n * d + a);
			if(value > mm_size - 4 ){
				i--;
			}else if(value < 0){
				i--;
			}else{
				fileData.println(value);
			}


		}
	}
}
