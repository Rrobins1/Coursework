#include <vector>
#include <memory>
#include "Node.hpp"

#pragma once


using namespace std;

template<class ItemType>
class SortedDoublyLinkedList {
    //DO NOT MODIFY THIS CLASS DECLARATION. FIND T-O-D-O-S to work on.
private:
    std::shared_ptr<Node<ItemType>> head;
    std::shared_ptr<Node<ItemType>> tail;
    int size;
public:

    SortedDoublyLinkedList();
    SortedDoublyLinkedList(std::shared_ptr<Node<ItemType>> head, std::weak_ptr<Node<ItemType>> tail, int size);
    SortedDoublyLinkedList(const SortedDoublyLinkedList<ItemType> &list);    // Copy constructor
    std::shared_ptr<Node<ItemType>> getPointerTo(const ItemType &target) const;
    virtual ~ SortedDoublyLinkedList();    // Destructor should be virtual
    int getCurrentSize() const;
    bool isEmpty() const;
    bool add(const ItemType &newEntry);
    bool remove(const ItemType &anEntry);
    bool contains(const ItemType &anEntry) const;
    int getFrequencyOf(const ItemType &anEntry) const;
    vector<ItemType> toVector() const;
};

template<class ItemType>
SortedDoublyLinkedList<ItemType>::SortedDoublyLinkedList() : head(nullptr), tail(nullptr),
                                                             size(0) { }

template<class ItemType>
SortedDoublyLinkedList<ItemType>::SortedDoublyLinkedList(std::shared_ptr<Node<ItemType>> head, std::weak_ptr<Node<ItemType>> tail, int size) :
        head(head), tail(tail), size(size) { }

template<class ItemType>
SortedDoublyLinkedList<ItemType>::SortedDoublyLinkedList(const SortedDoublyLinkedList<ItemType> &list) {
    //TODO - Implement the copy constructor
    head = nullptr;
    tail = nullptr;
    if(list.isEmpty()) return;

    shared_ptr<Node<ItemType>> currentPtr = list.head;

    while (currentPtr != nullptr )
    {
        this->add(currentPtr->getItem());
        currentPtr = currentPtr->getNext();
    }
}

template<class ItemType>
std::shared_ptr<Node<ItemType>> SortedDoublyLinkedList<ItemType>::getPointerTo(const ItemType &target) const
{
    //TODO - Return the Node pointer that contains the target(return nullptr if not found)
    shared_ptr<Node<ItemType>> currentPtr = this->head;
    while (currentPtr != nullptr)
    {
        if(currentPtr->getItem() == target) return currentPtr;
        currentPtr = currentPtr->getNext();
    }
    return nullptr;
}

template<class ItemType>
SortedDoublyLinkedList<ItemType>::~SortedDoublyLinkedList()
{
    //TODO - Implement the destructor
    head = nullptr;
    size = 0;
}

template<class ItemType>
int SortedDoublyLinkedList<ItemType>::getCurrentSize() const
{
    //TODO - Return the current size

    return size;
}

template<class ItemType>
bool SortedDoublyLinkedList<ItemType>::isEmpty() const {
    //TODO - Return True if the list is empty
    if (size == 0 || head == nullptr || tail == nullptr) return true;
    return false;
}

template<class ItemType>
bool SortedDoublyLinkedList<ItemType>::add(const ItemType &newEntry) {
    //TODO - Add an item to the sorted Doubly Linked list
    if(head == nullptr)
    {
       // Node<ItemType> newNode = new Node(newEntry);
        head =  make_shared<Node<ItemType>>(newEntry);
        if( tail == nullptr) tail = head;
        size++;
        return true;
    }

    if(head->getItem() > newEntry)
    {
        shared_ptr<Node<ItemType>> temp = make_shared<Node<ItemType>>(newEntry);
        temp->setNext(head );
        head->setPrev(temp);
        head = temp;
        size++;
        return true;
    }

    shared_ptr<Node<ItemType>> previous = head;
    shared_ptr<Node<ItemType>> current = head;

    while(current != nullptr)
    {
        if (current->getItem() > newEntry)
        {
            previous->setNext ( make_shared<Node<ItemType>> (newEntry, previous, current ) );
            current->setPrev(previous->getNext());
            size++;
            return true;
        }
        previous = current;
        current = current->getNext();
    }
    previous->setNext( make_shared<Node<ItemType>> (newEntry, current, nullptr) );
    tail = previous->getNext();
    size++;
    return true;
}

template<class ItemType>
bool SortedDoublyLinkedList<ItemType>::remove(const ItemType &anEntry) {
    //TODO - Remove the Item(anEntry) from the list - Return true if successful
    if (!contains (anEntry) ) return false;
    if(head == tail)
    {
        head = nullptr;
        tail = nullptr;
        size--;
        return true;
    }

    shared_ptr<Node<ItemType>> current = this->head;
    shared_ptr<Node<ItemType>> previous = head;
    while (current != nullptr)
    {
        if (current->getItem() == anEntry) break;
        previous = current;
        current = current->getNext();
    }
    if (current == head)
    {
        head = head->getNext();
        size--;
        return true;
    }
    if (current == tail) tail = previous;
    previous -> setNext(current->getNext());
    size--;
    return true;
}

template<class ItemType>
bool SortedDoublyLinkedList<ItemType>::contains(const ItemType &anEntry) const {
    //TODO - Check if the List contains the Item(anEntry)- Return true if successful
    if ( head == nullptr || tail == nullptr) return false;
    shared_ptr<Node<ItemType>> current = this->head;
    while (current != nullptr)
    {
        if (current->getItem() == anEntry) return true;
        current = current->getNext();
    }
    return false;
}

template<class ItemType>
int SortedDoublyLinkedList<ItemType>::getFrequencyOf(const ItemType &anEntry) const {
    //TODO - Return the frequency of the Item(anEntry) in the list
    int count = 0;
    if ( head == nullptr || tail == nullptr) return 0;
    shared_ptr<Node<ItemType>> current = this->head;
    while (current != nullptr)
    {
        if (current->getItem() == anEntry) count++;
        current = current->getNext();
    }
    return count;
}

template<class ItemType>
vector<ItemType> SortedDoublyLinkedList<ItemType>::toVector() const {
    // DO NOT MODIFY THIS FUNCTION
    vector <ItemType> myVector;
    std::shared_ptr<Node<ItemType>> cur;
    cur = this->head;
    while (cur != nullptr) {
        myVector.push_back(cur->getItem());
        cur = cur->getNext();
    }
    return myVector;
}
