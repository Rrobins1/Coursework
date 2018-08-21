#include <iostream>
#include <iomanip>
using namespace std;


static char *serviceType, *arrivalType;
static double serviceMean, arrivalMean;
static double utilization, serviceVariation, arrivalVariation, responseTime, jobs, wait;



/*============================================
*findVariation 
*finds variation for types present
============================================*/
static double findVariation( char *dataType, double mean, double lowerBound, double upperBound )
{
	if (dataType == "constant") return 0;
	else if (dataType == "uniform")
	{
		//return (upperBound - lowerBound) / ((pow(3, .5) * (upperBound + lowerBound)));
		return 1.0 / 12.0 * pow(upperBound - lowerBound, 2);
	}

	else if (dataType == "exponential")
	{
		return mean*mean;
	}

	return 500;
}



/*============================================
* findUtilization
* finds the utilization
============================================*/
static double findUtilization( double avgServiceTime, double avgInterarrivalTime)
{
	utilization = avgServiceTime/ avgInterarrivalTime;
	return utilization;
}

/*============================================
* findResponseTime
* finds the response time using G/G/1
============================================*/
static double findResponseTime( double avgService, double variationService, double utilization, double variationArrival)
{
	/*
	static double outside = avgService / (1.0 - utilization);
	static double numerator = (variationService * variationService + 1.0) * (variationArrival * variationArrival - 1.0);
	static double denominator = (utilization * utilization * variationService * variationService + 1.0);
	static double inside = 1.0 - utilization / 2.0 * (1.0 - variationService * variationService - numerator / denominator);
	responseTime = inside*outside;
	return responseTime;
	*/

}

/*=========================================
*findJobs
=========================================*/
static double findJobs( double utilization )
{
	jobs = utilization / (1.0 - utilization);
	return jobs;
}

/*=========================================
*findWait
=========================================*/
static double findWait()
{
    wait = utilization / (1 - utilization) * (pow(serviceVariation, 2) + pow(arrivalVariation, 2)) / 2 * serviceMean;
	return wait;
}

/*============================================
*findCalculations
*finds all items 
===========================================*/
static void findCalculations(char *inArrivalType, double inArrivalMean, char *inServiceType, double inServiceMean, double arrivalLowerBound, double arrivalUpperBound, double serviceLowerBound, double serviceUpperBound )
{
	serviceType = inServiceType;
	serviceMean = inServiceMean;
	arrivalType = inArrivalType;
	arrivalMean = inArrivalMean;
	
	
	arrivalVariation = findVariation( arrivalType, arrivalMean, arrivalLowerBound, arrivalUpperBound);
	serviceVariation = findVariation(serviceType, serviceMean, serviceLowerBound, serviceUpperBound);
	findUtilization(serviceMean, arrivalMean);
	responseTime = serviceMean + findWait();
	//findResponseTime(serviceMean, serviceVariation, utilization, arrivalVariation);
	findJobs(utilization);
}


/*===========================================
*printResults
*prints results
=========================================*/
static void printResults()
{
	cout << "Calculated:\n";
	cout << setw(29) << setprecision(5) << setfill(' ') << "Average";
	cout << setw(15) << setprecision(5) << setfill(' ') << "Deviation\n";
	cout << "Interarrival: ";
	cout << setw(15) << setprecision(5) << setfill(' ') << arrivalMean;
	cout << setw(15) << setprecision(5) << setfill(' ') << arrivalVariation << "\n";
	cout << "Service:      ";
	cout << setw(15) << setprecision(5) << setfill(' ') << serviceMean;
	cout << setw(15) << setprecision(5) << setfill(' ') << serviceVariation << "\n";
	cout << "Response:     ";
	cout << setw(15) << setprecision(5) << setfill(' ') << responseTime;
	cout << setw(15) << setprecision(5) << setfill(' ') << "" << "\n";
	cout << "Wait Time:    ";
	cout << setw(15) << setprecision(5) << setfill(' ') << wait;
	cout << setw(15) << setprecision(5) << setfill(' ') << "\n";
	cout << "Utilization:  ";
	cout << setw(15) << setprecision(5) << setfill(' ') << utilization;
	cout << setw(15) << setprecision(5) << setfill(' ') << "" << "\n\n\n";
}