package net.nuttle.sandbox.solr;

import org.apache.lucene.util.OpenBitSet;
import org.apache.solr.search.BitDocSet;
import org.apache.solr.search.DocIterator;
import org.apache.solr.search.DocSet;
import org.apache.solr.search.DocSlice;
import org.apache.solr.search.SortedIntDocSet;

/**
 * For solr and lucene experiments
 *
 */
public class SolrLab 
{
	/*
	 * Demonstrates that OpenBitSet starts with length of 1,
	 * but you can set bits beyond that, and length is adjusted.
	 * You can test something beyond the length of the set and it returns
	 * false, and length is not adjusted.
	 * Note that longs are 64 bits, so if you set(1), then if you print
	 * the first long in the set as a binary string, you get 10 (the first
	 * spot is for zero, the second is for 1).  If you also set 2, then 
	 * the binary string is 110 (2:on, 1:on, 0:off)
	 * So setting 1001 returns 1 followed by 41 zeroes in the 15th long
	 * 64 * 15 = 960, 41 zeroes go from 960 to 1000, and last spot is 1001
	 */
	public static void testOpenBitSet() {
		OpenBitSet s = new OpenBitSet();
		System.out.println("length: " + s.getBits().length);
		s.set(1001);
		System.out.println("get 1001: " + s.get(1001));
		System.out.println("length: " + s.getBits().length);
		System.out.println("get 1000001: " + s.get(1000001));
		System.out.println("length: " + s.getBits().length);
		s.set(1000001);
		System.out.println("length: " + s.getBits().length);
		long[] bits = s.getBits();
		int count = 0;
		for(long l : bits) {
			if(l!=0){
				System.out.println(count + ": " + Long.toBinaryString(l));
			}
			count++;
		}
	}

	public static void testDocs() {
		BitDocSet bs = new BitDocSet();
		bs.add(4);
		bs.add(1);
		bs.add(2);
		
		BitDocSet bs2 = new BitDocSet();
		bs2.add(5);
		bs2.add(2);
		
		DocSet fin = bs2.andNot(bs); //output only 5
		outputDocSet(fin);
		
		/* BitDocSet constructor not valid in Solr 4.8.1 (was supported in 4.1)
    long[] x = {8L}; //doc id 3
		bs2 = new BitDocSet(new OpenBitSet(x, 1));
		outputDocSet(bs2);
		
		fin = bs2.union(bs);
		outputDocSet(fin);
		*/
	}
	
	public static void testDocSlice() {
		int[] docs = {1, 2, 4, 5};
		float[] scores = {0.1F, 0.2F, 0.01F, 0.02F};
		DocSlice s = new DocSlice(0, 4, docs, scores, 4, 0.1F);
		BitDocSet bs = new BitDocSet();
		bs.add(1);
		bs.add(4);
		DocSet st = s.andNot(bs);
		int[] ids = {1,2,3};
		SortedIntDocSet sids = new SortedIntDocSet(ids);
		st = s.andNot(sids);
		System.out.println(st.getClass().getName());
	}
	
  public static void testDocSlice2() {
    int[] docs = {1, 2, 4, 5};
    float[] scores = {0.1F, 0.2F, 0.01F, 0.02F};
    DocSlice s = new DocSlice(1, 2, docs, scores, 12, 0.1F);
    DocIterator dit = s.iterator();
    while(dit.hasNext()){
      System.out.println(dit.nextDoc());
    }
    System.out.println("Matches: " + s.matches());
  }

  public static void testSortedIntDocSet() {
		int[] x = {1, 2, 3, 4};
		int[] y = {4, 5, 6, 7};
		SortedIntDocSet s1 = new SortedIntDocSet(x);
		SortedIntDocSet s2 = new SortedIntDocSet(y);
		DocSet s3 = s1.union(s2);
		System.out.println(s3.getClass().getName());
		outputDocSet(s3);
		System.out.println(s1.getClass().getName());
	}
	
	public static void outputDocSet(DocSet s) {
		DocIterator it = s.iterator();
		int count = 0;
		System.out.println("DocSet type: " + s.getClass().getSimpleName());
		while(it.hasNext()){
			System.out.println(count + ": " + it.nextDoc());
			count++;
		}
	}
}
