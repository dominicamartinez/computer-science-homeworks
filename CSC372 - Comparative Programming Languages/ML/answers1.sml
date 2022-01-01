fun twice(x:string):string = x^"-"^x;
fun power(x:int, y:int):int = if (y=0) then 1 else x * power(x,y-1);
fun repeat(x:string, y:int):string = if (y=0) then "" else x^repeat(x, y-1);
fun even(n:int):bool = if (n=0) then true else odd(n-1)
and odd(n:int):bool = if (n=0) then false else even(n-1);
fun twos(x:int):int = if (odd(x) = false) then 1 + twos(floor(real(x)/2.0)) else 0;
