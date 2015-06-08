package ir.assignments.three;

public class DocScore implements Comparable<DocScore> {
	public int docId;
	public double score;
	@Override
	public int compareTo(DocScore o) {
		return Double.compare(o.score, score);
	}
}
