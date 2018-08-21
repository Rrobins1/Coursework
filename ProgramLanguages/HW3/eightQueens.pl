

% This solution solves the 8 Queens Problem
% I had to look at 3-4 different solutions for help
% I understand the solution myself now, I did not know before htat you could
% group X and Y with any separator between the two before

eightQueens([]).
eightQueens([X/Y|T]) :- eightQueens(T),
                          member(Y,[1,2,3,4,5,6,7,8]),
                          safeArea(X/Y, T).

safeArea(_,[]).
safeArea(X1/Y1, [X2/Y2 | T ]) :-
                      Y1 \== Y2,        %Two Y Coords Cannot Be Equal
                      Y2-Y1 \== X2-X1,  %Diagonal Must Be Clear
                      Y2-Y1 \== X1-X2,  %Diagonal Must Be Clear
                      safeArea( X1/Y1, T).

% Template for solution-- each column must have a queen
template([1/A,2/B,3/C,4/D,5/E,6/F,7/G,8/H]).
% I used A-H because chest boards are keyed by <Letter><Number>
