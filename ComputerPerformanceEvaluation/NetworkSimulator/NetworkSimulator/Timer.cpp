/*
#include <thread>
#include <chrono>

using namespace std;

class Timer
{
public:
	//Timer Values
	typedef chrono::milliseconds Interval;
	typedef function<void(void)> Timeout;

	//Timer thread
	thread timerThread;

	//Start Timer
	bool running;
	void start(const Interval &interval, const Timeout &timeout)
	{
		running = true;
		timerThread = thread([=]()
		{
			while (running == true)
			{
				this_thread::sleep_for(interval);
				timeout();
			}
		});

		timerThread.join();
	}

	//Stop Timer
	void stop() { running = false; }
};
*/