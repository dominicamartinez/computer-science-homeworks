%                                                               handout #3

% This is a sample knowledge base of family relationships based on 
%examples
% from chapter 1 of "Programming in Prolog" by Clocksin & Mellish.  This
% set of facts involves four of the children of Queen Victoria and Prince
% Albert.  Also included are Albert's parents and one grandchild of 
%Victoria.

% male(arg) succeeds iff arg is male
/* clause  1 */ male(edward).
/* clause  2 */ male(ernest).
/* clause  3 */ male(albert).
/* clause  4 */ male(alfred).

% female(arg) succeeds iff arg is female
/* clause  5 */ female(helena).
/* clause  6 */ female(marie).
/* clause  7 */ female(louise).
/* clause  8 */ female(alice).
/* clause  9 */ female(victoria).
/* clause 10 */ female(alexandra).

% parents(arg1, arg2, arg3) succeeds iff the parents of arg1 are arg2 
%(mother)
% and arg3 (father)
/* clause 11 */ parents(alice, victoria, albert).
/* clause 12 */ parents(alfred, victoria, albert).
/* clause 13 */ parents(albert, louise, ernest).
/* clause 14 */ parents(edward, victoria, albert).
/* clause 15 */ parents(alexandra, marie, alfred).
/* clause 16 */ parents(helena, victoria, albert).


% sibling_of(arg1, arg2) succeeds iff arg1 is a sibling of arg2
/* clause 17 */ sibling_of(X, Y) :- parents(X, M, F), parents(Y, M, F), X 
\= Y.

% sister_of(arg1, arg2) succeeds iff arg1 is a sister of arg2
% brother_of(arg1, arg2) succeeds iff arg1 is a brother of arg2
/* clause 18 */ sister_of(X, Y) :- female(X), sibling_of(X, Y).
/* clause 19 */ brother_of(X, Y) :- male(X), sibling_of(X, Y).

% mother_of(arg1, arg2) succeeds iff arg1 is the mother of arg2
% father_of(arg1, arg2) succeeds iff arg1 is the father of arg2
/* clause 20 */ mother_of(X, Y) :- parents(Y, X, _).
/* clause 21 */ father_of(X, Y) :- parents(Y, _, X).

% parent_of(arg1, arg2) succeeds iff arg1 is a parent of arg2
/* clause 22 */ parent_of(X, Y) :- mother_of(X, Y).
/* clause 23 */ parent_of(X, Y) :- father_of(X, Y).

% related(arg1, arg2) succeeds iff arg1 is blood-related to arg2
/* clause 24 */ related(X, Y) :- sibling_of(X, Y).
/* clause 25 */ related(X, Y) :- parent_of(X, Y).
/* clause 26 */ related(X, Y) :- parent_of(Y, X).

uncle_of(X, Y) :- parents(Y, _, Z), brother_of(X, Z).
uncle_of(X, Y) :- parents(Y, Z, _), brother_of(X, Z).

aunt_of(X, Y) :- parents(Y, _, Z), sister_of(X, Z).
aunt_of(X, Y) :- parents(Y, Z, _), sister_of(X, Z).

grandfather_of(X, Y) :- parents(Y, _, Z), father_of(X, Z).
grandfather_of(X, Y) :- parents(Y, Z, _), father_of(X, Z).

grandmother_of(X, Y) :- parents(Y, Z, _), mother_of(X, Z).
grandmother_of(X, Y) :- parents(Y, _, Z), mother_of(X, Z).

grandparent_of(X, Y) :- grandfather_of(X, Y).
grandparent_of(X, Y) :- grandmother_of(X, Y).

ancestor_of(A, D) :- parent_of(A, D).
ancestor_of(A, D) :- parent_of(Z, D), ancestor_of(A, Z).

descendant_of(D, A) :- parent_of(A, D).
descendant_of(D, A) :- parent_of(A, Z), descendant_of(D, Z).

ancestor_of(A, D, N) :- parent_of(A, D), N is 1.
ancestor_of(A, D, N) :- parent_of(Z, D), ancestor_of(A, Z, N2), N is N2 + 1.

descendant_of(D, A, N) :- parent_of(A, D), N is 1.
descendant_of(D, A, N) :- parent_of(A, Z), descendant_of(D, Z, N2), N is N2 + 1.



