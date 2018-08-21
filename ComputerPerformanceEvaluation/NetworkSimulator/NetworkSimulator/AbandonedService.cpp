/*
#include <queue> //queue
#include <chrono> //for timer
#include <thread>
#include "RandomNumbers.cpp" //number generation
#include "Timer.cpp" //Timer
using namespace std;

//Number of items to process
static const int NUMBER_TRIALS = 100000;
static const int TIMER_INTERVAL = 100;
static const int TIME_TO_RUN = 10;

class SimulateServer
{
private:
	double arrivalMean, serviceMean;
	//start time, time at which current item in server finishes, and ending time
	double startTime, arrivalFinishTime, serviceFinishTime, endTime;

	//values needed for results
	double avgInterarrivalTime, avgServiceTime, avgQueueLength, avgServerUtilization, avgResponseTime;
	double currentArrival, currentService, currentQueue, currentUtilization, currentResponseTime;

	//Pointers to functions
	double(*arrivalGeneration)(int);
	double(*serviceGeneration)(int);

	//Counter for trials
	int trialCounter;

	//Current Queue
	queue <double> serverQueue;

	//Timer
	Timer timer;

	//Running Thread
	thread runSimulation;

	void updateSimulation()
	{
		if (arrivalFinishTime > serviceFinishTime)
		{
			serviceFinishTime = serviceFinishTime - currentArrival;
			serverQueue.push(arrivalGeneration(arrivalMean));
		}

		else if (serviceFinishTime < arrivalFinishTime)
		{
			serviceFinishTime = serviceGeneration(serviceMean);
			if (serverQueue.empty) wait
		}
	}
	/*
	void updateSimulation()
	{
	if (arrivalFinishTime > serviceFinishTime)
	{
	serviceFinishTime = serviceFinishTime - currentArrival;
	serverQueue.push(arrivalGeneration(arrivalMean));
	}
	if (serviceFinishTime < arrivalFinishTime)
	{
	currentService = serviceGeneration(serviceMean);

	}


	}
	


public:
	void simulate(char *arrivalType, int arrivalMean, char *serverType, int serviceMean)
	{
		//Generate functions to be used for indicated server
		if (arrivalType == "uniform") arrivalGeneration = &generateUniform;
		else if (arrivalType == "exponential") arrivalGeneration = &generateExponential;
		else if (arrivalType == "constant") arrivalGeneration = &generateConstant;

		if (serverType == "uniform") serviceGeneration = &generateUniform;
		else if (serverType == "exponential") serviceGeneration = &generateExponential;
		else if (serverType == "constant") serviceGeneration = &generateConstant;


		//Generate initial values
		currentArrival = arrivalGeneration(arrivalMean);
		currentService = serviceGeneration(serviceMean);

		startTime = clock() / CLOCKS_PER_SEC;
		endTime = startTime + TIME_TO_RUN;

		arrivalFinishTime = startTime + currentArrival;
		serviceFinishTime = startTime + currentService;

		runSimulation = thread([&]()
		{
			while (clock() / CLOCKS_PER_SEC < endTime)
			{
				this_thread::sleep_for(chrono::milliseconds(100));
				updateSimulation();
			}
		});
	};


*/







