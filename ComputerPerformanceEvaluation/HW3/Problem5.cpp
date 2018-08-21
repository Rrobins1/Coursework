
#include <iostream>

static int lehmerGenerator( int x )
{
	static int a = 13;
	static int c = 13;
	static int m = 256;	
	return ( a * x + c ) % m;
}

static void solve5()
{
	static const int N = 256;
	static int value = 0;

	std::cout << "SEQUENCE STARTS: \n";
	for (int i = 0; i < N; i++)
	{
		std::cout << value << "\t";
		value = lehmerGenerator(value);
	}

	std::cout << "\n\nSEQUENCE REPEATS: \n";
	for (int i = 0; i < N; i++)
	{
		std::cout << value << "\t";
		value = lehmerGenerator(value);
	}
}

