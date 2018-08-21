//Queue Generation And Shortest Seeking Operation
//Values are uniformly distributed

#include "QueueGeneration.h"
Queue::Queue(int Q, int xMax)
{
	srand(NULL);
	size = Q;
	dq = new int[ size ];
	this -> xMax = xMax;
	
	X = rand() % xMax + 1; //head generation
	for (counter = 0; counter < size; counter++)
	{
		dq[counter] = rand() % xMax + 1;	//place random numbers into the array
	}
}

std::ostream &operator<<(std::ostream &output, Queue &queue)//output
{
	for (int counter = 0; counter < queue.size; counter++)
	{
		output << queue.dq[counter] << " ";
	}
	output << "\n";
	return output;
}

/*==================================================
* moveToNext() : Int
* Returns: The distance the head moved
*
* Moves the head from current position to next position
* utilizing a shortest seek time first implementation.
* Once the head is moved, the cylinder location is
* then removed and another location is added using
* a random number generator.
==================================================*/
int Queue :: moveToNext()	
{	
	int smallestDistance = abs(X - dq[0]); //Just so it is initialized
	int smallestIndex = 0;

	if (smallestDistance == 0) //If there is no distance, no need to check
	{						   //for the closest location
		dq[0] = rand() % xMax + 1;
		return smallestDistance;
	}
	for (counter = 0; counter < size; counter++) //Loop to find closest value to X
	{
		if ( abs(X - dq[counter]) < smallestDistance )
		{
			smallestDistance = abs(X - dq[counter]);
			smallestIndex = counter;
		}
	}
	X = dq[smallestIndex];	 //Assign X 
	dq[smallestIndex] = rand() % xMax + 1;  //Replace old value with new random value
	return smallestDistance;
}