#include <iostream>
#include <time.h>

using namespace std;

int integerGetLength ( int x)
{
    int temp = 1;  // cannot start at 0 because of while
    int magnitude = 0;
    while ( temp != 0 )  //returns length
    {
        temp = x / magnitude;
        magnitude *= 10;
    }
    return temp;
}
//bin search breaks into parts, until only one element, if it finds the element looking for it returns
 int iterativeBinarySearch(int arr[], int itemToFind) {
        int length = integerGetLength(itemToFind);
        int low = 0;
        int high = length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (itemToFind < arr[mid]) high = mid - 1;
            else if (itemToFind > arr[mid]) low = mid + 1;
            else return arr[mid];
        }
        return -1;
    }

    int recursiveBinarySearch( int arr[], int low, int high, int itemToFind )
    {
        if( low > high ) return -1;
        int mid = (low + high)/2;

        if( itemToFind > arr[mid] ) recursiveBinarySearch(arr, low, mid-1, itemToFind);
        else if( itemToFind < arr[mid] ) recursiveBinarySearch( arr, mid+1, high, itemToFind);
        else return arr[mid];
    }



int main ()
{
    double startTime = clock();
    double endTime;
    double resultTime;

    int arr[] = { 1, 2 , 2, 3, 4, 5, 6, 7, 7, 7, 8, 9 };
    int itemToFind = 6;

    iterativeBinarySearch( arr, itemToFind );
    endTime = clock();
    resultTime = (endTime-startTime)/CLOCKS_PER_SEC;
    cout <<"\nIterative: " << resultTime;

    startTime = clock();
    recursiveBinarySearch( arr, 0, 9, itemToFind );
    endTime = clock();
    resultTime = (endTime-startTime)/CLOCKS_PER_SEC;
    cout << "\nRecursive: " << resultTime;

    return 1;
}