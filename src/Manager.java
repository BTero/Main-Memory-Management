public class Manager {

	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";
	
	private class Block{
		private int size;
		private int l_tag = 0;			// if 1 = occupied; 0 = free
		private int r_tag = 0;
		
		public Block(int size){
			this.size = size;
		}
	}
	
	private static int STRATEGY;		// Strategy = 0 - first fit | Strategy = 1 - Best fit
	private int mm[];
	
	public Manager(int strat, int mm_size){
		this.STRATEGY = strat;
		mm = new int[mm_size];
	}
	
	public boolean request(int size){
		if(STRATEGY == 1){
			
		}else if(STRATEGY == 2){
			
		}
		return false;
	}
	
	public void release(int index){
		
	}
}