#pragma once

#include "StackInterface.hpp"
#include "Node.hpp"
#include <vector>
#include <cassert>

using namespace std;

template<class T>
class DLinkedStack : public StackInterface<T>
{
private:
    Node<T> *headPtr;    // Pointer to first node in the chain;
    Node<T> *topPtr;    // Pointer to (last) node in the chain that contains the stack's top
public:
    DLinkedStack();
    DLinkedStack(const DLinkedStack<T> &aStack);    // Copy constructor
    virtual ~ DLinkedStack();    // Destructor
    Node<T> *getPointerTo(const T &target) const;
    bool isEmpty() const;
    bool push(const T &newItem);
    bool pop();
    T peek() const;
    vector<T> toVector() const;
    Node<T> *getHeadPTR() const;
    Node<T> *getTopPTR() const;
};

template<class T>
DLinkedStack<T>::DLinkedStack() : headPtr(nullptr), topPtr(nullptr) { }

template<class T>
DLinkedStack<T>::DLinkedStack(const DLinkedStack<T> &aStack)
{
    headPtr = nullptr;
    topPtr = nullptr;
    if(aStack.isEmpty()) return;

    Node<T> *currentPtr = aStack.headPtr;

    while (currentPtr != nullptr )
    {
        this->push(currentPtr->getItem());
        currentPtr = currentPtr->getNext();
    }
}

template<class T>
DLinkedStack<T>::~DLinkedStack()
{
    while (this->pop());
}

template<class T>
Node<T> *DLinkedStack<T>::getPointerTo(const T &target) const
{
    Node<T> *currentPtr = this->headPtr;
    while (currentPtr != nullptr)
    {
        if(currentPtr->getItem() == target) return currentPtr;
        currentPtr = currentPtr->getNext();
    }
    return nullptr;
}

template<class T>
bool DLinkedStack<T>::isEmpty() const
{
    if(headPtr == nullptr && topPtr == nullptr) return true;
    return false;
}

template<class T>
bool DLinkedStack<T>::push(const T &newItem)
{
    if(topPtr == nullptr)
    {
        headPtr = new Node<T>(newItem,topPtr, nullptr);
        topPtr = headPtr;
        return true;
    }
    topPtr->setNext(new Node<T>(newItem,topPtr, nullptr));
    topPtr = topPtr->getNext();
    return true;
}

template<class T>
bool DLinkedStack<T>::pop()
{
    if(topPtr == nullptr || headPtr == nullptr) return false;
    if(topPtr == headPtr)
    {
        delete topPtr;
        topPtr = nullptr;
        headPtr = nullptr;
        return true;
    }

    Node<T> *newTop = topPtr->getPrev();
    delete topPtr;
    topPtr->setPrev(nullptr);
    topPtr = newTop;
    topPtr->setNext(nullptr);
    return true;
}


template<class T>
T DLinkedStack<T>::peek() const
{
    return topPtr->getItem();
}

template<class T>
vector<T> DLinkedStack<T>::toVector() const
{
    // DO NOT MODIFY THIS FUNCTION
    vector<T> returnVector;
    // Put stack items into a vector in top to bottom order
    Node<T> *curPtr = topPtr;
    while (curPtr != nullptr) {
        returnVector.push_back(curPtr->getItem());
        curPtr = curPtr->getPrev();
    }
    return returnVector;
}

template<class T>
Node<T> *DLinkedStack<T>::getHeadPTR() const
{
    // DO NOT MODIFY THIS FUNCTION
    return headPtr;
}

template<class T>
Node<T> *DLinkedStack<T>::getTopPTR() const
{
    // DO NOT MODIFY THIS FUNCTION
    return topPtr;
}