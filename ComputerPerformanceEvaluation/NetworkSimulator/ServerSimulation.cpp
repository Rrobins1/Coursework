#include <iostream>
#include <queue> //queue
#include <iomanip> //setw
#include "RandomNumbers.cpp" //number generation

using namespace std;

//Number of items to process
static const int TOTAL_NUMBER_SERVICES = 100000;

class SimulateServer
{
private:
	char *arrivalType, *serviceType;
	double arrivalMean, serviceMean;
	double totalTime = 0;

	//Storing results to find std dev
	double serviceTimeArr[TOTAL_NUMBER_SERVICES];
	double interArrivalTimeArr[TOTAL_NUMBER_SERVICES * 2];
	double responseTimeArr[TOTAL_NUMBER_SERVICES * 2];
	double queueArr[TOTAL_NUMBER_SERVICES];
	double serverUtilizationArr[TOTAL_NUMBER_SERVICES];

	//Std dev
	double varService, varInterarrival, varResponse, varQueue, varUtilization, varWaitTime;

	
	
	//values needed for results

	//TOTALS
	double totalInterarrivalTime = 0, totalServiceTime = 0, totalResponseTime = 0, serverUtilizationFactor = 0, queueFactor = 0;

	//AVERAGES
	double avgInterarrivalTime, avgServiceTime, avgResponseTime, avgUtilization, avgQueue, avgWaitTime;

	//RESULTS
	double timeToNextArrival, currentServiceRemaining;
	double nextArrivalTime;

	//Pointers to functions
	double (*arrivalGeneration)(double value);
	double(*serviceGeneration)(double value);

	//Counter for trials
	int serviceCounter = 0;
	int arrivalCounter = 0;

	//Current Queue
	queue <double> simulatorQueue;


	void resetData()
	{
		 totalTime = 0;
		 totalInterarrivalTime = 0;
		 totalServiceTime = 0;
		 totalResponseTime = 0;
		 serverUtilizationFactor = 0;
		 queueFactor = 0;
	}

	void waitForArrival()
	{
		totalTime += timeToNextArrival;				//TIME
		arrive();
	}


	void getNextArrival()  //GENERATE NEXT ARRIVAL DATA
	{
		timeToNextArrival = arrivalGeneration(arrivalMean); 
		nextArrivalTime = totalTime + timeToNextArrival;
		totalInterarrivalTime += timeToNextArrival;			//TOTAL Interarrival time for all arrival times generated
		arrivalCounter++;
		interArrivalTimeArr[arrivalCounter - 1] = timeToNextArrival;
	}

	void getNextService() //GENERATE NEXT SERVICE
	{
		serviceCounter++;	
		currentServiceRemaining = serviceGeneration(serviceMean); //GENERATE NEXT SERVICE TIME
		totalServiceTime += currentServiceRemaining; 

		serviceTimeArr[serviceCounter -1] = currentServiceRemaining;
	}

	void arrive()	//PUSH, GENERATE NEXT ARRIVAL
	{
		queueArr[serviceCounter - 1] = simulatorQueue.size();
		simulatorQueue.push(nextArrivalTime );
		getNextArrival();
	}

	void arriveWhileServicing()
	{
		currentServiceRemaining = currentServiceRemaining - timeToNextArrival; //decrement service time
		totalTime += timeToNextArrival;		//THIS MUCH TIME PASSED		
		queueFactor += (double)( simulatorQueue.size() ) * timeToNextArrival; //Every time passes need to do this
		arrive();
	}

	void completeService()
	{
		//CHECK THIS LATER
		totalTime += currentServiceRemaining; //THIS MUCH TIME PASSED
		totalResponseTime += totalTime - simulatorQueue.front(); //Response time is found as dif between arrival and working on
		responseTimeArr[serviceCounter - 1] = totalTime - simulatorQueue.front();
		simulatorQueue.pop();

		timeToNextArrival = timeToNextArrival - currentServiceRemaining;  //find time to next arrival
		
		queueFactor += (double)( simulatorQueue.size() ) * currentServiceRemaining; //every time passes need to do this
	}

	void service()
	{
		{
			//IF NEXT JOB ARRIVES BEFORE CURRENT SERVICE FINISHES
			if (timeToNextArrival < currentServiceRemaining)  { arriveWhileServicing(); }
			//WORK ON NEXT ITEM
			else 
			{ 
				completeService(); 
				getNextService();
			}
		}
	}

	void generateAverages()
	{
		avgInterarrivalTime = totalInterarrivalTime / arrivalCounter;
		avgServiceTime = totalServiceTime / serviceCounter;
		avgResponseTime = totalResponseTime / serviceCounter;
		avgUtilization =  avgServiceTime / avgInterarrivalTime;
		avgQueue = queueFactor / serviceCounter;
		avgWaitTime = avgQueue * avgServiceTime;
	}

	double findVariance( double *item)
	{
		double tempArr[TOTAL_NUMBER_SERVICES];
		double tempSum = 0;
		double tempMean;
		double sumSquareDifference = 0;
		for (int i = 0; i < TOTAL_NUMBER_SERVICES; i++)
		{
			tempArr[i] = item[i];
			tempSum += tempArr[i];
		}
		tempMean = tempSum / TOTAL_NUMBER_SERVICES;

		for (int i = 0; i < TOTAL_NUMBER_SERVICES; i++)
		{
			sumSquareDifference += (tempArr[i] - tempMean) * (tempArr[i] - tempMean);
		}
		return sumSquareDifference / (TOTAL_NUMBER_SERVICES - 1);
		//return pow( (sumSquareDifference / (TOTAL_NUMBER_SERVICES - 1)), .5);
	}

	void calculateVariance()
	{
		for (int i = 0; i < TOTAL_NUMBER_SERVICES; i++)
		{
			serverUtilizationArr[i] = serviceTimeArr[i] / interArrivalTimeArr[i];
		}
		varUtilization = findVariance(serverUtilizationArr);
		varInterarrival = findVariance(interArrivalTimeArr);
		varService = findVariance(serviceTimeArr);
		varResponse = findVariance(responseTimeArr);
		varQueue = findVariance(queueArr);
	}
	
public:
	SimulateServer(char *arrivalType, double arrivalMean, char *serviceType, double serviceMean)
	{
		this->arrivalType = arrivalType;
		this->arrivalMean = arrivalMean;
		this->serviceType = serviceType;
		this->serviceMean = serviceMean;
	}

	void simulate()
	{
		//Generate functions to be used for indicated server
		if (arrivalType == "uniform") arrivalGeneration = generateUniform;
		else if (arrivalType == "exponential") arrivalGeneration = generateExponential;
		else if (arrivalType == "constant") arrivalGeneration = generateConstant;

		if (serviceType == "uniform") serviceGeneration = generateUniform;
		else if (serviceType == "exponential") serviceGeneration = generateExponential;
		else if (serviceType == "constant") serviceGeneration = generateConstant;


		//Generate initial values
		resetData();
		getNextArrival();
		getNextService();
		

		while (serviceCounter < TOTAL_NUMBER_SERVICES)
		{
			if (simulatorQueue.empty())  waitForArrival();
			service();
		}

		generateAverages();
		calculateVariance();
		printResults();
	}

	void printResults()
	{
		cout << "Results:\n";
		cout << setw(29) << setprecision(5) << setfill(' ') << "Average";
		cout << setw(15) << setprecision(5) << setfill(' ') << "Variance\n";
		cout << "Interarrival: ";
		cout << setw(15) << setprecision(5) << setfill(' ') << avgInterarrivalTime;
		cout << setw(15) << setprecision(5) << setfill(' ') << varInterarrival << "\n";
		cout << "Service:      ";
		cout << setw(15) << setprecision(5) << setfill(' ') << avgServiceTime;
		cout << setw(15) << setprecision(5) << setfill(' ') << varService << "\n";
		cout << "Response:     ";
		cout << setw(15) << setprecision(5) << setfill(' ') << avgResponseTime;
		cout << setw(15) << setprecision(5) << setfill(' ') << varResponse << "\n";
		cout << "Queue Length: ";
		cout << setw(15) << setprecision(5) << setfill(' ') << avgQueue;
		cout << setw(15) << setprecision(5) << setfill(' ') << varQueue << "\n";
		cout << "Wait Time:    ";
		cout << setw(15) << setprecision(5) << setfill(' ') << avgWaitTime;
		cout << setw(15) << setprecision(5) << setfill(' ') << "" << "\n";
		cout << "Utilization:  ";
		cout << setw(15) << setprecision(5) << setfill(' ') << avgUtilization;
		cout << setw(15) << setprecision(5) << setfill(' ') << varUtilization << "\n\n\n";
		
	}
};