package demo;

import java.util.ArrayList;

/**
 * The class to load the user images and 
 * compare with the perfect face
 * 
 * @author ruizhang
 *
 */
public class CatFaceDetecter {
	
	// convert the text matrix to 2d array
	private char[][] image;
	// the width of the image
	private int width = 0;
	// the match percentage 
	private double similarity;
	
	public CatFaceDetecter(ArrayList<String> img, double confidence) {
		
		image = new char[img.size()][];
		for (String line : img) {
			width = Math.max(width, line.length());
		}
		
		for (int i = 0; i < img.size(); i++) {
			image[i] = new char[width];
		}

		for (int i = 0; i < image.length; i++) {
			String line = img.get(i);
			image[i] = line.toCharArray();
		}
		similarity = confidence;
	}
	
	/**
	 * Method to find Cat face and return the position of matches
	 * @return
	 */
    public ArrayList<String> findCatFace() {
		
    	    ArrayList<String> results = new ArrayList<String>();
    	    
		char[][] sample = Application.sampleFace.getPerfectFace();

		for (int x = 0; x < image.length - sample.length + 1; x++) {
			for (int y = 0; y < image[x].length - sample[0].length + 1; y++) {
				// check similarity
				if (isFirstLineSimilar(sample[0], image[x], y, similarity)) {
					double rate = checkSimilarity(image, x, y);
					if (rate > similarity) {
						results.add("find one CatFace at row: " + x + " column: " + y + " confidence level: " + rate);
						y = y + sample[0].length;
					}
				}
			}
		}
		return results;
	}
	
	private double checkSimilarity(char[][] source, int x, int y) {
		
		char[][] sample = Application.sampleFace.getPerfectFace();
		int[] start = Application.sampleFace.getStartIdx();
		int[] end = Application.sampleFace.getEndIdx();
				
		if (x + sample.length > source.length || y + sample[0].length > source[x].length) {
			// out of the scope
			return 0;
		}
		
		int count = 0;
		int total = 0;
		
		for (int sx = 0; sx < sample.length; sx++) {
			total += end[sx] - start[sx] + 1;
			for (int sy = start[sx]; sy <= end[sx]; sy++) {
				if (source[x+sx][y+sy] != sample[sx][sy]) {
					count = count + 1;
				}
			}
		}
		return (double)(total - count)/total;
	}
	
	/**
	 * To find the beginning of catface image
	 * 
	 * @param sample
	 * @param source
	 * @param start_in_src
	 * @param similarity
	 * @return
	 */
	private boolean isFirstLineSimilar(char[] sample, char[] source, int start_in_src, double similarity) {
		if (sample.length + start_in_src > source.length) return false;
		int count = 0;
		int len = sample.length;
		for (int i = 0; i < sample.length; i++) {
			if (sample[i] != source[start_in_src + i]) {
				count++;
			}
		}
		
		double rate = (double)(len - count)/len; 
		
		if ( rate >= CatFaceConfig.first_line_min_similarity) {
			return true;
		}
		return false;
	}

}
