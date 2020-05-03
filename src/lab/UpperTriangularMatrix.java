package lab;
public class UpperTriangularMatrix {
	private int[] dataMatrix2; 
	private int mSize; 
	
	//constructor 1
	public UpperTriangularMatrix(int n){
	    if(n<=0){
	    	n=1; // verifies whether or not the value for n chosen is in invalid, if it is not, it changes it to 1
	    }
	    mSize = n; //sets private variable mSize, to be n
	    dataMatrix2 = new int[((mSize*(mSize+1))/2)];
	    for(int count = 0; count < n; count++){
	    	dataMatrix2[count] = 0;//sets all values of the matrix to be 0
	    }
	}//end constructor 1
	
	
	//constructor 2
	public UpperTriangularMatrix(Matrix upTriM) throws IllegalArgumentException{
		int counter = 0;
		if(upTriM.isUpperTr()==false){
			throw new IllegalArgumentException("Not Upper Triangular"); //if the matrix is not upper triangle it returns this
		}
	    mSize = upTriM.matRows(); //sets mSize to be the same rows as Matrix upTrim
	    dataMatrix2 = new int[((mSize*(mSize+1))/2)];
	    for(int count = 0; count < mSize; count++){
	    	for(int count2 = count ;count2 < mSize; count2++){
	    		dataMatrix2[counter] = upTriM.getElement(count, count2); //sets values of dataMatrix2 to be values of upTrim
	    		counter++;
	    	}
	    }
	}//constructor 2
	
	
	//method 1
	 public int getDim(){
		 return mSize; //returns the size of rows/cols of matrix
	 }// end method 1
	 
	 
	 //method 2
	 public int getElement(int i, int j) throws IndexOutOfBoundsException{
		 Matrix elemMat = fullMatrix(); // creates new Matrix
		 
		 if(i < mSize && i >= 0){
			 if  (j < mSize && j >= 0) {
				 return elemMat.getElement(i,j); //if i and j are valid index, it returns the element in that index
			 }
			 else{
		    	 throw new IndexOutOfBoundsException("Invalid index"); //else it throws this exception
		     	 }
		     }
		 else {
	    	 throw new IndexOutOfBoundsException("Invalid index");//else it throws this exception
	    	 }
		     
	 }//end method 2
	 
	 
	 //method 3
	 public void setElement(int x, int i, int j) throws IndexOutOfBoundsException, IllegalArgumentException{
		 
		 if (i > mSize) {
			 throw new IndexOutOfBoundsException ("Invalid Indexes"); // valid values for index are not provided then throws exception
		 }
		 else if (j > mSize) {
			 throw new IndexOutOfBoundsException ("Invalid Indexes");// valid values for index are not provided then throws exception
		 }
		 else if (j < 0) {
			 throw new IndexOutOfBoundsException ("Invalid Indexes");// valid values for index are not provided then throws exception
		 }
		 else if (i < 0) {
			 throw new IndexOutOfBoundsException ("Invalid Indexes");// valid values for index are not provided then throws exception
		 }
		 else if ((x != 0) && (i>j)) {
			 throw new IllegalArgumentException ("Incorrect argument");// valid values for index are not provided then throws exception
			 }
		 else {
			 Matrix setMat = fullMatrix(); //creates new Matrix
			 setMat.setElement(x,i,j);
			 int counter = 0;
			 for(int count = 0; count < mSize; count++) {
				 for(int count2 = count; count2 < mSize; count2++) {
					 dataMatrix2[counter]= setMat.getElement(count,count2); //sets values of dataMatrix2 to be elements of setMat
					 counter++;
				 }
			 }
		 }
	 }// method 3
	 
	 
	 // method4
	 public Matrix fullMatrix(){
			Matrix fullMat = new Matrix(mSize, mSize);
		    int counter = 0;
		    for (int count = 0; count < mSize; count++){
		    	for (int count2 = count; count2 < mSize; count2++){
		    		fullMat.setElement(dataMatrix2[counter],count,count2);
		    		counter++;
		    	}
		    }
			return fullMat;
	 }// end method 4
	 
	 
	 //method 5
	 public void print1DArray(){
		 int count = 0;
		 while (count <dataMatrix2.length){
			 System.out.print(dataMatrix2[count]+ " ");
			 count++;
		 }
	 }// end method 5
	 
	 
	 //method 6
	 public String toString(){
		 String stringOutput = new String();
		 Matrix stringMat = fullMatrix();
		 for(int count = 0; count < mSize; count++){
			 for(int count2 = 0; count2 <mSize; count2++) {
				 stringOutput = stringOutput + stringMat.getElement(count,count2)+ " ";
			 }
			 stringOutput = stringOutput+ "\n";
		 }
		 return stringOutput;
	 }// end method 6
	 
	 
	 // method 7
	 public int getDet(){
		 Matrix determinantMat = fullMatrix();
		 int determinant = 1;
		 int count = 0; // only need one counter as determinant is product of diagonals
		 while(count < mSize){
			 determinant = determinant *determinantMat.getElement(count, count); 
			 count++;
		 }
		 return determinant;
	 }// end method 7
	 
	//method 8
		 public double[] effSolve(double[] b) throws IllegalArgumentException{
			 double[] methodSoln = new double[mSize];
			 double placeHolder;
			 int counter = 1;
			 int x = dataMatrix2.length-1; //x and x2 are variables used to hold numbers needed to obtain the soln
			 
			 if(b.length != mSize){
				 throw new IllegalArgumentException ("Invalid size"); //if they are not the same length, it returns this
			 }
			 else if (getDet()==0){
				 throw new IllegalArgumentException ("Determinant Error"); // if determinant is 0, it returns this
			 } //if the two above conditions are not met, then the following code runs to solve
			 else {

				 for(int count = mSize-1; count >= 0; count--){
					 placeHolder = 0.0;
					 for(int count2 = mSize-1; count2 > count ; count2--){
						 int x2 = (mSize * count) - ((count * (count+1))/2)  + count2 ; 
						 placeHolder = placeHolder + dataMatrix2[x2] * methodSoln[count2];
					 }
					 methodSoln[count] = (b[count]-placeHolder)/dataMatrix2[x];
					 x = x - counter;
					 counter++;
				 }
				 return methodSoln;
			 }
		 }// end method 8
		 
}// end class
