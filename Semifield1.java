// Eric Landquist
// September 12, 1999
// Semifield1.java

// This program will implement a semifield, a field with no associative property, described in [Knuth, 184]. We define
// addition, multiplication over this field, and will test different ways of defining exponentiation and try to come up 
// with some conjectures about semifields.

class Semifield1 {

// The ground field: GF(4)
public static final int[] F = {0, 1, 2, 3}; 

// Addition table for F
public static final int[][] A = {{0, 1, 2, 3}, {1, 0, 3, 2}, {2, 3, 0, 1}, {3, 2, 1, 0}};

// Multiplication table for F
public static final int[][] M = {{0, 0, 0, 0}, {0, 1, 2, 3}, {0, 2, 3, 1}, {0, 3, 1, 2}};

// Squaring Table for F
public static final int[] S = {0, 1, 3, 2};

// The format for an element in S, the semifield, will be {a, b}, where a and b are elements of F.

	// The constructor.
	public Semifield1(){
	}

	// 
	public static void main(String args[]){
		// Two elements of S, and a result element of S.
		int[] s1 = {0, 0}, s2={0, 0}, t={0,0};
		// An exponent
		int e;
		
		// Counters)
		int i, j;

		// There are several different operations we can do, so lets enumerate the possibilities:

		// Add
		if(args[0].equals("a")) {
			s1[0] = Integer.parseInt(args[1]);
			s1[1] = Integer.parseInt(args[2]);
			s2[0] = Integer.parseInt(args[3]);
			s2[1] = Integer.parseInt(args[4]);
			
			t = add(s1, s2);
			
			printr(t);
		}
		
		else if(args[0].equals("m")) {
			s1[0] = Integer.parseInt(args[1]);
			s1[1] = Integer.parseInt(args[2]);
			s2[0] = Integer.parseInt(args[3]);
			s2[1] = Integer.parseInt(args[4]);
			
			t = mult(s1, s2);
			
			printr(t);
		}
		
		else if(args[0].equals("e")){
			if(args[2].equals("all")){
				// Then we will raise all elements of S to the given power in all ways.
				if(args[1].equals("0")){
					t[0] = t[1] = 0;
					printr(t);
				}
				
				else if(args[1].equals("1")){
					for(i=0; i<4; i++){
						t[0] = i;
						for(j=0; j<4; j++){
							t[1] = j;
							printr(t);
						}
					}
				}
				
				else if(args[1].equals("2")){
					for(i=0; i<4; i++){
						s1[0] = i;
						for(j=0; j<4; j++){
							s1[1] = j;
							t = mult(s1, s1);
							printr(t);
						}
					}
				} // End case for squaring.
				
				else if(args[1].equals("3")){
					for(i=0; i<4; i++){
						s1[0] = i;
						for(j=0; j<4; j++){
							s1[1] = j;
							t = mult(s1, mult(s1, s1));
							printr(t);
							t = mult(mult(s1, s1), s1);
							printr(t);
						}
					}
				} // End case for cubing.
			} // end if(args[2].equals("all")
		}
		
		else{
			System.out.println("Usage: java Semifield1 [a, m, e] [u] [v] [x] [y].");
		}
		
	}

	// This method will add two elements of S.
	public static int[] add(int[] s1, int[] s2){
		int[] t ={ A[s1[0]][s2[0]], A[s1[1]][s2[1]] }; 
		return(t);
	}
	
	// This method will multiply two elements of S.
	public static int[] mult(int[] s1, int[] s2){
		int[] t = {A[M[s1[0]][s2[0]]] [M[S[s1[1]]][s2[1]]], A[ A[ M[s1[1]][s2[0]] ] [M[S[s1[0]] ] [s2[1]] ] ][M[ S[s1[1]]][S[s2[1]] ] ] };
		return(t);
	}
	
	// Print the result of the computation.
	public static void printr(int[] r){
		System.out.println(r[0] + " " + r[1]);
	}
	
} // end Semifield1
