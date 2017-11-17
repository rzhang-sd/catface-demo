package demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CatFacePerfect {
	
	// pixel of the perfect cat face 
	private char[][] sample;
	// index of the first pixel in each row
	private int[] start;
	// index of the last pixel in each row
	private int[] end;
	// the width of the sample image
	int width = 0;
	
	private CatFacePerfect() {}
	private static CatFacePerfect instance = null;
	
	
	public CatFacePerfect(String filename) {
		ArrayList<String> perfect = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().length() > 0) {
				    perfect.add(line);
				    width = Math.max(width, line.length());
				}
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}		
		initArray(perfect);
	}
	
	public char[][] getPerfectFace() {
		return sample;
	}
	
	public int[] getStartIdx() {
		return start;
	}
	
	public int[] getEndIdx() {
		return end;
	}
	
	public int getWidth() {
		return width;
	}
	
	/**
	 * Store the perfect face string in 2d array as sample
	 * 
	 * @param perfect (String array)
	 */
	private void initArray(ArrayList<String> perfect) {
		System.out.println("the width is: " + width);
		sample = new char[perfect.size()][];
		start = new int[perfect.size()];
		end = new int[perfect.size()];
		
		for (int i = 0; i < perfect.size(); i++) {
			sample[i] = new char[width];
		}
				
		for (int i = 0; i < perfect.size(); i++) {
			start[i] = perfect.get(i).indexOf('+');
			end[i] = perfect.get(i).lastIndexOf('+');
			for (int j = 0; j <= end[i]; j++) {
				sample[i][j] = perfect.get(i).charAt(j);
			}
		}
	}

}
