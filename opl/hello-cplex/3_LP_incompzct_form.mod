/*********************************************
 * OPL 22.1.0.0 Model
 * Author: mars
 * Creation Date: 2 Oct 2022 at 19:40:47
 *********************************************/

int nProduct = 10;

range Products = 1..nProduct;

int p[Products] = [8, 7, 5, 2, 2, 10, 10, 10, 10, 6];
int t[Products] = [5, 2, 4, 3, 3, 3, 3, 3, 5, 3];
int m[Products] = [21, 49, 22, 28, 31, 41, 43, 36, 31, 37];
int d[Products] = [218, 589, 707, 831, 166, 420, 840, 710, 652, 336];

int AvailTime = 200;
int AvailMaterial = 5000;

dvar float+ x[Products];

maximize sum(i in 1..10) p[i] * x[i];

subject to {
  	sum(i in Products) 	t[i] * x[i] 	<= AvailTime;
  	sum(i in Products) 	m[i] * x[i] 	<= AvailMaterial;
  	forall(i in Products)	x[i] 			<= d[i];
  	x[1] + x[5] <= x[6];
  };
