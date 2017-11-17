package demo;

import java.util.ArrayList;

public class Request {
	
	private double similarity;
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	public ArrayList<String> getImgMatrix() {
		return imgMatrix;
	}
	public void setImgMatrix(ArrayList<String> imgMatrix) {
		this.imgMatrix = imgMatrix;
	}
	private ArrayList<String> imgMatrix;
}
