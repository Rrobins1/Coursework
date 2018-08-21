%Definitions
list1([a, b, c, d, e, f, g, h]).
list2([a, a, b, c, d, e, f, g, h]).
list3([a, a, a, b, c, d, e, f, g, h]).
list4([a, b, c, d, c, b, a]).



%Rules
firstElement( Element, [ H|_ ] ) :- Element = H.

lastElement( Element, [Element] ).
lastElement( Element, [_|T] ) :- lastElement(Element, T).

twoAdjacent( X, Y, [X,Y|_] ).
twoAdjacent( X,Y, [_|T] ) :- twoAdjacent(X, Y, T).

threeAdjacent( X, Y, Z, [X,Y,Z|_] ).
threeAdjacent( X, Y, Z, [_|T] ) :- threeAdjacent(X, Y, Z, T).

myAppendList( [], List, List ).
myAppendList( [X|TX], List, [X|T] ):- myAppendList( TX, List, T ).


delete( Element, [Element|T], T ).
delete( Element, [H|T], [H|T2]) :- delete(Element, T, T2).

insert( Element, List, ExpandedList ) :- delete(Element, ExpandedList, List).

computeLength( 0, []).
computeLength( Length, [_|T] ) :- computeLength( CurrentLength, T),
                                  Length is CurrentLength + 1.


myReverse( [], [] ).
myReverse( Reversed, [H|T] ) :- myReverse( RT, T ),
                                append( RT, [H], Reversed ).

isPalindrome( List ) :-  myReverse(Reversed, List),
                         List = Reversed, !.

displayList( [] ).
displayList( [H|T] ) :- write( H ); write(' ');
                        displayList( [T] ).
