package eg.edu.alexu.csd.datastructure.calculator.cs60;

import eg.edu.alexu.csd.datastructure.calculator.ICalculator;

public class Play implements ICalculator {

	@Override
	public int add(int x, int y) {
		return x+y ;
	}

	@Override
	public float divide(int x, int y) {
		return (float)x/y;
	}

}
