#include <math.h>
#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

static double result3[45] = { 0 }; //array to store results
								   
//generate number between 1 and 4 with given distributions
static int generateSingleNumber3() 
{											
	double v = ( (double)rand() / (double)RAND_MAX);
	if (v < .1) return 1;
	else if (v < .3) return 2;
	else if (v < .6) return 3;
	else return 4;
}

//generate the two digit number
static int generateNumber3()
{
	return generateSingleNumber3() + 10 * generateSingleNumber3();
}

//fills result array with probability
static void results3() 
{
	const int N = 100000;
	for (int i = 0; i < N; i++) { result3[generateNumber3()] ++; }
	for (int i = 0; i < 45; i++) { result3[i] = result3[i] / (double)N; }
}

//outputs solution
static void solve3()
{
	srand(NULL);
	results3();
	cout << "Probability of values: \n";
	for (int i = 1; i < 45; i++)
	{
		if ((i - 1) % 10 == 0) cout << "\n";
		if ( result3[i] != 0 ) cout << i << ": " << result3[i] << "\t"; //im too lazy to program skipping
																	    //impossible values in a better way
	}
}