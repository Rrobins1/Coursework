#include <iostream>
#include "ServerSimulation.cpp"
#include "MathCalculationsForServers.cpp"
#include <queue>
using namespace std;

int main()
{

	cout << "Goodbye World\n";
	queue <double> testQ;

	cout << "CONSTANT CONSTANT\n";

	SimulateServer *simulateServer;
	//CONSTANT ARRIVAL ( 2 sec )
	//CONSTANT SERVICE ( 1 sec )
	
	simulateServer = new SimulateServer("constant", 2, "constant", 1);
	simulateServer->simulate();
	
	findCalculations("constant", 2, "constant", 1, 0, 0, 0, 0);
	printResults();

	cout << "\n\nCONSTANT EXPONENTIAL\n";
	//CONSTANT ARRIVAL ( 2 sec )
	//EXPONENTIAL SERVICE ( mean = 1 sec )
	simulateServer = new SimulateServer("constant", 2, "exponential", 1);
	simulateServer->simulate();

	findCalculations("constant", 2, "exponential", 1, 0, 0, 0, 0 );
	printResults();

	cout << "\n\nCONSTANT UNIFORM\n";
	//CONSTANT ARRIVAL ( 2 sec )
	//UNIFORM SERVICE ( 1 - 2 sec )
	simulateServer = new SimulateServer("constant", 2, "uniform", 1.2);
	simulateServer->simulate();

	findCalculations("constant", 2, "uniform", 1.5, 0, 0, 1, 2);
	printResults();

	cout << "\n\nEXPONENTIAL CONSTANT\n";
	//EXPONENTIAL ARRIVAL ( mean = 2 sec )
	//CONSTANT SERVICE ( 1 sec )
	simulateServer = new SimulateServer("exponential", 2, "constant", 1);
	simulateServer->simulate();

	findCalculations("exponential", 2, "constant", 1, 0, 0, 0, 0);
	printResults();

	cout << "\n\nEXPONENTIAL EXPONENTIAL\n";
	//EXPONENTIAL ARRIVAL ( mean = 2 sec )
	//EXPONENTIAL SERVICE ( mean = 1 sec )
	simulateServer = new SimulateServer("exponential", 2, "exponential", 1);
	simulateServer->simulate();

	findCalculations("exponential", 2, "exponential", 1, 0, 0, 0, 0);
	printResults();

	cout << "\n\nEXPONENTIAL UNIFORM\n";
	//EXPONENTIAL ARRIVAL ( mean = 2 sec )
	//UNIFORM SERVICE ( 1 - 2 sec )
	simulateServer = new SimulateServer("exponential", 2, "uniform", 1.2);
	simulateServer->simulate();

	findCalculations("exponential", 2, "uniform", 1.5, 0, 0, 1, 2);
	printResults();

	cout << "\n\nUNIFORM CONSTANT\n";
	//UNIFORM ARRIVAL ( 1 - 3 sec )
	//CONSTANT SERVICE ( 1 sec )
	simulateServer = new SimulateServer("uniform", 1.3, "constant", 1);
	simulateServer->simulate();

	findCalculations("uniform", 2, "constant", 1, 1, 3, 0, 0);
	printResults();

	cout << "\n\nUNIFORM EXPONENTIAL\n";
	//UNIFORM ARRIVAL ( 1 - 3 sec )
	//EXPONENTIAL SERVICE ( mean = 1 sec )
	simulateServer = new SimulateServer("uniform", 1.3, "exponential", 1);
	simulateServer->simulate();

	findCalculations("uniform", 2, "exponential", 1, 1, 3, 0, 0);
	printResults();

	cout << "\n\nUNIFORM UNIFORM\n";
	//UNIFORM ARRIVAL ( 1 - 3 sec )
	//UNIFORM SERVICE ( 1 - 2 sec )
	simulateServer = new SimulateServer("uniform", 1.3, "uniform", 1.2);
	simulateServer->simulate();

	findCalculations("uniform", 2, "uniform", 1.5, 1, 3, 1, 2);
	printResults();

	/*=======================================
	*VERIFY RESULTS
	========================================*/

	system("PAUSE");
	return 0;
}