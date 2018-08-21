//Queue Generation And Shortest Seeking Operation

#pragma once
#ifndef QUEUE_GENERATION_H
#define QUEUE_GENERATION_H
#include <ctime>    //for seeding values
#include <cstdlib>  //for rand
#include <iostream>

class Queue
{
private:
	friend std::ostream &operator<<(std::ostream&, Queue &);
	int size;
	int *dq;
	int counter;
    int xMax;

public:
	Queue(int Q, int xMax);
	int X; //HEAD POSITION;
	int moveToNext();
};

#endif
