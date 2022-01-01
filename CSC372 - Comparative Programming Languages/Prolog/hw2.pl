% written by Dominic Martinez
% assignment 2
% answer to question 1
ancestor_of(A, D) :- parent_of(A, D).
ancestor_of(A, D) :- parent_of(Z, D), ancestor_of(A, Z).

%answer to question 2
descendant_of(D, A) :- parent_of(A, D).
descendant_of(D, A) :- parent_of(A, Z), descendant_of(D, Z).

% answer to question 3
ancestor_of(A, D, N) :- parent_of(A, D), N is 1.
ancestor_of(A, D, N) :- parent_of(Z, D), ancestor_of(A, Z, N2), N is N2 + 1.

% answer to question 4
descendant_of(D, A, N) :- parent_of(A, D), N is 1.
descendant_of(D, A, N) :- parent_of(A, Z), descendant_of(D, Z, N2), N is N2 + 1.

% answer to question 5
f(X, Y) :- Y is 2*(X^2) - 3.

% answer to question 6
pow(X, Y, R) :- R is X^Y, Y >= 0.

% answer to question 7
twos(X, 0) :- X2 is X/2, X3 is floor(X2), X4 is X2 - X3, X4 \= 0.0.
twos(X, N) :- X2 is abs(X), X2 >= 2, X3 is X2/2, X4 is floor(X3), X5 is X4 - X3, X5 = 0.0, twos(X3, N2), N is N2+1.

% answer to question 8
second([_|T], R) :- mysecond(T, R).
mysecond([H|_], H).

% answer to question 9
length3(L) :- length(L, 3).

% answer to question 10
average(L, N) :- sum(L, Num), list_length(L, Denom), N is Num/Denom.
sum([], 0).
sum([X|Rest], N) :- sum(Rest, N2), N is N2+X.
list_length([], 0).
list_length([_|T], N) :- list_length(T, N2), N is N2+1.


% answer to question 11
nth([_|T], N, R) :- N2 is N-1, nth(T, N2, R).
nth([H|_], 1, H).
nth([], _, _) :- fail.

% answer to question 12
pairwiseEqual([]).
pairwiseEqual([H1, H2|T]) :- H1 = H2, pairwiseEqual(T).

% answer to question 13
repeat(_, 0, []).
repeat(X, N, [X|R]) :- N2 is N-1, N2 >= 0, repeat(X, N2, R). 


% answer to question 14
sorted([]).
sorted([_]).
sorted([H1, H2|T]) :- H1 =< H2, sorted([H2|T]).


% answer to question 15
stutter([],[]).
stutter([H|T], [H, H|Y2]) :- stutter(T, Y2).

% answer to question 16
unique([]).
unique([H|T]) :- unique(T), \+member(H, T).
member(X, [X|_]).
member(X, [_|Rest]) :- member(X, Rest).

% answer to question 17
vars([], 0).
vars([H|T], N) :- \+var(H) -> vars(T, N2), N is N2; vars(T, N2), N is N2 + 1.

