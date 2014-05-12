import java.util.LinkedList;

public class Manager {

	private static String studentName = "Brian Tero";
	private static String studentID = "90121402";
	private static String uciNetID = "BTero";

	
	private class Block{
		private int size;				// negative size represents a hole of that size | a positive size represents a occupied block of that size
		private int start;
		private int end;

		public Block(int start, int size, int end){
			this.start = start;
			this.size = size;
			this.end = end;
		}
	}

	private int STRATEGY;		// Strategy = 0 - first fit | Strategy = 1 - Best fit
	private int mm[];
	private LinkedList<Block> list;
	private int total_size = 0;

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
		boolean found = false;
		int start = 0;
		int index = 0;

		do{
			index = search(STRATEGY, start);
			if(index == -1){
				return found;
			}
			int hole_size = mm[index] * -1;
			if(size <= hole_size){						// request size is allocatable
				int end = index + size + 3;
				mm[index] = size;						// set size in left tag
				mm[end] = size;			// set size in right tag
				mm[index + 1] = index - 1;				// set predecessor's pointer to previous block
				mm[index + size + 2] = end + 1;	// set successor's pointer to next block ( in this case the hole)
				total_size += size;
				Block b = new Block(index, size, end);
				list.add(b);
				found = true;
			}else{
				index = search(STRATEGY, index + hole_size + 4);
			}
		}while(!found);

		return found;
	}

	public int search(int strat, int start){					// finds the block corresponding to the allocation strategy used.
		int cursor = 0;

		if(strat == 0){								// first-fit allocation
			for(int i = start; i < mm.length; i++){		// find the location of the first size value in main memory
				if(mm[i] < 0){
					int block_size = (mm[i] * -1) + 3;
					cursor = i + block_size;
					if(cursor > mm.length){				// checks if the found index is the left tag
						int diff = cursor - mm.length;
						if(mm[i] == mm[diff]){
							return i;
						}
					}else{
						if(mm[i] == mm[cursor]){
							return i;
						}
					}

					cursor = i - block_size;
					if(cursor < 0){						// checks if the found index is the right tag
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