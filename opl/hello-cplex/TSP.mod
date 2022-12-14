/*********************************************
 * OPL 22.1.0.0 Model
 * Author: mars
 * Creation Date: 6 Oct 2022 at 22:40:28
 *********************************************/

int NVertex = ...;
range Vertexs = 1..NVertex;

float c[Vertexs][Vertexs] = ...;
//[
//	[0, 2, 4, 2],
//	[3, 0, 5, 90],
//	[1, 4, 0, 130],
//	[100, 400, 100, 0]
//];

dvar int+ x[Vertexs][Vertexs];	
dvar int+ u[Vertexs];			// artificial variable
dvar int+ p[Vertexs];
dvar float+ cost;

minimize cost;

subject to {
  cost == (sum(i in Vertexs, j in Vertexs) c[i][j] * x[i][j]) 
				- (sum(i in Vertexs) c[i][i] * x[i][i]);

  forall(i in Vertexs) p[i] == sum(j in Vertexs) j * x[i][j];
  
  forall(i in Vertexs, j in Vertexs) 0 <= x[i][j] <= 1;
  
  forall(i in Vertexs) 
  	(sum(j in Vertexs) x[i][j]) - (x[i][i]) == 1;
  forall(j in Vertexs) 
    (sum(i in Vertexs) x[i][j]) - (x[j][j]) == 1;
	
  forall(i in 2..NVertex, j in 2..NVertex) if (i != j) {
    u[i] - u[j] + (NVertex - 1) * x[i][j] <= NVertex - 2;
  };
  u[1] == 0;
  forall(i in 2..NVertex) {
    1 <= u[i] <= NVertex;
  };
  
}

