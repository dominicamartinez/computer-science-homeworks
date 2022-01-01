fun count(x, []) = 0
|   count(x, [a]) = if (x=a) then 1 else 0
|   count(x, a::rest) = if (x=a) then 1 + count(x, rest) else 0 + count(x, rest);

fun pct(x, y) = round((real(x)/real(y))*100.0);

fun translate2([]) = []
|   translate2([a]) = [pct(count("B", a), length(a))]
|   translate2(a::rest) = [pct(count("B", a), length(a))] @ translate2(rest);

fun translate3(a, b) = (a, translate2(translate1(b)));

fun pick(x, y, a) = if x = 50 then "X" else if x < 50 then  y else a;

fun typefor([a, b, c, d]) = pick(a, "E", "I") ^ pick(b, "S", "N") ^ pick(c, "T", "F") ^ pick(d, "J", "P");

fun addtype((a, b)) = (a, b, typefor(b));

fun less((a, b, c), (d:string, e:int list, f:string)) = if (c > f) then false else true; 
