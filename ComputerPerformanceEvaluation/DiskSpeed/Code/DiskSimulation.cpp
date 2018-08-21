//The testing environment utilizing the disk model

#include <iostream>
#include "QueueGeneration.h"
#include "HeadMovement.cpp"

using namespace std;

const int XMAX = int(xMax);
static const int NUMBERTRIALS = 100000;

struct SeekStatistics //Holds data for various queue sizes
{
	int queueSize;
	double averageTime;
	double averageDistance;
};

//Executes the simulation for a particular queue size and extracts data
static SeekStatistics simulate( int x) 
{
	static Queue *queuePtr;
	static int seekCounter;
	static int seekDistance, totalSeekDistance;
	static double seekTime, totalSeekTime;
	static SeekStatistics simulatedSeekStatistics;

	queuePtr = new Queue(x, XMAX);
	totalSeekDistance = 0;
	totalSeekTime = 0;
	for (seekCounter = 0; seekCounter < NUMBERTRIALS; seekCounter++)
	{
		seekDistance = queuePtr->moveToNext();
		totalSeekDistance += seekDistance;
		seekTime = calculateTimeRootEquation(seekDistance);
		totalSeekTime += seekTime;
	}
	simulatedSeekStatistics.queueSize = x;
	simulatedSeekStatistics.averageTime = double(totalSeekTime) / double(NUMBERTRIALS);
	simulatedSeekStatistics.averageDistance = double(totalSeekDistance) / double(NUMBERTRIALS);
	return simulatedSeekStatistics;
}

//Output method
static void printSeekStatistics(SeekStatistics statisticsToPrint)
{
	cout << "\nQueue Size: " << statisticsToPrint.queueSize
		<< "\tAvg Seek Distance: " << statisticsToPrint.averageDistance
		<< "\tAvg Seek Time: " << statisticsToPrint.averageTime;
}

int main()
{
	SeekStatistics seekData[20];
	for (int i = 0; i <= 20; i++) seekData[i] = simulate( i + 1 );  //Run the simulations
	for (int i = 0; i < 20; i++) printSeekStatistics(seekData[i]); //Print results
	cin.get();  //OS independant way of "pausing"
	return 0;
}