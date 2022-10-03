/*********************************************
 * OPL 22.1.0.0 Model
 * Author: mars
 * Creation Date: 1 Oct 2022 at 18:07:38
 *********************************************/
// Linear programming

dvar float+ x1;
dvar float+ x2;

maximize 5*x1 + 4*x2;
subject to 
{
	6*x1 + 4*x2	<= 24;
	x1 + 2*x2	<= 6;
	-x1 + x2	<= 1;
	x2			<= 2;
};
