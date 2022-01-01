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
