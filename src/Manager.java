import java.util.LinkedList;

public class Manager {

	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";


	private int STRATEGY;		// Strategy = 0 - first fit | Strategy = 1 - Best fit
	private int mm[];
	private LinkedList<Block> list;

	public Manager(int strat, int mm_size){
		this.STRATEGY = strat;
		mm = new int[mm_size];
		list = new LinkedList<Block>();

		// initialize everything to zero
		for(int i = 1; i < mm.length; i++){			
			mm[i] = 0;
		}
		// allocating size of usable memory and pointers
		mm[0] = (mm.length - 4) * -1;					// size
		mm[mm.length - 1] = (mm.length - 4) * -1;		// size
		mm[1] = mm.length - 1;							// predecessor pointer
		mm[mm.length - 2] = 0;							// successor pointer
	}

	//	public void init(int mm_size){
	//		mm = new int[mm_size];
	//	}

	public boolean request(int size){
		int index = search(STRATEGY);
		if(index == -1){
			return false;
		}else{
			
		}
		return false;
	}

	public int search(int strat){					// finds the block corresponding to the allocation strategy used.
		//		boolean found = false;
		int cursor = 0;

		if(strat == 0){								// first-fit allocation
			for(int i = 0; i < mm.length; i++){		// find the location of the first size value in main memory
				if(mm[i] < 0){
					int size = (mm[i] * -1) + 2;
					cursor = i + size;
					if(cursor > mm.length){
						int diff = cursor - mm.length;
						if(mm[i] == mm[diff]){
							return i;
						}
					}else{
						if(mm[i] == mm[cursor]){
							return i;
						}
					}

					cursor = i - size;
					if(cursor < 0){
						if(mm[i] == mm[mm.length + cursor]){
							return mm.length + cursor;
						}
					}else{
						if(mm[i] == mm[cursor]){
							return cursor;
						}
					}
				}
			}
			return -1;
		}else if(strat == 1){						// best-fit allocation

		}
		return 0;
	}

	public void release(int index){

	}

	public boolean checkLeft(){
		return false;
	}

	public boolean checkRight(){
		return false;
	}
}