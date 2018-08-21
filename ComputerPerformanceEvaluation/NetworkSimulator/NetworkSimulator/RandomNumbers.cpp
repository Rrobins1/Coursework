#include <math.h>
#include <cstdlib>
#include <time.h>
static int numbersGenerated = 0;

static double generateUniform( double combination )
{

	double lowerBound = floor(combination);
	double upperBound = (double) (combination - (double)lowerBound)  * (double)10;
	double difference =  (upperBound - lowerBound);

	return (double)rand() / (double)RAND_MAX * (double)difference + lowerBound;
}

static double generateExponential( double mean )
{
	return -1.0 * mean * log(1.0 + 1.0/(double)RAND_MAX - (double)rand()/(double)RAND_MAX);
}

static double generateConstant(double mean)
{
	return (double)mean;
}
