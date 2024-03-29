import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;




public class CountInversions {
	
	public static void main(String [] args){
		CountInversions ci = new CountInversions();
		try{
			double[] numbers = ci.count();
			InverseHolder ih = ci.performCount(numbers, numbers.length);
			System.out.println("inverse count is ==>" + ih.count);
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	double m = 100000;
	private double[] numbers = new double[100000];
	
	public double[] count() throws Exception{
		File fil = new File("IntegerArray.txt");
		FileReader inputFil = new FileReader(fil);
		BufferedReader in = new BufferedReader(inputFil);

		int tall;
		double i = 0;

		String s =in.readLine();

		while(s!=null)
		{
		    
		    tall = Integer.parseInt(s); //this is line 19
		    System.out.println(tall);
		    numbers[(int)i] = tall;
		    i++;
		    s = in.readLine();
		}

		in.close();
		return numbers;
	}
	
	private InverseHolder performCount(double[] a, int n){
		
		InverseHolder ih = new InverseHolder();
		if(n <= 1){			
			ih.sorted = a;			
			return ih;
		}
		
		List<double[]> l = splitArray(a);
		double[] firstHalf = l.get(0);
		double[] secondHalf = l.get(1);
		InverseHolder ihb = performCount(firstHalf, n/2);
		InverseHolder ihc = performCount(secondHalf, n/2);
		
		double[] b = ihb.sorted;
		double[] c = ihc.sorted;
		
		double[] d = new double[n];
		int i = 0;
		int j = 0;
		int inv = 0;
		for (int k = 0; k < n; k++){
			if((i < b.length && i < c.length) && b[i] < c[i]){
				d[k] = b[i];
				i++;
			} else if ((j < c.length && i < b.length) && c[j] < b[i]){
					d[k] = c[j];
					j++;
					inv++;
			}
		}
		
		ih.sorted = d;
		ih.count = inv + ihb.count + ihc.count;
		return ih;
				
	}
	
		
	private List<double[]> splitArray(double a[]){
		
		System.out.println("split array==>" + a.length);
		List<double[]> l = new ArrayList<double[]>();
		
		double[] ret = null;
		int firsthalf = (a.length / 2);
		int half = (a.length / 2);
				
		if(a.length % 2 != 0){
			ret = new double[(a.length / 2) + 1];
			
		} else {
			ret = new double[(a.length / 2)];
		}
		int i;
		for(i = 0; i < ret.length; i++){
			ret[i] = a[i];
		}
		
		l.add(ret);
		
		ret = new double[(a.length / 2)];
		
		int k = 0;
		for(int j = i; j < a.length; j++){
			ret[k] = a[j];
			k++;
		}
		l.add(ret);
		return l;
	}
	
	public class InverseHolder{
		public double[] sorted;
		public double count;
	}
	

}
