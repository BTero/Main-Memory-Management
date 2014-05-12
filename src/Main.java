public class Main {
	
	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";
	
	private static int mm_size = 10000;
	private static int sim_step = 10000;
	
	public static void main(String[] args){
		Driver driver = new Driver(mm_size, sim_step);
		TestFileCreator creator = new TestFileCreator(mm_size, sim_step);
		creator.createTestFiles();
//		driver.runSimulation();
	}
}