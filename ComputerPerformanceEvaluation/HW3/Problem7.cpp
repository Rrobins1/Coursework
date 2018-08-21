#include <cstdlib> 
#include <time.h>
#include <iostream>

static double f(double x) //the function being tested
{
	return exp(x);
}


static double monteCarlo( double upperBound )
{
	static const int N = 100000;  //number points
	static int n = 0;			  //number points within region
	static double x, y, result;
	srand(time(NULL));

	static double maxFunctionHeight = f(upperBound); //height of rectangle
	static double rectangleArea = upperBound * f(upperBound); //area rectangle

	n = 0;

	for (int i = 0; i < N; i++) //plot N points
	{
		x = (double)rand() / (double)RAND_MAX;
		y = maxFunctionHeight * (double)rand() / (double)RAND_MAX;
		if (f(x) >= y) n++;
	}
	result = rectangleArea * (double)n / double(N); 
	return result;
}

static void solve7(double x)
{
	static double sumTrials = 0;
	static const int NUMBER_TRIALS = 10;
	for (int i = 0; i < NUMBER_TRIALS; i++)
	{
		sumTrials += monteCarlo(x);
	}
	std::cout << "Area under function exp(x) from 0 to x: " << sumTrials / NUMBER_TRIALS << "\n";
}