fun product([x]) = x
|   product(x::rest) = x * product(rest);

fun last([x]) = x
|   last(x::rest) = last(rest);

fun occurrences(_, []) = 0
|   occurrences(x, a::rest) = if (x=a) then 1 + occurrences(x, rest) else 0 + occurrences(x, rest); 

fun collapse([]) = []
|   collapse([x]) = [x]
|   collapse(x::y::rest) = [x+y] @ collapse(rest);

fun SwapPairs([]) = []
|   SwapPairs([x]) = [x]
|   SwapPairs(x::y::rest) = [y, x] @ SwapPairs(rest);

fun insert(x, []) = [x]
|   insert(x, a::rest) = if (x > a) then [a] @ insert(x, rest) else [x, a] @ rest;

fun minimu(x, [y]) = if (y < x) then y else x
|   minimu(x, a::rest) = if (a < x) then minimu(a, rest) else minimu(x, rest);
 
fun min([x]) = x
|   min(a::rest) = minimu(a, rest);

fun isort([]) = []
|   isort([a]) = [a]
|   isort(a::rest) = insert(a, isort(rest));
