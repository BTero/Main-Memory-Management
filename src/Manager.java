import java.util.LinkedList;
import java.util.Random;

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

		public int getStart(){
			return this.start;
		}

		public int getEnd(){
			return this.end;
		}

		public int getSize(){
			return this.size;
		}
	}

	private int STRATEGY;		// Strategy = 0 - first fit | Strategy = 1 - Best fit
	private int mm[];
	private int mm_size;
	private LinkedList<Block> list;
	private int total_size = 0;

	public Manager(int strat, int mm_size){
		this.STRATEGY = strat;
		this.mm_size = mm_size;
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
				return false;
			}//System.out.println(index);
			int hole_size = mm[index] * -1;
			if(size + 4 <= hole_size){						// request size is allocatable
				int end = index + size + 3;
				mm[index] = size;						// set size in left tag
				mm[end] = size;			// set size in right tag
				if(index == 0){
					mm[index + 1] = mm_size - 1;
				}else{
					mm[index + 1] = index - 1;				// set predecessor's pointer to previous block
				}

				if(index + size + 2 > mm_size - 1){
					int diff = index + size + 2 - mm_size;
					mm[diff] = diff + 2;
				}else{
					mm[index + size + 2] = end + 1;	// set successor's pointer to next block ( in this case the hole)
				}
				int new_size = hole_size - size - 4;
				mm[end + 1] = new_size * -1;
				mm[end + 2] = end;
				mm[new_size + end + 4] = new_size * -1;
				total_size += size;
				Block b = new Block(index, size, end);
				list.add(b);
				found = true;
			}else{
				start = index + hole_size + 4;
				index = search(STRATEGY, start);
				//				if(index + hole_size + 4 > mm.length){
				//					return found;
				//				}
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
		return -1;
	}

	public void release(){
		Random r = new Random();
		if(list.size() != 0){
			int block_index = r.nextInt(list.size());
			Block b = list.get(block_index);
			int block_start = b.getStart();
			int block_size = b.getSize();
			int block_end = b.getEnd();
			if(list.size() == 1){
				for(int i = 0; i < mm.length; i++){
					mm[i] = 0;
				}
				mm[0] = (mm.length - 4) * -1;					// size
				mm[mm.length - 1] = (mm.length - 4) * -1;		// size
				mm[1] = mm.length - 1;							// predecessor pointer
				mm[mm.length - 2] = 0;							// successor pointer
			}else{
				if(mm[mm[block_start + 1]] < 0){
					int left_end = mm[block_start + 1];
					int left_hole_size = mm[left_end] * -1;
					int left_start = left_end - left_hole_size - 3;
					if(left_start < 0){
						left_start = mm_size - left_hole_size - 3 + left_end;
					}
					int new_size = left_hole_size + block_size + 1;
					if(new_size <= 9996){
						mm[left_start] = new_size * -1;
					}
					mm[left_end] = 0;
					mm[left_end - 1] = 0;
					mm[block_start] = 0;
					mm[block_start + 1] = 0;
					mm[block_end] = new_size * -1;

				}
				if(mm[mm[block_end - 1]] < 0){
					int right_start = block_end + 1;
					int right_hole_size = mm[block_end + 1] * -1;
					int right_end = right_start + right_hole_size + 3;
					if(right_end > 9996){
						right_end -= mm_size;
					}
					int new_size = right_hole_size + block_size + 3;
					if(new_size <= mm_size){
						mm[right_end] = new_size * -1;
					}
					mm[right_start] = 0;
					mm[right_start + 1] = 0;
					mm[block_end] = 0;
					mm[block_end - 1] = 0;
					mm[right_end] = new_size * -1;
				}
			}
			list.remove(block_index);
			total_size -= block_size;
		}
	}

	public void setStrat(int strat){
		this.STRATEGY = strat;
	}
	
	public double getMemoryUtilization(){
		return (double) this.total_size / (double)this.mm_size;
	}
	
	public double getAverageSearchTime(){
		return 0;
	}

	public void reset(int strat){
		setStrat(strat);
		for(int i = 1; i < mm.length; i++){			
			mm[i] = 0;
		}
		// allocating size of usable memory and pointers
		mm[0] = (mm.length - 4) * -1;					// size
		mm[mm.length - 1] = (mm.length - 4) * -1;		// size
		mm[1] = mm.length - 1;							// predecessor pointer
		mm[mm.length - 2] = 0;							// successor pointer

		list.clear();
	}
}