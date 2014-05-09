public class Main {
	
	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";
	
	private static int mm_size = 10000;
	
	public static void main(String[] args){
		Driver d = new Driver(mm_size);
		d.runSimulation();
	}
}