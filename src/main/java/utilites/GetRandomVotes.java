package utilites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRandomVotes {
	
	public List<String> getRandomVotes(int value){
		List<String> votes = new ArrayList<String>();
		Random random = new Random();

		for (int i = 0; i < value + 1; i++) {
			votes.add(String.format("%d", (random.nextInt(100) + 100)));
		}
		return votes;
	}

}
