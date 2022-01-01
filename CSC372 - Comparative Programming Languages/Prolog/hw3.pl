% Dominic Martinez

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% assign takes L and replaces all letters with numbers result in L2

assign(L1, L2) :- member(X, L1), X\= equals, X\=plus, \+integer(X), member(X2, [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]), \+member(X2, L1), replace(X, X2, L1, L3), assign(L3, L2). 
assign(L, L) :- break(L, L2), all_ints(L2).

% breakapart to check if all ints
break([H|T], [H, H2|T2]) :- break(T, [H2|T2]), integer(H).
break([H|T], [H2|T2]) :- break(T, [H2|T2]), \+ integer(H).
break([H|[]], [H]).

% checks to see if all members are ints
all_ints([]).
all_ints([X|Y]) :- all_ints(Y), integer(X).

% checks to see if it's a member of a List
member(X, [X|_]).
member(X, [_|Rest]) :- member(X, Rest).

% replaces X with Y in L, but results in L2
replace(_, _, [], []).
replace(X, Y, [X|Rest], [Y|Rest2]) :- replace(X, Y, Rest, Rest2).
replace(X, Y, [Z|Rest], [Z|Rest2]) :- Z \== X, replace(X, Y, Rest, Rest2).

% makes a sequence 0-9
sequence(X, X, [X]).
sequence(X, Y, [X|Result]) :- X < Y, Z is X + 1, sequence(Z, Y, Result).
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% collapse
collapse([B|[]], [B]).
collapse([H1, H2|T], [H1, B|BT]) :- collapse([H2|T], [B|BT]), integer(H1), \+ integer(H2).
collapse([H1, H2|T], [H1, B|BT]) :- collapse([H2|T], [B|BT]), \+ integer(H1).
collapse([H1, H2|T], [X|BT]) :- collapse([H2|T], [B|BT]), integer(H1), integer(H2), X is H1*10+B.


% verify
verify(L) :- vhelp(L, X, Y), X = Y.
vhelp([H, _|T], X, Y) :- vhelp(T, X2, Y), X is X2 + H.
vhelp([H|[]], 0, H).

% find
find(L, L4) :- assign(L, L3), collapse(L3, L4), verify(L4).

% solve
solve(P) :- find(P, P2), write(P2), nl, fail.