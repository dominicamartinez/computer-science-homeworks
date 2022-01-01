% This file contains predicates that will prove helpful for Prolog

% assignment #4.  It does not contain a definition for the 4-argument

% form of the anagram predicate.  It also does not contain a definition

% for the dictionary predicate, which should be loaded separately.

% succeeds iff arg1 is a member of arg2 (a list)

member(X, [X|_]).

member(X, [_|Rest]) :- member(X, Rest).


% append/2(L1, L2, L3) succeeds when L3 is the list formed by appending L1 to

% L2 (i.e., it contains all values from L1 in order followed by all values
% from L2 in order).
append([], L, L).
append([X|L1], L2, R) :- R = [X|L3], append(L1, L2, L3).

% remove/2(X, L1, L2) sets L2 to the list obtained by removing the first
% occurrence of instantiated value X from instantiated list L1; fails if
% X does not occur in L1
remove(X, [X|Rest], Rest).
remove(X, [Y|Rest], [Y|Rest2]) :- X \== Y, remove(X, Rest, Rest2).

% readint(P, N) takes an instantiated prompt in P and prompts the user for
% a value until a valid integer is entered.
readint(P, N) :- repeat, write(P), read(N), integer(N), !.

% anagram with no arguments prompts the user for a phrase and a maximum
% number of words to include in the answer, then prints all valid answers
% given the dictionary returned by the dictionary predicate
anagram :- write('Phrase to scramble (as a list of atoms)? '), read(L),
readint('Max words in result? ', N), dictionary(D),
anagram(L, N, D, R), write(R), nl, fail.

%%%%%%%%%%%%%%%%%%%%%%%%%% Written by Dominic Martinez
anagram(L, N, D, R) :- rip(L, L2), search(D, L2, [], R), olength(R, N2), N2 =< N.

% checks the length of the result
olength([_|T], N) :- length(T, N2), N is N2 + 1.
olength([], 0).

% turns [abc] into [a, b, c]
rip([H|T], L) :- rip(T, L2), name(H, L3), rip2(L3, L4), append(L4, L2, L).
rip([], []).
rip2([H|T], B) :- rip2(T, B2), char_code(X,H), append([X], B2, B). 
rip2([], []).

% this searches out the words in the dictionary
search(_, [], R, R).
search(D, L, L4, Result) :- member(X, D), rip([X], L2), cheapequality(L2, L), newremove(L2, L, L3), append([X], L4, R), search(D, L3, R, Result).

% removes [a, b, c] from [a, b, c, d, e, f]
newremove([H|T], L, L2) :- newremove(T, L, L4), remove(H, L4, L2).
newremove([], L, L).


% makes sure all the letters in dictionary word are in the anagram word
cheapequality([H|T], L2) :- comparison(H, L2), cheapequality(T, L2).
cheapequality([], _).

% this is part of cheap equality
comparison(X, [H|T]) :- X \= H, comparison(X, T).
comparison(X, [X|_]).



