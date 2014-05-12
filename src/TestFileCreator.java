import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;


public class TestFileCreator {

	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";

	private static FileOutputStream outFile;
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
		double high, low;
		r = new Random();
		for(int i = 0; i < 4; i++){
			try{
				file = new File("TestFile" + i + ".txt");
				if(!file.exists()){
					fileData = new PrintStream(file);
					if(count <= 1){
						double mid_point = mm_size / 2;
						low = mid_point - 100;
						d = (3.0 / 10.0) * mm_size;
						a = r.nextInt(200) + (int)low;
						createSizes(a, d);
					}else if(count == 2){
						double quarter_point = mm_size / 4;
						low = quarter_point - 100;
						d = (3.0 / 10.0) * mm_size;
						a = r.nextInt(200) + (int)low;
						createSizes(a, d);
					}else if(count == 3){
						double point = (mm_size * 3) / 4;
						low = point - 100;
						d = (3.0 / 10.0) * mm_size;
						a = r.nextInt(200) + (int)low;
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
		for(int i = 0; i < sim_step; i++){
			double n = r.nextGaussian();
			if(n > 0){
				int value = (int) (n * d + a);
				if(value > mm_size - 4){
					i--;
				}else{
					fileData.println(value);
				}
			}else{
				i--;
			}

		}
	}
}
