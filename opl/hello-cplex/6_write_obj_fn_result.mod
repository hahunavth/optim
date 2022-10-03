/*********************************************
 * OPL 22.1.0.0 Model
 * Author: mars
 * Creation Date: 3 Oct 2022 at 23:01:24
 *********************************************/

int nProducts = ...;

range Products = 1..nProducts;

float p[Products] = ...;
float t[Products] = ...;
float m[Products] = ...;
float d[Products] = ...;

int AvailTime = ...;
int AvailMaterial = ...;

dvar float+ x[Products];
dvar float+ z;		// -> write z to exel file

maximize z;

subject to {
  	z == sum(i in 1..10) p[i] * x[i];
  	
  	sum(i in Products) 	t[i] * x[i] 	<= AvailTime;
  	sum(i in Products) 	m[i] * x[i] 	<= AvailMaterial;
  	forall(i in Products)	x[i] 			<= d[i];
  };
 