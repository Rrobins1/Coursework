eightQueens(  )  :-
                               permuteEight( X ),
                               permuteEight( Y ),
                               checkSets( X, Y ).


permuteEight( List ) :- findall(X, permutation([1,2,3,4,5,6,7,8], X), List).

checkSets([], []).
checkSets([A,B,C,D,E,F,G,H | RestX], [A2,B2,C2,D2,E2,F2,G2,H2 | RestY] ) :-
  checkSet( [A,B,C,D,E,F,G,H], [A2, B2, C2, D2, E2, F2, G2, H2]),
  checkSets( RestX, RestY).


checkSet([], []).
checkSet([X | RestX], [Y | RestY]) :-
    checkSet(X, Y, RestX, RestY),
    checkSet(RestX, RestY).

checkSet(X, Y, [X2 | RestX], [Y2 | RestY]) :-
    Y2 - Y \== X2 - X,
    Y2 - Y \== X - X2,
    checkSet( X,Y, RestX, RestY).
