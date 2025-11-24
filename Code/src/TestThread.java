import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class TestThread {
	
	public static void main(String[] args) throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();
		String str1 = "Hello";
		String str2 = "Hello1";

		if(str1==str2)
		{
			System.out.println("str1 and str2 are the same object");
		}
		else
		{
			System.out.println("str1 and str2 are not the same object");
		};

		Date todayWithZeroTime = formatter.parse(formatter.format(today));	
		System.out.println(todayWithZeroTime);
		
	}
	public static void main1(String[] args) {
		
		
		String canonicalName  = TestThread.class.getPackage()+"."+TestThread.class.getSimpleName();
		System.out.println(canonicalName);
		System.exit(-1);
		
		boolean lock = true;
		
		if(lock=!lock)
		{
			System.out.println("--------"+lock);
		}
		
		
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(2);

		List<Future<Integer>> resultList = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}

		for (Future<Integer> future : resultList) {
			try {
				System.out.println("Future result is - " + " - " + future.get()
						+ "; And Task done is " + future.isDone());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// shut down the executor service now
		executor.shutdown();
	}
}

class FactorialCalculator implements Callable<Integer> {

	private Integer number;

	public FactorialCalculator(Integer number) {
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		int result = 1;
		if ((number == 0) || (number == 1)) {
			result = 1;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		System.out.println("Result for number - " + number + " -> " + result);
		return result;
	}
}

class Java8ForEachExample {

	public static void main(String[] args) {

		List<String> stringList = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			stringList.add("Name" + String.valueOf(i));

		stringList.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.print(t);

			}

		});
		System.out.println();
		stringList.forEach(s -> System.out.print(s));

	}

}