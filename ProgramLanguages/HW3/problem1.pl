%Definitions
m([eclipse, atom, euler, waring, jack, jimmy, chad]).
f([java, ruby, curie, sql, jill]).
family([eclipse, java, [ruby]]).
family([atom, ruby, [curie, waring]]).
family([euler, curie, [jill, jack]]).
family([waring, sql, [jimmy, chad]]).
%Rules
male(X) :- m(M), member(X,M).
female(X) :- f(F), member(X,F).

father(Father, Child) :- family([Father, _, Children]),
                         member(Child, Children).

mother(Mother, Child) :- family([ _, Mother, Children]),
                         member(Child, Children).

parent(Parent, Child) :- mother(Parent, Child);
                         father(Parent, Child).

grandFather(GrandFather, GrandChild) :- father(GrandFather, Parent),
                                        parent(Parent, GrandChild).

grandMother(GrandMother, GrandChild) :- mother(GrandMother, Parent),
                                        parent(Parent, GrandChild).

grandParent(GrandParent, GrandChild) :- grandFather(GrandParent, GrandChild);
                                        grandMother(GrandParent, GrandChild).

greatGrandParent(GreatGrandParent, GreatGrandChild) :- grandParent(GreatGrandParent, Parent),
                                                       parent(Parent, GreatGrandChild).

siblings1(SiblingX, SiblingY) :- parent(Parent, SiblingX), parent(Parent, SiblingY),
                                 SiblingX \== SiblingY.
siblings2(SiblingX, SiblingY) :- father(Father, SiblingX), father(Father, SiblingY),
                                 mother(Mother, SiblingX), mother(Mother, SiblingY),
                                 SiblingX \== SiblingY.

aunt(Aunt, Person) :- parent(Parent, Person),
                      siblings1(Parent, Aunt),
                      female(Aunt).

uncle(Uncle, Person) :- parent(Parent, Person),
                        siblings1(Parent, Uncle),
                        male(Uncle).

cousins(CousinX, CousinY) :- parent(ParentX, CousinX),
                             parent(ParentY, CousinY),
                             siblings1(ParentX, ParentY).

ancestor(Ancestor, Person) :- parent(Ancestor, Person);
                              parent(Ancestor, Z), ancestor(Ancestor, Z).
