public class Driver {
	
	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";
	
	private static int sim_step = 10000;
	private Manager m;
	public Driver(int mm_size){
		int strat = 0;
		m = new Manager(strat, mm_size);
	}
	
	public void runSimulation(){
		boolean success= false;
		for(int i = 0; i < sim_step; i++){
			do{
				int size = 0;
				success = m.request(size);
			}while(success == true);
		}
	}
}