
public class GuitarHero {

	private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

	public GuitarHero() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		int length = keyboard.length();
		GuitarString[] guitar = new GuitarString[length];

		for (int i = 0; i < length; i++) {
			double frequency = 440.0 * Math.pow(2.0, (i-24.0)/12.0);
			guitar[i] = new GuitarString(frequency);
		}
		
		 // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();
            	
                // pluck the corresponding string
	            if (keyboard.contains(Character.toString(key))) {
	            	guitar[keyboard.indexOf(key)].pluck();
	            }
            }

            // compute the superposition of the samples
            double sample = 0;
            for (int i = 0; i < length; i++) {
            	sample += guitar[i].sample();
            	guitar[i].tic();
            }

            // send the result to standard audio
            StdAudio.play(sample);   
        }
	}

}
