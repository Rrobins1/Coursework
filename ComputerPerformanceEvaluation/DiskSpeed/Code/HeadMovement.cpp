//Model data, and methods for determining the seek time of a disk

#pragma once
#ifndef HEAD_MOVEMENT_CPP
#define HEAD_MOVEMENT_CPP

#include <cmath> //for power function

static const double xMax = 8057;	//Cylinders
static const double C = 9.1;		//GB, disk capacity
static const double N = 7200;		//RPM
static const double xStar = 1686;   //Number cyl before max speed
static const double t = 1.5455;     //ms, min seek time
static const double c = 0.3197;     //ms, second cylinder increment time
static const double r = 0.3868;     //

static const double calculateTime(double x); //calculates time to traverse x cylinders
static const double calculateTime(int x); 


static const double calculateTime(double x)  //using a double instead of int to prevent type conversion
{
	if (x == 0) return 0;
	else if (x <= xStar) return t + c*pow(x - 1.0, r);
	else return c*r*(x - xStar) / pow(xStar - 1.0, 1.0 - r) + t + c*pow(xStar - 1.0, r);
}

static const double calculateTime(int x)    //this converts int to double before operations
{
    double xDouble = double(x);
	if (xDouble == 0) return 0;
	else if (xDouble <= xStar) return t + c*pow(xDouble - 1.0, r);
	else return c*r*(xDouble - xStar) / pow(xStar - 1.0, 1.0 - r) + t + c*pow(xStar - 1.0, r);
}


static const double calculateTimeRootEquation(double x) //The square root model
{
	return pow(x / xMax, .5);
}

static const double calculateTimeRootEquation(int x) //The square root model
{
	return *pow( double(x) / xMax, .5);
}
#endif