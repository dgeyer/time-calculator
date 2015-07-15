package com.sciencedirect.interview;

public class TimeCalculator implements Calculable<String> {

	private static final String COLON = ":";
	
	private TimeCalculator(){};
	
	public static Calculable<String> getInstance(){
		return new TimeCalculator();
	}

	public String sum(String a, String b) {
		String[] splitA = a.split(COLON);
		String[] splitB = b.split(COLON);
		
		validateInput(splitA);
		validateInput(splitB);
		
		String[] result = sum(splitA, splitB);
		return result[0]+result[1]+result[2];
	}

	private String[] sum(String[] splitA, String[] splitB) {
		String[] result = new String[3];
		int take = 0;
		for (int i = splitA.length - 1; i >= 0; i--) {
			int sum = Byte.valueOf(splitA[i]) + Byte.valueOf(splitB[i]) + take;
			if (i > 0 && sum >= 60) {
				sum = sum - 60;
				take = 1;
			} else if(i == 0 && sum >= 24){
				sum = sum - 24;
				take = 0;
			}else {
				take = 0;
			}

			result[i] = i > 0 ? COLON + String.format("%02d", sum) : String
					.format("%02d", sum);
		}
		return result;
	}

	private void validateInput(String[] split) {
		if(split.length != 3){
			throw new IllegalArgumentException("The hour must be in the format 24:00:00");
		}
		if(Byte.valueOf(split[0]) < 0 || Byte.valueOf(split[0]) > 24){
			throw new IllegalArgumentException("The hour must be greather than 0 and less than 24");
		}
		if(Byte.valueOf(split[1]) < 0 || Byte.valueOf(split[1]) > 59){
			throw new IllegalArgumentException("The minutes must be greather than 0 and less than 59");
		}
		if(Byte.valueOf(split[2]) < 0 || Byte.valueOf(split[2]) > 59){
			throw new IllegalArgumentException("The seconds must be greather than 0 and less than 59");
		}
	}
}
