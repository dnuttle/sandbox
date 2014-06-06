package net.nuttle.sandbox.lang;

/**
 * For general java language experiments
 *
 */
public class LangLab 
{
  @SuppressWarnings("unused")
  private static String trimLine(final String str) {
    final StringBuilder sb = new StringBuilder();
    for (String line : str.split("\n")) {
      sb.append(line.trim()).append("\n");
    }
    return sb.toString();
  }
  
  public static void testClassSimpleName() {
		Class<?> c = LangLab.class;
		System.out.println(c.getSimpleName().toLowerCase());
	}

  /*
	 * System.arraycopy copies the first array into the second, at the position specifed.
	 * The example below copies the first two elements of x into y at position 0,
	 * replacing the elements that were already in place.
	 */
	public static void testArrayCopy() {
		int[] x = {1, 2, 3, 4};
		int[] y = { 5, 6, 7, 8, 9, 10};
		System.arraycopy(x, 0, y, 0, 3);
		for(int i : y) {
			System.out.println(i);
		}
	}

}
